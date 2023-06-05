package br.com.neosaude.api.domain.paciente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record DTOCadastroPaciente(

        @CPF
        @NotBlank
        String cpf,
        @NotBlank
        String nome,
        LocalDate dataNascimento,
        String email,
        @NotBlank
        String celular,

        @NotBlank
        String senha
) {
}
