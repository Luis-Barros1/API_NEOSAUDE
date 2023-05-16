package br.com.neosaude.api.domain.alergia;

import java.time.LocalDate;

public record DTOUltimaReacaoAlergica(String tipoAlergia, LocalDate dataUltimaReacao) {
    public DTOUltimaReacaoAlergica(Alergia a) {
        this(a.getTipoAlergia(), a.getDataUltimaReacao());
    }
}
