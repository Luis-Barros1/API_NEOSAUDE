create table receitas(
	id bigint auto_increment primary key,
    data_expedicao date not null,
    ativo tinyint not null,
    observacao varchar(255),
    id_medico bigint,
    id_paciente bigint,

    CONSTRAINT fk_paciente_tratado_id FOREIGN KEY (id_paciente)
    REFERENCES pacientes (id),

    CONSTRAINT fk_medico_responsavel_id FOREIGN KEY (id_medico)
    REFERENCES medicos (id)
);