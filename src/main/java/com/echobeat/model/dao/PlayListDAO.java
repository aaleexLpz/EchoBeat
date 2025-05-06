package com.echobeat.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.echobeat.model.PlayList;

public class PlayListDAO {

	private final static String URL_CONEXION = "jdbc:mysql://localhost:3306/echobeat?user=root";

	public PlayListDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void insertarPlayList(PlayList playList) throws SQLException {
		try (Connection conexion = DriverManager.getConnection(URL_CONEXION);) {

			PreparedStatement sentencia = conexion.prepareStatement(
					"insert into playList (nombre, portada, publica, idUsuario) values (?, ?, ?, ?)");

			sentencia.setString(1, playList.getNombre());
			sentencia.setString(2, playList.getPortada());
			sentencia.setBoolean(3, playList.isPublica());
			sentencia.setInt(4, playList.getPropietario().getIdUsuario());

			sentencia.executeUpdate();
		}
	}
}
