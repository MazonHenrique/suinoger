package com.granjamazon.suino.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FornecedorRespostaListaDTO {
    private Long id;
    private String nome;
    private String endereco;
    private String cidade;
    private String estado;  
}
