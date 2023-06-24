package com.granjamazon.suino.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_raca")
public class Raca {
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(length = 100, nullable = false)
	@NotBlank(message = "Campo nome é obrigatório")
    private String nome;
    
    @Column(length = 100, nullable = false)
	private String nomeComercial;
    
    private Boolean isEnable;
    
    @ManyToOne
    @JoinColumn(name="fornecedor_id")
    private Fornecedor fornecedor;
    
}
