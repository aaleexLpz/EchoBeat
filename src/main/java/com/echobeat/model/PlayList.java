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
	private Usuario propietario;
	private Set<Usuario> seguidores;
	private List<Cancion> canciones;
	
	public PlayList(int idPlayList, String nombre, String portada, boolean publica, Usuario propietario, Set<Usuario> seguidores, List<Cancion> canciones) {
		this.idPlayList = idPlayList;
		this.nombre = nombre;
		this.portada = portada;
		this.publica = publica;
		this.propietario = propietario;
		this.seguidores = seguidores;
		this.canciones = canciones;
	}

	public PlayList(String nombre, String portada, boolean publica, Usuario propietario) {
		this.nombre = nombre;
		this.portada = portada;
		this.publica = publica;
		this.propietario = propietario;
		this.canciones = new ArrayList<Cancion>();
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

	public List<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPlayList);
	}

	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj != null && obj instanceof PlayList) {
			PlayList otra = (PlayList) obj;
			iguales = this.idPlayList == otra.idPlayList;
		}
		return iguales;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlayList [idPlayList=").append(idPlayList).append(", nombre=").append(nombre)
				.append(", portada=").append(portada).append(", publica=").append(publica).append(", propietario=")
				.append(propietario).append(", seguidores=").append(seguidores).append(", canciones=")
				.append(canciones).append("]");
		return builder.toString();
	}
	
	public int calcularDuracion() {
		int total = 0;
		for (Cancion cancion : this.canciones) {
			total = total + cancion.getDuracion();
		}
		return total;
	}
	
	public int numeroCanciones() {
		return this.canciones.size();
	}
	
	public String convertirDuracion() {
		int duracion = calcularDuracion();
		int horas = duracion / (60*60);
		int minutos = (duracion % (60*60)) / 60;
		int segundos = (duracion % (60*60)) % 60;
		return horas + ":" + minutos + ":" + segundos;
	}
	
	public void insertarCancion(Cancion cancion) {
		this.canciones.add(cancion);
	}
	
	public void eliminarCancion(int posicion) {
		this.canciones.remove(posicion);
	}
	
	public void eliminarCancion(Cancion cancion) {
		while (this.canciones.remove(cancion)) {}
//		boolean eliminada = true;
//		while (eliminada) {
//			eliminada = this.canciones.remove(cancion);
//		}
	}
	
	public void insertarSeguidor(Usuario seguidor) {
		this.seguidores.add(seguidor);
	}
	
	public void eliminarSeguidor(Usuario seguidor) {
		this.seguidores.remove(seguidor);
	}
}
