/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.negogio;

/**
 * @author Miguel Baron y Santiago Chaustre
 * Clase Direccion encargada de crear objetos de tipo direccion
 */
public class Direccion {
	private String tipoDirec;
	private int calle;
	private int carrera;
	private int numero;
	/**
	 * Contructor de la clase Direccion
	 * @param tipoDirec variable que contiene el tipode direccion CALLE o CARRERA
	 * @param calle variable con el numero de calle
	 * @param carrera variable con el numero de carrera
	 * @param numero variable con el numero de vivienda
	 */
	public Direccion(String tipoDirec, int calle, int carrera, int numero) {
		super();
		this.tipoDirec = tipoDirec;
		this.calle = calle;
		this.carrera = carrera;
		this.numero = numero;
	}
	/**
	 * @return the tipoDirec
	 */
	public String getTipoDirec() {
		return tipoDirec;
	}
	/**
	 * @param tipoDirec the tipoDirec to set
	 */
	public void setTipoDirec(String tipoDirec) {
		this.tipoDirec = tipoDirec;
	}
	/**
	 * @return the calle
	 */
	public int getCalle() {
		return calle;
	}
	/**
	 * @param calle the calle to set
	 */
	public void setCalle(int calle) {
		this.calle = calle;
	}
	/**
	 * @return the carrera
	 */
	public int getCarrera() {
		return carrera;
	}
	/**
	 * @param carrera the carrera to set
	 */
	public void setCarrera(int carrera) {
		this.carrera = carrera;
	}
	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return String.format("%-10s %-10d %-10d -10d",tipoDirec,calle,carrera,numero);
	}
	
	
}
