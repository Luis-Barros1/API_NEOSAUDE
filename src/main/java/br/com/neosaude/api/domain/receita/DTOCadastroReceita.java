package br.com.neosaude.api.domain.receita;

import br.com.neosaude.api.domain.prescricao.DTOCadastroPrescricao;
import br.com.neosaude.api.domain.prescricao.Prescricao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

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
