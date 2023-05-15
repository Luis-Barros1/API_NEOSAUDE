package br.com.neosaude.api.domain.paciente;

import br.com.neosaude.api.domain.alergia.Alergia;

import java.time.LocalDate;
import java.util.List;

public record DTODetalhamentoPaciente(Long id, String cpf, String nome, LocalDate dataNascimento, String email, String celular, List<Alergia> alergias) {


    public DTODetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getCpf(), paciente.getNome(), paciente.getDataNascimento(), paciente.getEmail(), paciente.getCelular(), paciente.getAlergias());
    }
}
