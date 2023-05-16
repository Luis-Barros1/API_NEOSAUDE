create table pacientes(
    id bigint not null auto_increment primary key,
    cpf VARCHAR(14) not null UNIQUE,
    nome VARCHAR(100) not null,
    data_nascimento DATE,
    email VARCHAR(100) UNIQUE,
    celular VARCHAR(20) not null UNIQUE
);