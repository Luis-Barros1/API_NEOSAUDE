package br.com.neosaude.api.domain.paciente;

import br.com.neosaude.api.domain.alergia.Alergia;
import br.com.neosaude.api.domain.receita.Receita;
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

    @Column(length = 20, nullable = false, unique = true)
    private String cpf;

    @Column(length = 100, nullable = false)
    private String nome;

    private LocalDate dataNascimento;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 20, nullable = false, unique = true)
    private String celular;

    @OneToMany(mappedBy = "pacienteDiagnosticado", fetch = FetchType.LAZY)
    private List<Alergia> alergias;

    @OneToMany(mappedBy = "pacienteTratado", fetch = FetchType.LAZY)
    private List<Receita> receitas;

    public Paciente(DTOCadastroPaciente dados) {
        this.cpf = dados.cpf();
        this.nome = dados.nome();
        this.dataNascimento = dados.dataNascimento();
        this.email = dados.email();
        this.celular = dados.celular();
    }

    public void atualizarDados(DTOAtualizacaoPaciente dados) {
        //if(dados.nome() != null)
        this.nome = dados.nome();
        //if(dados.email() != null) this.email = dados.email();
        //if(dados.dataNascimento() != null) this.dataNascimento = dados.dataNascimento();
    }
}
