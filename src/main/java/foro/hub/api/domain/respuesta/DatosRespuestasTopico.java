package foro.hub.api.domain.respuesta;


import java.time.LocalDateTime;

public record DatosRespuestasTopico(
        String mensaje,
        LocalDateTime fechaCreacion,
        Long topicoId,
        Long autorId,
        String autorNombre) {

}
