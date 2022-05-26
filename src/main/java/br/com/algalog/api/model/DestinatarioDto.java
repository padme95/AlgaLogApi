package br.com.algalog.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinatarioDto {

	private String nome;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	
}
