package com.victorminerva.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorminerva.cursomc.domain.Categoria;
import com.victorminerva.cursomc.repositories.CategoriaRepository;
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
}
