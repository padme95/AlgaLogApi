package br.com.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.algalog.api.mapper.EntregaMapper;
import br.com.algalog.api.model.EntregaDto;
import br.com.algalog.api.model.input.EntregaInput;
import br.com.algalog.domain.model.Entrega;
import br.com.algalog.domain.repository.EntregaRepository;
import br.com.algalog.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	@Autowired
	private SolicitacaoEntregaService solicitacaoEntregaService;

	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private EntregaMapper entregaMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDto solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = entregaMapper.toEntity(entregaInput);
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
		
		return entregaMapper.toDto(entregaSolicitada);
	}

	@GetMapping
	public List<EntregaDto> listar() {
		return entregaMapper.toCollectioModel(entregaRepository.findAll());

	}

	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaDto> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
			.map(entrega -> ResponseEntity.ok(entregaMapper.toDto(entrega)))
			.orElse(ResponseEntity.notFound().build());
	}
}
