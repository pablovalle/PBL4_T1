import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.TooManyListenersException;

import javax.swing.JList;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

public class Comunicador implements SerialPortEventListener {

	private Enumeration puertos;
	private HashMap<String, CommPortIdentifier> mapaPuertos;
	private CommPortIdentifier puertoEnUso;
	private SerialPort puertoSerial;
	
	private InputStream input;
	private OutputStream output;
	
	private final static int DELAY = 2000;
	private final static int ASCII_LINEA_NUEVA = 10;
	private static final int BAUDRATE_9600 = 9600;
	
	String texto;
	boolean estaConectado;
	GUI vista;
	PropertyChangeSupport soporte;
	
	String datosRecibidos;
	
	public Comunicador(GUI vista) {
		super();
		this.puertos = null;
		this.mapaPuertos = new HashMap<String, CommPortIdentifier>();
		this.puertoEnUso = null;
		this.puertoSerial = null;
		this.input = null;
		this.output = null;
		this.texto = "";
		datosRecibidos = "";
		estaConectado = false;
		this.vista = vista;
		soporte = new PropertyChangeSupport(this);
	}

	public void addListener(PropertyChangeListener listener) {
		soporte.addPropertyChangeListener(listener);
	}
	
	public void removeListener(PropertyChangeListener listener) {
		soporte.removePropertyChangeListener(listener);
	}
	
	public void buscarPuertos() {
		puertos = CommPortIdentifier.getPortIdentifiers();
		
		while(puertos.hasMoreElements()) {
			CommPortIdentifier puertoActual = (CommPortIdentifier) puertos.nextElement();
			if (puertoActual.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				vista.getComboBox().addItem(puertoActual.getName());
				mapaPuertos.put(puertoActual.getName(), puertoActual);
				
			}	
		}	
	}

	public boolean conectar() {
		String puertoSeleccionado = vista.getComboBoxSeleccionado();
		puertoEnUso = (CommPortIdentifier) mapaPuertos.get(puertoSeleccionado);
		CommPort commPort = null;		
		boolean exito = false;
		try {
			commPort = puertoEnUso.open("Controlador MUTel", DELAY);
			puertoSerial = (SerialPort) commPort;
			puertoSerial.setSerialPortParams(BAUDRATE_9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
			estaConectado = true;
			texto = puertoSeleccionado + " abierto correctamente.";
			exito = true;
		} catch (PortInUseException e) {
			texto = puertoSeleccionado + " está en uso ( " + e.toString() + ")";
		} catch (UnsupportedCommOperationException e) {
			texto = "Los parámetros de configuración no son correctos";

		}catch(NullPointerException e) {
			texto = "Error: " + e.toString();
		}
		vista.escribirLinea(texto);

		return exito;		
	}
	
	public boolean inicializarInputOutput() {
		boolean exito = false;
		
		try {
			input = puertoSerial.getInputStream();
			output = puertoSerial.getOutputStream();
			texto = "Streams I/O inicializados correctamente.";
			exito = true;
		} catch (IOException e) {
			texto = "Streams de input y output no se han abierto correctamente. ( " + e.toString() + ")";
		}
		vista.escribirLinea(texto);
		
		return exito;
	}
	
	public String getDatosRecibidos() {
		return datosRecibidos;
	}
	
	public boolean inicializarListeners() {
		boolean exito = false;
		
		try {
			puertoSerial.addEventListener(this);
			puertoSerial.notifyOnDataAvailable(true);
			exito = true;
		} catch (TooManyListenersException e) {
			texto = "Error: Demasiados listeners. ( " + e.toString() + " )";
			vista.escribirLinea(texto);
		}	
		
		return exito;
	}
	
	public void desconectar() {
		
		try {
			puertoSerial.removeEventListener();		
			input.close();
			output.close();
			puertoSerial.close();
			//setConnected(false);
			estaConectado = false;
			texto = "Desconectado.";

		} catch (IOException e) {
			texto = "Fallo al cerrar. ( " + e.toString() + " )";
		}
		vista.escribirLinea(texto);
	}
	
	public boolean getEstadoConexion() {
		return estaConectado;
	}
	
	public void enviarDatos(String string) {
		
		try {
			output.write(string.getBytes());
			texto = "";
			//texto = "Envío correcto: " + string;
			output.flush();
		} catch (IOException e) {
			texto = "No se ha enviado correctamente. ( " + e.toString() + ")";
		}
		vista.escribirLinea(texto);	
	}
	
	@Override
	public void serialEvent(SerialPortEvent evento) {
		if (evento.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			
			try {
				byte dato = (byte)input.read();
				
				if (dato != ASCII_LINEA_NUEVA && dato != '$') {
					texto = new String(new byte[] {dato});
					datosRecibidos += texto;
				}
				else {
					if (datosRecibidos.contains("tarea")) {
						System.out.println("cambio en tarea");
						soporte.firePropertyChange("cambio", null, datosRecibidos);
					}
					else if (datosRecibidos.contains("reset")) {
						soporte.firePropertyChange("reset", null, datosRecibidos);
					}
					else if (datosRecibidos.contentEquals("salir")) {
						soporte.firePropertyChange("out", false, true);
					}
					else {
						soporte.firePropertyChange("clave", null, datosRecibidos);
					}
					datosRecibidos = "";
				}
				
			} catch (IOException e) {
				texto = "No se ha podido leer. ( "+ e.toString() + ")";
				vista.getTextArea().append(texto + "\n");

			}
			
		}

	}

}
