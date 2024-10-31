package com.bondijet.vuelo;

import java.util.ArrayList;
import java.util.HashMap;

import com.bondijet.asiento.Asiento;
import com.bondijet.pasaje.Pasaje;

public abstract class Vuelo {
	protected String codigo;
	protected String origen; 
	protected String destino; 
	protected String fecha; 
	protected int tripulantes;
	
	public Vuelo(String origen, String destino, String fecha, int tripulantes) {
		this.origen = origen;
		this.destino = destino;
		this.fecha = fecha;
		this.tripulantes = tripulantes;
	}
	
	public abstract String devolverCodigo();
	public abstract HashMap<Integer, Asiento> devolverListaAsientos(); 
	public abstract String devolverDestino();
	public abstract String devolverOrigen();
	public abstract String devolverFecha();
	public abstract ArrayList<Pasaje> cancelarVuelo();
	
}
