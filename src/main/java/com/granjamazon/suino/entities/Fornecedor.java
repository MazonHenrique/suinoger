package com.granjamazon.suino.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(length = 100, nullable = false)
	@NotBlank(message = "Campo nome é obrigatório")
    private String nome;
    
    private String telefone;

    @Column(length = 150, nullable = false)
	@NotBlank(message = "Campo endereço é obrigatório")
    private String endereco;

    @Column(length = 10)
    private String numero;

    @Column(length = 30)
    private String complemento;

    @Column(length = 15)
    private String cep;

    @Column(length = 50, nullable = false)
	@NotBlank(message = "Campo cidade é obrigatório")
    private String cidade;
 
    @Column(length = 2, nullable = false)
	@NotBlank(message = "Campo estado é obrigatório")
    private String estado;

    private Boolean isEnable;
    private Boolean isGenetic;
    
}
