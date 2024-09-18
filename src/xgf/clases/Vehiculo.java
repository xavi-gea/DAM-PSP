package xgf.clases;

/**
 * Clase utilizada en ejercicios 28 y 29
 */
public class Vehiculo {
	
	private String tipo;
	private String marca;
	private String modelo;
	
	/**
	 * Constructor de la clase
	 * @param tipo
	 * @param marca
	 * @param modelo
	 */
	public Vehiculo(String tipo, String marca, String modelo) {
		
		super();
		this.tipo = tipo;
		this.marca = marca;
		this.modelo = modelo;
	}

	/**
	 * Devuelve el tipo de vehículo
	 * @return String
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Establece el tipo de vehículo
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Devuelve la marca de vehículo
	 * @return String
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Establece la marca de vehículo
	 * @param marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Devuelve el modelo de vehículo
	 * @return String
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * Establece el modelo de vehículo
	 * @param modelo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}