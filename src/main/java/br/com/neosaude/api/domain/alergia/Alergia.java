package br.com.neosaude.api.domain.alergia;

import br.com.neosaude.api.domain.paciente.Paciente;
import br.com.neosaude.api.domain.paciente.PacienteRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Table(name = "alergias")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Alergia {

    //@Autowired
    //private PacienteRepository pacienteRepository;

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

    @ManyToOne
    private Paciente pacienteDiagnosticado;

    public Alergia(DTOCadastroAlergia dados) {
        this.intensidade = dados.intensidade();
        this.tipoAlergia = dados.tipoAlergia();
        this.descricao = dados.descricao();
        this.tratamento = dados.tratamento();
        this.dataDiagnostico = dados.dataDiagnostico();
        this.dataUltimaReacao = dados.dataUltimaReacao();

        this.pacienteDiagnosticado = pacienteRepository.getReferenceById(dados.idPacienteDiagnosticado());
    }
}
