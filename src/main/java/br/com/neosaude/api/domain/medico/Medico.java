package br.com.neosaude.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    public Medico(DTOCadastroMedico dados) {
        this.crm = dados.crm();
        this.nome = dados.nome();
        this.especialidade = dados.especialidade();
        this.clinica = dados.clinica();
        this.dataNascimento = dados.dataNascimento();
        this.email = dados.email();
        this.celular = dados.celular();
    }

    public void atualizarDados(DTOAtualizacaoMedico dados) {
        if(dados.especialidade() != null) this.especialidade = dados.especialidade();
        if(dados.clinica() != null) this.clinica = dados.clinica();
        if(dados.email() != null) this.email = dados.email();
    }
}
