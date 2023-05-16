create table alergias(
	id bigint auto_increment primary key,
    intensidade varchar(100) not null,
    tipo_alergia varchar(100) not null,
    data_diagnostico date,
    data_ultima_reacao date,
    descricao varchar(100),
    tratamento varchar(100),
    id_paciente bigint,

    CONSTRAINT fk_paciente_id FOREIGN KEY (id_paciente)
    REFERENCES pacientes (id)

);