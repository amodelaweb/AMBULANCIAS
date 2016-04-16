/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.negogio;

/**
 * @author Miguel Baron y Santiago Chaustre
 *Clase AmbulanciaNoMedicalizada encargada de crear objetos de tipo ambulancia no medicalizada
 */
public class AmbulanciaNoMedicalizada extends Ambulancia{
	private String enfermero;

	/**
	 * Constructor de la clase AmbulanciaNoMedicalizada
	 * @param codigo Variable int con el codigo de la ambulancia
	 * @param placa Variable String con la placa de la ambulancia
	 * @param enfermero Variable String con el nombre del enfermero
	 */
	public AmbulanciaNoMedicalizada(int codigo, String placa,
			 String enfermero) {
		super(codigo, placa);
		this.enfermero = enfermero;
	}
	/**
	 * Metodo heredado de la clase Ambulancia
	 */
	public long calcularTarifa(){
		return TARIFA_BASE;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  String.format("%s %-15s", super.toString(), enfermero);
	}
	
}
