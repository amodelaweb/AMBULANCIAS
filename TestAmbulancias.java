/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.presentacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import co.edu.javeriana.empresaAmbulancias.negogio.Ambulancia;
import co.edu.javeriana.empresaAmbulancias.negogio.AmbulanciaBasica;
import co.edu.javeriana.empresaAmbulancias.negogio.AmbulanciaNoMedicalizada;
import co.edu.javeriana.empresaAmbulancias.negogio.AmbulanciaUCI;
import co.edu.javeriana.empresaAmbulancias.negogio.ComparaFechas;
import co.edu.javeriana.empresaAmbulancias.negogio.ComparaNombre;
import co.edu.javeriana.empresaAmbulancias.negogio.CompareCodigo;
import co.edu.javeriana.empresaAmbulancias.negogio.EmpresaAmbulancias;
import co.edu.javeriana.empresaAmbulancias.negogio.IPS;
import co.edu.javeriana.empresaAmbulancias.negogio.IServiciosAmbulancias;
import co.edu.javeriana.empresaAmbulancias.negogio.Servicio;
import co.edu.javeriana.empresaAmbulancias.persistencia.ManejoDeArchivos;

/**
 * @author Miguel Baron y Santiago Chaustre
 * Clase TestAmbulancia encargada de ejecutar el sistema
 */
public class TestAmbulancias {

