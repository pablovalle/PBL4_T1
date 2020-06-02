package Dialogos;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import DAO.DAOUsuario;
import Excepciones.ExcepcionCampos;
import Excepciones.ExcepcionPassword;
import Excepciones.ExcepcionUsername;
import Renderers.RectangleBorder;

public class DialogoRegistrarUsuario extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//JOptionPane panelInfo;
	JTextField txtNombre, txtApellido, txtEmail, txtPeso, txtAltura, txtNombreUsuario;
	JPasswordField txtPassword1, txtPassword2;
	JRadioButton rdbtnHombre, rdbtnMujer,rdbtnOtro;
	ButtonGroup buttonGroup;
	JButton btnAceptar, btnCancelar;
	JLabel labelInfo;
	JFrame ventana;
	//Usuario user;


	public DialogoRegistrarUsuario(JFrame ventana, String titulo, boolean modo) {
		super(ventana, titulo, modo);
		this.ventana = ventana;
		this.setSize(400, 600);
		this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().width/2 - (int)(this.getSize().getWidth()/2), (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2 - (this.getSize().getHeight()/2)));
		//this.user=null;
		//panelInfo = new JOptionPane();

		
		ImageIcon ImageIcon = new ImageIcon("img/Logo_MUFit.png");
		Image image = ImageIcon.getImage();
		this.setIconImage(image);
		this.getContentPane().add(crearPanelVentana(), BorderLayout.CENTER);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout(0, 3));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.add(crearPanelDatosUsuario(), BorderLayout.CENTER);
		panel.add(crearPanelBotones(), BorderLayout.SOUTH);
		
		//panel.setBackground(Color.WHITE);
		panel.setBackground(Color.BLACK);
		return panel;
	}

	private Component crearPanelDatosUsuario() {
		JPanel panel = new JPanel(new GridLayout(9, 1, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 75, 20, 75));
		
		txtNombre = new JTextField();
		txtNombre.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Nombre"));
		txtApellido = new JTextField();
		txtApellido.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Apellido"));
		txtEmail = new JTextField();
		txtEmail.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Email"));
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Nombre de usuario"));
		txtPassword1 = new JPasswordField();
		txtPassword1
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Contrase�a"));
		txtPassword2 = new JPasswordField();
		txtPassword2.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Repite la contrase�a"));

		panel.add(txtNombre);
		panel.add(txtApellido);
		panel.add(crearPanelRadioButton());
		panel.add(txtEmail);
		panel.add(txtNombreUsuario);
		panel.add(txtPassword1);
		panel.add(txtPassword2);

		panel.setBackground(Color.WHITE);
		return panel;
	}

	private Component crearPanelRadioButton() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonGroup = new ButtonGroup();
		rdbtnHombre = new JRadioButton("Hombre");
		rdbtnMujer = new JRadioButton("Mujer");
		rdbtnOtro= new JRadioButton("Otro");
		buttonGroup.add(rdbtnHombre);
		buttonGroup.add(rdbtnMujer);
		buttonGroup.add(rdbtnOtro);
		rdbtnHombre.setSelected(true);
		panel.add(rdbtnHombre);
		panel.add(rdbtnMujer);
		panel.add(rdbtnOtro);
		panel.setBackground(Color.WHITE);
		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.setBorder(new RectangleBorder(Color.WHITE));

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setToolTipText("Aceptar");
		btnAceptar.setActionCommand("aceptar");
		btnAceptar.addActionListener(this);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.addActionListener(this);

		panel.add(btnAceptar);
		panel.add(btnCancelar);
		panel.setBackground(Color.BLACK);
//		panel.setBackground(Color.WHITE);

		return panel;
	}

	/*public Usuario getUsuario() {
		return user;
	}*/

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "aceptar":
			
			try {
				if(DAOUsuario.registrarse(txtNombre.getText(), txtApellido.getText(), txtNombreUsuario.getText(), String.valueOf(txtPassword1.getPassword()),String.valueOf(txtPassword2.getPassword()), txtEmail.getText())) {
					this.dispose();
				}
				else{
					JOptionPane.showMessageDialog(this, "�Los datos no son correctos!", "�ERROR!",
							JOptionPane.ERROR_MESSAGE);
				}
				
			} catch (ExcepcionPassword e1) {
				if (e1.getMessage().equals("�Las contraseñas no coinciden!")) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(this, "�Las contraseñas no coinciden!", "�ERROR!",
							JOptionPane.ERROR_MESSAGE);
					txtPassword2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red),
							"Repite la contrase�a"));
					txtPassword2.setText("");
				} else {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(this,
							"�La contrase�a no cumple con los requisitos m�nimos!" + "\n" + "Requisitos:" + "\n"
									+ "1- Debe contener 8 caracteres como m�nimo." + "\n"
									+ "2- Debe contener como m�nimo una min�scula." + "\n"
									+ "3- Debe contener como m�nimo una may�scula." + "\n"
									+ "4- Debe contener como m�nimo un n�mero.",
							"�ERROR!", JOptionPane.ERROR_MESSAGE);
					txtPassword1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red),
							"Repite la contrase�a"));
					txtPassword2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red),
							"Repite la contrase�a"));
					txtPassword1.setText("");
					txtPassword2.setText("");
				}
			
			
			
			} catch (ExcepcionUsername e1) {
				if (e1.getMessage().equals("�El nombre de usuario sobrepasa el m�ximo de caracteres permitidos!")) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(this,
							"�El nombre de usuario sobrepasa el m�ximo de caracteres permitidos!", "�ERROR!",
							JOptionPane.ERROR_MESSAGE);
					txtNombreUsuario.setBorder(BorderFactory
							.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Nombre de usuario"));
					txtNombreUsuario.setText("");
				} else {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(this, "�Ese nombre de usuario ya existe!", "�ERROR!",
							JOptionPane.ERROR_MESSAGE);
					txtNombreUsuario.setBorder(BorderFactory
							.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Nombre de usuario"));
					txtNombreUsuario.setText("");
				}
			} catch (ExcepcionCampos e1) {
				System.out.println(e1.getMessage());
				switch (e1.getMessage()) {
				case "�Debes rellenar todos los campos!":
					JOptionPane.showMessageDialog(this, "�Debes rellenar todos los campos!", "�ERROR!",
							JOptionPane.ERROR_MESSAGE);
					break;
				case "�No se admiten espacios en el nombre de ususario!":
					JOptionPane.showMessageDialog(this, "�No se admiten espacios en el nombre de ususario!", "�ERROR!",
							JOptionPane.ERROR_MESSAGE);
					txtNombreUsuario.setText("");
					break;
				case "�No se admiten espacios al principio y al final del nombre y del apellido!":
					JOptionPane.showMessageDialog(this, "�No se admiten espacios al principio y al final del nombre y del apellido!", "�ERROR!",
							JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
			this.dispose();
			break;
				
		case "cancelar":
			this.dispose();
			break;
		}
	}

}
