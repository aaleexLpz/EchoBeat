package com.echobeat.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.echobeat.model.Cancion;

public class CancionDAO {

	private final static String URL_CONEXION = "jdbc:mysql://localhost:3306/echobeat?user=root";
	
	public CancionDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void insertarCancion(Cancion cancion) throws SQLException {
		try(Connection conexion = DriverManager.getConnection(URL_CONEXION);){
			
			PreparedStatement sentencia = conexion.prepareStatement(
					"insert into cancion (titulo, cancion, duracion, genero, anho, publica, idUsuario, idAlbum) values (?, ?, ?, ?, ?, ?, ?, ?)"
					);
			
			sentencia.setString(1, cancion.getTitulo());
			sentencia.setString(2, cancion.getRutaCancion());
			sentencia.setInt(3, cancion.getDuracion());
			sentencia.setString(4, cancion.getGenero().name());
			sentencia.setInt(5, cancion.getAnho());
			sentencia.setBoolean(6, cancion.isPublica());
			sentencia.setInt(7, cancion.getPropietario().getIdUsuario());
			sentencia.setNull(8, Types.INTEGER);
			
			sentencia.executeUpdate();
		}
	}
	
}
