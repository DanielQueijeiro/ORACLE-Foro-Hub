package foro.hub.api.domain.topico;

import foro.hub.api.domain.cursos.Curso;
import foro.hub.api.domain.respuesta.DatosRespuestasTopico;
import foro.hub.api.domain.respuesta.RespuestasTopico;
import foro.hub.api.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topico")
@Entity(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private boolean status;


    @ManyToOne
    @JoinColumn(name = "autorId")
    private Usuario autor;

    @ManyToOne
    @JoinColumn(name = "cursoId")
    private Curso curso;


    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RespuestasTopico> respuestasTopico;


    public Topico(DatosRegistroTopico datosRegistroTopico,  Usuario usuario, Curso curso) {
        this.status = false;
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.autor = usuario;
        this.curso = curso;
        this.respuestasTopico = new ArrayList<>();
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }
        this.fechaCreacion = LocalDateTime.now();
    }

    public void agregarRespuesta(RespuestasTopico respuestasTopico) {
        this.respuestasTopico.add(respuestasTopico);
    }


    public Topico getTopico() {
        return this;
    }

}
