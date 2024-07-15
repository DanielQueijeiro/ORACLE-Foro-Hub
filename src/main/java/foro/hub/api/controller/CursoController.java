package foro.hub.api.controller;

import foro.hub.api.domain.cursos.Curso;
import foro.hub.api.domain.cursos.CursoRepository;
import foro.hub.api.domain.cursos.DatosCurso;
import foro.hub.api.domain.cursos.DatosListadoCurso;
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
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosCurso> registrarCurso(@RequestBody @Valid DatosCurso datosCurso,
                                                     UriComponentsBuilder uriComponentsBuilder) {
        Curso curso = cursoRepository.save(new Curso(datosCurso));

        DatosCurso datosCursoRegreso = new DatosCurso(curso.getNombre(), curso.getCategoria());

        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosCursoRegreso);

    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoCurso>> listadoCurso(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(cursoRepository.findAll(paginacion).map(DatosListadoCurso::new));
    }
}
