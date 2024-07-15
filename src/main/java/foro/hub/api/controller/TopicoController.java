package foro.hub.api.controller;

import foro.hub.api.domain.respuesta.DatosRespuestasTopico;
import foro.hub.api.domain.topico.*;
import foro.hub.api.domain.usuarios.Usuario;
import foro.hub.api.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRegresoTopicoListado> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                                     UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoService.crearTopico(datosRegistroTopico);

        DatosRegresoTopicoListado datosRegreso = new DatosRegresoTopicoListado(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaCreacion(), topico.getAutor().getNombre(), topico.getCurso().getNombre());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRegreso);

    }

    @PostMapping("/{id}/respuesta")
    @Transactional
    public ResponseEntity<DatosRegresoTopicoIndividual> registrarRespuesta(@PathVariable Long id, @RequestBody @Valid DatosRespuestasTopico datosRespuestasTopico,
                                                                        UriComponentsBuilder uriComponentsBuilder) {

        Topico topico = topicoService.agregarRespuesta(id, datosRespuestasTopico);
        Usuario autor = usuarioRepository.findById(datosRespuestasTopico.autorId()).get();
        return ResponseEntity.ok(new DatosRegresoTopicoIndividual(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), datosRespuestasTopico.mensaje(), topico.getFechaCreacion(), autor.getNombre(),
                topico.getCurso().getNombre()));
    }


    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRegresoTopicoIndividual> retornaDatosTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRegresoTopicoIndividual(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getRespuestasTopico().toString(),topico.getFechaCreacion(),
                topico.getAutor().getNombre(), topico.getCurso().getNombre());

        return ResponseEntity.ok(datosTopico);
    }

    @GetMapping("/top")
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicosTop10
            (@PageableDefault(size = 5, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
        var sortedByDate = topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
        sortedByDate.stream().limit(10);
        return ResponseEntity.ok(sortedByDate);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRegresoTopicoListado(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getFechaCreacion(), topico.getAutor().getNombre(), topico.getCurso().getNombre()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.ok("El topico: '" + topico.getTitulo() + "' ha sido eliminado");
    }



}
