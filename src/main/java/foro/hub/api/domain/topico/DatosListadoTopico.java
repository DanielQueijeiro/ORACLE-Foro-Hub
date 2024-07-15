package foro.hub.api.domain.topico;

import foro.hub.api.domain.cursos.Curso;

import foro.hub.api.domain.usuarios.Usuario;

import java.time.LocalDateTime;

public record DatosListadoTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, Boolean status,
                                 String autor, String curso) {

    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.isStatus(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}

