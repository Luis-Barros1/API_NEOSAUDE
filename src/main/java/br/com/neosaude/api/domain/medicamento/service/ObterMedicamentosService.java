package br.com.neosaude.api.domain.medicamento.service;

import br.com.neosaude.api.domain.medicamento.Medicamento;
import br.com.neosaude.api.domain.medicamento.MedicamentoRepository;
import br.com.neosaude.api.domain.medicamento.dto.DTODetalhamentoMedicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ObterMedicamentosService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public List<DTODetalhamentoMedicamento> obterTodos(){
        List<Medicamento> listaMedicamentos = medicamentoRepository.findAll();
        List<DTODetalhamentoMedicamento> listaTratadaMedicamentos = new ArrayList<>();

        for(Medicamento m : listaMedicamentos){
            listaTratadaMedicamentos.add(new DTODetalhamentoMedicamento(m));
        }

        return listaTratadaMedicamentos;
    }
}
