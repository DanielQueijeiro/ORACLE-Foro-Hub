package foro.hub.api.domain.respuesta;

import foro.hub.api.domain.topico.Topico;
import foro.hub.api.domain.usuarios.Usuario;

import java.time.LocalDateTime;

public record DatosRespuestasTopico(Long id, String mensaje, Topico topico, LocalDateTime fechaCreacion, Usuario autor,
                                    boolean solucion) {
}
