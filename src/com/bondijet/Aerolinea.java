package com.bondijet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bondijet.interfaces.*;
import com.bondijet.pasaje.Pasaje;
import com.bondijet.cliente.*;
import com.bondijet.aeropuerto.*;
import com.bondijet.asiento.Asiento;
import com.bondijet.vuelo.*;

public class Aerolinea implements IAerolinea {
	private final String nombre;
	private final String cuit;
	private final List<Cliente> registroClientes;
	private final List<Aeropuerto> registroAeropuertos;
	private final Map<String, Vuelo> registroCodigosVuelosGeneral;
	private final Map<Integer, Cliente> registroDniCliente;
	private int nroPasaje = 0;
	
	 public Aerolinea(String nombre, String cuit) {
		this.nombre = nombre;
		this.cuit = cuit;
		this.registroAeropuertos = new ArrayList<Aeropuerto>();
		this.registroClientes = new ArrayList<Cliente>();
		this.registroCodigosVuelosGeneral = new HashMap<String, Vuelo>();
		this.registroDniCliente = new HashMap<Integer, Cliente>();
	}
	@Override
	public void registrarCliente(int dni, String nombre, String telefono) {
		// TODO: chequear posibles excepciones
		Cliente cliente = new Cliente(dni, nombre, telefono);
		registroClientes.add(cliente);
	}
	@Override
	public void registrarAeropuerto(String nombre, String pais, String provincia, String direccion) {
		// TODO: chequear posibles excepciones
		Aeropuerto aeropuerto = new Aeropuerto(nombre, pais, provincia, direccion);
		registroAeropuertos.add(aeropuerto);
	}
	@Override
	public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, double[] precios, int[] cantAsientos) {
		VueloPublico vuelo = new VueloPublicoNacional(origen, destino, fecha, tripulantes, valorRefrigerio, precios, cantAsientos);
		
		Aeropuerto aeropuerto = buscarAeropuerto(origen);
//		aeropuerto.agregarVueloPublico(vuelo);
		aeropuerto.agregarVuelo(vuelo);
		registroCodigosVuelosGeneral.put(vuelo.devolverCodigo(), vuelo);
		return vuelo.devolverCodigo();
	}
	@Override
	public String registrarVueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {
		VueloPublico vuelo = new VueloPublicoInternacional(origen, destino, fecha, tripulantes, valorRefrigerio, cantRefrigerios,
				precios, cantAsientos, escalas);
		Aeropuerto aeropuerto = buscarAeropuerto(origen);
//		aeropuerto.agregarVueloPublico(vuelo);
		aeropuerto.agregarVuelo(vuelo);
		registroCodigosVuelosGeneral.put(vuelo.devolverCodigo(), vuelo);
		return vuelo.devolverCodigo();
		
	}
	@Override
	public String VenderVueloPrivado(String origen, String destino, String fecha, int tripulantes, double precio,
			int dniComprador, int[] acompaniantes) {
		VueloPrivado vuelo = new VueloPrivado(origen, destino, fecha, tripulantes, precio, dniComprador, acompaniantes);
		Aeropuerto aeropuerto = buscarAeropuerto(origen);
//		aeropuerto.agregarVueloPrivado(vuelo);
		aeropuerto.agregarVuelo(vuelo);
		registroCodigosVuelosGeneral.put(vuelo.devolverCodigo(), vuelo);
		return vuelo.devolverCodigo();
	}
	@SuppressWarnings("null")
	@Override
	public Map<Integer, String> asientosDisponibles(String codVuelo) {
		Vuelo vuelo = null;
		for(Aeropuerto aero: registroAeropuertos) {
			vuelo = aero.buscarVueloConCodigo(codVuelo);
			if(vuelo != null) {
				break;
			} 
		}
		Map<Integer, String> mapaAsientos = null;
		try {
			HashMap<Integer, Asiento> asientos = vuelo.devolverListaAsientos();
			for(Map.Entry<Integer, Asiento> asientoEntry: asientos.entrySet()) {
				if(asientoEntry.getValue().estaDisponible()) {
					mapaAsientos.put(asientoEntry.getValue().devolverNumeroAsiento(), asientoEntry.getValue().devolverSeccion());
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return mapaAsientos;
	}
	@Override
	public int venderPasaje(int dni, String codVuelo, int nroAsiento, boolean aOcupar) {
		// tambien puede lanzar una excepcion si el codVuelo es invalido
		// hay que chequear que el nroAsiento este disponible tambien :(
		Cliente cliente = null;
		try {
			cliente = buscarClientePorDni(dni);
			if(cliente == null) {
				throw new Error();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		int codigoPasaje = nroPasaje;
		aumentarNumeroPasaje();
		Pasaje pasaje = new Pasaje(dni, codVuelo, nroAsiento, aOcupar, codigoPasaje);
		cliente.adicionarPasaje(codigoPasaje, pasaje);
		registroDniCliente.put(dni, cliente);
		VueloPublico vuelo = (VueloPublico) registroCodigosVuelosGeneral.get(codVuelo);
		HashMap<Integer, Asiento> asientos = vuelo.devolverListaAsientos();
		Asiento asiento = asientos.get(nroAsiento);
		asiento.cambiarEstado();
		return codigoPasaje;
	}
	@SuppressWarnings("null")
	@Override
	public List<String> consultarVuelosSimilares(String origen, String destino, String fecha) {
		//Extraemos el dia, mes y anio
		fecha = fecha.trim();
		StringBuilder fechaAux = new StringBuilder(fecha);
		int dia = Integer.parseInt(fechaAux.substring(0, 2)); 
		int mes = Integer.parseInt(fechaAux.substring(3, 5)); 
		int anio = Integer.parseInt(fechaAux.substring(6)); 
		LocalDate date = LocalDate.of(anio, mes, dia);
		LocalDate datePlus7Days = date.plusDays(7);
		ArrayList<Vuelo> vuelos = devolverVuelosMismoOrigenDestino(origen, destino);
		List<String> vuelosSimilares = null;
		for(Vuelo vuelo: vuelos) {
			String fechaVuelo = vuelo.devolverFecha(); // 20/10/2024
			StringBuilder fechaAuxVuelo = new StringBuilder(fechaVuelo); 
			int diaVuelo = Integer.parseInt(fechaAux.substring(0, 2)); 
			int mesVuelo = Integer.parseInt(fechaAux.substring(3, 5)); 
			int anioVuelo = Integer.parseInt(fechaAux.substring(6));
			LocalDate dateVuelo = LocalDate.of(anio, mes, dia);
			if(dateVuelo.isAfter(date) && dateVuelo.isBefore(datePlus7Days)) {
				vuelosSimilares.add(vuelo.devolverCodigo());
			}
		}
		
		
		
		
		return vuelosSimilares;
	}
	@Override
	public void cancelarPasaje(int dni, String codVuelo, int nroAsiento) {
		// hay que explicar que la complejidad es O(1) 
		VueloPublico vuelo = (VueloPublico) registroCodigosVuelosGeneral.get(codVuelo);
		HashMap<Integer, Asiento> asientos = vuelo.devolverListaAsientos();
		Asiento asiento = asientos.get(nroAsiento);
		Pasaje pasaje = asiento.devolverPasajeAsociado();
		int codigoUnicoPasaje = pasaje.devolverCodigoPasaje();
		Cliente cliente = registroDniCliente.get(dni);
		cliente.removerPasaje(codigoUnicoPasaje);	
		asiento.cambiarEstado();
		
	}
	@Override
	public void cancelarPasaje(int dni, int codPasaje) {
		// TODO Auto-generated method stub
		
		Cliente cliente = registroDniCliente.get(dni);
		cliente.removerPasaje(codPasaje);
		Pasaje pasaje = cliente.devolverPasaje(codPasaje);
		int nroAsiento = pasaje.devolverNumeroAsiento();
		String codVuelo = pasaje.devolverCodigoVuelo();
		VueloPublico vuelo = (VueloPublico) registroCodigosVuelosGeneral.get(codVuelo);
		HashMap<Integer, Asiento> asientos = vuelo.devolverListaAsientos();
		Asiento asiento = asientos.get(nroAsiento);
		asiento.removerPasajeAsociado();
		asiento.cambiarEstado();
		
	}
	@SuppressWarnings("null")
	@Override
	public List<String> cancelarVuelo(String codVuelo) {
		
		Vuelo vuelo = registroCodigosVuelosGeneral.get(codVuelo);
		// ver: metodo cancelarVuelo
		ArrayList<Pasaje> pasajeros = vuelo.cancelarVuelo(); // lista pasajeros cancelados
		String destino = vuelo.devolverDestino();
		String origen = vuelo.devolverOrigen();
		ArrayList<Vuelo> vuelosMismoDestino = devolverVuelosMismoOrigenDestino(origen, destino);
		List<String> listaPasajeros = null;
		HashMap<String, Integer> secciones = new HashMap<String, Integer>();
		secciones.put("Turista", 1);
		secciones.put("Ejecutivo", 2);
		secciones.put("PrimeraClase", 3);
		int contador = 0; 
		for(Vuelo vueloAux: vuelosMismoDestino) {
			HashMap<Integer, Asiento> asientos = vueloAux.devolverListaAsientos();
			
			for(Entry<Integer, Asiento> asiento: asientos.entrySet()) {
				String seccionPasajeroCancelado = pasajeros.get(contador).devolverSeccion();
				String seccionVueloNuevo = asiento.getValue().devolverSeccion();
				if(asiento.getValue().estaDisponible() && 
						secciones.get(seccionPasajeroCancelado) >= secciones.get(seccionVueloNuevo)) {
					StringBuilder detallesReprogramacion = new StringBuilder();
					Cliente cliente = registroDniCliente.get(pasajeros.get(contador).devolverDni());
					detallesReprogramacion.append(cliente.devolverDetalleCliente() 
							+ vueloAux.devolverCodigo());
					listaPasajeros.add(detallesReprogramacion.toString());
				}
				contador++;
			}	
		}
		
		for(int i = contador; i < pasajeros.size(); i++) {
			StringBuilder detallesReprogramacion = new StringBuilder();
			Cliente cliente = registroDniCliente.get(pasajeros.get(contador).devolverDni());
			detallesReprogramacion.append(cliente.devolverDetalleCliente() 
					+ " -  CANCELADO");
			listaPasajeros.add(detallesReprogramacion.toString());
			contador++;
		}
		
		
		return listaPasajeros;
	}
	@Override
	public double totalRecaudado(String destino) {
		
		return 0;
	}
	@Override
	public String detalleDeVuelo(String codVuelo) {
		Vuelo vuelo = registroCodigosVuelosGeneral.get(codVuelo);
		return vuelo.toString();
	}
	private Aeropuerto buscarAeropuerto(String origen) {
		Aeropuerto aeropuerto = null;
		for(Aeropuerto ar: registroAeropuertos) {
			if(ar.obtenerNombre().equals(origen)) {
				aeropuerto = ar;
			}
		}
		return aeropuerto;
	}
	
	private Cliente buscarClientePorDni(int dni) {
		for(Cliente cliente: registroClientes) {
			if(cliente.devolverDni() == dni) {
				return cliente;
			}
		
		}
		return null;
	}
	
	@SuppressWarnings("null")
	private ArrayList<Vuelo> devolverVuelosMismoOrigenDestino(String origen, String destino) {
		ArrayList<Vuelo> vuelosSimilares = null;
		for(Entry<String, Vuelo> vueloEntry: registroCodigosVuelosGeneral.entrySet()) {
			Vuelo vuelo = vueloEntry.getValue();
			if(vuelo.devolverDestino().equals(destino) && vuelo.devolverOrigen().equals(origen)) {
				vuelosSimilares.add(vuelo);
			}
		}
		return vuelosSimilares;
	}
	
	
	private void aumentarNumeroPasaje() {
		this.nroPasaje++;
	}
	
}
