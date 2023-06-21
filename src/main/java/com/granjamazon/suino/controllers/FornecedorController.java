package com.granjamazon.suino.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.granjamazon.suino.dto.FornecedorRespostaListaDTO;
import com.granjamazon.suino.entities.Fornecedor;
import com.granjamazon.suino.repositories.FornecedorRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {
    
    @Autowired
    FornecedorRepository fornecedorRepository;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(montarDTO(), HttpStatus.OK);
    }

    public List<FornecedorRespostaListaDTO> montarDTO(){
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        return fornecedores.stream().map(
                item -> FornecedorRespostaListaDTO.builder()
                    .id(item.getId())
                    .nome(item.getNome())
                    .endereco(item.getEndereco())
                    .cidade(item.getCidade())
                    .estado(item.getEstado())
                    .build()
        ).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody Fornecedor fornecedor){
        try{
            return new ResponseEntity<>(fornecedorRepository.save(fornecedor), HttpStatus.CREATED);
        }catch(Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
