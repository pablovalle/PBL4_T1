

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ControladorPuerta implements ActionListener, PropertyChangeListener {

	GUI vista;
	Comunicador comunicador;
		
	static String claveIntroducida;
	
	final String MENSAJE_ERROR = "Error$";
	final String MENSAJE_CORRECTO = "Correcto$";
	
	boolean estadoHabitacion;
	boolean puertaRecepcion;
	
	public ControladorPuerta(GUI vista, Comunicador comunicador) {
		this.vista = vista;
		this.comunicador = comunicador;
		puertaRecepcion = true;
	}
	
	public static void setClaveIntroducida(String clave) {
		claveIntroducida = clave;
	}
	
	public boolean comprobarClave(String claveIntro) {
		String claveDB = "";
		if (vista.getEstadoRecepcion()) {
			claveDB = DAOHabitacion.getClaveRecepcion(vista.getNumHotel());
		}
		else {
			claveDB = DAOHabitacion.getClaveHabitacion(vista.getNumHabitacion());
		}
		vista.escribirLinea("Clave BBDD: " + claveDB);	
		return claveIntro.contentEquals(claveDB);
	}
	
	public void getEstadoHabitacion() {
		String texto = "";
		texto = DAOHabitacion
				.getEstadoHabitacion(vista.getNumHabitacion(), vista.getNumHotel());
		comunicador.enviarDatos("SI$");
		if (texto.contentEquals("limpiezaSI")) estadoHabitacion = true;
		else estadoHabitacion = false;
	}
	
	public void cambiarEstadoHabitacion(String cambioEstado) {
		String ret = "";
		ret = DAOHabitacion.setEstadoHabitacion(vista.getNumHabitacion(), vista.getNumHotel(),cambioEstado);
		if (cambioEstado.contentEquals("para hacer")) DAOTarea.crearTarea(vista.getNumHotel(), vista.getNumHabitacion(), 1);
		vista.escribirLinea("Resultado del cambio: " + ret);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "rbHabitacion":
			vista.setVisiblePanelHabitacion(true);
			puertaRecepcion = false;
			break;
		case "rbRecepcion":
			vista.setVisiblePanelHabitacion(false);
			puertaRecepcion = true;
			break;
		case "conectar":
			if (comunicador.conectar()) {
				if (comunicador.getEstadoConexion() == true) {
					if (comunicador.inicializarInputOutput() == true) {
						comunicador.inicializarListeners();
					}
				}
				vista.setBotonConectar(false);
				vista.setbotonDesconectar(true);
				vista.escribirLinea("Realice reset de la placa para continuar.");
			}
			break;
		case "desconectar":
			comunicador.desconectar();
			vista.setBotonConectar(true);
			vista.setbotonDesconectar(false);
			vista.setEstadoTexfields(true);
			break;
		case "empezar":
			if (vista.getNumHotel().length()<1) {
				vista.escribirLinea("Introduzca número de hotel.");
				break;
			}
			if (vista.getEstadoRecepcion()) {
				comunicador.enviarDatos("Puerta recepcion$");
				vista.escribirLinea("Configurada simulación: puerta RECEPCION." );
				vista.setBotonEmpezar(false);

			}
			else {
				if (vista.getNumHabitacion().length()>= 1) {
					comunicador.enviarDatos("Puerta habitacion$");
					vista.escribirLinea("Configurada simulación: puerta HABITACION." );
					vista.setBotonEmpezar(false);
					vista.setEstadoTexfields(false);

				}
				else vista.escribirLinea("Introduzca número de habitación.");
			}
			break;
		default:
			vista.escribirLinea("TODO No está preparado aun para el comando: " + comando);		
		}
	}
	

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String evento = evt.getPropertyName();
		
		switch(evento) {
		case "clave":
			String clave = (String) evt.getNewValue();
			if(comprobarClave(clave)) { 
				comunicador.enviarDatos(MENSAJE_CORRECTO);
				vista.getTextArea().append("Entrada correcta. Pase.\n");
				if(puertaRecepcion) {
					cambiarEstadoHabitacion("hecho");
				}
			}
			else {
				comunicador.enviarDatos(MENSAJE_ERROR);
				vista.getTextArea().append("Entrada incorrecta. Vuelva a intentarlo.\n");

			}
			break;
		case "reset":
			vista.setBotonEmpezar(true);
			vista.escribirLinea("Reset correcto");
			break;
		case "cambio":
			String value = (String) evt.getNewValue();
			String cambioEstado = ((value.contains("si"))? "para hacer" : "hecho");
			vista.escribirLinea(cambioEstado);
			cambiarEstadoHabitacion(cambioEstado);
			break;
		case "out":
			System.out.println("Ha salido");
			break;
		default:
			vista.getTextArea().append("No se contempla esa operativa todavía.");
		}
		
	}
	
	
}
