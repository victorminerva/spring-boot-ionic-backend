package com.victorminerva.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.victorminerva.cursomc.domain.Cliente;
import com.victorminerva.cursomc.dto.ClienteDTO;
import com.victorminerva.cursomc.repositories.ClienteRepository;
import com.victorminerva.cursomc.services.exception.DataIntegrityException;
import com.victorminerva.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;

	public Cliente findById(Integer id) {
		Optional<Cliente> categ = clienteRepo.findById(id);
		return categ.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
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
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas.");
		}
	}

	public List<Cliente> findAll() {
		return clienteRepo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageReq = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return clienteRepo.findAll(pageReq);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}
	
	private void updateDate(Cliente clienteExisting, Cliente cliente) {
		clienteExisting.setNome(cliente.getNome());
		clienteExisting.setEmail(cliente.getEmail());
	}
}
