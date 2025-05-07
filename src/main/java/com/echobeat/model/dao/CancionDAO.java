package com.echobeat.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.echobeat.model.Cancion;
import com.echobeat.model.Genero;
import com.echobeat.model.TipoUsuario;
import com.echobeat.model.Usuario;

public class CancionDAO {

	private DataSource ds; 
	
	public CancionDAO() {
		try {
			Context contexto = new InitialContext();
			this.ds = (DataSource) contexto.lookup("java:comp/env/jdbc/EchoBeatDS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void insertarCancion(Cancion cancion) throws SQLException {
		try(Connection conexion = this.ds.getConnection()){
			
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
	
	public List<Cancion> buscarCancionesPorTitulo(String titulo, Usuario usuario) throws SQLException {
		
		List<Cancion> canciones = new ArrayList<>();
		Cancion cancion = null;
		
		try(
				Connection conexion = this.ds.getConnection();	
			){
			
			PreparedStatement sentencia = conexion.prepareStatement(
					"select * from cancion C join usuario U on C.idUsuario = U.idUsuario where titulo like ? and (U.idUsuario = ? or publica = true)"
					);
			
			sentencia.setString(1, "%" + titulo + "%");
			sentencia.setInt(2, usuario.getIdUsuario());
			System.out.println(sentencia);
			ResultSet resultado = sentencia.executeQuery();
			
			while(resultado.next()) {
				int idCancion = resultado.getInt("idCancion");
				String tituloCancion = resultado.getString("titulo");
				String rutaCancion = resultado.getString("cancion");
				int duracion = resultado.getInt("duracion");
				Genero genero = Genero.valueOf(resultado.getString("genero"));
				int anho = resultado.getInt("anho");
				boolean publica = resultado.getBoolean("publica");
				// int idUsuario = resultado.getInt("idUsuario");
				// int idAlbum = resultado.getInt("idAlbum");
				
				
				int idUsuario = resultado.getInt("U.idUsuario");
				String nombreUsuario = resultado.getString("U.nombreUsuario");
				Usuario propietario = new Usuario(idUsuario, nombreUsuario, null, null, false, null, null, null);
				cancion = new Cancion(idCancion, tituloCancion, rutaCancion, duracion, genero, anho, publica, propietario);
				canciones.add(cancion);
			}
			
		}
		return canciones;
	}
	
}
