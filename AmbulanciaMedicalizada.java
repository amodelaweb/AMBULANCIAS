/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.negogio;

/**
 * @author Miguel Baron y Santiago Chaustre
 * Clase AmbulanciaMedicalizada encarga de crear objetos de tipo AmbulanciasMedicalizada
 */
public abstract class AmbulanciaMedicalizada extends Ambulancia{

	protected String medico;

	/**
	 * @param codigo Variable int con el codigo de la ambulancia
	 * @param placa Variable String con la placa de la ambulancia
	 * @param medico Variable String con el nombre del medico
	 */
	public AmbulanciaMedicalizada(int codigo, String placa,
			String medico) {
		super(codigo, placa);
		this.medico = medico;
	}
}
