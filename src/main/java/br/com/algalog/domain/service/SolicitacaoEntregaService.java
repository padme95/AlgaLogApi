package br.com.algalog.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.algalog.domain.model.Cliente;
import br.com.algalog.domain.model.Entrega;
import br.com.algalog.domain.model.StatusEntrega;
import br.com.algalog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoEntregaService {

	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private CatalogoClienteService catatologoClienteService;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = catatologoClienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRepository.save(entrega);
	}
}
