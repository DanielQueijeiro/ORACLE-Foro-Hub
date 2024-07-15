package foro.hub.api.domain.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder contrasenaCifrado;

    public Usuario crearUsuario(DatosUsuario datosUsuario) {
        var usuario = new Usuario();

        usuario.setNombre(datosUsuario.nombre());
        usuario.setCorreoElectronico(datosUsuario.correoElectronico());
        usuario.setContrasena(contrasenaCifrado.encode(datosUsuario.contrasena()));

        usuarioRepository.save(usuario);

        return usuario;
    }
}
