package com.victorminerva.cursomc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.victorminerva.cursomc.domain.Cidade;
import com.victorminerva.cursomc.domain.Cliente;
import com.victorminerva.cursomc.domain.Endereco;
import com.victorminerva.cursomc.domain.enums.Perfil;
import com.victorminerva.cursomc.domain.enums.TipoCliente;
import com.victorminerva.cursomc.dto.ClienteDTO;
import com.victorminerva.cursomc.dto.ClienteNewDTO;
import com.victorminerva.cursomc.repositories.ClienteRepository;
import com.victorminerva.cursomc.repositories.EnderecoRepository;
import com.victorminerva.cursomc.security.UserSS;
import com.victorminerva.cursomc.services.exception.AuthorizationException;
import com.victorminerva.cursomc.services.exception.DataIntegrityException;
import com.victorminerva.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	public Cliente findById(Integer id) {
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado.");
		}
		
		Optional<Cliente> cliente = clienteRepo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepo.save(cliente);
		
		enderecoRepo.saveAll(cliente.getEnderecos());
		
		return cliente;
	}
	
	public Cliente update(Cliente cliente) {
		Cliente clienteExist = findById(cliente.getId());
		
		updateDate(clienteExist, cliente);
		
		return clienteRepo.save(clienteExist);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			clienteRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionadas.");
		}
	}

	public List<Cliente> findAll() {
		return clienteRepo.findAll();
	}
	
	public Cliente findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado.");
		}

		Cliente cliente = clienteRepo.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException(
					"Cliente não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
		}
		return cliente;
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageReq = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return clienteRepo.findAll(pageReq);
	}
	
	/**/
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO clienteDTO) {
		Cliente cliente = new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(clienteDTO.getTipo()), encoder.encode(clienteDTO.getSenha()));

		Cidade cidade = new Cidade(clienteDTO.getCidadeId(), null, null);
		
		Endereco endereco = new Endereco(null, clienteDTO.getLogradouro(), clienteDTO.getNumero(),
							clienteDTO.getComplemento(), clienteDTO.getBairro(), clienteDTO.getCep(), cliente,
							cidade);

		
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(clienteDTO.getTelefone());
		
		if(clienteDTO.getTelefone1() != null) { 
			cliente.getTelefones().add(clienteDTO.getTelefone1());
		}
		if(clienteDTO.getTelefone2() != null) { 
			cliente.getTelefones().add(clienteDTO.getTelefone2());
		}
		
		return cliente;
	}
	
	private void updateDate(Cliente clienteExisting, Cliente cliente) {
		clienteExisting.setNome(cliente.getNome());
		clienteExisting.setEmail(cliente.getEmail());
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado.");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
}
