package com.projeto.bolsafamilia.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.projeto.bolsafamilia.model.Bolsafamiliamodel;


@Repository
public interface BolsafamiliaRepository extends JpaRepository<Bolsafamiliamodel, Long>, JpaSpecificationExecutor<Bolsafamiliamodel> {
    
    // O Spring traduz isso para o SQL que usa seu índice de nome automaticamente!
    Page<Bolsafamiliamodel> findByNomeFavorecidoContainingIgnoreCase(String nome, Pageable pageable);
    
    // Busca por município
    Page<Bolsafamiliamodel> findByNomeMunicipioIgnoreCase(String municipio, Pageable pageable);

    Page<Bolsafamiliamodel> findByUfIgnoreCase(String uf, Pageable pageable);
}
 