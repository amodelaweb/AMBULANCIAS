/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.negogio;

import java.util.Comparator;

/**
 * @author Miguel Baron y Santiago Chaustre
 * Clase ComparaCodigo encargada de crear objetos de tipo Compara codigo
 */
public class CompareCodigo implements Comparator<Servicio>{

	/**
	 * Metodo heredado de la interfas Comparator. Encargada de comprar dos codigos
	 */
	public int compare(Servicio o1, Servicio o2) {
		if(o1.getCodigo() > o2.getCodigo()){
			return 1;
		}
		if(o1.getCodigo() < o2.getCodigo()){
			return -1;
		}
		return 0;
	}

}
