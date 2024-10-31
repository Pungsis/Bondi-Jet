package com.bondijet.vuelo;

import java.util.ArrayList;
import java.util.HashMap;

import com.bondijet.asiento.Asiento;

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
		return Math.round((1 + acompaniantes.length) / 15); 
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
	
	public String toString() {
		StringBuilder detalles = new StringBuilder();
		detalles.append(this.codigo + " - " + this.origen + " - " + this.destino + " - " + this.fecha + " - PRIVADO - " + this.cantJets  );
		return detalles.toString();
	}

}
