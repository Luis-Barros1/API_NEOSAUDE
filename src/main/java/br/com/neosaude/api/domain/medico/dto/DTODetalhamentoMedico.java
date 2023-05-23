package br.com.neosaude.api.domain.medico.dto;

import br.com.neosaude.api.domain.medico.Medico;

import java.time.LocalDate;

public record DTODetalhamentoMedico(String crm, String nome, String especialidade, String clinica, LocalDate dataNascimento, String email, String celular) {
    public DTODetalhamentoMedico(Medico medico) {
        this(medico.getCrm(), medico.getNome(), medico.getEspecialidade(), medico.getClinica(), medico.getDataNascimento(), medico.getEmail(), medico.getCelular());
    }
}
