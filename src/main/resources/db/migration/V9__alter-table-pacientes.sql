alter table pacientes
add id_usuario bigint;

alter table pacientes
add constraint fk_usuario_paciente_id
foreign key (id_usuario) references usuarios (id);