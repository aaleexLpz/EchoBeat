package com.echobeat.music;

import java.time.LocalDate;
import java.util.Objects;

public class Interprete {

	private String nombre;
	private LocalDate fechaNacimiento;
	private String comentario;
	
	public Interprete(String nombre, LocalDate fechaNacimiento, String comentario) {
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.comentario = comentario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interprete other = (Interprete) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Interprete [nombre=").append(nombre).append(", fechaNacimiento=").append(fechaNacimiento)
				.append(", comentario=").append(comentario).append("]");
		return builder.toString();
	}
	
}
