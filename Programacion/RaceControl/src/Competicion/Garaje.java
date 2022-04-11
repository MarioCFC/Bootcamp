package Competicion;

import java.util.HashSet;

public class Garaje {
	private final String nombre;
	private HashSet<Coche> coches = new HashSet();

	public Garaje(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public HashSet<Coche> getCoches() {
		return coches;
	}

	public int numeroCoches() {
		return coches.size();
	}

	public Coche getCoche(int indice) throws Exception {
		int posicion = 0;

		for (Coche coche : coches) {
			if (posicion++ == indice)
				return coche;
		}
		throw new Exception("Error en el indice");


	}

	public void addCoche(Coche nuevoCoche) {
		coches.add(nuevoCoche);
	}
}
