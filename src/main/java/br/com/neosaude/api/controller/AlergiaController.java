package br.com.neosaude.api.controller;

import br.com.neosaude.api.domain.alergia.*;
import com.electronwill.nightconfig.core.conversion.Path;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/alergia")
public class AlergiaController {

    @Autowired
    private AlergiaRepository alergiaRepository;

    @Autowired
    private CriarAlergiaService criarAlergiaService;

    @Autowired
    private ObterUltimaReacaoService obterUltimaReacaoService;

    @Autowired
    private ContagemAlergiasService contagemAlergiasService;



    @PostMapping
    @Transactional
    public ResponseEntity cadastrarAlergia(@RequestBody @Valid DTOCadastroAlergia dados, UriComponentsBuilder uriBuilder){
        Alergia alergia = criarAlergiaService.criarAlergia(dados);
        URI uri = uriBuilder.path("/api/alergia/{id}").buildAndExpand(alergia.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTODetalhamentoAlergia(alergia));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTODetalhamentoAlergia> detalharAlergia(@PathVariable Long id){
        Alergia alergia = alergiaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DTODetalhamentoAlergia(alergia));
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Alergia>> obterAlergias(@PathVariable Long idPaciente){
        List<Alergia> listaAlergias = alergiaRepository.findByIdPaciente(idPaciente);
        return ResponseEntity.ok(listaAlergias);
    }

    @GetMapping("/ultimaReacao/paciente/{idPaciente}")
    public ResponseEntity<DTOUltimaReacaoAlergica> obterUltimaReacao(@PathVariable Long idPaciente){
        DTOUltimaReacaoAlergica dtoUltimaReacaoAlergica = obterUltimaReacaoService.obterUltimaReacao(idPaciente);

        return ResponseEntity.ok(dtoUltimaReacaoAlergica);
    }

    @GetMapping("/contar/paciente/{idPaciente}")
    public ResponseEntity<DTOContagemAlergias> contarAlergiasPaciente(@PathVariable Long idPaciente){
        return ResponseEntity.ok(contagemAlergiasService.contarAlergias(idPaciente));
    }

    @Transactional
    @DeleteMapping("/{idAlergia}")
    public ResponseEntity deletarAlergia(@PathVariable Long idAlergia){
        Alergia alergia = alergiaRepository.getReferenceById(idAlergia);
        alergiaRepository.delete(alergia);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping
    public ResponseEntity<DTODetalhamentoAlergia> atualizarAlergia(@RequestBody DTOAtualizacaoAlergia dados){
        Alergia alergia = alergiaRepository.getReferenceById(dados.id());
        alergia.atualizarDados(dados);

        return ResponseEntity.ok(new DTODetalhamentoAlergia(alergiaRepository.getReferenceById(dados.id())));
    }
}
