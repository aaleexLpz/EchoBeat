package com.echobeat.model;

import java.util.List;
import java.util.Objects;

public class Cancion implements Comparable<Cancion>{

	private int idCancion;
	private String titulo;
	private int duracion;
	private Genero genero;
	private int anho;
	private Album album;
	private boolean publica;
	private List<Interprete> interprete;
	private Usuario usuario;
	
	public Cancion(int idCancion, String titulo, int duracion, Genero genero, int anho, Album album, boolean publica, List<Interprete> interprete, Usuario usuario) {
		this.idCancion = idCancion;
		this.titulo = titulo;
		this.duracion = duracion;
		this.genero = genero;
		this.anho = anho;
		this.album = album;
		this.publica = publica;
		this.interprete = interprete;
		this.usuario = usuario;
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

	public boolean getPublica() {
		return publica;
	}

	public void setPublica(boolean publica) {
		this.publica = publica;
	}

	public List<Interprete> getInterprete() {
		return interprete;
	}

	public void setInterprete(List<Interprete> interprete) {
		this.interprete = interprete;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCancion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cancion other = (Cancion) obj;
		return idCancion == other.idCancion;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nID de la canción: ")
		.append(getIdCancion())
		.append("\nTítulo: ")
		.append(getTitulo())
		.append("\nDuración: ")
		.append(getDuracion())
		.append("\nGénero: ")
		.append(getGenero())
		.append("\nAño de salida: ")
		.append(getAnho())
		.append("\nÁlbum: ")
		.append(album != null ? album.getNombre() : "No hay ningún álbum")
		.append("\nEs pública? ")
		.append(getPublica())
		.append("\nIntérpretes: ")
		.append(getInterprete())
		.append("\nPropietario: ")
		.append(usuario.getNombreUsuario());
		return builder.toString();
	}
	
	public String convertirDuracion() {
		int min = this.duracion / 60;
		int seg = this.duracion % 60;
		return min + ":" + seg;
	}

	@Override
	public int compareTo(Cancion otra) {
		return this.titulo.compareTo(otra.titulo);
	}
	
}
