package br.com.neosaude.api.domain.alergia.dto;

import br.com.neosaude.api.domain.alergia.IntensidadeAlergia;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DTOAtualizacaoAlergia(

        @NotNull
        Long id,
        IntensidadeAlergia intensidadeAlergia,
        String descricao,
        String tratamento,
        LocalDate dataUltimaReacao) {
}
