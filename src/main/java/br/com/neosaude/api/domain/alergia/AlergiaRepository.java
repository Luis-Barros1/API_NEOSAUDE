package br.com.neosaude.api.domain.alergia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlergiaRepository extends JpaRepository<Alergia, Long> {

    @Query(value = "SELECT * FROM alergias WHERE id_paciente = ?1 ORDER BY data_ultima_reacao DESC", nativeQuery = true)
    List<Alergia> findByIdPaciente(Long id);
}
