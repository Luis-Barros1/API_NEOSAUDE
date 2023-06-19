package br.com.neosaude.api.controller;

import br.com.neosaude.api.domain.medico.dto.DTOAtualizacaoMedico;
import br.com.neosaude.api.domain.medico.dto.DTOCadastroMedico;
import br.com.neosaude.api.domain.medico.dto.DTODetalhamentoMedico;
import br.com.neosaude.api.domain.medico.Medico;
import br.com.neosaude.api.domain.medico.MedicoRepository;
import br.com.neosaude.api.domain.medico.service.AdicionarMedicoService;
import br.com.neosaude.api.domain.medico.service.ObterMedicoPorCrmService;
import br.com.neosaude.api.domain.usuario.Usuario;
import br.com.neosaude.api.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/medico")
@SecurityRequirement(name = "bearer-key")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private AdicionarMedicoService adicionarMedicoService;

    @Autowired
    private ObterMedicoPorCrmService obterMedicoPorCrmService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Transactional
    @PostMapping
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DTOCadastroMedico dados, UriComponentsBuilder uriBuilder){
        Medico medico = adicionarMedicoService.cadastrar(dados);
        URI uri = uriBuilder.path("/api/medico/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTODetalhamentoMedico(medico));
    }

    @GetMapping("/crm/{crm}")
    public ResponseEntity<DTODetalhamentoMedico> obterPorCrm(@PathVariable String crm){
        Medico medico = obterMedicoPorCrmService.obter(crm);
        return ResponseEntity.ok(new DTODetalhamentoMedico(medico));
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
        Usuario usuario = medico.getUsuarioMedico();
        medicoRepository.delete(medico);
        usuarioRepository.delete(usuario);

        return ResponseEntity.noContent().build();
    }
}
