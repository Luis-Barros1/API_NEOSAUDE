package br.com.neosaude.api.domain.prescricao;

import br.com.neosaude.api.domain.medicamento.Medicamento;
import br.com.neosaude.api.domain.prescricao.dto.DTOCadastroPrescricao;
import br.com.neosaude.api.domain.receita.Receita;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prescricoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Prescricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantidadeDias;

    @Column(length = 45, nullable = false)
    private String frequencia;

    @Column(nullable = false)
    private LocalDate dataExpedicao;

    @Column(nullable = false)
    private boolean ativo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_receita_origem")
    private Receita receitaOrigem;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_medicamento_prescrito")
    private Medicamento medicamentoPrescrito;

    public Prescricao(DTOCadastroPrescricao dados, Medicamento medicamentoPrescrito, Receita receitaOrigem) {
        this.quantidadeDias = dados.quantidadeDeDias();
        this.frequencia = dados.frequencia();
        this.dataExpedicao = dados.dataExpedicao();
        this.ativo = true;
        this.receitaOrigem = receitaOrigem;
        this.medicamentoPrescrito = medicamentoPrescrito;
    }
}
