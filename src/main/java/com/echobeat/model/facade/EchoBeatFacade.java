package com.echobeat.model.facade;

import java.sql.SQLException;
import java.util.List;

import com.echobeat.model.Cancion;
import com.echobeat.model.PlayList;
import com.echobeat.model.Usuario;
import com.echobeat.model.dao.CancionDAO;
import com.echobeat.model.dao.UsuarioDAO;
import com.echobeat.music.bd.BaseDatos;

public class EchoBeatFacade {

	/* El método devolverá un usuario si los datos son correctos o null si no lo son*/
	public Usuario iniciarSesion(String nombreUsuario, String clave) {
		//BaseDatos bd = BaseDatos.getInstance();
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario;
		try {
			usuario = dao.buscarUsuarioPorNombreUsuario(nombreUsuario);
			
			if (usuario != null) { //El nombreUsuario existe en la BD
				
			if (usuario.getClave().equals(clave)) { //Las claves coinciden
				
				return usuario;
				
			} else {
				
				return null;
				
			}
			
		} else {
			
			return null;
			
		}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		
		}
		
	}

	public boolean registrarUsuario(Usuario usuario) {
		//BaseDatos bd = BaseDatos.getInstance();
		UsuarioDAO dao = new UsuarioDAO();
		try {
			//bd.insertarUsuario(usuario);
			dao.insertarUsuario(usuario);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void subirCancion(Cancion cancion) {
		//BaseDatos bd = BaseDatos.getInstance();
		CancionDAO dao = new CancionDAO();
		try {
			dao.insertarCancion(cancion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Cancion> buscarCancionesPorTitulo(String titulo) {
		BaseDatos bd = BaseDatos.getInstance();
		return bd.buscarCancionesPorTitulo(titulo);
	}

	public void crearPlaylist(PlayList playList) {
		BaseDatos bd = BaseDatos.getInstance();
		bd.insertarPlayList(playList);
		
	}
	
}
