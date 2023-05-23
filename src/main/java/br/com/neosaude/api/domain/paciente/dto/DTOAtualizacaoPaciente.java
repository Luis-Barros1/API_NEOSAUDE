package br.com.neosaude.api.domain.paciente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DTOAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        LocalDate dataNascimento,

        @Email
        String email) {
}
