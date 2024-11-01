package com.bondijet.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import com.bondijet.asiento.Asiento;
import com.bondijet.pasaje.Pasaje;

public interface VueloInterface {
	/* @return -> devuelve el codigo de vuelo */ 
	public String devolverCodigo();
	/* @return -> devuelve la lista de asientos de un vuelo */ 
	public HashMap<Integer, Asiento> devolverListaAsientos(); 
	/* @return -> devuelve el destino del vuelo */ 
	public String devolverDestino();
	/* @return ->  devuelve el origen del vuelo */ 
	public String devolverOrigen();
	/* @return ->  devuelve la fecha del vuelo */ 
	public String devolverFecha();
	/* 
	Cancela un vuelo.
	@return -> devuelve la lista de pasajeros del vuelo cancelado
	@throws -> RuntimeException: si lista asientos es null;
	*/ 
	public ArrayList<Pasaje> cancelarVuelo();
 }
 
