package com.victorminerva.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.victorminerva.cursomc.domain.Categoria;
import com.victorminerva.cursomc.domain.Produto;
import com.victorminerva.cursomc.repositories.CategoriaRepository;
import com.victorminerva.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categ1 = new Categoria(null, "Informática");
		Categoria categ2 = new Categoria(null, "Escritório");

		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);

		categ1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		categ1.getProdutos().addAll(Arrays.asList(prod2));

		prod1.getCategorias().addAll(Arrays.asList(categ1));
		prod2.getCategorias().addAll(Arrays.asList(categ1, categ2));
		prod3.getCategorias().addAll(Arrays.asList(categ1));
		
		categoriaRepo.saveAll(Arrays.asList(categ1, categ2));
		produtoRepo.saveAll(Arrays.asList(prod1, prod2, prod3));
	}
}
