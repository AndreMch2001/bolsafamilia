//serve para controlar o fluxo da minha API, os pedido que vem das URLs serão tratados aqui, e a lógica de negócio fica na camada de serviço,
// e a camada de acesso a dados fica na camada de repositório
package com.projeto.bolsafamilia.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.bolsafamilia.model.Bolsafamiliamodel;
import com.projeto.bolsafamilia.repository.BolsafamiliaRepository;
import com.projeto.bolsafamilia.service.BolsafamiliaService;

import jakarta.persistence.criteria.Predicate;


@RestController
@RequestMapping("/api/Bolsafamiliamodel")
@CrossOrigin(origins = "*")
public class Bolsafamiliacontroller {

    @Autowired
    private BolsafamiliaRepository repository;
    @Autowired
    private BolsafamiliaService service;
    //busca os dados do banco de dados, e retorna uma página de resultados, com base nos parâmetros de busca e paginação fornecidos na URL
    //
    @GetMapping("/busca")
    public 
    
    //encontra todos os dados do banco de dados, e retorna uma página de resultados, com base nos parâmetros de paginação fornecidos na URL
    @GetMapping("/todos")
    public Page<Bolsafamiliamodel> todosRegistros(
        @RequestParam(defaultValue = "0") int pagina,
        @RequestParam(defaultValue = "20") int tamanho) {

    // Criamos a paginação explicitando que queremos ordenar pelo campo "id" de forma ASCENDENTE (do menor para o maior)
    Pageable paginacao = PageRequest.of(pagina, tamanho, Sort.by("id").ascending());

    return repository.findAll(paginacao);
    }
}
