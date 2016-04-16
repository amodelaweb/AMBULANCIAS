/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.negogio;

import java.util.Comparator;
import java.util.GregorianCalendar;

import co.edu.javeriana.empresaAmbulancias.presentacion.Utils;

/**
 * @author Miguel Baron y Santiago Chaustre
 * Clase ComparaFechas encargada de crear objetos de tipo compara fechas
 */
public class ComparaFechas implements Comparator<Servicio>{
	/**
	 * Metodo heredado de la interfas Comparator. Encargado de compara dos fechas
	 */
	public int compare(Servicio o1, Servicio o2) {
		if(o1.getHoraSoli().before(o2.getHoraSoli())){
			return -1;
		}
		if(o1.getHoraSoli().after(o2.getHoraSoli())){
			return 1;
		}
		return 0;
	}

}
