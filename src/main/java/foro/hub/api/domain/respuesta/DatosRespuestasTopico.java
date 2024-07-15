package foro.hub.api.domain.respuesta;

import foro.hub.api.domain.topico.Topico;
import foro.hub.api.domain.usuarios.Usuario;

import java.time.LocalDateTime;

public record DatosRespuestasTopico(
        String mensaje,
        LocalDateTime fechaCreacion,
        Long topicoId,
        Long autorId,
        String autorNombre) {

}
