alter table topico
    modify autorId BIGINT null;
alter table topico
    modify cursoId BIGINT not null;
alter table topico
    add respuestaId BIGINT not null;