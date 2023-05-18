package br.com.neosaude.api.domain.prescricao;

import br.com.neosaude.api.domain.medicamento.DTODetalhamentoMedicamento;

import java.time.LocalDate;

public record DTODetalhamentoPrescricao(Long idPrescricao, int quantidadeDias, String frequencia, LocalDate dataExpedicao, boolean ativo, DTODetalhamentoMedicamento dadosMedicamento) {


    public DTODetalhamentoPrescricao(Prescricao p) {
        this(p.getId(), p.getQuantidadeDias(), p.getFrequencia(), p.getDataExpedicao(), p.isAtivo(), new DTODetalhamentoMedicamento(p.getMedicamentoPrescrito()));
    }
}
