package br.com.neosaude.api.controller;

import br.com.neosaude.api.domain.medicamento.Medicamento;
import br.com.neosaude.api.domain.medicamento.MedicamentoRepository;
import br.com.neosaude.api.domain.medicamento.service.ObterMedicamentosService;
import br.com.neosaude.api.domain.medicamento.dto.DTOAtualizacaoMedicamento;
import br.com.neosaude.api.domain.medicamento.dto.DTOCadastroMedicamento;
import br.com.neosaude.api.domain.medicamento.dto.DTODetalhamentoMedicamento;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/medicamento")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class MedicamentoController {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private ObterMedicamentosService obterMedicamentosService;

    @GetMapping
    public ResponseEntity<List<DTODetalhamentoMedicamento>> listarTodos(){
        return ResponseEntity.ok(obterMedicamentosService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTODetalhamentoMedicamento> obter(@PathVariable Long id){
        Medicamento medicamento = medicamentoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DTODetalhamentoMedicamento(medicamento));
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedicamento(@RequestBody DTOCadastroMedicamento dtoCadastroMedicamento, UriComponentsBuilder uriBuilder){
        Medicamento medicamento = new Medicamento(dtoCadastroMedicamento);
        medicamentoRepository.save(medicamento);
        URI uri = uriBuilder.path("/api/medicamento/{id}").buildAndExpand(medicamento.getId()).toUri();

        return ResponseEntity.created(uri).body(new DTODetalhamentoMedicamento(medicamento));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedicamento(@RequestBody DTOAtualizacaoMedicamento dados){
        Medicamento medicamento = medicamentoRepository.getReferenceById(dados.id());
        medicamento.atualizarDados(dados);

        return ResponseEntity.ok(new DTODetalhamentoMedicamento(medicamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirMedicamento(@PathVariable Long id){
        Medicamento medicamento = medicamentoRepository.getReferenceById(id);
        medicamentoRepository.delete(medicamento);

        return ResponseEntity.noContent().build();
    }

}
