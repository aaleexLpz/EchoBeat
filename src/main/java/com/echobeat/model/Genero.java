package com.echobeat.model;

public enum Genero {
	ROCK ("Rock"),
	POP ("Pop"),
	RAP ("Rap"),
	REGGAETON ("Reggaeton"),
	CLASICA ("Clásica"),
	FOLK  ("Folk"),
	METAL ("Metal"),
	BSO ("Banda Sonora Original"),
	INDIE ("Indie"),
	JAZZ ("Jazz"),
	ELECTRONICA ("Electrónica"),
	COUNTRY ("Country"),
	SALSA ("Salsa"),
	BLUES ("Blues"),
	PUNK ("Punk"),
	CUMBIA ("Cumbia"),
	TANGO ("Tango"),
	BACHATA ("Bachata"),
	MERENGUE ("Merengue"),
	HOUSE ("House"),
	TECHNO ("Techno"),
	REGGAE ("Reggae"),
	TRAP ("Trap");
	
	private String estilo;
	
	private Genero(String estilo) {
		this.estilo = estilo;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	
}
