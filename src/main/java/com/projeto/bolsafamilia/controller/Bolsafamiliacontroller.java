package com.projeto.bolsafamilia.controller;

import com.projeto.bolsafamilia.model.Bolsafamiliamodel;
import com.projeto.bolsafamilia.service.BolsafamiliaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/bolsa-familia") // Dica: use kebab-case em URLs
@CrossOrigin(origins = "*")
public class Bolsafamiliacontroller {

    @Autowired
    private BolsafamiliaService service;

    @GetMapping("/busca")
    public Page<Bolsafamiliamodel> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String uf,
            @RequestParam(required = false) String nomeMunicipio,
            @RequestParam(required = false) String competencia,
            @RequestParam(required = false) String nisFavorecido,
            @RequestParam(required = false) BigDecimal valorMinimo,
            @RequestParam(required = false) BigDecimal valorMaximo,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanho) {
        
        return service.buscarComFiltros(nome, uf, nomeMunicipio, competencia, 
                                        nisFavorecido, valorMinimo, valorMaximo, 
                                        pagina, tamanho);
    }
}