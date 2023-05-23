package br.com.neosaude.api.domain.alergia.dto;

import br.com.neosaude.api.domain.alergia.Alergia;

import java.time.LocalDate;

public record DTOUltimaReacaoAlergica(String tipoAlergia, LocalDate dataUltimaReacao) {
    public DTOUltimaReacaoAlergica(Alergia a) {
        this(a.getTipoAlergia(), a.getDataUltimaReacao());
    }
}
