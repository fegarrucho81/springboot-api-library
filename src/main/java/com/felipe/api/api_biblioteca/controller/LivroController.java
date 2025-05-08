package com.felipe.api.api_biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.api.api_biblioteca.model.Livro;
import com.felipe.api.api_biblioteca.repository.LivroRepository;

@RestController
@RequestMapping("/livros")
public class LivroController {
    
    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    @PostMapping
    public Livro adicionarLivro(@RequestBody Livro livro) {
        return livroRepository.save(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerLivro(@PathVariable Long id) {
        livroRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody Livro dadosAtualizados) {
        Optional<Livro> livroOpt = livroRepository.findById(id);

        if (livroOpt.isEmpty())
            return ResponseEntity.notFound().build();

        Livro livro = livroOpt.get();
        livro.setNomeLivro(dadosAtualizados.getNomeLivro());
        livro.setAutor(dadosAtualizados.getAutor());
        livro.setGenero(dadosAtualizados.getGenero());

        livroRepository.save(livro);

        return ResponseEntity.ok(livro);
    }
}