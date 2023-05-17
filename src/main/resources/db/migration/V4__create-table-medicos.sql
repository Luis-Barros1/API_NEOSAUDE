create table medicos(
	id bigint auto_increment primary key,
    crm varchar(20) unique not null,
    nome varchar(100) not null,
    especialidade varchar(100) not null,
    clinica varchar(100),
    data_nascimento date,
    email varchar(100) unique,
    celular varchar(20) unique not null
);