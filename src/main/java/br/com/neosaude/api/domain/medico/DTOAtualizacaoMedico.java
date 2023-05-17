package br.com.neosaude.api.domain.medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DTOAtualizacaoMedico(

        @NotNull
        Long id,
        String especialidade,
        String clinica,

        @Email
        String email) {
}
