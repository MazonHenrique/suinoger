package com.granjamazon.suino.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RacaDTO {
	private String nome;
	private String nomeComercial;
	private Boolean isEnable;
	private Long fornecedor;

}
