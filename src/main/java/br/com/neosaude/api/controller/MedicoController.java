package br.com.neosaude.api.controller;

import br.com.neosaude.api.domain.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DTOCadastroMedico dados, UriComponentsBuilder uriBuilder){
        Medico medico = new Medico(dados);
        medicoRepository.save(medico);
        URI uri = uriBuilder.path("/api/medico/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTODetalhamentoMedico(medico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTODetalhamentoMedico> detalharMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DTODetalhamentoMedico(medico));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<DTODetalhamentoMedico> atualizarMedico(@RequestBody @Valid DTOAtualizacaoMedico dados){
        Medico medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarDados(dados);

        return ResponseEntity.ok(new DTODetalhamentoMedico(medico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity excluirMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);

        return ResponseEntity.noContent().build();
    }
}
