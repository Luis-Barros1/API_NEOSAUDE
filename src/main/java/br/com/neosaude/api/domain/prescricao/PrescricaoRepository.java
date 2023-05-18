package br.com.neosaude.api.domain.prescricao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrescricaoRepository extends JpaRepository<Prescricao, Long> {


    @Query(value = "SELECT * FROM prescricoes WHERE id_receita_origem = ?1", nativeQuery = true)
    List<Prescricao> findByIdReceita(Long id);
}
