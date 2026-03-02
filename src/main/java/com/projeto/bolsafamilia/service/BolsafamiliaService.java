package com.projeto.bolsafamilia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.projeto.bolsafamilia.model.Bolsafamiliamodel;
import com.projeto.bolsafamilia.repository.BolsafamiliaRepository;

import jakarta.persistence.criteria.Predicate;

public class BolsafamiliaService {
    @Autowired
    private BolsafamiliaRepository repository;

    public Page<Bolsafamiliamodel> listarTodos(int pagina, int tamanho){
        return repository.findAll(PageRequest.of(pagina, tamanho));
    }
    
    public Page<Bolsafamiliamodel> Filtrar(Long id, String nome, String uf, String nomeMunicipio, String competencia, String nisFavorecido, BigDecimal valorMinimo, BigDecimal valorMaximo, int pagina, int tamanho){
        {
        Specification<Bolsafamiliamodel> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (nome != null && !nome.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("nomeFavorecido")), "%" + nome.toLowerCase() + "%"));
            }
            
            if (uf != null && !uf.isEmpty()) {
                predicates.add(cb.equal(cb.upper(root.get("uf")), uf.toUpperCase()));
            }
            
            if (nomeMunicipio != null && !nomeMunicipio.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("nomeMunicipio")), "%" + nomeMunicipio.toLowerCase() + "%"));
            }
            
            if (competencia != null && !competencia.isEmpty()) {
                predicates.add(cb.equal(root.get("competencia"), competencia));
            }
            
            if (nisFavorecido != null && !nisFavorecido.isEmpty()) {
                predicates.add(cb.equal(root.get("nisFavorecido"), nisFavorecido));
            }
            
            if (valorMinimo != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("valorParcela"), valorMinimo));
            }
            
            if (valorMaximo != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("valorParcela"), valorMaximo));
            }
            
            return cb.and(predicates.toArray(Predicate[]::new));
            
        };
        return repository.findAll(spec, PageRequest.of(pagina, tamanho));
     }
    }
}
