package br.com.neosaude.api.domain.paciente.dto;

import br.com.neosaude.api.domain.paciente.Paciente;

import java.time.LocalDate;

public record DTODetalhamentoPaciente(Long id, String cpf, String nome, LocalDate dataNascimento, String email, String celular) {


    public DTODetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getCpf(), paciente.getNome(), paciente.getDataNascimento(), paciente.getEmail(), paciente.getCelular());
    }
}
