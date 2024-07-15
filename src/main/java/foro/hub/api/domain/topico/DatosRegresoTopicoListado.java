package foro.hub.api.domain.topico;

import java.time.LocalDateTime;

public record DatosRegresoTopicoListado(
        Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, String autorNombre, String cursoNombre) {
}
