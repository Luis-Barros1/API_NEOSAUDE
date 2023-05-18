package br.com.neosaude.api.domain.receita;


import br.com.neosaude.api.domain.medico.Medico;
import br.com.neosaude.api.domain.paciente.Paciente;
import br.com.neosaude.api.domain.prescricao.Prescricao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "receitas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private LocalDate dataExpedicao;

    @Column(nullable = false)
    private boolean ativo;

    private String observacao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medicoResponsavel;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente pacienteTratado;

    @OneToMany(mappedBy = "receitaOrigem")
    private List<Prescricao> prescricoes;

    public Receita(Paciente paciente, Medico medico, LocalDate dataExpedicao, String observacao) {
        this.ativo = true;
        this.dataExpedicao = dataExpedicao;
        this.pacienteTratado = paciente;
        this.medicoResponsavel = medico;
        this.observacao = observacao;
    }

    public void atualizarDados(DTOAtualizacaoReceita dados) {
        this.ativo = dados.ativo();
    }
}
