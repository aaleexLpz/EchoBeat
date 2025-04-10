package com.echobeat.music;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Usuario {
	
	private int idUsuario;
	private String nombreUsuario;
	private String clave;
	private TipoUsuario tipoUsuario;
	private boolean deBaja;
	private String nombre;
	private String email;
	private LocalDate fechaNacimiento;
//	private Set<PlayList> playListsSeguidas;
	
	public Usuario(int idUsuario, String nombreUsuario, String clave, TipoUsuario tipoUsuario, boolean deBaja, String nombre, String email, LocalDate fechaNacimiento/*, Set<PlayList> playListsSeguidas*/) {
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.clave = clave;
		this.tipoUsuario = tipoUsuario.CLIENTE;
		this.deBaja = false;
		this.nombre = nombre;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
//		this.playListsSeguidas = playListsSeguidas;
	}
	
	public int getIdUsuario() {
		return this.idUsuario;
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getClave() {
		return this.clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public TipoUsuario getTipoUsuario() {
		return this.tipoUsuario;
	}
	
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
		
	public boolean getDeBaja() {
		return this.deBaja;
	}
	
	public void setDeBaja(boolean deBaja) {
		this.deBaja = deBaja;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getFechaNacimiento() {
		return this.fechaNacimiento;
	}
	
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
//	public Set<PlayList> getPlayListsSeguidas() {
//		return playListsSeguidas;
//	}
//
//	public void setPlayListsSeguidas(Set<PlayList> playListsSeguidas) {
//		this.playListsSeguidas = playListsSeguidas;
//	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario);
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if(obj != null && obj instanceof Usuario) {
			Usuario otro = (Usuario) obj;
			iguales = otro.idUsuario == this.idUsuario;
		}
		return iguales;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ID: ")
		.append(getIdUsuario())
		.append("\nNombre de usuario: ")
		.append(getNombreUsuario())
		.append("\nTipo de usuario: ")
		.append(getTipoUsuario())
		.append("\nEstá de baja? ")
		.append(getDeBaja())
		.append("\nNombre: ")
		.append(getNombre())
		.append("\nEmail: ")
		.append(getEmail())
		.append("\nFecha de Nacimiento: ")
		.append(getFechaNacimiento());
//		.append("\nPlayLists seguidas: ")
//		.append(getPlayListsSeguidas());
		
		return builder.toString();
	}
	
	public int calcularEdad() {
		LocalDate hoy = LocalDate.now();
		return this.fechaNacimiento.until(hoy).getYears();
	}
	
//	public void seguirPlayList(PlayList playList) {
//		this.playListsSeguidas.add(playList);
//	}
//	
//	public void eliminarPlayList(PlayList playList) {
//		this.playListsSeguidas.remove(playList);
//	}
}






















