package br.com.neosaude.api.domain.paciente;

import java.time.LocalDate;

public record DTOAtualizacaoPaciente(Long id, String nome, LocalDate dataNascimento, String email) {
}
