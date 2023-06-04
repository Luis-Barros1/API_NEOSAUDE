package br.com.neosaude.api.domain.medicamento;

import br.com.neosaude.api.domain.medicamento.dto.DTOAtualizacaoMedicamento;
import br.com.neosaude.api.domain.medicamento.dto.DTOCadastroMedicamento;
import br.com.neosaude.api.domain.prescricao.Prescricao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "medicamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "medicamentoPrescrito", fetch = FetchType.LAZY)
    private List<Prescricao> prescricoes;

    public Medicamento(DTOCadastroMedicamento dtoCadastroMedicamento) {
        this.nome = dtoCadastroMedicamento.nome();
    }

    public void atualizarDados(DTOAtualizacaoMedicamento dados) {
        if(dados.nome() != null) this.nome = dados.nome();
    }
}
