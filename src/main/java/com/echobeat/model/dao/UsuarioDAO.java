package com.echobeat.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.echobeat.model.TipoUsuario;
import com.echobeat.model.Usuario;

public class UsuarioDAO {

	private DataSource ds; 
	
	public UsuarioDAO() {
		try {
			Context contexto = new InitialContext();
			this.ds = (DataSource) contexto.lookup("java:comp/env/jdbc/EchoBeatDS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void insertarUsuario(Usuario usuario) throws SQLException {
		try (
			// 1º Obtener conexión del DriverManager
			Connection conexion = this.ds.getConnection()) {

			// 2º Preparar la sentencia desde la conexión
			PreparedStatement sentencia = conexion.prepareStatement(
					"insert into usuario (nombreUsuario, clave, tipoUsuario, deBaja, nombre, email, fechaNac) values (?, ?, ?, ?, ?, ?, ?)");

			// 3º Dar valor a los parámetros
			sentencia.setString(1, usuario.getNombreUsuario());
			sentencia.setString(2, usuario.getClave());
			sentencia.setObject(3, usuario.getTipoUsuario().name());
			sentencia.setBoolean(4, usuario.getDeBaja());
			sentencia.setString(5, usuario.getNombre());
			sentencia.setString(6, usuario.getEmail());
			sentencia.setDate(7, Date.valueOf(usuario.getFechaNacimiento()));

			// 4º Ejecutar la sentencia
			sentencia.executeUpdate();
		}

	}
	
	public Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario) throws SQLException {
		
		Usuario usuario = null;

		try(
				
				// 1º Obtener la conexión
				Connection conexion = this.ds.getConnection();
			){
			
			// 2º Preparar la sentencia
			PreparedStatement sentencia = conexion.prepareStatement(
					"select * from usuario where nombreUsuario = ?");
			
			// 3º Dar valor al parámetro (?)
			sentencia.setString(1, nombreUsuario);
			
			// 4º Ejecutar la sentencia
			ResultSet resultado = sentencia.executeQuery();
			
			// 5º Procesar el resultado
			if(resultado.next()) {
				
				int idUsuario = resultado.getInt("idUsuario");
				// String nombreUsuario = resultado.getString("nombreUsuario");
				String clave = resultado.getString("clave");
				TipoUsuario tipoUsuario = TipoUsuario.valueOf(resultado.getString("tipoUsuario"));
				boolean deBaja = resultado.getBoolean("deBaja");
				String nombre = resultado.getString("nombre");
				String email = resultado.getString("email");
				LocalDate fechaNacimiento = resultado.getDate("fechaNac").toLocalDate();
				
				usuario = new Usuario(idUsuario, nombreUsuario, clave, tipoUsuario, deBaja, nombre, email, fechaNacimiento);
			}
		}
		return usuario;
	}
	
	public boolean modificarUsuario(Usuario usuario) throws SQLException {
		
		try(
			Connection conexion = this.ds.getConnection()){
			
			PreparedStatement sentencia = conexion.prepareStatement(
					"update usuario set clave = ? and deBaja = ? and nombre = ? and email = ? fechaNac = ? where idUsuario = ?"
					);
			
			sentencia.setString(1, usuario.getClave());
			sentencia.setBoolean(2, usuario.getDeBaja());
			sentencia.setString(3, usuario.getNombre());
			sentencia.setString(4, usuario.getEmail());
			sentencia.setDate(5, Date.valueOf(usuario.getFechaNacimiento()));
			sentencia.setInt(6, usuario.getIdUsuario());
			
			int numFilas = sentencia.executeUpdate();
		}
		
		return false;
	}

}
