package br.com.neosaude.api.controller;

import br.com.neosaude.api.domain.alergia.Alergia;
import br.com.neosaude.api.domain.alergia.AlergiaRepository;
import br.com.neosaude.api.domain.alergia.DTODetalhamentoAlergia;
import br.com.neosaude.api.domain.alergia.DTOCadastroAlergia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/alergia")
public class AlergiaController {

    @Autowired
    private AlergiaRepository alergiaRepository;

    @PostMapping
    public ResponseEntity cadastrarAlergia(@RequestBody DTOCadastroAlergia dados, UriComponentsBuilder uriBuilder){
        Alergia alergia = new Alergia(dados);
        alergiaRepository.save(alergia);
        URI uri = uriBuilder.path("/api/alergia/{id}").buildAndExpand(alergia.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTODetalhamentoAlergia(alergia));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTODetalhamentoAlergia> detalharAlergia(@PathVariable Long id){
        Alergia alergia = alergiaRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DTODetalhamentoAlergia(alergia));
    }
}
