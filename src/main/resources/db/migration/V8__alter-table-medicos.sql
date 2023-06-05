alter table medicos
add id_usuario bigint;

alter table medicos
add constraint fk_usuario_medico_id
foreign key (id_usuario) references usuarios (id);