package com.bondijet.asiento;

import java.util.AbstractMap;
import java.util.Map;

import com.bondijet.pasaje.Pasaje;

public class Asiento {
	private final int numero;
	private final String seccion;
	private boolean disponible;
	private Pasaje pasajeAsociado;
	
	public Asiento(int numero, String seccion) {
		this.seccion = seccion;
		this.numero = numero;
		this.disponible = true;
		this.pasajeAsociado = null;
	}
	
	public boolean estaDisponible() {
		return this.disponible;
	}
	
//	public Map.Entry<Integer, String> devolverAsiento() {
//		Map.Entry<Integer, String> tupla;
//		tupla = new AbstractMap.SimpleEntry<Integer, String>(this.numero, this.seccion);
//		return tupla;
//	}
	public int devolverNumeroAsiento() {
		return this.numero;
	}
	
	public void asociarPasaje(Pasaje p) {
		this.pasajeAsociado = p;
		p.ingresarSeccion(this.seccion);
	}
	
	public Pasaje devolverPasajeAsociado() {
		return this.pasajeAsociado;
	}
	
	public String devolverSeccion() {
		return this.seccion;
	}
	
	public void cambiarEstado() {
		this.disponible = !this.disponible;
	}
	
	public void removerPasajeAsociado() {
		this.pasajeAsociado = null;
	}
	}
