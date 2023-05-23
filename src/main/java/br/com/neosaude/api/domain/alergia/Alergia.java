package br.com.neosaude.api.domain.alergia;

import br.com.neosaude.api.domain.alergia.dto.DTOAtualizacaoAlergia;
import br.com.neosaude.api.domain.alergia.dto.DTOCadastroAlergia;
import br.com.neosaude.api.domain.alergia.IntensidadeAlergia;
import br.com.neosaude.api.domain.paciente.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "alergias")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Alergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    @Column(length = 45, nullable = false)
    private IntensidadeAlergia intensidade;

    @Column(length = 45, nullable = false)
    private String tipoAlergia;

    @Column(length = 100)
    private String descricao;

    @Column(length = 45)
    private String tratamento;

    private LocalDate dataDiagnostico;
    private LocalDate dataUltimaReacao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente pacienteDiagnosticado;

    public Alergia(DTOCadastroAlergia dados, Paciente pacienteAlergico) {
        this.intensidade = dados.intensidade();
        this.tipoAlergia = dados.tipoAlergia();
        this.descricao = dados.descricao();
        this.tratamento = dados.tratamento();
        this.dataDiagnostico = dados.dataDiagnostico();
        this.dataUltimaReacao = dados.dataUltimaReacao();
        this.pacienteDiagnosticado = pacienteAlergico;
    }

    public void atualizarDados(DTOAtualizacaoAlergia dados) {
        if(dados.intensidadeAlergia() != null) this.intensidade = dados.intensidadeAlergia();
        if(dados.dataUltimaReacao() != null) this.dataUltimaReacao = dados.dataUltimaReacao();
        if(dados.descricao() != null) this.descricao = dados.descricao();
        if(dados.tratamento() != null) this.tratamento = dados.tratamento();
    }
}
