package Controladores;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import Vistas.VistaPrincipal;
import Vistas.VistaPrincipalLogin;



public class ControladorLogin implements ActionListener {

	VistaPrincipalLogin vista;

	public ControladorLogin(VistaPrincipalLogin vista) {
		this.vista = vista;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		switch (e.getActionCommand()) {
		case "iniciarSesion":		
			if(vista.verificar()) {
				vista.dispose();
				VistaPrincipal menuCentral = new VistaPrincipal("Administrador de tareas", vista.getUsername()); // <-- Hay que quitar (para comprobar rápido)
				
			}
			
			break;
		}
	}
}
