/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.negogio;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miguel Baron y Santiago Chaustre
 * Clase IPs encargada de crear objetos de tipo IPS
 */
public class IPS {
	private String nombre;
	private String tipoAtencion;
	private Direccion direccion;
	private List<Servicio> servicios;
	
	/**
	 * Contructor de la clase IPS
	 * @param nombre Variable String con el nombre de la IPS
	 * @param tipoAtencion Variable String con el tipo de servicio que presta la IPS
	 */
	public IPS(String nombre, String tipoAtencion) {
		super();
		this.nombre = nombre;
		this.tipoAtencion = tipoAtencion;
		this.servicios = new ArrayList<Servicio>();
	}
	
/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the tipoAtencion
	 */
	public String getTipoAtencion() {
		return tipoAtencion;
	}

	/**
	 * @param tipoAtencion the tipoAtencion to set
	 */
	public void setTipoAtencion(String tipoAtencion) {
		this.tipoAtencion = tipoAtencion;
	}

	/**
	 * @return the direccion
	 */
	public Direccion getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the servicios
	 */
	public List<Servicio> getServicios() {
		return servicios;
	}

	/**
	 * @param servicios the servicios to set
	 */
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%-20s %-20s %-20s", nombre, tipoAtencion, imprimirDireccion());
	}
	/**
	 * Metodo para imprimir la direcion del servicio
	 * @return retorna un String con los datos a imprimir
	 */
	public String imprimirDireccion(){
		if(direccion.getTipoDirec().equalsIgnoreCase("CALLE")){
			return direccion.getTipoDirec() + " " + direccion.getCalle() + " # " +
					direccion.getCarrera() + "-" + direccion.getNumero();
		}else{
			return direccion.getTipoDirec() + " " + direccion.getCarrera() + " # " +
					direccion.getCalle() + "-" + direccion.getNumero();
		}
	}
/**
 * Metodo para asignar una direccion a la IPS
 * @param tipoDirec Variable String con el tipo de direccion CALLE o CARRERA
 * @param calle Variable int con el numero de calle
 * @param carrera Variable int con el numero de carrera
 * @param numero Varible int con el numero de vivienda
 */
	public void asignarDireccion(String tipoDirec, int calle, int carrera, int numero){
		direccion = new Direccion(tipoDirec, calle, carrera, numero);
	}
}
