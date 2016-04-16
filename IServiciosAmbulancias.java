/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.negogio;

import java.util.List;
import java.util.Map;

/**
 * @author Miguel Baron
 *
 */
public interface IServiciosAmbulancias {
	/**
	 * Metodo para agregra una IPS a la empresa
	 * @param nombre Variable String con el nombre de la IPS
	 * @param atencion tipo Variable String con el tipo de servicio de la IPS
	 * @param tipoDirec Variable String con el tipo de direccion CALLE o CARRERA
	 * @param calle Variable int con el numero de cale
	 * @param carrera Variable int con el numero de carrera
	 * @param numero Variable int con el numero de vivienda
	 */
	void agregarIPS(String nombre, String atencion, String tipoDirec,
			int calle, int carrera, int numero);
	/**
	 * Metodo para agregar una ambulancia a la empresa
	 * @param codigo Varianle int con el codigo de la ambulancia
	 * @param placa Variable String con la placa de la ambulancia
	 * @param tipoAmbulancia Variable String con el tipo de ambulancia 
	 * @param medicoEnfermero Variable String con el nombre del medio o enfermero
	 * @param tipoUCI Variable String con el tipo de UCI
	 */
	void agregarAmbulancia(String tipoAmbulancia,int codigo, String placa, 
			String medicoEnfermero, String tipoUCI);
	/**
	 * Metodo para reguistrar la pocision de una ambulancia
	 * @param codigo Variable int con el codigo de la ambulancia
	 * @param calle Variable int con el numero de calle
	 * @param carrera Varianle int con el numero de carrera
	 * @return Variable boolean 
	 */
	boolean registrarPosicionAmbulancia(int codigo, int calle,
			int carrera);
	/**
	 * Metodo para registrar un servicio a la empresa
	 * @param paciente Variable String con el nombre del paciente
	 * @param tipoServi Variable String con el tipo de servicio del paciente URGENCIA o EMERGENCIA
	 * @param tel Variable String con el numero de telefono del paciente
	 * @param tipoDirec Varible String con el tipo de direccfion CARRERA o CALLE
	 * @param calle Variable int con el numero de calle
	 * @param carrera Variable int con e numero de carrera
	 * @param numero Variable int con el numero de vivienda
	 * @return Variable int con el codigo del servicio registrado
	 */
	int registrarServicio(String paciente, String tipoServi, String tel,
			String tipoDirec, int calle, int carrera, int numero);
	/**
	 * Metodo para asignar un servicio a una ambulancia y a una IPS
	 * @param codigo Variable int con el codigo del servicio
	 * @return Variable String con el codigo de ambulancia y nombre de la IPS a la que se asigno el servicio
	 */
	String asignarServicio(int codigo);
	/**
	 * Metodo para finalizar un servicio
	 * @param codigo Variable int con el codigo del servicio
	 * @return Variable  boolean
	 */
	boolean finalizarServicio(int codigo);
	/**
	 * Metodo encargado de dar visibilidad a los servicios
	 * @return the servicios
	 */
	List<Servicio> getServicios();
	/**
	 * Metodo de dar visibilidad a las ambulancias
	 * @return the ambulancias
	 */
	Map<Integer, Ambulancia> getAmbulancias();
	/**
	 * Metodo encargado de dar visibilidad a las IPS
	 * @return the losIPS
	 */
	Map<String, IPS> getLosIPS();
	
}
