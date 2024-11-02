package com.bondijet.vuelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.bondijet.asiento.Asiento;
import com.bondijet.pasaje.Pasaje;

public abstract class VueloPublico extends Vuelo {
	protected double valorRefrigerio;
	protected double[] precios;
	protected int[] cantAsientos;
	private final String[] clases;
	protected final HashMap<Integer,Asiento> asientos;
	
	public VueloPublico(String origen, String destino, String fecha, int tripulantes,
		double valorRefrigerio, double[] precios, int[] cantAsientos) {
		super(origen, destino, fecha, tripulantes);
		// TODO Auto-generated constructor stub
		this.valorRefrigerio = valorRefrigerio;
		this.precios = precios;
		this.cantAsientos = cantAsientos;
		this.clases = new String[] {"Turista", "Ejecutivo", "PrimeraClase"};
		this.asientos = this.generarAsientos();
		this.codigo = generarCodigoVuelo();
	}
	
	public abstract int devolverCantRefregerios();
	
	public double devolverValorRefrigerio() {
		return this.valorRefrigerio;
	}
	

	public double devolverCostoSeccion(int numeroAsiento) {
		Asiento asiento = asientos.get(numeroAsiento);
		String seccion = asiento.devolverSeccion();
		double costo = 0;
		for(int i = 0; i < this.clases.length; i++) {
			if(this.clases[i].equals(seccion)) {
				costo = precios[i];
			}
		}
		return costo;
	}
	
	private String generarCodigoVuelo() {
		StringBuilder codigo = new StringBuilder();
		int numero = VueloUtils.devolverCodigoDiario();
		VueloUtils.aumentarCodigoDiario();
		codigo.append(numero);
		codigo.append("-PUB");
		return codigo.toString();
	}
	@Override
	public HashMap<Integer, Asiento> devolverListaAsientos() {
		return this.asientos;
	}
	
	private HashMap<Integer, Asiento> generarAsientos() {
		HashMap<Integer, Asiento> asientos = new HashMap<Integer, Asiento>();
		int contador = 0;
		try {
			
		for(int i = 0; i < this.cantAsientos.length; i++ ) {
			String seccion = this.clases[i];
			for(int j = 0; j < this.cantAsientos[i]; j++ ) {
				Asiento asiento = new Asiento(contador, seccion);
				asientos.put(contador,asiento);
				contador++;
		}
	}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return asientos;
	}
	@Override
	public String devolverOrigen() {
		return this.origen;
	}
	@Override
	public String devolverDestino() {
		return this.destino;
	}
	@Override
	public String devolverFecha() {
		return this.fecha;
	}
	
	@SuppressWarnings("null")
	@Override
	public ArrayList<Pasaje> cancelarVuelo() {
		List<Pasaje> pasajes = new ArrayList<Pasaje>();
		try {
			
		for(Entry<Integer, Asiento> asiento: asientos.entrySet()) {
			if(!asiento.getValue().estaDisponible()) {
				pasajes.add(asiento.getValue().devolverPasajeAsociado());
			}
		}
		} 
		catch (Exception e) {
			// TODO: handle exception
		}
		return (ArrayList<Pasaje>) pasajes;
	}
}
