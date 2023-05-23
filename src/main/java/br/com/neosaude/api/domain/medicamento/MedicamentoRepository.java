package br.com.neosaude.api.domain.medicamento;

import br.com.neosaude.api.domain.medicamento.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}
