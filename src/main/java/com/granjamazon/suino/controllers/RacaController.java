package com.granjamazon.suino.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.granjamazon.suino.dto.RacaDTO;
import com.granjamazon.suino.entities.Fornecedor;
import com.granjamazon.suino.entities.Raca;
import com.granjamazon.suino.repositories.FornecedorRepository;
import com.granjamazon.suino.repositories.RacaRepository;

import jakarta.validation.Valid;
import services.RacaService;

@Controller
@RequestMapping("/raca")
public class RacaController {
	
	@Autowired
	RacaRepository racaRepository;
	
	@Autowired
	FornecedorRepository fornecedorRepository;
	
	@Autowired
	RacaService racaService;
	
    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(racaService.getAll(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody RacaDTO raca){
        try{
            Fornecedor fornecedor = fornecedorRepository.findById(raca.getFornecedor())
                    .orElseThrow(() -> new EmptyResultDataAccessException("Fornecedor não encontrado!", 0));
            Raca newRaca= new Raca();
            newRaca.setNome(raca.getNome());
            newRaca.setNomeComercial(raca.getNomeComercial());
            newRaca.setIsEnable(raca.getIsEnable());
            newRaca.setFornecedor(fornecedor);
            return new ResponseEntity<>(racaRepository.save(newRaca), HttpStatus.CREATED);
        }catch(Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
