package br.com.neosaude.api.domain.medicamento;

public record DTODetalhamentoMedicamento(Long id, String nome) {
    public DTODetalhamentoMedicamento(Medicamento medicamentoPrescrito) {
        this(medicamentoPrescrito.getId(), medicamentoPrescrito.getNome());
    }
}
