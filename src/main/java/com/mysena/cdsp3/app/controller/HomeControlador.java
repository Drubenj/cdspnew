package com.mysena.cdsp3.app.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysena.cdsp3.app.entities.Menu;
import com.mysena.cdsp3.app.entities.Rol;
import com.mysena.cdsp3.app.entities.Usuario;
import com.mysena.cdsp3.app.servicioimp.EmailServiceImpl;
import com.mysena.cdsp3.app.servicioimp.MenuServicioimp;
import com.mysena.cdsp3.app.servicioimp.RolServicioimp;
import com.mysena.cdsp3.app.servicioimp.UsuarioServicioimp;


@Controller
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST })
public class HomeControlador {
	
	@Autowired
	private UsuarioServicioimp usuarioServicio;
	@Autowired
	private MenuServicioimp menuServicio;
	
	@Autowired
	private RolServicioimp rolSerImp;
	
	@Autowired
	private EmailServiceImpl mailService;
	
	private final Logger logger = LoggerFactory.getLogger(UsuarioControlador.class);
	
	@GetMapping("/cdsp")
	public String home(Model modelo) {
		
		List <Menu> menus= this.menuServicio.listarMenu();
		modelo.addAttribute("listaMenu", menus);
		return "index";
	}
	
	@GetMapping("/cdsp/nosotros")
	public String nosotros(Model modelo) {
		
		return "home/nosotros";
	}
	
	@GetMapping("/cdsp/send")
	public String sendMailTest() {
		mailService.sendMailTest(null);
		return "index";
		
		}
	@GetMapping("/cdsp/registro")
	public String registrarseCliente() {
		return "usuarios/registrarse";
	}
	@GetMapping("/cdsp/login")
	public String iniciosesion() {
		return "usuarios/iniciosesion";
	}
	@PostMapping("/cdsp/acceder")
	public String acceder(Usuario usuario, HttpSession sesion) {
		logger.info("accesos : {}", usuario);
		try {
		Optional<Usuario> user = this.usuarioServicio.obtenerPorCorreoContrasenia(usuario.getCorreo(), usuario.getContrasenia());
		logger.info("Usuario obtenido db correo{}", user.get().getCorreo());
		logger.info("Usuario obtenido db {} contrasenia", user.get().getContrasenia());
		logger.info("Usuario obtenido db {}", user.get());
		
		if(user.isPresent()) {
			
			sesion.setAttribute("idusuario", user.get().getId());
			if (user.get().getListaRoles().contains(this.rolSerImp.obtenerId(1))) {
				return "redirect:/cdsp/inicio";
			}else if(user.get().getListaRoles().contains(this.rolSerImp.obtenerId(2))){
				return "redirect:/cdsp/cliente/";
			}else if(user.get().getListaRoles().contains(this.rolSerImp.obtenerId(3))){
				return "redirect:/cdsp/pedidosm/";
			}else if(user.get().getListaRoles().contains(this.rolSerImp.obtenerId(4))){
				return "redirect:/cdsp/inicioDomiciliario/";
			}else if(user.get().getListaRoles().contains(this.rolSerImp.obtenerId(5))){
				return "redirect:/cdsp/cocina/pedidos";
			}else {
				return "redirect:/cdsp";
			}
		}else {
			logger.info("usuario no existe");
			return "redirect:/cdsp/login?error";		
		}
	}catch(Exception e) {
		return "redirect:/cdsp/login?error";
		
	}
		
	}
	@GetMapping("/cdsp/inicio")
	public String inicioAdministrador(HttpSession sesion) {
		
		logger.info("Sesion del usuarios: {}", sesion.getAttribute("idusuario"));
		return "administrador/bienvenida";
	}
	@GetMapping("/cdsp/iniciom")
	public String inicioMesero(HttpSession sesion) {
		
		logger.info("Sesion del usuarios: {}", sesion.getAttribute("idusuario"));
		return "mesero/bienvenida";
	}
	
	@GetMapping("/cdsp/inicioDomiciliario")
	public String inicioDomiciliario() {
	return "CONTACTENOSCR.html";
	
	}
	@GetMapping("/cdsp/iniciococina")
	public String inicioJefeCocina(HttpSession sesion) {
		
		logger.info("Sesion del usuarios: {}", sesion.getAttribute("idusuario"));
		return "jefecocina/bienvenida";
	}
	
	@GetMapping("/cdsp/miperfil")
	public String miPerfil(HttpSession sesion, Model modelo) {
		
		Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
  		List <Rol> roles = usuario.getListaRoles();
  		modelo.addAttribute("roles", roles);
		modelo.addAttribute("usuario", usuario);
		return "pedidosventas/miperfil";
	}
	
	@GetMapping("/cdsp/pedidosm/miperfil")
  	public String miPerfilm(HttpSession sesion, Model modelo) {
  		
		Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
  		List <Rol> roles = usuario.getListaRoles();
  		modelo.addAttribute("roles", roles);
  		modelo.addAttribute("usuario", usuario);
  		return "mesero/miperfil";
  	}
	
	@GetMapping("/cdsp/cliente/miperfil")
  	public String miPerfilc(HttpSession sesion, Model modelo) {
  		
  		Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
  		List <Rol> roles = usuario.getListaRoles();
  		modelo.addAttribute("roles", roles);
  		modelo.addAttribute("usuario", usuario);
  		return "cliente/miperfil";
  	}
	
	@GetMapping("/cdsp/jefecocina/miperfil")
  	public String miPerfilj(HttpSession sesion, Model modelo) {
  		
  		Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
  		List <Rol> roles = usuario.getListaRoles();
  		modelo.addAttribute("roles", roles);
  		modelo.addAttribute("usuario", usuario);
  		return "jefecocina/miperfil";
  	}
	@GetMapping("/cdsp/domiciliario/miperfil")
	public String miPerfilD(HttpSession sesion, Model modelo) {
		
		Usuario usuario = this.usuarioServicio.obtenerId(Long.parseLong(sesion.getAttribute("idusuario").toString()));
		List <Rol> roles = usuario.getListaRoles();
		modelo.addAttribute("roles", roles);
		modelo.addAttribute("usuario", usuario);
		return "domiciliario/miperfil";
	}
	
	@GetMapping("/cdsp/cerrar")
	public String cerrarSesion(HttpSession sesion) {
	
		sesion.removeAttribute("idusuario");
		return"redirect:/cdsp";
	}

}
