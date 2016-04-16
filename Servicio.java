/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.negogio;

import java.util.Calendar;
import java.util.GregorianCalendar;

import co.edu.javeriana.empresaAmbulancias.presentacion.Utils;

/**
 * @author Miguel Baron y Santiago Chaustre
 * Clase Servicio encargada de crear objetos de tipo servicio
 */
public class Servicio {
	private static long CONSECUTIVO=0;
	private long codigo;
	private GregorianCalendar horaSoli;
	private String paciente;
	private String tipoSer;
	private String telefono;
	private String estado;
	private Direccion direccion;
	private IPS ips;
	private Ambulancia ambulancia;
	/**
	 * Constructor de la clase Servicio
	 * @param horaSoli Variable GregorianCalendar con la hora actual de solicitud
	 * @param paciente Variable String con el nombre del paciente
	 * @param tipoSer Variable String con el tipo de servicio URGENCIA o EMERGENCIA
	 * @param telefono Variable String con el telefono del paciente
	 * @param estado Varible String con el estado del servicio NO_ASIGNADO, ASIGNADO o FINALIZADO
	 */
	public Servicio(GregorianCalendar horaSoli, String paciente, String tipoSer, String telefono, String estado) {
		super();
		this.codigo = CONSECUTIVO;
		this.horaSoli = horaSoli;
		this.paciente = paciente;
		this.tipoSer = tipoSer;
		this.telefono = telefono;
		this.estado = estado;
		this.CONSECUTIVO++;
	}
	/**
	 * @return the codigo
	 */
	public long getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the horaSoli
	 */
	public GregorianCalendar getHoraSoli() {
		return horaSoli;
	}
	/**
	 * @param horaSoli the horaSoli to set
	 */
	public void setHoraSoli(GregorianCalendar horaSoli) {
		this.horaSoli = horaSoli;
	}
	/**
	 * @return the paciente
	 */
	public String getPaciente() {
		return paciente;
	}
	/**
	 * @param paciente the paciente to set
	 */
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	/**
	 * @return the tipoSer
	 */
	public String getTipoSer() {
		return tipoSer;
	}
	/**
	 * @param tipoSer the tipoSer to set
	 */
	public void setTipoSer(String tipoSer) {
		this.tipoSer = tipoSer;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
	 * @return the ips
	 */
	public IPS getIps() {
		return ips;
	}
	/**
	 * @param ips the ips to set
	 */
	public void setIps(IPS ips) {
		this.ips = ips;
	}
	/**
	 * @return the ambulancia
	 */
	public Ambulancia getAmbulancia() {
		return ambulancia;
	}
	/**
	 * @param ambulancia the ambulancia to set
	 */
	public void setAmbulancia(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(int numero) {
		if(numero == 1){
			return String.format("%-6d %-13s %-16s %-12s %-8s %-20s %-15s", codigo,Utils.convertirFechaString(horaSoli) 
					+ Utils.convertirHorasMinutos(horaSoli),paciente,tipoSer,telefono, imprimirDireccion(), estado);
		}
		if(numero == 2){
			    String nomIPS = ips != null ? ips.getNombre() : " ";
				return String.format("%-6d %-16s %-8d %-15s", codigo, paciente, ambulancia.getCodigo(), nomIPS);
		}
		if(numero == 3){
			return String.format("%-6d %-13s %-16s %-12s %-7s %-20s %-10s %-15d", codigo,Utils.convertirFechaString(horaSoli) 
					+ Utils.convertirHorasMinutos(horaSoli),paciente,tipoSer,telefono, imprimirDireccion(), estado, calcularValor(horaSoli));
		}
		return null;
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
	 * Metodo para asignar una direccion al servicio
	 * @param tipoDirec Variable String que contiene el tipo de direccion CALLE o CARRERA
	 * @param calle Variable int con numero de calle
	 * @param carrera Variable int con numero de carrera
	 * @param numero Variable int con numero de vivienda
	 */
	public void asignarDireccion(String tipoDirec, int calle, int carrera, int numero){
		direccion = new Direccion(tipoDirec, calle, carrera, numero);
	}
	/**
	 * Metodo encargado de calcular el valor de la tarifa si es fin de semana o no
	 * @param horaSoli Variable de tipo GregorianCalendar con la fecha en la que se pidio el servcio
	 * @return Variable long con el valor de la tarifa
	 */
	public long calcularValor(GregorianCalendar horaSoli){
		if(horaSoli.get(Calendar.DAY_OF_WEEK) == 1 || horaSoli.get(Calendar.DAY_OF_WEEK)==7){
			return (long)(ambulancia.getTARIFA_BASE() + (ambulancia.getTARIFA_BASE()*0.2));
		}
		return ambulancia.calcularTarifa();
	}
}
