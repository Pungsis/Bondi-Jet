package com.bondijet.vuelo;


//utility class
public final class VueloUtils {

	private static String[] listaVuelosInternacionalValidos = new String[] {
		"BuenosAires", "Mexico"
	};
	private static String[] listaVuelosNacionalesValidos = new String[] {
			"LaPampa", "Neuquen"
		};
	private static int codigoDiario = 1;
	
	public static void aumentarCodigoDiario( ) {
		codigoDiario++;
	}
	
	public static int devolverCodigoDiario() {
		return codigoDiario;
	}
	
	public static void destinoEsValido(String destino) {
		
	}
	
	public static String[] devolverListaDestinosValidos() {
		return listaVuelosInternacionalValidos;
	}

}
