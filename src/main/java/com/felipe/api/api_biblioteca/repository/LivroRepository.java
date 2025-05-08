package com.felipe.api.api_biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.api.api_biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}