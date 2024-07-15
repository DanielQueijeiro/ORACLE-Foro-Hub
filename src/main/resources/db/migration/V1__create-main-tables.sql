
create table topico(

    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(100) not null unique,
    fechaCreacion date not null,
    status smallint not null,
    autor varchar(100) not null,
    curso varchar(100) not null,

    primary key(id)

);

create table respuesta(

    id bigint not null auto_increment,
    mensaje varchar(100) not null,
    topico varchar(100) not null,
    fechaCreacion date not null,
    autor varchar(100) not null,
    solucion boolean not null,

    primary key (id)
);

create table curso(

    id bigint not null auto_increment,
    nombre varchar(100) not null,
    categoria varchar(100) not null,

    primary key (id)
);

create table usuario(

    id bigint not null auto_increment,
    nombre varchar(100) not null,
    correoElectronico varchar(100) not null,
    contrasena varchar(100) not null,

    primary key (id)
);
