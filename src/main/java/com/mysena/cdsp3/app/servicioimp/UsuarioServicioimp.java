/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mysena.cdsp3.app.servicioimp;


import com.mysena.cdsp3.app.entities.Rol;
import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.repositorio.UsuarioRepositorio;
import com.mysena.cdsp3.app.servicio.UsuarioServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicioimp implements UsuarioServicio {
    

	
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Override
    public List<Usuario> listarTodos() {
        return this.usuarioRepositorio.findAll();
    }

    @Override
    public Usuario agregar(Usuario usuario) {
    	//usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
        return this.usuarioRepositorio.save(usuario);
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
      return this.usuarioRepositorio.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        this.usuarioRepositorio.deleteById(id);
    }


    @Override
    public Usuario obtenerId(Long id) {
        return this.usuarioRepositorio.findById(id).get();
    }

	@Override
	public Optional<Usuario> obtenerPorCorreoContrasenia(String correo, String contrasenia) {
		
		return this.usuarioRepositorio.findByCorreoAndContrasenia(correo, contrasenia);
	}

	@Override
	public List<Usuario> listarClientes(Rol rol) {
		
		List<Usuario> usuarios = this.usuarioRepositorio.findAll();
		List<Usuario> listaClientes = new ArrayList<Usuario>();
		for(Usuario user: usuarios) {
			if (user.getListaRoles().contains(rol)) {
				listaClientes.add(user);
			}
		}
		return listaClientes;
	}

}
