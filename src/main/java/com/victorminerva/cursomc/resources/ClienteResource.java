package com.victorminerva.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorminerva.cursomc.domain.Cliente;
import com.victorminerva.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		Cliente categoria = clienteService.findById(id);
		
		return ResponseEntity.ok().body(categoria);	
	}
}
