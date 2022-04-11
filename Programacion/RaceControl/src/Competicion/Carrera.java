package Competicion;

import java.util.HashMap;

public class Carrera {
	private final String nombre;
	private HashMap<Coche, Garaje> garajes = new HashMap();

	private HashMap<Coche, Double> podio = new HashMap();

	public Carrera(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public HashMap<Coche, Garaje> getGarajes() {
		return garajes;
	}

	public HashMap<Coche, Double> getPodio() {
		return podio;
	}

	public void correr() {
		// TODO:Implementar
	}
}
