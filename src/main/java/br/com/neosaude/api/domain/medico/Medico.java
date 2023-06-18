package br.com.neosaude.api.domain.medico;

import br.com.neosaude.api.domain.medico.dto.DTOAtualizacaoMedico;
import br.com.neosaude.api.domain.medico.dto.DTOCadastroMedico;
import br.com.neosaude.api.domain.receita.Receita;
import br.com.neosaude.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
    private String crm;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    private String especialidade;

    @Column(length = 100)
    private String clinica;

    private LocalDate dataNascimento;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 20, unique = true, nullable = false)
    private String celular;

    @OneToMany(mappedBy = "medicoResponsavel", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Receita> receitas;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioMedico;

    public Medico(DTOCadastroMedico dados, Usuario usuario) {
        this.crm = dados.crm();
        this.nome = dados.nome();
        this.especialidade = dados.especialidade();
        this.clinica = dados.clinica();
        this.dataNascimento = dados.dataNascimento();
        this.email = dados.email();
        this.celular = dados.celular();
        this.usuarioMedico = usuario;
    }

    public void atualizarDados(DTOAtualizacaoMedico dados) {
        if(dados.especialidade() != null) this.especialidade = dados.especialidade();
        if(dados.clinica() != null) this.clinica = dados.clinica();
        if(dados.email() != null) this.email = dados.email();
        if(dados.dataNascimento() != null) this.dataNascimento = dados.dataNascimento();
    }
}
