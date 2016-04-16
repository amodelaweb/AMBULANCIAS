/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.presentacion;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * @author Miguel Baron y Santiago Chaustre
 * Clase Utils encargada de realizar operaciones auxiliares necesesarias para el sistema
 */
public class Utils {
	/**
	 * Metodo para convertir una fecha de tipo GregoraiCalendar en String
	 * @param fecha variable GragorianCalendar con una fecha
	 * @return Variable String con la fecha ingresada como parametro
	 */
	public static String convertirFechaString(GregorianCalendar fecha){
		String[] meses = new String[12];
		meses[0]= "Enero"; meses[1]= "Febrero"; meses[2]="Marzo"; meses[3]= "Abril";
		meses[4]= "Mayo"; meses[5]= "Junio"; meses[6]= "Julio"; meses[7]= "Agosto";
		meses[8]= "Septiembre"; meses[9]= "Octubre"; meses[10]= "Noviembre"; meses[11]="Diciembre";
		int dia = fecha.get(Calendar.DATE);
		int mes = fecha.get(Calendar.MONTH);
		String fecha1 = meses[mes] + " " + dia + " ";
		
		return fecha1;
	}
	/**
	 * Metodo para confertir una fecha en minutos y horas
	 * @param fecha variable GragorianCalendar con una fecha
	 * @return Variable String con la hora y minutos de la fecha ingresada como parametro
	 */
	public static String convertirHorasMinutos(GregorianCalendar fecha){
		if(fecha.get(Calendar.MINUTE)<10){
			return fecha.get(Calendar.HOUR)+":0"+fecha.get(Calendar.MINUTE);
		}else{
			return fecha.get(Calendar.HOUR)+":"+fecha.get(Calendar.MINUTE);
		}
	}
	/**
	 * Metodo encargaso de convertir una fecha de tipo String en una fecha de tipo GregorianCalendar
	 * @param fecha Varible String con una fecha
	 * @return Variable de tipo GregorianCalendar con le fecha introducida
	 */
public static GregorianCalendar convertirStringFecha (String fecha){
		StringTokenizer token;
		String dia;
		String mes;
		String anio;
		int anioInt;
		int mesInt;
		int diaInt;
		GregorianCalendar fecha1;
		
		token = new StringTokenizer(fecha, "-");
		anio = token.nextToken();
		mes = token.nextToken();
		dia = token.nextToken();
		anioInt = Integer.parseInt(anio);
		mesInt = Integer.parseInt(mes);
		diaInt = Integer.parseInt(dia);
		fecha1 = new GregorianCalendar(anioInt, mesInt, diaInt);
		
		return fecha1;
	}
}
