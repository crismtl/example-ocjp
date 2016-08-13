package ec.edu.epn.modelo;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Juego {
	private static final int TURNOS = 5;
	private Jugador j1;
	private Jugador j2;

	public Juego() throws Exception {
		this.j1 = new Jugador("Cris", "tablero_jugador_1.txt");
		this.j2 = new Jugador("Lily", "tablero_jugador_2.txt");
	}

	public void jugar(String idioma) throws Exception {
		int turnosJugador1 = 0;
		int turnosJugador2 = 0;

		int aciertosJugador1 = 0;
		int aciertosJugador2 = 0;

		Locale localizacion = new Locale(idioma);
		ResourceBundle recurso = ResourceBundle.getBundle("Etiquetas",
				localizacion);

		while (turnosJugador1 < TURNOS && turnosJugador2 < TURNOS) {
			this.j1.getTablero().imprimirTableroDeDisparos(
					recurso.getString("Tablero") + " - " + this.j1.getNombre());
			System.out.print(recurso.getString("Turno") + " "
					+ this.j1.getNombre() + ": ");
			System.out.flush();
			Scanner entradaJugador1 = new Scanner(System.in);
			String disparoJugador1 = entradaJugador1.next();

			while (comprobarCoordenadasEnDisparo(disparoJugador1) == false) {
				System.err
						.print(">Error en el ingreso de la coordenada, ingresela nuevamente: ");
				System.out.flush();
				entradaJugador1 = new Scanner(System.in);
				disparoJugador1 = entradaJugador1.next();
			}

			if (j1.disparar(disparoJugador1, this.j2)) {
				System.out.println("\t *** " + this.j1.getNombre() + " "
						+ recurso.getString("Exito"));
				aciertosJugador1++;
			} else {
				System.out.println("\t" + recurso.getString("Fallo"));
			}
			turnosJugador1++;

			this.j2.getTablero().imprimirTableroDeDisparos(
					recurso.getString("Tablero") + " - " + this.j2.getNombre());
			System.out.print(recurso.getString("Turno") + " "
					+ this.j2.getNombre() + ": ");
			System.out.flush();
			Scanner entradaJugador2 = new Scanner(System.in);
			String disparoJugador2 = entradaJugador2.next();

			while (comprobarCoordenadasEnDisparo(disparoJugador2) == false) {
				System.err
						.print(">Error en el ingreso de la coordenada, ingresela nuevamente: ");
				System.out.flush();
				entradaJugador2 = new Scanner(System.in);
				disparoJugador2 = entradaJugador2.next();
			}

			if (j2.disparar(disparoJugador2, this.j1)) {
				System.out.println("\t *** " + this.j2.getNombre() + " "
						+ recurso.getString("Exito"));
				aciertosJugador2++;
			} else {
				System.out.println("\t" + recurso.getString("Fallo"));
			}
			turnosJugador2++;
		}

		if (aciertosJugador1 > aciertosJugador2) {
			System.out.println(this.j1.getNombre() + " "
					+ recurso.getString("Ganador"));
		} else if (aciertosJugador1 < aciertosJugador2) {
			System.out.println(this.j2.getNombre() + " "
					+ recurso.getString("Ganador"));
		} else {
			System.out.println(this.j1.getNombre() + "y " + this.j2.getNombre()
					+ " " + recurso.getString("Empate"));
		}
	}

	private boolean comprobarCoordenadasEnDisparo(String disparo) {
		Pattern patron = Pattern.compile("[1-5][A-Ea-e]");
		Matcher coincidencia = patron.matcher(disparo);
		if (coincidencia.find()) {
			return true;
		} else {
			return false;
		}
	}

}
