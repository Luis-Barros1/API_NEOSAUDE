package br.com.neosaude.api.domain.medico;

import br.com.neosaude.api.domain.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query(value = "SELECT * FROM medicos WHERE crm = ?1", nativeQuery = true)
    Medico getReferenceByCrm(String crm);

    boolean existsByCrm(String crm);
}
