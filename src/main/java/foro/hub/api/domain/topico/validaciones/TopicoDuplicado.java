package foro.hub.api.domain.topico.validaciones;

import foro.hub.api.domain.topico.DatosRegistroTopico;
import foro.hub.api.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoDuplicado implements Validador{

    @Autowired
    private TopicoRepository repository;

    public void validar(DatosRegistroTopico datos) {

        var topico = repository.findByTitulo(datos.titulo());
        if(topico != null){
            if (topico.getTitulo().equals(datos.titulo()) && topico.getMensaje().equals(datos.mensaje())) {
                throw new ValidationException("Ya existe un topico con el titulo: " + datos.titulo() + "\n y mensaje: " + datos.mensaje() + " en el sistema");
            }
        }
    }
}