/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.negogio;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miguel Baron y Santiago Chaustre
 * Clase Ambulancia encargada de crear objetos de tipo Ambulancias
 */
public abstract class Ambulancia {
	protected int codigo;
	protected String placa;
	protected final long TARIFA_BASE = 80000;
	protected String horaPosi;
	protected int posiCalle;
	protected int posiCarrera;
	protected List<Servicio> servicios;
	/**
	 * Contructor de ambulancia
	 * @param codigo Variable int con el codigo de la ambulancia
	 * @param placa Variable String con la placa de la ambulancia
	 */
	public Ambulancia(int codigo, String placa) {
		super();
		this.codigo = codigo;
		this.placa = placa;
		this.servicios = new ArrayList<Servicio>();
	}
	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}
	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	/**
	 * @return the tARIFA_BASE
	 */
	public long getTARIFA_BASE() {
		return TARIFA_BASE;
	}
	/**
	 * @return the horaPosi
	 */
	public String getHoraPosi() {
		return horaPosi;
	}
	/**
	 * @param horaPosi the horaPosi to set
	 */
	public void setHoraPosi(String horaPosi) {
		this.horaPosi = horaPosi;
	}
	/**
	 * @return the posiCalle
	 */
	public int getPosiCalle() {
		return posiCalle;
	}
	/**
	 * @param posiCalle the posiCalle to set
	 */
	public void setPosiCalle(int posiCalle) {
		this.posiCalle = posiCalle;
	}
	/**
	 * @return the posiCarretera
	 */
	public int getPosiCarrera() {
		return posiCarrera;
	}
	/**
	 * @param posiCarrera the posiCarretera to set
	 */
	public void setPosiCarrera(int posiCarrera) {
		this.posiCarrera = posiCarrera;
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
	public String toString() {
			return String.format("%-17s %-6d %-10s %-10s %-5d %-5d %-8d",tipo(), codigo, placa, horaPosi, posiCalle,
					posiCarrera , calcularTarifa());
	}
	public String reportaAmbulancias(){
		return String.format("%-8d %-13s %-18s %-18s %-15d %-4s",codigo,placa,horaPosi,posiCalle,
				posiCarrera,ServiciActivo());
	}
	/**
	 * Metodo para buscar el tipo de ambulancia
	 * @return Variable String con el nombre del tipo de ambulancia
	 */
	private String tipo(){
		if(this instanceof AmbulanciaBasica){
			return "Basica";
		}
		if ( this instanceof AmbulanciaUCI)
		{
			return "UCI";
		}
		if (this instanceof AmbulanciaNoMedicalizada)
		{
			return "NO_MEDICALIZADA";
		}
		return " " ;
	}
	/**
	 * Metoso para calcular la tarifa de una ambulancia
	 * @return Variable de tipo long con la tarifa de la ambulancia
	 */
	public abstract long calcularTarifa();
	/**
	 * Metodo para imprimir los codigos de los servicios que se encuentran asignados
	 * @return Variable de tipo string con el codigo del servicio
	 */
	private String ServiciActivo(){
		for(Servicio x : servicios){
			if(x.getEstado().equalsIgnoreCase("ASIGNADO")){
				String y = "" + x.getCodigo();
				return y;
			}
		}
		return "";
	}
}
