package com.granjamazon.suino.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.granjamazon.suino.dto.RacaDTO;
import com.granjamazon.suino.entities.Fornecedor;
import com.granjamazon.suino.entities.Raca;
import com.granjamazon.suino.repositories.FornecedorRepository;
import com.granjamazon.suino.repositories.RacaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RacaService {
	
	private final RacaRepository racaRepository;
	private final FornecedorRepository fornecedorRepository;
	
	public List<RacaDTO> getAll() {
		List<Raca> raca = racaRepository.findAll();
        return raca.stream().map(
               item -> RacaDTO
                		.builder()
                		.id(item.getId())
                		.nome(item.getNome())
                		.nomeComercial(item.getNomeComercial())
                		.isEnable(item.getIsEnable())
                		.fornecedor(item.getFornecedor().getId())
                		.nomeForencedor(item.getFornecedor().getNome())
                		.build()
                ).collect(Collectors.toList());
	}
	
	public RacaDTO post(RacaDTO raca) {
	    Fornecedor fornecedor = fornecedorRepository.findById(raca.getFornecedor())
	            .orElseThrow(() -> new EmptyResultDataAccessException("Fornecedor não encontrado!", 0));
	    Raca newRaca= new Raca();
	    newRaca.setNome(raca.getNome());
	    newRaca.setNomeComercial(raca.getNomeComercial());
	    newRaca.setIsEnable(raca.getIsEnable());
	    newRaca.setFornecedor(fornecedor);
	    racaRepository.save(newRaca);
	    return new RacaDTO(newRaca.getId(), newRaca.getNome(), newRaca.getNomeComercial(), newRaca.getIsEnable(), 
	    				   newRaca.getFornecedor().getId(), newRaca.getFornecedor().getNome());
	}

	public RacaDTO put(RacaDTO raca) {
	    Fornecedor fornecedor = fornecedorRepository.findById(raca.getFornecedor())
	            .orElseThrow(() -> new EmptyResultDataAccessException("Fornecedor não encontrado!", 0));
	    Raca newRaca= new Raca();
	    newRaca.setId(raca.getId());
	    newRaca.setNome(raca.getNome());
	    newRaca.setNomeComercial(raca.getNomeComercial());
	    newRaca.setIsEnable(raca.getIsEnable());
	    newRaca.setFornecedor(fornecedor);
	    racaRepository.save(newRaca);
	    return new RacaDTO(newRaca.getId(), newRaca.getNome(), newRaca.getNomeComercial(), newRaca.getIsEnable(), 
	    				   newRaca.getFornecedor().getId(), newRaca.getFornecedor().getNome());
	}
}