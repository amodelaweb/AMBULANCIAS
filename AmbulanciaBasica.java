/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.negogio;

/**
 * @author Miguel Baron y Santiago Chaustre
 * Clase AmbulanciaBasica encargada de crear objetos de tipo ambulancia basica
 */
public class AmbulanciaBasica extends AmbulanciaMedicalizada{
	/**
	 * Constructor de ambulancia basica
	 * @param codigo Variable de tipos int con el codigo de la ambulancia
	 * @param placa Variable String con la palca de la ambulancia
	 * @param medico Variable String con el nombre del medico
	 */
	public AmbulanciaBasica(int codigo, String placa, String medico) {
		super(codigo, placa, medico);
		// TODO Auto-generated constructor stub
	}
/**
 * Metodo heredado de la clase Ambulancia
 */
	public long calcularTarifa(){
		return (long) (TARIFA_BASE +(TARIFA_BASE*0.2));
	}
}
