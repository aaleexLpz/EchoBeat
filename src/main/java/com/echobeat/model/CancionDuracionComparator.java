package com.echobeat.model;

import java.util.Comparator;

public class CancionDuracionComparator implements Comparator<Cancion>{

	@Override
	public int compare(Cancion cancion1, Cancion cancion2) {
		return cancion1.getDuracion() - cancion2.getDuracion();
	}
	
}
