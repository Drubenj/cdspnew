package com.mysena.cdsp3.app.servicio;

import com.mysena.cdsp3.app.entities.Rol;
import com.mysena.cdsp3.app.entities.Usuario;
import java.util.List;
import java.util.Optional;


public interface UsuarioServicio {
    
	//extends UserDetailsService
    public List<Usuario> listarTodos();
    public Usuario obtenerId(Long id);
    public Usuario agregar(Usuario usuario);
    public Usuario actualizar(Usuario usuario);
    public void eliminar(Long id);
    public Optional <Usuario> obtenerPorCorreoContrasenia(String correo, String Contrasenia);
    public List<Usuario> listarClientes(Rol rol);
    
}
