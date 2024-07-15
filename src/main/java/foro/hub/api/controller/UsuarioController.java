package foro.hub.api.controller;

import foro.hub.api.domain.cursos.Curso;
import foro.hub.api.domain.cursos.DatosCurso;
import foro.hub.api.domain.cursos.DatosListadoCurso;
import foro.hub.api.domain.usuarios.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody @Valid DatosUsuario usuario,
                                                     UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuarioGuardado = usuarioService.crearUsuario(usuario);

        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuarioGuardado.getId()).toUri();
        return ResponseEntity.created(url).body(usuarioGuardado);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listadoCurso(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(DatosListadoUsuario::new));
    }
}
