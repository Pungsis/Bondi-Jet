package com.bondijet.vuelo;

public class VueloPublicoNacional extends VueloPublico {

	public VueloPublicoNacional(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio,
			double[] precios, int[] cantAsientos) {
		super(origen, destino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String devolverCodigo() {
		// TODO Auto-generated method stub
		return this.codigo;
	}
	
	public String toString() {
		StringBuilder detalles = new StringBuilder();
		detalles.append(this.codigo + " - " + this.origen + " - " + this.destino + " - " + this.fecha + " - NACIONAL" );
		return detalles.toString();
	}


}
