package br.com.neosaude.api.domain.prescricao;


import br.com.neosaude.api.domain.medicamento.Medicamento;
import br.com.neosaude.api.domain.medicamento.MedicamentoRepository;
import br.com.neosaude.api.domain.receita.DTOCadastroReceita;
import br.com.neosaude.api.domain.receita.Receita;
import br.com.neosaude.api.domain.receita.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdicionarPrescricaoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private PrescricaoRepository prescricaoRepository;


    public Prescricao adicionarPrescricao(DTOCadastroPrescricao dados, Long idReceita){
        Medicamento medicamentoPrescrito = medicamentoRepository.getReferenceById(dados.idMedicamento());
        Receita receitaOrigem = receitaRepository.getReferenceById(idReceita);
        Prescricao prescricaoGerada = new Prescricao(dados, medicamentoPrescrito, receitaOrigem);

        prescricaoRepository.save(prescricaoGerada);
        return prescricaoGerada;
    }


    public List<Prescricao> adicionarListaPrescricoes(List<DTOCadastroPrescricao> listaDadosPrescricoes, Long idReceita){
        List<Prescricao> listaPrescricoesAdicionadas = new ArrayList<>();

        for(DTOCadastroPrescricao dados : listaDadosPrescricoes){
            Prescricao prescricao = this.adicionarPrescricao(dados, idReceita);
            listaPrescricoesAdicionadas.add(prescricao);
        }

        return listaPrescricoesAdicionadas;
    }
}
