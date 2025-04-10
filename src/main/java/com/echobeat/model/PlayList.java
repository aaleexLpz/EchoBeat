package com.echobeat.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class PlayList {

	private int idPlayList;
	private String nombre;
	private String portada;
	private boolean publica;
	private List<Cancion> canciones;
	private Usuario propietario;
	private Set<Usuario> seguidores; // El orden de los seguidores no es relevante 
	
	public PlayList(int idPlayList, String nombre, String portada, boolean publica, List<Cancion> canciones,
			Usuario propietario, Set<Usuario> seguidores) {
		this.idPlayList = idPlayList;
		this.nombre = nombre;
		this.portada = portada;
		this.publica = publica;
		this.canciones = new ArrayList<Cancion>();
		this.propietario = propietario;
		this.seguidores = new HashSet<Usuario>();
	}

	public int getIdPlayList() {
		return idPlayList;
	}

	public void setIdPlayList(int idPlayList) {
		this.idPlayList = idPlayList;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public boolean isPublica() {
		return publica;
	}

	public void setPublica(boolean publica) {
		this.publica = publica;
	}

	public List<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public Set<Usuario> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(Set<Usuario> seguidores) {
		this.seguidores = seguidores;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPlayList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayList other = (PlayList) obj;
		return idPlayList == other.idPlayList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlayList [idPlayList=").append(idPlayList).append(", nombre=").append(nombre)
				.append(", portada=").append(portada).append(", publica=").append(publica).append(", canciones=")
				.append(canciones).append(", propietario=").append(propietario).append(", seguidores=")
				.append(seguidores).append("]");
		return builder.toString();
	}
	
	public int calcularDuracion() {
		int total = 0;
		for(Cancion cancion : this.canciones) {
			total = total + cancion.getDuracion();
		}
		return total;
	}
	
	public int numeroCanciones() {
		return this.canciones.size();
	}
	
	public String convertirDuracion() {
		int duracion = calcularDuracion();
		int horas = duracion / (60 * 60);
		int min = (duracion % (60 * 60)) / 60;
		int seg = (duracion % (60 * 60)) % 60;
		return horas + ":" + min + ":" + seg;
	}
	
	public void insertarCancion(Cancion cancion) {
		this.canciones.add(cancion);
	}
	
	public void eliminarCancion(int posicion) {
		this.canciones.remove(posicion);
	}
	
	// Eliminar todas las instancias de la cancion en la PlayList
	public void eliminarCancion(Cancion cancion) {
		boolean eliminada = true;
		while(eliminada) {
			eliminada = this.canciones.remove(cancion);
		}
	}
	
	public void insertarSeguidor(Usuario seguidor) {
		this.seguidores.add(seguidor);
	}
	
	public void eliminarSeguidor(Usuario seguidor) {
		this.seguidores.remove(seguidor);
	}
	
}
