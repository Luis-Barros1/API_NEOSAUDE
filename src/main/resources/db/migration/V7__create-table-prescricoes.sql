create table prescricoes(
	id bigint auto_increment primary key,
    quantidade_dias int not null,
    frequencia varchar(45) not null,
    data_expedicao date not null,
    ativo tinyint not null,
    id_receita_origem bigint,
    id_medicamento_prescrito bigint,

    CONSTRAINT fk_receita_origem_id FOREIGN KEY (id_receita_origem)
    REFERENCES receitas (id),

    CONSTRAINT fk_medicamento_prescrito_id FOREIGN KEY (id_medicamento_prescrito)
    REFERENCES medicamentos (id)
);