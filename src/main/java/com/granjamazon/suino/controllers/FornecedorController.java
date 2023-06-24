package com.granjamazon.suino.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.granjamazon.suino.dto.FornecedorRespostaListaDTO;
import com.granjamazon.suino.entities.Fornecedor;
import com.granjamazon.suino.repositories.FornecedorRepository;

import jakarta.validation.Valid;
import lombok.var;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {
    
    @Autowired
    FornecedorRepository fornecedorRepository;
    
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        return new ResponseEntity<>(fornecedores.stream().map(
                item -> FornecedorRespostaListaDTO.builder()
                    .id(item.getId())
                    .nome(item.getNome())
                    .endereco(item.getEndereco())
                    .cidade(item.getCidade())
                    .estado(item.getEstado())
                    .isGenetic(item.getIsGenetic())
                    .build()
        ).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable long id){
        try {
            Fornecedor fornecedor = fornecedorRepository.findById(id).get();
            return new ResponseEntity<>(new FornecedorRespostaListaDTO(fornecedor.getId(), 
                                            fornecedor.getNome(), fornecedor.getEndereco(), fornecedor.getCidade(),
                                            fornecedor.getEstado(), fornecedor.getIsGenetic()), HttpStatus.OK);
        }catch(Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }    
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody Fornecedor fornecedor){
        try{
            return new ResponseEntity<>(fornecedorRepository.save(fornecedor), HttpStatus.CREATED);
        }catch(Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping
    public ResponseEntity<?> put(@Valid @RequestBody Fornecedor fornecedor){
        try{
            return new ResponseEntity<>(fornecedorRepository.save(fornecedor), HttpStatus.OK);
        }catch(Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> delet(@PathVariable long id){
        try{
        	fornecedorRepository.deleteById(id);
            return new ResponseEntity<>("Fornecedor removido com sucesso", HttpStatus.OK);
        }catch(Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    
}