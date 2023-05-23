package br.com.neosaude.api.domain.medicamento.dto;

import br.com.neosaude.api.domain.medicamento.Medicamento;

public record DTODetalhamentoMedicamento(Long id, String nome) {
    public DTODetalhamentoMedicamento(Medicamento medicamentoPrescrito) {
        this(medicamentoPrescrito.getId(), medicamentoPrescrito.getNome());
    }
}
