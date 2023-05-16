package br.com.neosaude.api.domain.paciente;

import br.com.neosaude.api.domain.alergia.Alergia;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "pacientes")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String celular;

    @OneToMany(mappedBy = "pacienteDiagnosticado", fetch = FetchType.LAZY)
    private List<Alergia> alergias;

    public Paciente(DTOCadastroPaciente dados) {
        this.cpf = dados.cpf();
        this.nome = dados.nome();
        this.dataNascimento = dados.dataNascimento();
        this.email = dados.email();
        this.celular = dados.celular();
    }
}
