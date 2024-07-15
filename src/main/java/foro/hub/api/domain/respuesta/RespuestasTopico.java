package foro.hub.api.domain.respuesta;

import foro.hub.api.domain.topico.Topico;
import foro.hub.api.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestasTopico")
@Entity(name = "respuestasTopico")
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RespuestasTopico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private boolean solucion = false;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "topicoId")
    private Topico topico;

    public RespuestasTopico(DatosRespuestasTopico datosRespuestasTopico, Usuario autor, Topico topico) {
        this.mensaje = datosRespuestasTopico.mensaje();
        this.autor = autor;
        this.topico = topico;
    }

    @Override
    public String toString() {
        return "[Mensaje: " + mensaje +
                " | Autor:" + (autor != null ? autor.getNombre() : "null") + "]";
    }
}
