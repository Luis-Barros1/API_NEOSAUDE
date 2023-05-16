package br.com.neosaude.api.controller;

import br.com.neosaude.api.domain.alergia.*;
import com.electronwill.nightconfig.core.conversion.Path;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/alergia")
public class AlergiaController {

    @Autowired
    private AlergiaRepository alergiaRepository;

    @Autowired
    private CriarAlergiaService criarAlergiaService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarAlergia(@RequestBody DTOCadastroAlergia dados, UriComponentsBuilder uriBuilder){
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
}
