package com.echobeat.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Album {

	private int idAlbum;
	private String nombre;
	private LocalDate fechaPublicacion;
	private String comentario;
	private Interprete interprete;
	private List<Cancion> canciones;
	
	public Album(int idAlbum, String nombre, LocalDate fechaPublicacion, String comentario, Interprete interprete,
			List<Cancion> canciones) {
		this.idAlbum = idAlbum;
		this.nombre = nombre;
		this.fechaPublicacion = fechaPublicacion;
		this.comentario = comentario;
		this.interprete = interprete;
		this.canciones = canciones;
	}

	public int getIdAlbum() {
		return idAlbum;
	}
	
	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}
	
	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public List<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}
	
	public Interprete getInterprete() {
		return interprete;
	}

	public void setInterprete(Interprete interprete) {
		this.interprete = interprete;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAlbum);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		return idAlbum == other.idAlbum;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Album [idAlbum=").append(idAlbum).append(", nombre=").append(nombre)
				.append(", fechaPublicacion=").append(fechaPublicacion).append(", comentario=").append(comentario)
				.append(", interprete=").append(interprete).append(", canciones=").append(canciones)
				.append("]");
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
		if(!this.canciones.contains(cancion)) {
			this.canciones.add(cancion);
		}
	}
	
}
