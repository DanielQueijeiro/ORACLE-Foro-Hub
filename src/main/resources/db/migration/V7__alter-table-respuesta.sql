alter table respuesta
    change topico topicoId BIGINT not null;

alter table respuesta
    change autor autorId BIGINT not null;