	/**
	 * Metodo encargado de ejecutar el sistema
	 * @param args Varible String del metodo main
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String lee;
		IServiciosAmbulancias empresa = new EmpresaAmbulancias("Emergency");
		int opcion=0;
		
		do{
			opcion = mostrarMenu(opcion);
			switch (opcion){
				case 1:				
					System.out.println("Ingrese el nombre del archivo");
					lee = in.next();
					if(ManejoDeArchivos.leerDatosIPS(empresa, lee)){
						System.out.println("IPS cargadas correctamente");
					};
					break;
				case 2:
					System.out.println("Ingrese el nombre del archivo");
					lee = in.next();
					if(ManejoDeArchivos.leerDatosAmbulancias(empresa, lee)){
						System.out.println("Ambulancias cargadas correctamente");
					};
					break;
				case 3:
					registrarPosicionAmbulancia(empresa);
					break;
				case 4:
					int codigo = registrarServicio(empresa);
					System.out.println("El servicio se a registrado correctamente con el codigo: " + codigo);
					break;
				case 5:
					reporteAmbulancias(empresa);
					break;
				case 6:
					asignarUnaAmbulanciaIPS(empresa);
					break;
				case 7:
					finalizarServicio(empresa);
					break;
				case 8:
					ReporteServicioIPS(empresa);
					break;
				case 9:
					ReporteIPS(empresa);
					break;
				case 10:
					estadísticasDeAmbulanciasDisponibles(empresa);
					break;
				case 11:
					
					break;
				case 12:
					System.out.println("Fin del programa");
					break;
				default:
					System.out.println("Opcion invalida intentelo de nuevo");
					break;
			}
		}while(opcion != 12);
	}
	/**
	 * Metodo para imprimir el menu
	 * @param opcion Variable int para asignar una opcion seleccionada
	 * @return Variable int con el numero de opcion selecionado
	 */
	private static int mostrarMenu(int opcion){
		Scanner in = new Scanner(System.in);
		System.out.println("**********************************************************************");
		System.out.println("MENU");
		System.out.println("Ingrse la opccion que desea");
		System.out.println(" 1) ingresar las IPS al sistema");
		System.out.println(" 2) ingresar las ambulancias al sistema");
		System.out.println(" 3) registrar la posicion actual de una ambulancia");
		System.out.println(" 4) registrar un servicio");
		System.out.println(" 5) reporte de ambulancias");
		System.out.println(" 6) asignar a un servicio una ambulancia y una IPS");
		System.out.println(" 7) finalizar un servicio");
		System.out.println(" 8) reporte de servicios con IPS y ambulancias asignados");
		System.out.println(" 9) reporte de las IPS con servicios asociados");
		System.out.println(" 10) estadisticas de las ambulancias disponibles");
		System.out.println(" 11) pacientes atendidos");
		System.out.println(" 12) finalizar programa");
		return opcion = in.nextInt();
	}
	/**
	 * Metodo para registrar la posicion de una ambulancia
	 * @param empresa Variable Empresa  un objeto de tipo Empresa
	 */
	private static void registrarPosicionAmbulancia(IServiciosAmbulancias empresa){
		Scanner in = new Scanner(System.in);
		int codigo;
		int calle;
		int carrera;
		System.out.println("Ingrese el codigo de la ambulancia");
		codigo = Integer.parseInt(in.next());
		System.out.println("Ingrese la calle");
		calle = Integer.parseInt(in.next());
		System.out.println("Ingrese la carrera");
		carrera = Integer.parseInt(in.next());
		if(empresa.registrarPosicionAmbulancia(codigo, calle, carrera)){
			System.out.println("Sea registrado correctamente la ambulancia");
		}else{
			System.out.println("Fallo al registrar la ambulancia");
		}
	}
	/**
	 * Metodo para registrar un servicio
	 * @param empresa Variable Empresa con la empresa
	 * @return Variable Empresa  un objeto de tipo Empresa
	 */
	private static int registrarServicio(IServiciosAmbulancias empresa){
		Scanner in = new Scanner(System.in);
		String nombre, tipoSer, tel, tipoDirec;
		System.out.println("Ingrese los datos del pasiente nombre, tipo de servio(URGENCIA o EMERGENCIA) y telefono separados por (*)");
		String datos = in.nextLine();
		StringTokenizer token = new StringTokenizer(datos, "*");
		nombre = token.nextToken().trim();
		tipoSer = token.nextToken().trim();
		tel = token.nextToken().trim();
		System.out.println("Ingrese los datos de la direccion tipo de direccion(CALLE o CARRERA), calle, carrera y numero separados por (*)");
		datos = in.nextLine();
		token = new StringTokenizer(datos, "*");
		int calle, carrera, numero;
		tipoDirec = token.nextToken().trim();
		calle = Integer.parseInt(token.nextToken().trim());
		carrera = Integer.parseInt(token.nextToken().trim());
		numero = Integer.parseInt(token.nextToken().trim());
		return empresa.registrarServicio(nombre, tipoSer, tel, tipoDirec, calle, carrera, numero);
	}
	/**
	 * Metodo para imprimir un reporte de las ambulancias
	 * @param empresa Variable Empresa  un objeto de tipo Empresa
	 */
	private static void reporteAmbulancias(IServiciosAmbulancias empresa){
		System.out.println();
		System.out.println("REPORTE AMBULANCIAS \n");
		System.out.println("Codigo   Placa     Hora_Posicion     Posicion_Calle  Posicion_Carretera  Servicio");
		System.out.println("--------------------------------------------------------------------------------------------------------");
		Set<Integer> llaves = empresa.getAmbulancias().keySet();
		List<Integer> ordenLlaves = new ArrayList(llaves);
		Collections.sort(ordenLlaves);
		for(int x : ordenLlaves){
			System.out.println(empresa.getAmbulancias().get(x).reportaAmbulancias());
		}
	}
	/**
	 * Metodo para asignar una ambulancia y una IPS a un servicio
	 * @param empresa Variable Empresa  un objeto de tipo Empresa
	 */
	private static void asignarUnaAmbulanciaIPS(IServiciosAmbulancias empresa){
		Scanner in = new Scanner(System.in);
		int codigo;
		System.out.println("ASIGNAR UN SERVICIO A UNA AMBULANCIA Y A UN IPS");
		System.out.println("Se muestran los servicios del sistema sin asignar:");
		System.out.println("Codigo HoraSolicitud Paciente        TipoServicio Telefono Direccion");
		System.out.println("-------------------------------------------------------------------------------");
		Collections.sort(empresa.getServicios(), new ComparaFechas());
		for(Servicio x: empresa.getServicios()){
			if(x.getEstado().equalsIgnoreCase("NO_ASIGNADO")){
				System.out.println(x.toString(1));
			}
		}
		System.out.println("Selecione el codigo del servico");
		codigo = Integer.parseInt(in.next());
		System.out.println(empresa.asignarServicio(codigo));
	}
	/**
	 * Metodo para finalizar un servicio
	 * @param empresa Variable Empresa  un objeto de tipo Empresa
	 */
	public static void finalizarServicio(IServiciosAmbulancias empresa){
		Scanner in = new Scanner(System.in);
		int codigo;
		System.out.println("FINALIZAR UN SERVICIO");
		System.out.println("Semuestran los servicios actulamente asignados:");
		System.out.println("Codigo Paciente     Ambulancia   IPS");
		System.out.println("----------------------------------------------------");
		Collections.sort(empresa.getServicios(), new CompareCodigo());
		for(Servicio x : empresa.getServicios()){
			if(x.getEstado().equalsIgnoreCase("ASIGNADO")){
				System.out.println(x.toString(2));
			}
		}
		System.out.println("Seleccione el codigo del servicio");
		codigo = Integer.parseInt(in.next());
		if(empresa.finalizarServicio(codigo)){
			System.out.println("Exito al finalizar el servicio");
		}else{
			System.out.println("Falla al finalizar el servicio");
		}
	}
	/**
	 * 	La funcion ReporteServicioIPS imprime los datos por pantalla de cada servicio 
	 *  registrado en la base de datos el programa y tambien imprime los datos de su ambulancia
	 *  y la IPS asignada , recibe como parametros la empresa y se realiza mediante un for
	 *  para el servicio y los metodos get ambulancia y get IPS
	 * @param empresa Variable Empresa  un objeto de tipo Empresa
	 */
		private static void ReporteServicioIPS(IServiciosAmbulancias empresa) {
			System.out.println("--REPORTE DE SERVICIOS CON IPS Y AMBULANCIAS ASOCIADAS ");
			for (Servicio x : empresa.getServicios()) {
				if (x.getEstado().equalsIgnoreCase("ASIGNADO") || x.getEstado().equalsIgnoreCase("FINALIZADO")) {
					System.out.println("\nSERVICIO : ");
					System.out.println("Codigo HoraSolicitud Paciente        TipoServicio Telefono Direccion            Estado     Valor");
					System.out.println("----------------------------------------------------------------------------------------------------");
					System.out.println(x.toString(3));
					System.out.println("\n\t IPS asignada: ");
					String mensaje = x.getIps() != null ? x.getIps().toString() : "No tiene IPS asignada";
					System.out.println("\t Nombre               TipoAtencion         Direccion");
					System.out.println("\t --------------------------------------------------------------------------------");
					System.out.println("\t " + mensaje);
					System.out.println("\n\t Ambulancia asignada: ");
					System.out.println("\t TipoAmb         Codigo   Placa  Horaposicion Calle Carrera Tarifa   Medico/Enfermero    TipoUCI");
					System.out.println("\t --------------------------------------------------------------------------------------------");
					System.out.println("\t " + x.getAmbulancia().toString());
				}
			}
		}
		/**
		 * La funcion ReporteIPS recibe como parametro un objeto de EmpresaAmbulancias
		 * el objetivo de esta funcion es imprimir en pantalla los datos de cada IPS y 
		 * los datos de cada uno de sus servicios arignados y finalizados , mediante doble
		 * for consizo.
		 * @param empresa Variable Empresa  un objeto de tipo Empresa
		 */
	    private static void ReporteIPS (IServiciosAmbulancias empresa){
	    	System.out.println("--REPORTE DE LAS IPS CON SERVICIOS ASOCIADOS");
	    	Set<String>llaves = empresa.getLosIPS().keySet();
	    	List<String>nombres = new ArrayList<String>(llaves);
	    	Collections.sort(nombres, new ComparaNombre());
	    	for (String x : nombres){
	    		System.out.println("IPS asignada :");
	    		System.out.println("Nombre               TipoAtencion         Direccion");
	    		System.out.println("----------------------------------------------------------");
	    		System.out.println(empresa.getLosIPS().get(x).toString());
				Collections.sort(empresa.getLosIPS().get(x).getServicios(), new ComparaFechas());
	    		for (Servicio y : empresa.getLosIPS().get(x).getServicios()){
	    			System.out.println("\n\tSERVICIOS : ");
					System.out.println("\tCodigo HoraSolicitud  Paciente         TipoServicio Telefono Direccion            Estado       Ambul.");
	    			System.out.println("\t-------------------------------------------------------------------------------------------------------");
	    			if(y.getEstado().equalsIgnoreCase("ASIGNADO")||y.getEstado().equalsIgnoreCase("FINALIZADO")){
	    				System.out.println("\t"+y.toString(1)+y.getAmbulancia().getCodigo());
	    			}
	    		}
	    		System.out.println();
	    	}
	    }
	    /**
	     * Metodo encargado de hacer una estadistica de cuantas ambulancias se encuentran 
	     * disponibles para realizar un servicio, imprimiendo por pantalla la cantidad de
	     * ambulancias disponibles
	     * @param empresa Variable Empresa  un objeto de tipo Empresa
	     */
	    private static void estadísticasDeAmbulanciasDisponibles(IServiciosAmbulancias empresa){
	    	int ambuUCI=0, ambuBasi=0, ambuNoMedi=0;
	    	boolean b1;
	    	System.out.println("ESTADISTICAS DE LAS AMBULANCIAS DISPONIBLES\n");
			Set<Integer> llaves = empresa.getAmbulancias().keySet();
			for(int x : llaves){
				b1=false;
				for(Servicio y : empresa.getAmbulancias().get(x).getServicios()){
					if(y.getEstado().equalsIgnoreCase("ASIGNADO")){
						b1=true;
					}
				}
				if(!b1){
					ambuUCI += empresa.getAmbulancias().get(x) instanceof AmbulanciaUCI ? 1 : 0;
					ambuBasi += empresa.getAmbulancias().get(x) instanceof AmbulanciaBasica ? 1 : 0;
					ambuNoMedi += empresa.getAmbulancias().get(x) instanceof AmbulanciaNoMedicalizada ? 1 : 0;
				}
			}
			System.out.println("Ambul.UCI Ambul.Basica Ambul.NoMedi.");
			System.out.printf("    %-10d %-12d %-4d", ambuUCI, ambuBasi, ambuNoMedi);
			System.out.println();
	    }
	}