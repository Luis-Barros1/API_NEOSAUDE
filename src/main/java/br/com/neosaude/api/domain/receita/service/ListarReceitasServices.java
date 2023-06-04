package br.com.neosaude.api.domain.receita.service;

import br.com.neosaude.api.domain.receita.Receita;
import br.com.neosaude.api.domain.receita.ReceitaRepository;
import br.com.neosaude.api.domain.receita.dto.DTODetalhamentoReceita;
import br.com.neosaude.api.domain.receita.dto.DTOListagemReceita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListarReceitasServices {

    @Autowired
    private ReceitaRepository receitaRepository;

    public List<DTOListagemReceita> listarReceitasAtivas(Long idPaciente){
        List<Receita> listaReceitas = receitaRepository.findReceitaAtivaByPaciente(idPaciente);
        return tratarListaReceitas(listaReceitas);
    }

    public List<DTOListagemReceita> listarReceitasInativas(Long idPaciente){
        List<Receita> listaReceitas = receitaRepository.findReceitaInativaByPaciente(idPaciente);
        return tratarListaReceitas(listaReceitas);
    }

    private List<DTOListagemReceita> tratarListaReceitas(List<Receita> listaReceitas){
        List<DTOListagemReceita> listaTratadaReceitas = new ArrayList<>();

        for(Receita r : listaReceitas){
            listaTratadaReceitas.add(new DTOListagemReceita(r));
        }

        return listaTratadaReceitas;
    }
}
