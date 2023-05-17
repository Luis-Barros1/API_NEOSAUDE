package br.com.neosaude.api.controller;

import br.com.neosaude.api.domain.paciente.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody DTOCadastroPaciente dados, UriComponentsBuilder uriBuilder){
        Paciente paciente = new Paciente(dados);
        pacienteRepository.save(paciente);
        URI uri = uriBuilder.path("/api/paciente/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTODetalhamentoPaciente(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTODetalhamentoPaciente> detalharPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DTODetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        pacienteRepository.delete(paciente);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<DTODetalhamentoPaciente> atualizarPaciente(@RequestBody DTOAtualizacaoPaciente dados){
        Paciente paciente = pacienteRepository.getReferenceById(dados.id());
        pacienteRepository.save(paciente);
        return ResponseEntity.ok(new DTODetalhamentoPaciente(paciente));
    }
}
