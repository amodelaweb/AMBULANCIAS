/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.negogio;

import java.util.Comparator;

/**
 * @author Miguel Baron y Santiago Chaustre
 * Clase ComparaNombre encargada de instanciar objetos de tipo compara nombre
 */
public class ComparaNombre implements Comparator<String>{
	/**
	 * Metodo heredado de la interfas Comparator. Encargado de comparar dos nombres
	 */
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}
}
