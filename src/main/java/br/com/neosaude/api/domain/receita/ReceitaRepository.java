package br.com.neosaude.api.domain.receita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    @Query(value = "SELECT * FROM receitas WHERE id_paciente = ?1 AND ativo = 1", nativeQuery = true)
    List<Receita> findReceitaAtivaByPaciente(Long idPaciente);

    @Query(value = "SELECT * FROM receitas WHERE id_paciente = ?1 AND ativo = 0", nativeQuery = true)
    List<Receita> findReceitaInativaByPaciente(Long idPaciente);

    @Query(value = "select count(*) from receitas where id_paciente = ?1 and ativo = 1", nativeQuery = true)
    Integer contarReceitasAtivas(Long idPaciente);
}

