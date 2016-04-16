/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.negogio;

/**
 * @author Miguel Baron y Santiago Chaustre
 * Clase AmbulanciaUCI encargada de crear objetos de tipo ambulancia UCI
 */
public class AmbulanciaUCI extends AmbulanciaMedicalizada{
	private String tipoUCI;

	/**
	 * Constructor de la clase AmbulanciaUCI
	 * @param codigo Variable int con el codigo de la ambulancia
	 * @param placa Variable String con la placa de la ambulancia
	 * @param medico Variable String con el nombre del medico
	 * @param tipoUCI Varible String con el tipo de UCI
	 */
	public AmbulanciaUCI(int codigo, String placa, String medico, String tipoUCI) {
		super(codigo, placa, medico);
		this.tipoUCI = tipoUCI;
	}
	/**
	 * Metodo heredado de la clase Ambulancia
	 */
	public long calcularTarifa(){
		if(tipoUCI.equalsIgnoreCase("CARDIOVASCULAR")){
			return (long) (TARIFA_BASE + (TARIFA_BASE*0.5)) ;
		}
		if(tipoUCI.equalsIgnoreCase("PEDIATRICA")){
			return (long) (TARIFA_BASE + (TARIFA_BASE*0.6));
		}
		return 0;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s %-18s %-15s",super.toString(), medico, tipoUCI);
	}
	
}
