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

    public RespuestasTopico(DatosRespuestasTopico datosRespuestasTopico) {
        this.mensaje = datosRespuestasTopico.mensaje();
        this.autor = datosRespuestasTopico.autor();
        this.topico = datosRespuestasTopico.topico();
        this.solucion = datosRespuestasTopico.solucion();
    }
}
