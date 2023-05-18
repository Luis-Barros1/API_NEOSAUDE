package br.com.neosaude.api.domain.alergia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DTOCadastroAlergia(
        @NotNull
        Long idPacienteDiagnosticado,
        @NotNull
        IntensidadeAlergia intensidade,
        @NotBlank
        String tipoAlergia,
        @NotNull
        LocalDate dataDiagnostico,
        @NotNull
        LocalDate dataUltimaReacao,
        String descricao,
        String tratamento) {
}
