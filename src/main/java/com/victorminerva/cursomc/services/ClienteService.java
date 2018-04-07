package com.victorminerva.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorminerva.cursomc.domain.Cliente;
import com.victorminerva.cursomc.repositories.ClienteRepository;
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
}
