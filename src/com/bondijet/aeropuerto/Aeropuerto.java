package com.bondijet.aeropuerto;

import java.util.ArrayList;
import java.util.List;

import com.bondijet.vuelo.Vuelo;
import com.bondijet.vuelo.VueloPrivado;
import com.bondijet.vuelo.VueloPublico;

public class Aeropuerto {
	private final String nombre;
	private final String pais;
	private final String provincia;
	private final String direccion;
	private final List<Vuelo> listaVuelos;
//	private final List<VueloPrivado> listaCodigosVuelosPrivados;
	
	public Aeropuerto(String nombre, String pais, String provincia, String direccion) {
		this.nombre = nombre;
		this.pais = pais;
		this.provincia = provincia;
		this.direccion = direccion;
		this.listaVuelos = new ArrayList<Vuelo>();
//		this.listaCodigosVuelosPrivados = new ArrayList<VueloPrivado>();
	}
	
	public void agregarVuelo(Vuelo vuelo) {
		listaVuelos.add(vuelo);
	}
//	public void agregarVueloPublico(VueloPublico vuelo) {
//		listaCodigosVuelosPublicos.add(vuelo);
//	}
//	public void agregarVueloPrivado(VueloPrivado vuelo) {
//		listaCodigosVuelosPrivados.add(vuelo);
//	}
	
	public Vuelo buscarVueloConCodigo(String codigo) {
		Vuelo vueloAux = null;
		for(Vuelo vuelo: listaVuelos) {
			if (vuelo.devolverCodigo().equals(codigo)) {
				vueloAux = vuelo;
			}
		}
		return vueloAux;
	}
	
	public String obtenerNombre() {
		return this.nombre;
	}
}
