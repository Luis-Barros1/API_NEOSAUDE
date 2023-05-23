package br.com.neosaude.api.domain.prescricao.service;

import br.com.neosaude.api.domain.prescricao.Prescricao;
import br.com.neosaude.api.domain.prescricao.PrescricaoRepository;
import br.com.neosaude.api.domain.prescricao.dto.DTODetalhamentoPrescricao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ObterPrescricoesService {

    @Autowired
    private PrescricaoRepository prescricaoRepository;

    public List<DTODetalhamentoPrescricao> obter(Long idReceita){
        List<Prescricao> listaPrescricoes = prescricaoRepository.findByIdReceita(idReceita);
        List<DTODetalhamentoPrescricao> listaPrescricoesFormatada = new ArrayList<>();
        for(Prescricao p : listaPrescricoes){
            DTODetalhamentoPrescricao dto = new DTODetalhamentoPrescricao(p);
            listaPrescricoesFormatada.add(dto);
        }
        return listaPrescricoesFormatada;
    }
}
