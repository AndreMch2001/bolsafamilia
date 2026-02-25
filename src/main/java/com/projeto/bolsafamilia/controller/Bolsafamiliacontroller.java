package com.projeto.bolsafamilia.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.projeto.bolsafamilia.model.Bolsafamiliamodel;
import com.projeto.bolsafamilia.repository.BolsafamiliaRepository;

@RestController
@RequestMapping("/api/Bolsafamiliamodel") // Endpoint base para acessar os dados do Bolsa Família
@CrossOrigin(origins = "*") // Permite que o Flutter acesse a API sem bloqueio
public class Bolsafamiliacontroller {

    @Autowired
    private BolsafamiliaRepository repository;

    @GetMapping("/busca")
    public Page<Bolsafamiliamodel> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanho) {
        
        // Se o usuário passar um nome, busca por nome. Se não, lista tudo (com paginação!)
        if (nome != null && !nome.isEmpty()) {
            return repository.findByNomeFavorecidoContainingIgnoreCase(nome, PageRequest.of(pagina, tamanho));
        }
        return repository.findAll(PageRequest.of(pagina, tamanho));
    }
}