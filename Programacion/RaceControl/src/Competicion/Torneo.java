package Competicion;

import java.util.ArrayList;
import java.util.HashSet;

public class Torneo {
	private String nombre;
	private ArrayList<Carrera> carreras = new ArrayList();
	private HashSet<Garaje> garajes = new HashSet();

	public String getNombre() {
		return nombre;
	}


	public ArrayList<Carrera> getCarreras() {
		return carreras;
	}



}
