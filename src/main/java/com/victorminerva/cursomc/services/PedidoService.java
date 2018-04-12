package com.victorminerva.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victorminerva.cursomc.domain.ItemPedido;
import com.victorminerva.cursomc.domain.PagamentoComBoleto;
import com.victorminerva.cursomc.domain.Pedido;
import com.victorminerva.cursomc.domain.enums.EstadoPagamento;
import com.victorminerva.cursomc.repositories.ItemPedidoRepository;
import com.victorminerva.cursomc.repositories.PagamentoRepository;
import com.victorminerva.cursomc.repositories.PedidoRepository;
import com.victorminerva.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepo;

	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private BoletoService boletoService;
	
	public Pedido findById(Integer id) {
		Optional<Pedido> pedido = pedidoRepo.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgtoBoleto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgtoBoleto, pedido.getInstante());
		}
		pedido = pedidoRepo.save(pedido);
		pagamentoRepo.save(pedido.getPagamento());
		
		for (ItemPedido itemPedido : pedido.getItens()) {
			itemPedido.setDesconto(0.0);
			itemPedido.setPreco(produtoService.findById(itemPedido.getProduto().getId()).getPreco());
			itemPedido.setPedido(pedido);
		}
		itemPedidoRepo.saveAll(pedido.getItens());
		
		return pedido;
	}
}
