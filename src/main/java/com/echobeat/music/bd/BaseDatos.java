package com.echobeat.music.bd;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.echobeat.model.Cancion;
import com.echobeat.model.PlayList;
import com.echobeat.model.TipoUsuario;
import com.echobeat.model.Usuario;
import com.echobeat.model.exception.NombreUsuarioRepetidoException;

public class BaseDatos {
	
	private Map<String, Usuario> usuarios;
	private Map<Integer, Cancion> canciones;
	private Map<Integer, PlayList> listas;
	
	private static BaseDatos instancia; 
	
	private BaseDatos() {
		this.usuarios = new HashMap<String, Usuario>();
		Usuario admin = new Usuario(0, "Admin", "1Q2W", TipoUsuario.ADMIN, false, "Administrador", "admin@echobeat.org", LocalDate.of(2000, 1, 1));
		this.usuarios.put(admin.getNombreUsuario(), admin);
		
		this.canciones = new HashMap<Integer, Cancion>();
		this.listas = new HashMap<Integer, PlayList>();
	}
	
	public static BaseDatos getInstance() {
		if (BaseDatos.instancia == null) {
			BaseDatos.instancia = new BaseDatos();
		}
		return BaseDatos.instancia;
	}
	
	public static void guardarBD() {
		try (FileOutputStream fos = new FileOutputStream("baseDatos.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(BaseDatos.instancia.usuarios);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void cargarBD() {
		try (FileInputStream fis = new FileInputStream("baseDatos.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			BaseDatos.instancia.usuarios = (Map<String, Usuario>) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void insertarCancion(Cancion cancion) {
		int idCancion;
		try {
			idCancion = Collections.max(this.canciones.keySet());
		} catch (NoSuchElementException e) {
			idCancion = 0;
		}
		this.canciones.put(idCancion+1, cancion);
	}
	
	public List<Cancion> buscarCancionesPorTitulo(String titulo) {
		List<Cancion> resultado = new ArrayList<Cancion>();
		for (Cancion cancion : this.canciones.values()) {
			if (cancion.getTitulo().contains(titulo)) {
				resultado.add(cancion);
			}
		}
		return resultado;
	}
	
	public void insertarUsuario(Usuario usuario) throws NombreUsuarioRepetidoException {
		if (!this.usuarios.containsKey(usuario.getNombreUsuario())) {
			this.usuarios.put(usuario.getNombreUsuario(), usuario);
		} else {
			throw new NombreUsuarioRepetidoException("El usuario " + usuario.getNombreUsuario() + " ya existe");
		}
	}
	
	public void borrarUsuario(Usuario usuario) {
		this.usuarios.remove(usuario.getNombreUsuario());
	}

	public void modificarUsuario(Usuario usuario) {
		this.usuarios.put(usuario.getNombreUsuario(), usuario);
	}
	
	public Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario) {
		return this.usuarios.get(nombreUsuario);
	}
	
	public List<Usuario> buscarUsuariosPorTipo(TipoUsuario tipoUsuario) {
		List<Usuario> resultado = new ArrayList<Usuario>();
		for (Usuario usuario : this.usuarios.values()) {
			if (usuario.getTipoUsuario().equals(tipoUsuario)) {
				resultado.add(usuario);
			}
		}
		return resultado;
	}

	public void insertarPlayList(PlayList playList) {
		int idPlayList;
		try {
			idPlayList = Collections.max(this.listas.keySet());
		} catch (NoSuchElementException e) {
			idPlayList = 0;
		}
		this.listas.put(idPlayList+1, playList);
		
	}
}