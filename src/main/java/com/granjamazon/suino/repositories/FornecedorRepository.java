package com.granjamazon.suino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.granjamazon.suino.entities.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{
    
}
