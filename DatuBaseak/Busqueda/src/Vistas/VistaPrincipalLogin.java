package Vistas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import Controladores.ControladorLogin;
import DAO.DAOUsuario;
import Renderers.RectangleBorder;


public class VistaPrincipalLogin extends JFrame {

	static Splash splash;
	private static final long serialVersionUID = 1L;
	final int WIDTH = 550; //500
	final int HEIGTH = 205; //175
	String username = null;

	ControladorLogin controladorLogin;
	

	JButton btnInicioSesion, btnRegistrar;
	JLabel lblUsuario, lblPassword;
	JTextField txtUsuario;
	JPasswordField txtPassword;
	
	public VistaPrincipalLogin() {

		super("Inicia sesión en MUTel");
		controladorLogin = new ControladorLogin(this);
		//user = null;
		//this.setLocationRelativeTo(null);
		this.setLocation(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2 - (this.WIDTH/2), java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2 - (this.HEIGTH/2));
		this.setSize(WIDTH, HEIGTH);

//	this.setIconImage(new ImageIcon(getClass().getResource("icons/Logo_MUFit.png")).getClass());

		ImageIcon ImageIcon = new ImageIcon("img/Logo_PBL4_b.png");
		Image image = ImageIcon.getImage();
		this.setIconImage(image);
		this.setContentPane(crearPanelVentana());
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout(20, 10));
		panel.add(crearPanelLogin(), BorderLayout.CENTER);
		panel.add(crearPanelBotones(), BorderLayout.SOUTH);

		panel.setBackground(Color.WHITE);
		return panel;
	}

	private Component crearPanelLogin() {
		JPanel panel = new JPanel(new GridLayout(2, 2, 10, 20));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
		lblUsuario = new JLabel("Usuario");
		txtUsuario = new JTextField();

		lblPassword = new JLabel("Contraseña");
		txtPassword = new JPasswordField();
		
		panel.add(lblUsuario);
		panel.add(txtUsuario);
		panel.add(lblPassword);
		panel.add(txtPassword);
		panel.setBackground(Color.WHITE);
		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		panel.setBorder(new RectangleBorder(Color.WHITE));
		btnInicioSesion = new JButton("Iniciar sesión");
		btnInicioSesion.setToolTipText("Iniciar sesión");
		btnInicioSesion.setActionCommand("iniciarSesion");
		btnInicioSesion.addActionListener(controladorLogin);

		btnRegistrar = new JButton("Registrarse");
		btnRegistrar.setToolTipText("Registrarse");
		btnRegistrar.setActionCommand("registrar");
		btnRegistrar.addActionListener(controladorLogin);

		//btnInicioSesion.setSelected(true);
		panel.add(btnInicioSesion);
		panel.add(btnRegistrar);
		panel.setBackground(Color.BLACK);
//		panel.setBackground(Color.WHITE);

		return panel;
	}
	
	/*public void verificarPassword(Usuario aux) throws ExcepcionLogin {
	 if(!aux.getPassword1().equals(String.valueOf(txtPassword.getPassword()))){
		 throw new ExcepcionLogin("¡La contraseña no es correcta!");
	 }
	}*/
	public boolean verificar() {
		boolean ret= false;
		if(!DAOUsuario.comprobarUsername(this.txtUsuario.getText())) {
			JOptionPane.showMessageDialog(this, "¡No existe ningún usuario con ese nombre!", "¡ERROR!",
					JOptionPane.ERROR_MESSAGE);
			txtUsuario.setText("");
			txtPassword.setText("");
		}
		else if(!DAOUsuario.comprobarContraseña(this.txtUsuario.getText(), String.valueOf(this.txtPassword.getPassword()))) {
			JOptionPane.showMessageDialog(this, "¡La contraseña no es correcta!", "¡ERROR!",
					JOptionPane.ERROR_MESSAGE);
			txtPassword.setText("");
		}
		else {
			username = txtUsuario.getText();
			ret=true;
		}
		return ret;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public static void main(String[] args) {

		splash = new Splash();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		splash.dispose();

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// Si Nimbus no esta disponible, no puedes establecer el GUI a otro look and feel.
		}
		
		@SuppressWarnings("unused")
		VistaPrincipalLogin programa = new VistaPrincipalLogin();
	}



	

}
