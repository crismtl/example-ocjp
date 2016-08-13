package ec.edu.epn.modelo.enums;

public enum TipoDeNave {
	PORTAAVIONES("portaaviones", 3, "P", 1), BUQUE("buque", 2, "B", 1), LANCHA("lancha", 1, "L", 3), VACIO("vacio", 1, ".", 12);
	
	private String nombre;
	private int numeroDePiezas;
	private String caracter;
	private int numeroMaximoDeNaves;
	
	private TipoDeNave(String nombre, int numeroDePiezas, String caracter,
			int numeroMaximoDeNaves) {
		this.nombre = nombre;
		this.numeroDePiezas = numeroDePiezas;
		this.caracter = caracter;
		this.numeroMaximoDeNaves = numeroMaximoDeNaves;
	}

	public String getNombre() {
		return nombre;
	}

	public int getNumeroDePiezas() {
		return numeroDePiezas;
	}

	public String getCaracter() {
		return caracter;
	}

	public int getNumeroMaximoDeNaves() {
		return numeroMaximoDeNaves;
	}
	
	
	
}
