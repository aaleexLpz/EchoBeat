package com.echobeat.model;

import java.util.List;
import java.util.Objects;

public class Cancion implements Comparable<Cancion>{
	
	private int idCancion;
	private String titulo; //
	private String rutaCancion; //input type=file
	private int duracion; //
	private Genero genero; //
	private int anho; //
	private Album album;
	private boolean publica; //
	private List<Interprete> interpretes;
	private Usuario propietario;

	public Cancion(int idCancion, String titulo, String rutaCancion, int duracion, Genero genero, int anho, Album album,
			boolean publica, List<Interprete> interpretes, Usuario propietario) {
		this.idCancion = idCancion;
		this.titulo = titulo;
		this.rutaCancion = rutaCancion;
		this.duracion = duracion;
		this.genero = genero;
		this.anho = anho;
		this.album = album;
		this.publica = publica;
		this.interpretes = interpretes;
		this.propietario = propietario;
	}
	
	public Cancion(String titulo, String rutaCancion, int duracion, Genero genero, int anho, Album album,
			boolean publica, List<Interprete> interpretes, Usuario propietario) {
		super();
		this.titulo = titulo;
		this.rutaCancion = rutaCancion;
		this.duracion = duracion;
		this.genero = genero;
		this.anho = anho;
		this.album = album;
		this.publica = publica;
		this.interpretes = interpretes;
		this.propietario = propietario;
	}



	public Cancion(int idCancion2, String titulo2, String rutaCancion, int duracion2, Genero genero2, int anho2, boolean publica2, Usuario usuario) {
		this.idCancion = idCancion2;
		this.titulo = titulo2;
		this.rutaCancion = rutaCancion;
		this.duracion = duracion2;
		this.genero = genero2;
		this.anho = anho2;
		this.publica = publica2;
		this.propietario = usuario;
	}

	public int getIdCancion() {
		return idCancion;
	}

	public void setIdCancion(int idCancion) {
		this.idCancion = idCancion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public int getAnho() {
		return anho;
	}

	public void setAnho(int anho) {
		this.anho = anho;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public boolean isPublica() {
		return publica;
	}

	public void setPublica(boolean publica) {
		this.publica = publica;
	}

	public List<Interprete> getInterpretes() {
		return interpretes;
	}

	public void setInterpretes(List<Interprete> interpretes) {
		this.interpretes = interpretes;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public String getRutaCancion() {
		return rutaCancion;
	}

	public void setRutaCancion(String rutaCancion) {
		this.rutaCancion = rutaCancion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCancion);
	}

	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj != null && obj instanceof Cancion) {
			Cancion otra = (Cancion) obj;
			iguales = this.idCancion == otra.idCancion;
		}
		return iguales;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cancion [idCancion=").append(idCancion).append(", titulo=").append(titulo).append(", duracion=")
				.append(duracion).append(", genero=").append(genero).append(", anho=").append(anho).append(", album=")
				.append(album != null ? album.getNombre() : "null").append(", publica=").append(publica).append(", interpretes=")
				.append(interpretes).append(", propietario=").append(propietario).append("]");
		return builder.toString();
	}
	
	public String convertirDuracion() {
		int minutos = this.duracion / 60;
		int segundos = this.duracion % 60;
		return minutos + ":" + segundos;
	}

	@Override
	public int compareTo(Cancion otra) {
		return this.titulo.compareTo(otra.titulo);
	}
	
}
