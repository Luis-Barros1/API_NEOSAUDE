package br.com.neosaude.api.domain.medico.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DTOCadastroMedico(

        @NotBlank
        String crm,

        @NotBlank
        String nome,

        @NotBlank
        String especialidade,

        String clinica,
        LocalDate dataNascimento,

        @Email
        String email,

        @NotBlank
        String celular) {
}
