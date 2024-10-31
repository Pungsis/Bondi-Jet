package com.bondijet.cliente;

import java.util.ArrayList;
import java.util.HashMap;

import com.bondijet.pasaje.Pasaje;

public class Cliente {
	private final int dni;
	private final String nombre;
	private final String telefono;
	private final HashMap<Integer, Pasaje> pasajes;
	
	public Cliente(int dni, String nombre, String telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.pasajes = new HashMap<Integer, Pasaje>();
	}
	
	public int devolverDni() {
		return this.dni;
	}
	
	public void adicionarPasaje(int nroPasaje, Pasaje p) {
		pasajes.put(nroPasaje, p);
	}
	
	public void removerPasaje(int i) {
		pasajes.remove(i);
	}
	
	public Pasaje devolverPasaje(int codigo) {
		return pasajes.get(codigo);
	}
	
	public String devolverDetalleCliente() {
		return this.dni + " - " + this.nombre + " - " + this.telefono;
	}
}
