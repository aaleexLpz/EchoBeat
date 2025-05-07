package com.echobeat.model.facade;

import java.sql.SQLException;
import java.util.List;

import com.echobeat.model.Cancion;
import com.echobeat.model.PlayList;
import com.echobeat.model.Usuario;
import com.echobeat.model.dao.CancionDAO;
import com.echobeat.model.dao.PlayListDAO;
import com.echobeat.model.dao.UsuarioDAO;
import com.echobeat.model.exception.ErrorInternoException;
import com.echobeat.model.exception.NombreUsuarioRepetidoException;
import com.echobeat.model.exception.SinConexionException;

public class EchoBeatFacade {

	private static void erroresBD(SQLException e) throws SinConexionException, ErrorInternoException {
		if(e.getErrorCode() == 0 && e.getSQLState().equals("08S01")) {
			throw new SinConexionException("No se ha podido conectar con el servidor");
		} else {
			throw new ErrorInternoException("Error al iniciar sesión: " + e.getErrorCode() + " SQLState: " + e.getSQLState());
		}
	}
	
	/* El método devolverá un usuario si los datos son correctos o null si no lo son */
	public Usuario iniciarSesion(String nombreUsuario, String clave) throws SinConexionException, ErrorInternoException {
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
			EchoBeatFacade.erroresBD(e);
			return null;
		}
		
	}

	public boolean registrarUsuario(Usuario usuario) throws NombreUsuarioRepetidoException, ErrorInternoException, SinConexionException {
		//BaseDatos bd = BaseDatos.getInstance();
		UsuarioDAO dao = new UsuarioDAO();
		try {
			//bd.insertarUsuario(usuario);
			dao.insertarUsuario(usuario);
			return true;
		} catch (SQLException e) {
			if(e.getErrorCode() == 1062 && e.getSQLState().equals("23000")) {
				throw new NombreUsuarioRepetidoException("Ese nombre está ocupado.");
			} else {
				EchoBeatFacade.erroresBD(e);
				return false;
			}
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
	
	public List<Cancion> buscarCancionesPorTitulo(String titulo, Usuario usuario) {
		// BaseDatos bd = BaseDatos.getInstance();
		CancionDAO dao = new CancionDAO();
		try {
			return dao.buscarCancionesPorTitulo(titulo, usuario);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void crearPlaylist(PlayList playList) {
		// BaseDatos bd = BaseDatos.getInstance();
		PlayListDAO dao = new PlayListDAO();
		try {
			dao.insertarPlayList(playList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<PlayList> buscarPlayListPorUsuario(Usuario usuario){
		PlayListDAO dao = new PlayListDAO();
		List<PlayList> lista = null;
		try {
			lista = dao.buscarPlayListPorUsuario(usuario);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
