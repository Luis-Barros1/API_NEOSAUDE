package br.com.neosaude.api.domain.paciente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByCpf(String s);

    boolean existsByCpf(String cpf);
}
