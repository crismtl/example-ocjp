package ec.edu.epn;

import ec.edu.epn.modelo.Juego;

public class Main {
	private static Juego miJuego;

	public static void main(String[] args) {
		try {
			miJuego = new Juego();
			miJuego.jugar("en");
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}
