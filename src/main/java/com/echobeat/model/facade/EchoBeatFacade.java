package com.echobeat.model.facade;

import com.echobeat.model.Usuario;
import com.echobeat.model.exception.NombreUsuarioRepetidoException;
import com.echobeat.music.bd.BaseDatos;

public class EchoBeatFacade {

	// El método devolverá un usuario si los datos son correctos o null si no lo son
	public Usuario iniciarSesion(String username, String password) {
		BaseDatos bd = BaseDatos.getInstance();
		Usuario usuario = bd.buscarUsuarioPorNombreUsuario(username);
		if(usuario != null) { // El usuario existe en la bbdd
			if(usuario.getClave().equals(password)){ // Las claves coinciden
				return usuario;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public boolean registrarUsuario(Usuario usuario) {
		BaseDatos bd = BaseDatos.getInstance();
		try {
			bd.insertarUsuario(usuario);
			return true;
		} catch (NombreUsuarioRepetidoException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
}
