package com.victorminerva.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorminerva.cursomc.domain.Pedido;
import com.victorminerva.cursomc.repositories.PedidoRepository;
import com.victorminerva.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepo;

	public Pedido findById(Integer id) {
		Optional<Pedido> pedido = pedidoRepo.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
