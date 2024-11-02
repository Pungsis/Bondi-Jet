package com.bondijet.vuelo;

import java.util.Random;

public class VueloPublicoInternacional extends VueloPublico {
	
	private int cantRefrigerios;
	private String[] escalas;
		
	public VueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {
		super(origen, destino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos);
		this.cantRefrigerios = cantRefrigerios;
		this.escalas = escalas;
		
	}	
	
	
	
	@Override
	public String devolverCodigo() {
		// TODO Auto-generated method stub
		return this.codigo;
	}
	
	@Override
	public String toString() {
		StringBuilder detalles = new StringBuilder();
		detalles.append(this.codigo + " - " + this.origen + " - " + this.destino + " - " + this.fecha + " - INTERNACIONAL" );
		return detalles.toString();
	}

	@Override
	public int devolverCantRefregerios() {
		// TODO Auto-generated method stub
		return this.cantRefrigerios;
	}


}
