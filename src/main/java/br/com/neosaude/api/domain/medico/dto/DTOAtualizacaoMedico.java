package br.com.neosaude.api.domain.medico.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DTOAtualizacaoMedico(

        @NotNull
        Long id,
        String especialidade,
        String clinica,

        LocalDate dataNascimento,

        @Email
        String email) {
}
