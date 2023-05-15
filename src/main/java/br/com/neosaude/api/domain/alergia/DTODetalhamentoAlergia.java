package br.com.neosaude.api.domain.alergia;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record DTODetalhamentoAlergia(Long id, IntensidadeAlergia intensidade, String tipoAlergia, String descricao, String tratamento, LocalDate dataDiagnostico, LocalDate dataUltimaReacao) {
    public DTODetalhamentoAlergia(Alergia alergia) {
        this(alergia.getId(), alergia.getIntensidade(), alergia.getTipoAlergia(), alergia.getDescricao(), alergia.getTratamento(), alergia.getDataDiagnostico(), alergia.getDataUltimaReacao());
    }
}
