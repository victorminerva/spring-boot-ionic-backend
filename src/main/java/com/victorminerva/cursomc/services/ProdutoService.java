package com.victorminerva.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.victorminerva.cursomc.domain.Categoria;
import com.victorminerva.cursomc.domain.Produto;
import com.victorminerva.cursomc.repositories.CategoriaRepository;
import com.victorminerva.cursomc.repositories.ProdutoRepository;
import com.victorminerva.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;

	public Produto findById(Integer id) {
		Optional<Produto> produto = produtoRepo.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageReq = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		
		return produtoRepo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageReq);
	}
}
