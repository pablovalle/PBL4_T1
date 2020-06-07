

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import Dialogos.DialogoRegistrarUsuario;
//import Vistas.VistaMenuCentral;

public class ControladorLogin implements ActionListener {

	VistaPrincipalLogin vista;

	public ControladorLogin(VistaPrincipalLogin vista) {
		this.vista = vista;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		switch (e.getActionCommand()) {
		case "iniciarSesion":
			//vista.leerAlumno();
		
			if(vista.verificar()) {
				vista.dispose();
				Ventana menuCentral = new Ventana("Búsqueda MUTel", vista.getUsername()); // <-- Hay que quitar (para comprobar rÃ¡pido)
				
			}
			
			break;
		case "registrar":
			
			@SuppressWarnings("unused") DialogoRegistrarUsuario dialogo = new DialogoRegistrarUsuario(vista, "Registrar usuario", true);
			//if(dialogo.getUsuario()!=null) vista.dispose();
			break;
		}
	}
}
