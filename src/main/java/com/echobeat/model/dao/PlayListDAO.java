package com.echobeat.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.echobeat.model.PlayList;
import com.echobeat.model.Usuario;

public class PlayListDAO {

	private DataSource ds; 
	
	public PlayListDAO() {
		try {
			Context contexto = new InitialContext();
			this.ds = (DataSource) contexto.lookup("java:comp/env/jdbc/EchoBeatDS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void insertarPlayList(PlayList playList) throws SQLException {
		try (Connection conexion = this.ds.getConnection()) {

			PreparedStatement sentencia = conexion.prepareStatement(
					"insert into playList (nombre, portada, publica, idUsuario) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			sentencia.setString(1, playList.getNombre());
			sentencia.setString(2, playList.getPortada());
			sentencia.setBoolean(3, playList.isPublica());
			sentencia.setInt(4, playList.getPropietario().getIdUsuario());

			sentencia.executeUpdate();
			
			ResultSet claves = sentencia.getGeneratedKeys();
			
			if(claves.next()) {
				int idPlayList = claves.getInt(1);
				playList.setIdPlayList(idPlayList);
			}
		}
	}
	
	public List<PlayList> buscarPlayListPorUsuario(Usuario usuario) throws SQLException{
		List<PlayList> lista = new ArrayList<>();
		try(Connection conexion = this.ds.getConnection()){
			PreparedStatement sentencia = conexion.prepareStatement(
					"select * from playlist where idUsuario = ?"
					);
			sentencia.setInt(1, usuario.getIdUsuario());
			ResultSet resultado = sentencia.executeQuery();
			
			while(resultado.next()) {
				int idPlayList = resultado.getInt("idPlayList");
				String nombre = resultado.getString("nombre");
				String portada = resultado.getString("portada");
				boolean publica = resultado.getBoolean("publica");
				
				PlayList playList = new PlayList(idPlayList, nombre, portada, publica, usuario, null, null);
				lista.add(playList);
			}
		}
		return lista;
	}
	
	public void insertarCancionPlayList(int idCancion, int idPlayList) throws SQLException {
		
		try(Connection conexion = this.ds.getConnection()){
			PreparedStatement sentencia1 = conexion.prepareStatement(
					"select max(posicion) as posicion from playListCancion where idPlayList = ?"
					);
			sentencia1.setInt(1, idPlayList);
			ResultSet resultado1 = sentencia1.executeQuery();
			int posicion = 0;
			if(resultado1.next()) {
				posicion = resultado1.getInt("posicion");
			}
			PreparedStatement sentencia2 = conexion.prepareStatement(
					"insert into playListCancion (idPlayList, idCancion, posicion) values (?, ?, ?)"
					);
			sentencia2.setInt(1, idPlayList);
			sentencia2.setInt(2, idCancion);
			sentencia2.setInt(3, posicion + 1);
			sentencia2.executeUpdate();
		}
		
	}

	public List<PlayList> buscarPlayListPorSeguidor(Usuario usuario) throws SQLException {
		List<PlayList> lista = new ArrayList<>();
		try(Connection conexion = this.ds.getConnection()){
			PreparedStatement sentencia = conexion.prepareStatement(
					"select * from seguidor S join playList P on S.idPlayList = P.idPlayList join Usuario U on U.idUsuario = P.idUsuario where S.idUsuario = ?"
					);
			sentencia.setInt(1, usuario.getIdUsuario());
			ResultSet resultado = sentencia.executeQuery();
			while(resultado.next()) {
				int idPlayList = resultado.getInt("P.idPlayList");
				String nombre = resultado.getString("P.nombre");
				String portada = resultado.getString("P.portada");
				boolean publica = resultado.getBoolean("P.publica");
				
				int idUsuario = resultado.getInt("U.idUsuario");
				String nombreUsuario = resultado.getString("U.nombreUsuario");
				
				Usuario propietario = new Usuario(idUsuario, nombreUsuario, null, null, false, null, null, null);
				PlayList playList = new PlayList(idPlayList, nombre, portada, publica, propietario, null, null);
				lista.add(playList);
			}
		}
		return lista;
	}
	
	
	
	
	
	
}











