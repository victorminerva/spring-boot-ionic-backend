package com.victorminerva.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorminerva.cursomc.domain.Cidade;
import com.victorminerva.cursomc.domain.Estado;
import com.victorminerva.cursomc.dto.CidadeDTO;
import com.victorminerva.cursomc.dto.EstadoDTO;
import com.victorminerva.cursomc.services.CidadeService;
import com.victorminerva.cursomc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;

	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAllByOrderByNome() {
		List<Estado> estados = estadoService.findAll();
		
		List<EstadoDTO> estadosDto = estados.stream().map(e -> new EstadoDTO(e)).collect(Collectors.toList());
		return ResponseEntity.ok().body(estadosDto);
	}
	
	@GetMapping(value = "/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		List<Cidade> cidades = cidadeService.findByEstado(estadoId);
		
		List<CidadeDTO> cidadesDTO = cidades.stream().map(c -> new CidadeDTO(c)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cidadesDTO);
	}
}
