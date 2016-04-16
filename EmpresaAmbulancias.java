/**
 * 
 */
package co.edu.javeriana.empresaAmbulancias.negogio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import co.edu.javeriana.empresaAmbulancias.presentacion.Utils;

/**
 * @author Miguel Baron y Santiago Chaustre
 * Clase EmpresaAmbulancias encargada de crear objetos de tipo empresa ambulancias
 */
public class EmpresaAmbulancias implements IServiciosAmbulancias{
	private String nombre;
	private Map<Integer,Ambulancia> ambulancias;
	private List<Servicio> servicios;
	private Map<String,IPS> losIPS;

	/**
	 * Constructor de la clase EmpresaAmbulancias
	 * @param nombre Variable String con el nombre de la empresa
	 */
	public EmpresaAmbulancias(String nombre) {
		super();
		this.nombre = nombre;
		this.ambulancias = new Hashtable<Integer,Ambulancia>();
		this.servicios = new ArrayList<Servicio>();
		this.losIPS = new Hashtable<String,IPS>();
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Metodo heredado de IServiciosAmbulancias
	 */
	public Map<Integer, Ambulancia> getAmbulancias() {
		return ambulancias;
	}
	/**
	 * @param ambulancias the ambulancias to set
	 */
	public void setAmbulancias(Map<Integer, Ambulancia> ambulancias) {
		this.ambulancias = ambulancias;
	}
	/**
	 * Metodo heredado de IServiciosAmbulancias
	 */
	public List<Servicio> getServicios() {
		return servicios;
	}

	/**
	 * @param servicios
	 *            the servicios to set
	 */
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	/**
	 * Metodo heredado de IServiciosAmbulancias
	 */
	public Map<String, IPS> getLosIPS() {
		return losIPS;
	}

	/**
	 * @param losIPS the losIPS to set
	 */
	public void setLosIPS(Map<String, IPS> losIPS) {
		this.losIPS = losIPS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmpresaAmbulancias [nombre=" + nombre + "]";
	}
	/**
	 * Metodo heredado de la interfas IServiciosAmbulancias
	 */
	public void agregarIPS(String nombre, String atencion, String tipoDirec,
			int calle, int carrera, int numero) {
		IPS x = new IPS(nombre, atencion);
		x.asignarDireccion(tipoDirec, calle, carrera, numero);
		losIPS.put(nombre, x);
	}
	/**

	 */
	public void agregarAmbulancia(String tipoAmbulancia,int codigo, String placa, 
			String medicoEnfermero, String tipoUCI) {
		if(tipoAmbulancia.equalsIgnoreCase("BASICA")){
			Ambulancia x = new AmbulanciaBasica(codigo, placa, medicoEnfermero);
			ambulancias.put(codigo, x);
		}
		if(tipoAmbulancia.equalsIgnoreCase("UCI")){
			Ambulancia x = new AmbulanciaUCI(codigo, placa, medicoEnfermero, tipoUCI );
			ambulancias.put(codigo, x);
		}
		if(tipoAmbulancia.equalsIgnoreCase("NOMEDICALIZADA")){
			Ambulancia x = new AmbulanciaNoMedicalizada(codigo, placa, medicoEnfermero);
			ambulancias.put(codigo, x);
		}
	}
	/**
	 * Metodo heredado de la interfas IServiosAmbulancias
	 */
	public boolean registrarPosicionAmbulancia(int codigo, int calle,
			int carrera) {
		Ambulancia x = ambulancias.get(codigo);
		GregorianCalendar y = new GregorianCalendar();
		if (x != null) {
			x.setPosiCalle(calle);
			x.setPosiCarrera(carrera);
			x.setHoraPosi(Utils.convertirHorasMinutos(y));
			ambulancias.put(codigo, x);
			return true;
		} else {
			System.out
					.println("No se encontro ninguna ambulancia con el codigo ingresado");
			return false;
		}
	}
	/**
	 * Metodo heredado de la interfas IServiosAmbulancias
	 */
	public int registrarServicio(String paciente, String tipoServi, String tel,
			String tipoDirec, int calle, int carrera, int numero) {
		GregorianCalendar y = new GregorianCalendar();
		Servicio x = new Servicio(y, paciente, tipoServi, tel,
				"NO_ASIGNADO");
		x.asignarDireccion(tipoDirec, calle, carrera, numero);
		servicios.add(x);
		return (int) x.getCodigo();
	}
	/**
	 * Metodo heredado de la interfas IServiosAmbulancias
	 */
	public String asignarServicio(int codigo) {
		List<Ambulancia> ambuDisponibles = new ArrayList<Ambulancia>();
		Servicio x = buscarServicio(codigo);
		if (x != null) {
			ambuDisponibles = ConstruitAmbulanciasDisponibles(x);
			if (ambuDisponibles != null) {
				Ambulancia a = calcularAmbulanciaMasCercana(ambuDisponibles, x.getDireccion().getCalle(), x.getDireccion()
						.getCarrera());
				if(!x.getTipoSer().equalsIgnoreCase("DOMICILIO")){
					IPS i = consultaIPSMasCercana(x.getDireccion().getCalle(), x.getDireccion().getCarrera());
					for(Servicio s : servicios){
						if(s.getCodigo()==x.getCodigo()){
							s.setEstado("ASIGNADO");
							s.setAmbulancia(a);
							s.setIps(i);
							Set<Integer> llaves = ambulancias.keySet();
							for (Integer llave : llaves) {
								if (ambulancias.get(llave).getCodigo() == a.getCodigo()) {
									ambulancias.get(llave).getServicios().add(s);
								}
							}
						}
					}
					i.getServicios().add(x);
					return "El servicio " + x.getCodigo()+ " fue asignado a la ambulancia " + a.getCodigo()
							+ " y la IPS " + i.getNombre();
				}else{
					for(Servicio s : servicios){
						if(s.getCodigo()==x.getCodigo()){
							s.setEstado("ASIGNADO");
							s.setAmbulancia(a);
							s.setIps(null);
							Set<Integer> llaves = ambulancias.keySet();
							for (Integer llave : llaves) {
								if (ambulancias.get(llave).getCodigo() == a.getCodigo()) {
									ambulancias.get(llave).getServicios().add(s);
								}
							}
						}
					}	
					return "El servicio " + x.getCodigo()+ " fue asignado a la ambulancia " + a.getCodigo();
				}
			} else {
				return "No hay ambulancias disponibles";
			}
		}
		return "No se encontro un servicio con ese codigo";
	}
	/**
	 * Metodo heredado de la interfas IServiosAmbulancias
	 */
	public boolean finalizarServicio(int codigo) {
		boolean b1=false, b2=false;
		Servicio x = buscarServicio(codigo);
		x.setEstado("FINALIZADO");
		Ambulancia a = x.getAmbulancia();
		IPS i = x.getIps();
		for(Servicio s : a.getServicios()){
			if(s.getCodigo()==x.getCodigo()){
				s = x; b1=true;
			}
		}
		if(i != null){
			for(Servicio s : i.getServicios()){
				if(s.getCodigo()==x.getCodigo()){
					s=x; b2=true;
				}
			}
		}else{
			b2=true;
		}
		if(!b1){
			System.out.println("El codigo ingresado no se encontro en los servicios de la ambulancia");
			return false;
		}
		if(!b2){
			System.out.println("El codigo ingresado no se encontro en los servicios de la IPS");
			return false;
		}
		return true;
	}
	/**
	 * Metodo para buscar un servicio
	 * @param codigo Variable int con el codigo del servicio
	 * @return Variable Servicio con el objeto servicio encontrado
	 */
	private Servicio buscarServicio(int codigo) {
		for (Servicio x : servicios) {
			if (x.getCodigo() == codigo) {
				return x;
			}
		}
		return null;
	}
	/**
	 * Metodo para contruir una lista con las ambulancias disponible para un servicio de URGENCIA o EMERGENCIA
	 * @param servicio Variable Servicio con el objeto servicio
	 * @return Lista de tipo Ambulancia con las ambulancias disponibles
	 */
	private List<Ambulancia> ConstruitAmbulanciasDisponibles(Servicio servicio) {
		List<Ambulancia> ambuDisponibles = new ArrayList<Ambulancia>();
		boolean b2 = false;
		Set<Integer> llaves = ambulancias.keySet(); 
		for (int llave : llaves) {
			if (servicio.getTipoSer().equalsIgnoreCase("URGENCIA")) {
				if(ambulancias.get(llave) instanceof AmbulanciaMedicalizada){
					if(ambulanciaUrgenciaEmergencia(ambulancias.get(llave)) != null){
						ambuDisponibles.add(ambulancias.get(llave));
						b2=true;
					}
				}
			}
			if (servicio.getTipoSer().equalsIgnoreCase("EMERGENCIA")) {
				if (ambulancias.get(llave) instanceof AmbulanciaUCI) {
					if(ambulanciaUrgenciaEmergencia(ambulancias.get(llave)) != null){
						ambuDisponibles.add(ambulancias.get(llave));
						b2=true;
					}
				}
			}
			if(servicio.getTipoSer().equalsIgnoreCase("DOMICILIO")){
				if(ambulancias.get(llave) instanceof AmbulanciaNoMedicalizada){
					if(ambulanciaUrgenciaEmergencia(ambulancias.get(llave)) != null){
						ambuDisponibles.add(ambulancias.get(llave));
						b2=true;
					}
				}
			}
		}
		if (b2) {
			return ambuDisponibles;
		} else {
			return null;
		}
	}
	/**
	 * Metodo para construir una lista de ambulancias disponible
	 * @param ambulancia Variable Ambulancia con el objeto ambulancia
	 * @return Lista de tipo Ambulancia con las ambulancias disponibles
	 */
	private Ambulancia ambulanciaUrgenciaEmergencia(Ambulancia ambulancia){
		for (Servicio y : ambulancia.getServicios()){
			if (y.getEstado().equalsIgnoreCase("ASIGNADO")) {
				return null;
			}
		}
		return ambulancia;
	}
	/**
	 * Metodo para clacular la ambulancia mas sercana al servico
	 * @param ambuDisponibles Lista de tipo Ambulancia  con las ambulancias disponibles
	 * @param calle Variable int con el numero de calle del servicio
	 * @param carrera Variable int con el numero de carrera del servicio
	 * @return Variable Ambulancia con la ambulancia mas cercana
	 */
	private static Ambulancia calcularAmbulanciaMasCercana(
			List<Ambulancia> ambuDisponibles, int calle, int carrera) {
		long menor1 = 10000, menor2;
		int codigo = 0;
		for (Ambulancia x : ambuDisponibles) {
			menor2 = calcularDistancia(x.getPosiCalle(), calle,
					x.getPosiCarrera(), carrera);
			if (menor2 < menor1) {
				menor1 = menor2;
				codigo = x.getCodigo();
			}
		}
		for (Ambulancia x : ambuDisponibles) {
			if (x.getCodigo() == codigo) {
				return x;
			}
		}
		return null;
	}
	/**
	 *  Metodo para consultar la IPS mas cercana
	 * @param calle Variable int con el numero de calle
	 * @param carrera Variable int con el numero de carrera
	 * @return Variable IPS con la ips mas cercana
	 */
	private IPS consultaIPSMasCercana(int calle, int carrera) {
		long menor1 = 1000, menor2;
		String nombre = "";
		Set<String> llaves = losIPS.keySet();
		for (String x : llaves) {
			menor2 = calcularDistancia(losIPS.get(x).getDireccion().getCalle(), calle, losIPS.get(x)
					.getDireccion().getCarrera(), carrera);
			if (menor2 < menor1) {
				menor1 = menor2;
				nombre = losIPS.get(x).getNombre();
			}
		}
		return losIPS.get(nombre);
	}
	/**
	 * Metodo para calcular la distancia que hay entre dos direcciones
	 * @param calle1 Variable int con el numero de calle de la primer direccion
	 * @param calle2 Variable int con el numero de calle de la segunda direccion
	 * @param carrera1 Variable int con el numero de carrera de la primera direccion
	 * @param carrera2 Variable int con el numero de carrera de la segunda direccion
	 * @return Variable long con la distancia de las dos direcciones
	 */
	private static long calcularDistancia(int calle1, int calle2, int carrera1,
			int carrera2) {
		long menor, menor2;
		menor = calle1 - calle2;
		menor2 = carrera1 - carrera2;
		menor *= menor < 0 ? -1 : 1;
		menor2 *= menor2 < 0 ? -1 : 1;
		menor += menor2;
		return menor;
	}
}
