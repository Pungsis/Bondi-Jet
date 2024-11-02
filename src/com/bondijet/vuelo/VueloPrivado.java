package com.bondijet.vuelo;

import java.util.ArrayList;
import java.util.HashMap;

import com.bondijet.asiento.Asiento;
import com.bondijet.pasaje.Pasaje;

public class VueloPrivado extends Vuelo {
	private double precio;
	private int dniComprador;
	private int[] acompaniantes;
	private int cantJets;

	public VueloPrivado(String origen, String destino, String fecha, int tripulantes,
			double precio,
			int dniComprador, int[] acompaniantes) {
		super(origen, destino, fecha, tripulantes);
		// TODO Auto-generated constructor stub
		this.precio = precio;
		this.dniComprador = dniComprador;
		this.acompaniantes = acompaniantes;
		this.codigo = generarCodigoVuelo();
		this.cantJets = calcularCantJetsNecesarios();
	}
	
	private int calcularCantJetsNecesarios() {
		int jets = (int) Math.ceil((float)(1 + acompaniantes.length) / 15);
		return jets;
	}
	
	public int devolverCantJets() {
		return this.cantJets;
	}
	
	private static String generarCodigoVuelo() {
		StringBuilder codigo = new StringBuilder();
		int numero = VueloUtils.devolverCodigoDiario();
		VueloUtils.aumentarCodigoDiario();
		codigo.append(numero);
		codigo.append("-PRI");
		return codigo.toString();
	}

	public double devolverCostoTotal() {
		float impuesto = (float) 1.3;
		return (double) (this.precio * this.cantJets) * impuesto;
	}
	
	@Override
	public String devolverCodigo() {
		// TODO Auto-generated method stub
		return this.codigo;
	}



	@Override
	public HashMap<Integer, Asiento> devolverListaAsientos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String devolverDestino() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String devolverOrigen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String devolverFecha() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString() {
		StringBuilder detalles = new StringBuilder();
		detalles.append(this.codigo + " - " + this.origen + " - " + this.destino + " - " + this.fecha + " - PRIVADO (" + this.cantJets + ")"  );
		return detalles.toString();
	}

	@Override
	public ArrayList<Pasaje> cancelarVuelo() {
		// TODO Auto-generated method stub
		return null;
	}

}
