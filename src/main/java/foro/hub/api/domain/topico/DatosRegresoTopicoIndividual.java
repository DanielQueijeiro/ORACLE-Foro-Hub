package foro.hub.api.domain.topico;

import java.time.LocalDateTime;

public record DatosRegresoTopicoIndividual(
        Long id,
        String titulo,
        String mensaje,
        String respuesta,
        LocalDateTime fechaCreacion,
        String autorNombre,
        String cursoNombre
) {
}
