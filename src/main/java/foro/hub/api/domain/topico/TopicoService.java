package foro.hub.api.domain.topico;

import foro.hub.api.domain.cursos.CursoRepository;
import foro.hub.api.domain.respuesta.DatosRespuestasTopico;
import foro.hub.api.domain.respuesta.RespuestaTopicosRepository;
import foro.hub.api.domain.respuesta.RespuestasTopico;
import foro.hub.api.domain.topico.validaciones.Validador;
import foro.hub.api.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private Validador validador;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private RespuestaTopicosRepository respuestaTopicosRepository;

    public Topico crearTopico(DatosRegistroTopico datosRegistroTopico) {

        validador.validar(datosRegistroTopico);

        var usuario = usuarioRepository.findById(datosRegistroTopico.usuario_id()).get();
        var curso = cursoRepository.findById(datosRegistroTopico.curso_id()).get();
        var topico = new Topico(datosRegistroTopico, usuario, curso);
        topicoRepository.save(topico);

        return topico;
    }

    public Topico agregarRespuesta(Long id, DatosRespuestasTopico datosRespuestasTopico) {
        var topico = topicoRepository.findById(id).get();
        var usuario = usuarioRepository.findById(datosRespuestasTopico.autorId()).get();
        var respuesta = new RespuestasTopico(datosRespuestasTopico, usuario , topico);
        topico.agregarRespuesta(respuesta);
        respuestaTopicosRepository.save(respuesta);

        return topico;
    }
}
