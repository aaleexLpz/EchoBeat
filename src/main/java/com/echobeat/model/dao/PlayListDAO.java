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
			
			if(resultado.next()) {
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
}
