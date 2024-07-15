package foro.hub.api.domain.topico;

import foro.hub.api.domain.cursos.CursoRepository;
import foro.hub.api.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    public Topico crearTopico(DatosRegistroTopico datosRegistroTopico) {
        var usuario = usuarioRepository.findById(datosRegistroTopico.usuario_id()).get();
        var curso = cursoRepository.findById(datosRegistroTopico.curso_id()).get();
        var topico = new Topico(datosRegistroTopico, usuario, curso);
        topicoRepository.save(topico);

        return topico;
    }
}
