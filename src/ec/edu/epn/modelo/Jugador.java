package ec.edu.epn.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import ec.edu.epn.modelo.enums.TipoDeNave;

public class Jugador {

	private String nombre;
	private String nombreDeArchivo;
	private Tablero tablero;

	public Jugador(String nombre, String nombreDeArchivo) throws Exception {
		super();
		this.nombre = nombre;
		this.nombreDeArchivo = nombreDeArchivo;
		this.tablero = new Tablero(this.nombreDeArchivo);
	}

	public String getNombre() {
		return nombre;
	}

	public String getNombreDeArchivo() {
		return nombreDeArchivo;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public boolean disparar(String disparo, Jugador enemigo) throws Exception {
		int x = Character.getNumericValue(disparo.charAt(0)) - 1;
		int y = -1;

		String letra = Character.toString(disparo.charAt(1));
		if (letra.compareToIgnoreCase("A") == 0) {
			y = 0;
		} else if (letra.compareToIgnoreCase("B") == 0) {
			y = 1;
		} else if (letra.compareToIgnoreCase("C") == 0) {
			y = 2;
		} else if (letra.compareToIgnoreCase("D") == 0) {
			y = 3;
		} else if (letra.compareToIgnoreCase("E") == 0) {
			y = 4;
		}

		if (y == -1 && (x > 5 || x < 0)) {
			throw new Exception(
					">ERROR: Las coordenadas ingresadas no son válidas "
							+ disparo + " x: " + x + ", y: " + y);
		}
		
		boolean acierto = this.tablero.comprobarDisparoEnTableroEnemigoDeNaves(x, y,
				enemigo.getTablero().getTableroDeNaves());
		this.tablero.actualizarTableroDeDisparos(x, y, acierto);
		if (acierto) {
			return true;
		} else {
			return false;
		}
	}
}
