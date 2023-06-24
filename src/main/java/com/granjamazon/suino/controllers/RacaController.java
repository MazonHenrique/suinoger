package com.granjamazon.suino.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.granjamazon.suino.entities.Raca;
import com.granjamazon.suino.repositories.RacaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/raca")
public class RacaController {
	
	@Autowired
	RacaRepository racaRepository;
	
    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(racaRepository.findAll(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody Raca raca){
        try{
            return new ResponseEntity<>(racaRepository.save(raca), HttpStatus.CREATED);
        }catch(Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
