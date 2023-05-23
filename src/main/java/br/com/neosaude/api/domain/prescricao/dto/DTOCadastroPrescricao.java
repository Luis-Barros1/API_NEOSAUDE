package br.com.neosaude.api.domain.prescricao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DTOCadastroPrescricao(

        @NotNull
        int quantidadeDeDias,

        @NotBlank
        String frequencia,

        @NotNull
        LocalDate dataExpedicao,

        @NotNull
        Long idMedicamento) {
}
