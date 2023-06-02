package br.com.neosaude.api.controller;

import br.com.neosaude.api.domain.prescricao.service.ObterPrescricoesService;
import br.com.neosaude.api.domain.prescricao.PrescricaoRepository;
import br.com.neosaude.api.domain.receita.*;
import br.com.neosaude.api.domain.receita.dto.DTOAtualizacaoReceita;
import br.com.neosaude.api.domain.receita.dto.DTOCadastroReceita;
import br.com.neosaude.api.domain.receita.dto.DTODetalhamentoReceita;
import br.com.neosaude.api.domain.receita.service.AdicionarReceitaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/receita")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ReceitaController {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private AdicionarReceitaService criarReceitaService;

    @Autowired
    private PrescricaoRepository prescricaoRepository;

    @Autowired
    private ObterPrescricoesService obterPrescricoesService;


    @Transactional
    @PostMapping
    public ResponseEntity cadastrarReceita(@RequestBody @Valid DTOCadastroReceita dados, UriComponentsBuilder uriBuilder){
        Receita receita = criarReceitaService.adicionar(dados);
        receitaRepository.save(receita);
        URI uri = uriBuilder.path("/api/receita/{id}").buildAndExpand(receita.getId()).toUri();
        return ResponseEntity.created(uri).body(new DTODetalhamentoReceita(receita, obterPrescricoesService.obter(receita.getId())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTODetalhamentoReceita> detalharReceita(@PathVariable Long id){
        Receita receita = receitaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DTODetalhamentoReceita(receita, obterPrescricoesService.obter(id)));
    }

    @Transactional
    @PutMapping("/alterarStatus/{id}")
    public ResponseEntity<DTODetalhamentoReceita> alterarStatusReceita(@RequestBody DTOAtualizacaoReceita dados){
        Receita receita = receitaRepository.getReferenceById(dados.id());
        receita.atualizarDados(dados);
        return ResponseEntity.ok(new DTODetalhamentoReceita(receita, obterPrescricoesService.obter(receita.getId())));
    }

    @GetMapping("/ativas/{idPaciente}")
    public ResponseEntity<List<Receita>> obterTodasReceitasAtivas(@PathVariable Long idPaciente){
        return ResponseEntity.ok(receitaRepository.findReceitaAtivaByPaciente(idPaciente));
    }

    @GetMapping("/inativas/{idPaciente}")
    public ResponseEntity<List<Receita>> obterTodasReceitasInativas(@PathVariable Long idPaciente){
        return ResponseEntity.ok(receitaRepository.findReceitaInativaByPaciente(idPaciente));
    }

    @GetMapping("/ativas/contar/{idPaciente}")
    public ResponseEntity<Integer> contarReceitasAtivas(@PathVariable Long idPaciente){
        return ResponseEntity.ok(receitaRepository.contarReceitasAtivas(idPaciente));
    }
}
