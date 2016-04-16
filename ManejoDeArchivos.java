/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import co.edu.javeriana.empresaAmbulancias.negogio.EmpresaAmbulancias;
import co.edu.javeriana.empresaAmbulancias.negogio.IServiciosAmbulancias;

/**
 * @author Miguel Baron y Santiago Chaustre
 * Clase ManejoDeArchivos encargada de manejar el flujo de datos entre los archivo y el sistema
 */
public class ManejoDeArchivos {
	/**
	 * Metodo para leer un archivo tipo texto con la infirmacion de las IPS
	 * @param empresa Variable Empresa con la empresa
	 * @param nombreArch Variable String con el nombre del archivo a buscar
	 * @return Variable boolena
	 */
	public static boolean leerDatosIPS(IServiciosAmbulancias empresa, String nombreArch) {
		File inFile = new File("./" + nombreArch);
		Scanner input = null;
		String linea;
		String nombre;
		String atencion;
		String tipoDirec;
		int calle;
		int carrera;
		int numero;
		boolean resultado=false;
		try {
			input = new Scanner(inFile);
			linea = input.nextLine().trim();
			linea = input.nextLine().trim();
			while (!linea.equals("0")) {
				StringTokenizer token = new StringTokenizer(linea,"*");
				nombre = token.nextToken().trim();
				atencion = token.nextToken().trim();
				tipoDirec = token.nextToken().trim();
				calle = Integer.parseInt(token.nextToken().trim());
				carrera = Integer.parseInt(token.nextToken().trim());
				numero = Integer.parseInt(token.nextToken().trim());
				empresa.agregarIPS(nombre, atencion, tipoDirec, calle, carrera, numero);	
				linea = input.nextLine();
			}
			resultado=true;
		} catch (FileNotFoundException e) {
			System.out.println("Error en ruta de archivo:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error leyendo del archivo:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("excepcion inesperada:" + e.getMessage());
		} finally {
			try{
				input.close();
			}
			catch(Exception e){
				System.out.println("Error al cerrar el archivo.");
			}
		}
		return resultado;
	}
/**
 * Metodo para leer un archivo tipo texto con los datos de las ambulancias
 * @param empresa Variable Empresa con la empresa
 * @param nombreArch Variable String con el nombre del archivo a buscar
 * @return Variable boolean
 */
	public static boolean leerDatosAmbulancias(IServiciosAmbulancias empresa,
			String nombreArch) {
		File inFile = new File("./" + nombreArch);
		Scanner input = null;
		String linea;
		String tipoAmbulancia;
		int codigo;
		String placa;
		String medicoEnfermero;
		String tipoUCI;
		boolean resultado=false;
		try {
			input = new Scanner(inFile);
			linea = input.nextLine().trim();
			linea = input.nextLine().trim();
			linea = input.nextLine().trim();
			linea = input.nextLine().trim();
			while (!linea.equals("0")) {
				StringTokenizer token = new StringTokenizer(linea, "*");
				tipoAmbulancia = token.nextToken().trim();
				codigo = Integer.parseInt(token.nextToken().trim());
				placa = token.nextToken().trim();
				medicoEnfermero = token.nextToken().trim();
				if (token.hasMoreTokens())
				{
					tipoUCI = token.nextToken().trim();
					empresa.agregarAmbulancia(tipoAmbulancia,codigo, placa, medicoEnfermero, tipoUCI);
				}else{
					empresa.agregarAmbulancia(tipoAmbulancia,codigo, placa, medicoEnfermero, "");
				}
				linea = input.nextLine().trim();
			}
			resultado = true;
		} catch (FileNotFoundException e) {
			System.out.println("Error en ruta de archivo:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error leyendo del archivo:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("excepcion inesperada:" + e.getMessage());
		} finally {
			try{
				input.close();
			}
			catch(Exception e){
				System.out.println("Error al cerrar el archivo.");
			}
		}
		return resultado;
	}
}