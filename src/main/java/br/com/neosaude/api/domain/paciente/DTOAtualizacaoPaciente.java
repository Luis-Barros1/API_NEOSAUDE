package br.com.neosaude.api.domain.paciente;

import java.time.LocalDate;

public record DTOAtualizacaoPaciente(Long id, String nome, LocalDate dataNascimento, String celular) {
    public DTOAtualizacaoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getDataNascimento(), paciente.getCelular());
    }
}
