package ec.edu.epn.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import ec.edu.epn.modelo.enums.TipoDeNave;

public class Tablero {
	private static final int DIM = 5;
	private String[][] tableroDeNaves;
	private String[][] tableroDeDisparos;

	public Tablero(String nombreDeArchivo) throws Exception {
		this.tableroDeNaves = new String[DIM][DIM];
		this.tableroDeDisparos = new String[DIM][DIM];

		cargarTableroDeNavesDesdeArchivo(nombreDeArchivo);
		inicializarTableroDeDisparos();
		comprobarReglasDeTablero(nombreDeArchivo);
	}

	public String[][] getTableroDeNaves() {
		return tableroDeNaves;
	}

	public String[][] getTableroDeDisparos() {
		return tableroDeDisparos;
	}

	private void cargarTableroDeNavesDesdeArchivo(String nombreDeArchivo)
			throws IOException, FileNotFoundException {
		File archivo = new File(nombreDeArchivo);
		try {
			FileReader fileReader = new FileReader(archivo);
			BufferedReader lector = new BufferedReader(fileReader);
			String data;
			int columna;
			int fila = 0;
			while ((data = lector.readLine()) != null) {
				columna = 0;
				StringTokenizer tokenizer = new StringTokenizer(data, "\t");
				while (tokenizer.hasMoreTokens()) {
					this.tableroDeNaves[fila][columna] = tokenizer.nextToken();
					columna++;
				}
				fila++;
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(
					">ERROR: No se encuentra el archivo con el nombre "
							+ nombreDeArchivo);
		} catch (IOException e) {
			throw new IOException(
					">ERROR: Ocurrió un error mientrás se leía el archivo "
							+ nombreDeArchivo);
		}
	}

	private void inicializarTableroDeDisparos() {
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				this.tableroDeDisparos[i][j] = ".";
			}
		}
	}

	private void comprobarReglasDeTablero(String nombreDeArchivo)
			throws Exception {
		comprobarLanchas(nombreDeArchivo);
		comprobarBuques(nombreDeArchivo);
		comprobarPortaaviones(nombreDeArchivo);
	}

	// Tomar en cuenta las medidas de las naves:
	// lancha: L 3 1, buque: B 1 2, portaviones: P 1 3

	private void comprobarPortaaviones(String nombreDeArchivo) throws Exception {
		// 4ta regla: solo 3 letras en portaviones
		int p = 0;
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				if (this.tableroDeNaves[i][j]
						.compareToIgnoreCase(TipoDeNave.PORTAAVIONES
								.getCaracter()) == 0) {
					p++;
				} else if (this.tableroDeNaves[i][j]
						.compareToIgnoreCase(TipoDeNave.VACIO.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.LANCHA
										.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.BUQUE
										.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.PORTAAVIONES
										.getCaracter()) != 0) {
					throw new Exception(">ERROR: El caracter "
							+ this.tableroDeNaves[i][j]
							+ " no se reconoce en el archivo "
							+ nombreDeArchivo);
				}
			}
		}

		if (p != 3) {
			throw new Exception(
					">ERROR: Existe un error en las posiciones del PORTAAVIONES en el archivo "
							+ nombreDeArchivo);
		}

		// 5ta regla: comprobar que no esten las letras en diagonal en
		// portaaviones
		boolean errorPortaavionesFilas = false;

		int comprobacionFilasPortaaviones;
		for (int i = 0; i < DIM; i++) {
			comprobacionFilasPortaaviones = 0;
			for (int j = 0; j < DIM; j++) {
				if (tableroDeNaves[i][j]
						.compareToIgnoreCase(TipoDeNave.PORTAAVIONES
								.getCaracter()) == 0) {
					comprobacionFilasPortaaviones++;
				} else if (this.tableroDeNaves[i][j]
						.compareToIgnoreCase(TipoDeNave.VACIO.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.LANCHA
										.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.BUQUE
										.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.PORTAAVIONES
										.getCaracter()) != 0) {
					throw new Exception(">ERROR: El caracter "
							+ this.tableroDeNaves[i][j]
							+ " no se reconoce en el archivo "
							+ nombreDeArchivo);
				}
			}
			if (comprobacionFilasPortaaviones != 3
					&& comprobacionFilasPortaaviones != 0) {
				errorPortaavionesFilas = true;
			}
		}

		int comprobacionColumnasPortaaviones;
		boolean errorPortaavionesColumnas = false;
		for (int i = 0; i < DIM; i++) {
			comprobacionColumnasPortaaviones = 0;
			for (int j = 0; j < DIM; j++) {
				if (this.tableroDeNaves[j][i]
						.compareToIgnoreCase(TipoDeNave.PORTAAVIONES
								.getCaracter()) == 0) {
					comprobacionColumnasPortaaviones++;
				} else if (this.tableroDeNaves[i][j]
						.compareToIgnoreCase(TipoDeNave.VACIO.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.LANCHA
										.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.BUQUE
										.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.PORTAAVIONES
										.getCaracter()) != 0) {
					throw new Exception(">ERROR: El caracter "
							+ this.tableroDeNaves[i][j]
							+ " no se reconoce en el archivo "
							+ nombreDeArchivo);
				}
			}

			if (comprobacionColumnasPortaaviones != 3
					&& comprobacionColumnasPortaaviones != 0) {
				errorPortaavionesColumnas = true;
			}
		}

		if (errorPortaavionesFilas && errorPortaavionesColumnas) {
			throw new Exception(
					">ERROR: Existe un error en las posiciones del PORTAAVIONES en el archivo "
							+ nombreDeArchivo
							+ ", no se aceptan posiciones diagonales");
		}
	}

	private void comprobarBuques(String nombreDeArchivo) throws Exception {
		// 2da regla: solo 2 letras b de buque
		int b = 0;
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				if (tableroDeNaves[i][j].compareToIgnoreCase(TipoDeNave.BUQUE
						.getCaracter()) == 0) {
					b++;
				} else if (this.tableroDeNaves[i][j]
						.compareToIgnoreCase(TipoDeNave.VACIO.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.LANCHA
										.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.BUQUE
										.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.PORTAAVIONES
										.getCaracter()) != 0) {
					throw new Exception(">ERROR: El caracter "
							+ this.tableroDeNaves[i][j]
							+ " no se reconoce en el archivo "
							+ nombreDeArchivo);
				}
			}
		}

		if (b != 2) {
			throw new Exception(
					">ERROR: Existe un error en las posiciones del BUQUE en el archivo "
							+ nombreDeArchivo);
		}

		// 3era regla: comprobar que no esten las letras en diagonal en buque
		boolean errorBuqueFilas = false;

		int comprobacionFilasBuque;
		for (int i = 0; i < DIM; i++) {
			comprobacionFilasBuque = 0;
			for (int j = 0; j < DIM; j++) {
				if (tableroDeNaves[i][j].compareToIgnoreCase("B") == 0) {
					comprobacionFilasBuque++;
				} else if (this.tableroDeNaves[i][j]
						.compareToIgnoreCase(TipoDeNave.VACIO.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.LANCHA
										.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.BUQUE
										.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.PORTAAVIONES
										.getCaracter()) != 0) {
					throw new Exception(">ERROR: El caracter "
							+ this.tableroDeNaves[i][j]
							+ " no se reconoce en el archivo "
							+ nombreDeArchivo);
				}
			}

			if (comprobacionFilasBuque != 2 && comprobacionFilasBuque != 0) {
				errorBuqueFilas = true;
			}
		}

		boolean errorBuqueColumnas = false;

		int comprobacionColumnasBuque;
		for (int i = 0; i < DIM; i++) {
			comprobacionColumnasBuque = 0;
			for (int j = 0; j < DIM; j++) {
				if (tableroDeNaves[j][i].compareToIgnoreCase(TipoDeNave.BUQUE
						.getCaracter()) == 0) {
					comprobacionColumnasBuque++;
				} else if (this.tableroDeNaves[i][j]
						.compareToIgnoreCase(TipoDeNave.VACIO.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.LANCHA
										.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.BUQUE
										.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.PORTAAVIONES
										.getCaracter()) != 0) {
					throw new Exception(">ERROR: El caracter "
							+ this.tableroDeNaves[i][j]
							+ " no se reconoce en el archivo "
							+ nombreDeArchivo);
				}
			}

			if (comprobacionColumnasBuque != 2
					&& comprobacionColumnasBuque != 0) {
				errorBuqueColumnas = true;
			}
		}

		if (errorBuqueFilas && errorBuqueColumnas) {
			throw new Exception(
					">ERROR: Existe un error en las posiciones del BUQUE en el archivo "
							+ nombreDeArchivo
							+ ", no se aceptan posiciones diagonales");
		}
	}

	private void comprobarLanchas(String nombreDeArchivo) throws Exception {
		// 1era regla: solo 3 lanchas en el tablero
		int l = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (this.tableroDeNaves[i][j]
						.compareToIgnoreCase(TipoDeNave.LANCHA.getCaracter()) == 0) {
					l++;
				} else if (this.tableroDeNaves[i][j]
						.compareToIgnoreCase(TipoDeNave.VACIO.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.LANCHA
										.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.BUQUE
										.getCaracter()) != 0
						&& this.tableroDeNaves[i][j]
								.compareToIgnoreCase(TipoDeNave.PORTAAVIONES
										.getCaracter()) != 0) {
					throw new Exception(">ERROR: El caracter "
							+ this.tableroDeNaves[i][j]
							+ " no se reconoce en el archivo "
							+ nombreDeArchivo);
				}
			}
		}
		if (l != 3) {
			throw new Exception(
					">ERROR: Existe un error en las posiciones de las LANCHAS en el archivo "
							+ nombreDeArchivo);
		}
	}

	public void imprimirTableroDeNaves(String titulo) {
		System.out.println("\n" + titulo);
		System.out.println("\tA\tB\tC\tD\tE");
		for (int i = 0; i < DIM; i++) {
			System.out.print(i + 1 + "\t");
			for (int j = 0; j < DIM; j++) {
				System.out.print(this.tableroDeNaves[i][j]);
				System.out.print("\t");
			}
			System.out.print("\n");
		}
	}

	public void imprimirTableroDeDisparos(String titulo) {
		System.out.println("\n" + titulo);
		System.out.println("\tA\tB\tC\tD\tE");
		for (int i = 0; i < DIM; i++) {
			System.out.print(i + 1 + "\t");
			for (int j = 0; j < DIM; j++) {
				System.out.print(this.tableroDeDisparos[i][j]);
				System.out.print("\t");
			}
			System.out.print("\n");
		}
	}

	public void actualizarTableroDeDisparos(int x, int y, boolean acierto) {
		if (acierto) {
			this.tableroDeDisparos[x][y] = "X";
		} else {
			this.tableroDeDisparos[x][y] = "O";
		}
	}

	public boolean comprobarDisparoEnTableroEnemigoDeNaves(int x, int y,
			String[][] tableroDeNaves) {
		if (tableroDeNaves[x][y].compareToIgnoreCase(".") == 0) {
			return false;
		} else {
			return true;
		}
	}

}
