CREATE TABLE respuestasTopico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje varchar(200) NOT NULL,
    fechaCreacion TIMESTAMP NOT NULL,
    topicoId BIGINT NOT NULL,
    usuarioId BIGINT NOT NULL,
    solucion BOOLEAN NOT NULL,
    FOREIGN KEY (topicoId) REFERENCES topico(id),
    FOREIGN KEY (usuarioId) REFERENCES usuario(id)
);

drop table respuesta;