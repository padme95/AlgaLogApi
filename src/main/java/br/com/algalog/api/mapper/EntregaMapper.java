package br.com.algalog.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.algalog.api.model.EntregaDto;
import br.com.algalog.api.model.input.EntregaInput;
import br.com.algalog.domain.model.Entrega;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EntregaMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EntregaDto toDto(Entrega entrega) {
		return modelMapper.map(entrega, EntregaDto.class);
	}
	
	public List<EntregaDto> toCollectioModel(List<Entrega> entregas){
		return entregas.stream()
		.map(this::toDto)
		.collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
}
