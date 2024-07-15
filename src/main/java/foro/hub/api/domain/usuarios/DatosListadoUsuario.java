package foro.hub.api.domain.usuarios;

public record DatosListadoUsuario(
        Long id,
        String nombre,
        String correoElectronico) {

    public DatosListadoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico());
    }
}
