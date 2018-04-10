package com.victorminerva.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.victorminerva.cursomc.domain.Categoria;
import com.victorminerva.cursomc.dto.CategoriaDTO;
import com.victorminerva.cursomc.repositories.CategoriaRepository;
import com.victorminerva.cursomc.services.exception.DataIntegrityException;
import com.victorminerva.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepo;

	public Categoria findById(Integer id) {
		Optional<Categoria> categ = categoriaRepo.findById(id);
		return categ.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepo.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		Categoria categoriaExisting = findById(categoria.getId());
		
		updateDate(categoriaExisting, categoria);
		
		return categoriaRepo.save(categoria);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			categoriaRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
		}
	}

	public List<Categoria> findAll() {
		return categoriaRepo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageReq = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return categoriaRepo.findAll(pageReq);
	}
	
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
	
	private void updateDate(Categoria categoriaExisting, Categoria categoria) {
		categoriaExisting.setNome(categoria.getNome());
	}
}
