package com.echobeat.music.bd;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.echobeat.model.TipoUsuario;
import com.echobeat.model.Usuario;
import com.echobeat.model.exception.NombreUsuarioRepetidoException;

public class BaseDatos {

	private Map<String, Usuario> usuarios;
	private static BaseDatos instancia;
	
	private BaseDatos() {
		this.usuarios = new HashMap<String, Usuario>();
		
		Usuario admin = new Usuario(0, "Admin", "1Q2W", TipoUsuario.ADMIN, false, "Administrador", "admin@echobeat.com", LocalDate.of(2003, 8, 2));
		
		this.usuarios.put(admin.getNombreUsuario(), admin);
	}
	
	public static BaseDatos getInstance() {
		if(BaseDatos.instancia == null) {
			BaseDatos.instancia = new BaseDatos();
		}
		return BaseDatos.instancia;
	}
	
	public static void guardarBD() {
		try(FileOutputStream fos = new FileOutputStream("baseDatos.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos)) {;
			
			oos.writeObject(BaseDatos.instancia.usuarios);
		
		}catch(IOException e) {
		
			e.printStackTrace();
		
		}
	}
	
	public static void cargarBD() {
		try(FileInputStream fis = new FileInputStream("baseDatos.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);){
			BaseDatos.instancia.usuarios =  (Map<String, Usuario>) ois.readObject();
		} catch(IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
			e.printStackTrace();
		}
	}
	
	public void insertarUsuario(Usuario usuario) throws NombreUsuarioRepetidoException {
		if(!this.usuarios.containsKey(usuario.getNombreUsuario())) {
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
	
	public List<Usuario> buscarUsuariosPorTipo(TipoUsuario tipoUsuario){
		List<Usuario> usuariosEncontrados = new ArrayList<>();
		for(Usuario u : this.usuarios.values()) {
			if(u.getTipoUsuario().equals(tipoUsuario)) {
				usuariosEncontrados.add(u);
			}
		}
		return usuariosEncontrados;
	}
	
}
