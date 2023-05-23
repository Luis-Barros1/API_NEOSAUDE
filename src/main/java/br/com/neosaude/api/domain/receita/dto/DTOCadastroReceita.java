package br.com.neosaude.api.domain.receita.dto;

import br.com.neosaude.api.domain.prescricao.dto.DTOCadastroPrescricao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DTOCadastroReceita(

        @NotNull
        Long idMedico,

        @NotBlank
        String cpfPaciente,

        @NotNull
        List< @Valid DTOCadastroPrescricao> prescricoes,

        String observacao
) {
}
