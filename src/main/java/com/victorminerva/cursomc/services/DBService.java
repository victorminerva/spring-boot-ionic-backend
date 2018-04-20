package com.victorminerva.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.victorminerva.cursomc.domain.Categoria;
import com.victorminerva.cursomc.domain.Cidade;
import com.victorminerva.cursomc.domain.Cliente;
import com.victorminerva.cursomc.domain.Endereco;
import com.victorminerva.cursomc.domain.Estado;
import com.victorminerva.cursomc.domain.ItemPedido;
import com.victorminerva.cursomc.domain.Pagamento;
import com.victorminerva.cursomc.domain.PagamentoComBoleto;
import com.victorminerva.cursomc.domain.PagamentoComCartao;
import com.victorminerva.cursomc.domain.Pedido;
import com.victorminerva.cursomc.domain.Produto;
import com.victorminerva.cursomc.domain.enums.EstadoPagamento;
import com.victorminerva.cursomc.domain.enums.Perfil;
import com.victorminerva.cursomc.domain.enums.TipoCliente;
import com.victorminerva.cursomc.repositories.CategoriaRepository;
import com.victorminerva.cursomc.repositories.CidadeRepository;
import com.victorminerva.cursomc.repositories.ClienteRepository;
import com.victorminerva.cursomc.repositories.EnderecoRepository;
import com.victorminerva.cursomc.repositories.EstadoRepository;
import com.victorminerva.cursomc.repositories.ItemPedidoRepository;
import com.victorminerva.cursomc.repositories.PagamentoRepository;
import com.victorminerva.cursomc.repositories.PedidoRepository;
import com.victorminerva.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepo;

	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private EstadoRepository estadoRepo;

	@Autowired
	private CidadeRepository cidadeRepo;

	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private EnderecoRepository enderecoRepo;

	@Autowired
	private PedidoRepository pedidoRepo;

	@Autowired
	private PagamentoRepository pagamentoRepo;

	@Autowired
	private ItemPedidoRepository itemPedidoRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instantiateTestDatabase() throws ParseException {

		Categoria categ1 = new Categoria(null, "Informática");
		Categoria categ2 = new Categoria(null, "Escritório");
		Categoria categ3 = new Categoria(null, "Cama, Mesa e Banho");
		Categoria categ4 = new Categoria(null, "Eletrônicos");
		Categoria categ5 = new Categoria(null, "Jardinagem");
		Categoria categ6 = new Categoria(null, "Decoração");
		Categoria categ7 = new Categoria(null, "Perfumaria");

		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		Produto prod4 = new Produto(null, "Mesa de escritório", 80.00);
		Produto prod5 = new Produto(null, "Toalha", 80.00);
		Produto prod6 = new Produto(null, "Colcha", 80.00);
		Produto prod7 = new Produto(null, "TV true color", 80.00);
		Produto prod8 = new Produto(null, "Roçadeira", 80.00);
		Produto prod9 = new Produto(null, "Abajour", 80.00);
		Produto prod10 = new Produto(null, "Pendente", 80.00);
		Produto prod11 = new Produto(null, "Shampoo", 80.00);
		Produto prod12 = new Produto(null, "Produto 12", 10.00);
		Produto prod13 = new Produto(null, "Produto 13", 10.00);
		Produto prod14 = new Produto(null, "Produto 14", 10.00);
		Produto prod15 = new Produto(null, "Produto 15", 10.00);
		Produto prod16 = new Produto(null, "Produto 16", 10.00);
		Produto prod17 = new Produto(null, "Produto 17", 10.00);
		Produto prod18 = new Produto(null, "Produto 18", 10.00);
		Produto prod19 = new Produto(null, "Produto 19", 10.00);
		Produto prod20 = new Produto(null, "Produto 20", 10.00);
		Produto prod21 = new Produto(null, "Produto 21", 10.00);
		Produto prod22 = new Produto(null, "Produto 22", 10.00);
		Produto prod23 = new Produto(null, "Produto 23", 10.00);
		Produto prod24 = new Produto(null, "Produto 24", 10.00);
		Produto prod25 = new Produto(null, "Produto 25", 10.00);
		Produto prod26 = new Produto(null, "Produto 26", 10.00);
		Produto prod27 = new Produto(null, "Produto 27", 10.00);
		Produto prod28 = new Produto(null, "Produto 28", 10.00);
		Produto prod29 = new Produto(null, "Produto 29", 10.00);
		Produto prod30 = new Produto(null, "Produto 30", 10.00);
		Produto prod31 = new Produto(null, "Produto 31", 10.00);
		Produto prod32 = new Produto(null, "Produto 32", 10.00);
		Produto prod33 = new Produto(null, "Produto 33", 10.00);
		Produto prod34 = new Produto(null, "Produto 34", 10.00);
		Produto prod35 = new Produto(null, "Produto 35", 10.00);
		Produto prod36 = new Produto(null, "Produto 36", 10.00);
		Produto prod37 = new Produto(null, "Produto 37", 10.00);
		Produto prod38 = new Produto(null, "Produto 38", 10.00);
		Produto prod39 = new Produto(null, "Produto 39", 10.00);
		Produto prod40 = new Produto(null, "Produto 40", 10.00);
		Produto prod41 = new Produto(null, "Produto 41", 10.00);
		Produto prod42 = new Produto(null, "Produto 42", 10.00);
		Produto prod43 = new Produto(null, "Produto 43", 10.00);
		Produto prod44 = new Produto(null, "Produto 44", 10.00);
		Produto prod45 = new Produto(null, "Produto 45", 10.00);
		Produto prod46 = new Produto(null, "Produto 46", 10.00);
		Produto prod47 = new Produto(null, "Produto 47", 10.00);
		Produto prod48 = new Produto(null, "Produto 48", 10.00);
		Produto prod49 = new Produto(null, "Produto 49", 10.00);
		Produto prod50 = new Produto(null, "Produto 50", 10.00);

		categ1.getProdutos()
				.addAll(Arrays.asList(prod12, prod13, prod14, prod15, prod16, prod17, prod18, prod19, prod20, prod21,
						prod22, prod23, prod24, prod25, prod26, prod27, prod28, prod29, prod30, prod31, prod32, prod34,
						prod35, prod36, prod37, prod38, prod39, prod40, prod41, prod42, prod43, prod44, prod45, prod46,
						prod47, prod48, prod49, prod50));

		prod12.getCategorias().add(categ1);
		prod13.getCategorias().add(categ1);
		prod14.getCategorias().add(categ1);
		prod15.getCategorias().add(categ1);
		prod16.getCategorias().add(categ1);
		prod17.getCategorias().add(categ1);
		prod18.getCategorias().add(categ1);
		prod19.getCategorias().add(categ1);
		prod20.getCategorias().add(categ1);
		prod21.getCategorias().add(categ1);
		prod22.getCategorias().add(categ1);
		prod23.getCategorias().add(categ1);
		prod24.getCategorias().add(categ1);
		prod25.getCategorias().add(categ1);
		prod26.getCategorias().add(categ1);
		prod27.getCategorias().add(categ1);
		prod28.getCategorias().add(categ1);
		prod29.getCategorias().add(categ1);
		prod30.getCategorias().add(categ1);
		prod31.getCategorias().add(categ1);
		prod32.getCategorias().add(categ1);
		prod33.getCategorias().add(categ1);
		prod34.getCategorias().add(categ1);
		prod35.getCategorias().add(categ1);
		prod36.getCategorias().add(categ1);
		prod37.getCategorias().add(categ1);
		prod38.getCategorias().add(categ1);
		prod39.getCategorias().add(categ1);
		prod40.getCategorias().add(categ1);
		prod41.getCategorias().add(categ1);
		prod42.getCategorias().add(categ1);
		prod43.getCategorias().add(categ1);
		prod44.getCategorias().add(categ1);
		prod45.getCategorias().add(categ1);
		prod46.getCategorias().add(categ1);
		prod47.getCategorias().add(categ1);
		prod48.getCategorias().add(categ1);
		prod49.getCategorias().add(categ1);
		prod50.getCategorias().add(categ1);

		categ1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		categ2.getProdutos().addAll(Arrays.asList(prod2, prod4));
		categ3.getProdutos().addAll(Arrays.asList(prod5, prod6));
		categ4.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
		categ5.getProdutos().addAll(Arrays.asList(prod8));
		categ6.getProdutos().addAll(Arrays.asList(prod9, prod10));
		categ7.getProdutos().addAll(Arrays.asList(prod11));

		prod1.getCategorias().addAll(Arrays.asList(categ1, categ4));
		prod2.getCategorias().addAll(Arrays.asList(categ1, categ2, categ4));
		prod3.getCategorias().addAll(Arrays.asList(categ1, categ4));
		prod4.getCategorias().addAll(Arrays.asList(categ2));
		prod5.getCategorias().addAll(Arrays.asList(categ3));
		prod6.getCategorias().addAll(Arrays.asList(categ3));
		prod7.getCategorias().addAll(Arrays.asList(categ4));
		prod8.getCategorias().addAll(Arrays.asList(categ5));
		prod9.getCategorias().addAll(Arrays.asList(categ6));
		prod10.getCategorias().addAll(Arrays.asList(categ6));
		prod11.getCategorias().addAll(Arrays.asList(categ7));

		categoriaRepo.saveAll(Arrays.asList(categ1, categ2, categ3, categ4, categ5, categ6, categ7));
		produtoRepo
				.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11));

		produtoRepo.saveAll(Arrays.asList(prod12, prod13, prod14, prod15, prod16, prod17, prod18, prod19, prod20,
				prod21, prod22, prod23, prod24, prod25, prod26, prod27, prod28, prod29, prod30, prod31, prod32, prod34,
				prod35, prod36, prod37, prod38, prod39, prod40, prod41, prod42, prod43, prod44, prod45, prod46, prod47,
				prod48, prod49, prod50));

		/**/
		Estado est1 = new Estado(null, "Ceará");
		Estado est2 = new Estado(null, "Pernambuco");

		Cidade cid1 = new Cidade(null, "Fortaleza", est1);
		Cidade cid2 = new Cidade(null, "Caucaia", est1);
		Cidade cid3 = new Cidade(null, "Recife", est2);

		est1.getCidades().addAll(Arrays.asList(cid1, cid2));
		est2.getCidades().addAll(Arrays.asList(cid3));

		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "victorminerva.m@gmail.com", "36378912377",
				TipoCliente.PESSOAFISICA, encoder.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Cliente cli2 = new Cliente(null, "Ana Costa", "victor.minerva@portfoliogc.com", "99385348051",
				TipoCliente.PESSOAFISICA, encoder.encode("123"));
		cli2.addPerfil(Perfil.ADMIN);
		cli2.getTelefones().addAll(Arrays.asList("27364444", "93833333"));

		Endereco end1 = new Endereco(null, "Rua flores", "300", "Apto 303", "Jardim", "60850200", cli1, cid1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "60870400", cli1, cid2);
		Endereco end3 = new Endereco(null, "Floriano", "2106", "Sala 800", "Centro", "60870100", cli2, cid2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		cli1.getEnderecos().addAll(Arrays.asList(end3));

		clienteRepo.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepo.saveAll(Arrays.asList(end1, end2, end3));

		/**/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2018 10:32)"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2018 19:35)"), cli1, end2);

		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);

		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2018 00:00"),
				null);
		ped2.setPagamento(pgto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pgto1, pgto2));

		/**/
		ItemPedido item1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);
		ItemPedido item2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.00);
		ItemPedido item3 = new ItemPedido(ped2, prod2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(item1, item2));
		ped2.getItens().addAll(Arrays.asList(item3));

		prod1.getItens().addAll(Arrays.asList(item1));
		prod2.getItens().addAll(Arrays.asList(item3));
		prod3.getItens().addAll(Arrays.asList(item2));

		itemPedidoRepo.saveAll(Arrays.asList(item1, item2, item3));

	}

}
