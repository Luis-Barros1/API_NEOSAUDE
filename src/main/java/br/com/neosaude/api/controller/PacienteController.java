package br.com.neosaude.api.controller;

import br.com.neosaude.api.domain.paciente.*;
import br.com.neosaude.api.domain.paciente.dto.DTOAtualizacaoPaciente;
import br.com.neosaude.api.domain.paciente.dto.DTOCadastroPaciente;
import br.com.neosaude.api.domain.paciente.dto.DTODetalhamentoPaciente;
import br.com.neosaude.api.domain.paciente.Paciente;
import br.com.neosaude.api.domain.paciente.service.AdicionarPacienteService;
import br.com.neosaude.api.domain.paciente.service.ObterPacientePorCpfService;
import br.com.neosaude.api.domain.usuario.Usuario;
import br.com.neosaude.api.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/paciente")
@SecurityRequirement(name = "bearer-key")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AdicionarPacienteService adicionarPacienteService;


    @Autowired
    private ObterPacientePorCpfService obterPacientePorCpfService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody DTOCadastroPaciente dados, UriComponentsBuilder uriBuilder){
        Paciente paciente = adicionarPacienteService.cadastrar(dados);
        URI uri = uriBuilder.path("/api/paciente/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTODetalhamentoPaciente(paciente));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<DTODetalhamentoPaciente> obterPorCpf(@PathVariable String cpf){
        Paciente paciente = obterPacientePorCpfService.obter(cpf);
        return ResponseEntity.ok(new DTODetalhamentoPaciente(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTODetalhamentoPaciente> detalharPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DTODetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarPaciente(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        Usuario usuario = paciente.getUsuarioPaciente();
        pacienteRepository.delete(paciente);
        usuarioRepository.delete(usuario);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping
    public ResponseEntity<DTODetalhamentoPaciente> atualizarPaciente(@RequestBody DTOAtualizacaoPaciente dados){
        Paciente paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarDados(dados);

        return ResponseEntity.ok(new DTODetalhamentoPaciente(pacienteRepository.getReferenceById(dados.id())));
    }
}
