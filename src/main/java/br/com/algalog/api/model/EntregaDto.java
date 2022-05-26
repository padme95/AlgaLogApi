package br.com.algalog.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.modelmapper.ModelMapper;

import br.com.algalog.domain.model.Entrega;
import br.com.algalog.domain.model.StatusEntrega;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntregaDto {

	private Long id;
	private ClienteResumoModel cliente;
	private DestinatarioDto destinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
	
	public Entrega toEntrega() {
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, Entrega.class);
	}
}
