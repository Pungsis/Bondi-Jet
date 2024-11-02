package com.bondijet.pasaje;

public class Pasaje {
	
	private final int dni;
	private final String codVuelo;
	private final int nroAsiento;
	private final boolean aOcupar;
	private final int codigoPasaje;
	private String seccion;
	
	public Pasaje(int dni, String codVuelo, int nroAsiento, boolean aOcupar, int codigoPasaje) {
		this.dni = dni;
		this.codVuelo = codVuelo;
		this.nroAsiento = nroAsiento;
		this.aOcupar = aOcupar;
		this.codigoPasaje = codigoPasaje;
		this.seccion = null;
	}
	
	public void ingresarSeccion(String sec) {
		this.seccion = sec;
	}
	
	public int devolverCodigoPasaje() {
		return this.codigoPasaje;
	}
	
	public String devolverCodigoVuelo() {
		return this.codVuelo;
	}
	
	public int devolverNumeroAsiento() {
		return this.nroAsiento;
	}
	
	public String devolverSeccion() {
		return this.seccion;
	}
	
	public int devolverDni() {
		return this.dni;
	}
	
	public double devolverCostoPasaje(double valorPasaje, double valorRefrigerio, int cantRefrigerios) {
		float impuesto = (float) 1.2;
		return (double) Math.floor((valorPasaje + valorRefrigerio * cantRefrigerios) * impuesto);
		
	}
}

