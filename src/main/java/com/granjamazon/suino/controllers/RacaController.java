package com.granjamazon.suino.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.granjamazon.suino.dto.RacaDTO;
import com.granjamazon.suino.entities.Fornecedor;
import com.granjamazon.suino.services.RacaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/raca")
public class RacaController {
	
	@Autowired
	RacaService racaService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(racaService.getAll(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody RacaDTO raca){
        try{
            return new ResponseEntity<>(racaService.post(raca), HttpStatus.CREATED);
        }catch(Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping
    public ResponseEntity<?> put(@Valid @RequestBody RacaDTO raca){
        try{
            return new ResponseEntity<>(racaService.put(raca), HttpStatus.OK);
        }catch(Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
