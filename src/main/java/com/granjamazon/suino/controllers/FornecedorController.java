package com.granjamazon.suino.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return new ResponseEntity<>(fornecedorRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody Fornecedor fornecedor){
        try{
            //fornecedor.setEnable(true);
            //fornecedor.setGenetic(true);
            return new ResponseEntity<>(fornecedorRepository.save(fornecedor), HttpStatus.CREATED);
        }catch(Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
