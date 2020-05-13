package presentaci�n;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dominio.ModeloTablaRecursos;
import dominio.Persona;
import dominio.RecursoExtendido;
import persistencia.DAOPrestamos;
import persistencia.DAORecursos;
import persistencia.DAOReservas;



public class FormRecursos extends JFrame implements ListSelectionListener {
	
	final int ADMINISTRADOR = 1;
	JMenuBar barra;
	JMenu	menuRecursos,  menuSalir;
	JMenuItem opcionMenu;
	
	AbstractAction accAdd,accDelete,accEdit,accReservar,accTake;
	ModeloTablaRecursos tabla;
	 
	JTable vTabla;
	TrazadorTablaRecursos trazador;
	ModeloColumnasTablaRecursos columnas;
	JScrollPane panelS;
	Persona persona;
	
		
	public FormRecursos(Persona p){
		
		super ("Recursos");
		this.persona = p; 
		this.crearAcciones();
		this.darpermisos();
		this.setJMenuBar(crearBarraMenu());
		this.setLocation(200,100);
		this.setSize(800,600);
		this.getContentPane().add(crearToolBar(),BorderLayout.NORTH);
		this.getContentPane().add(crearPanelTabla(),BorderLayout.CENTER);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void darpermisos() {
		if (persona.getIdTipoUsuario() != ADMINISTRADOR ){
			accAdd.setEnabled(false);
			accDelete.setEnabled(false);
			accEdit.setEnabled(false);
		}
		
	}

	private Component crearPanelTabla() {
		
		panelS = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		crearTabla();
		panelS.setViewportView(vTabla);
		return panelS;
	}
	private void crearTabla() {
		trazador = new TrazadorTablaRecursos();
		columnas = new ModeloColumnasTablaRecursos (trazador);
		try {
			tabla = new ModeloTablaRecursos(columnas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vTabla = new JTable(tabla,columnas);
		vTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vTabla.getSelectionModel().addListSelectionListener(this);
		vTabla.setFillsViewportHeight(true);
		vTabla.getTableHeader().setReorderingAllowed(false);
		vTabla.setRowSelectionInterval(0, 0);
		vTabla.setFillsViewportHeight(true);
		
	}
	

	private void crearAcciones() {
		accAdd = new MiAccion ("A�adir", new ImageIcon("iconos/edit_add.png"), "A�adir", KeyEvent.VK_A);
		accDelete = new MiAccion ("Borrar",new ImageIcon("iconos/edit_remove.png"),"Borrar",KeyEvent.VK_D);
		accEdit = new MiAccion ("Editar",new ImageIcon("iconos/edit.png"),"Editar",KeyEvent.VK_E);
		accReservar = new MiAccion ("Reservar",new ImageIcon("iconos/month.png"),"Reservar",KeyEvent.VK_R);
		accTake = new MiAccion ("Llevar",new ImageIcon("iconos/agt_login.png"),"Llevar",KeyEvent.VK_P);
	}

	private JToolBar crearToolBar() {
		JToolBar toolBar = new JToolBar();
		JButton boton;
		toolBar.setBorder(BorderFactory.createRaisedBevelBorder());
		toolBar.add(accAdd);
		toolBar.add(accDelete);
		toolBar.add(accEdit);
		
		toolBar.addSeparator(new Dimension (20,0));
		
		toolBar.add(accReservar);
		toolBar.add(accTake);
		
		
		
		toolBar.add(Box.createHorizontalGlue());
		
		boton =(JButton) toolBar.add(new JButton (new ImageIcon("iconos/exit.png")));
		boton.setActionCommand("salir");
		boton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				FormRecursos.this.dispose();
			}
			
		});
		return toolBar;
	}

	private JMenuBar crearBarraMenu() {
		barra = new JMenuBar();
		barra.add (crearMenuRecursos());
		
		
		barra.add(Box.createHorizontalGlue());
		barra.add (crearMenuSalir());
		
		return barra;
		
	}

	private JMenu crearMenuSalir() {
		JMenuItem op;
		menuSalir = new JMenu ("Salir");
		
		op=menuSalir.add("Salir");
		op.setIcon(new ImageIcon("iconos/exit.png"));
		op.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e){
				FormRecursos.this.dispose();
			}
		});
		return menuSalir;
	}

	private JMenu crearMenuRecursos() {
		
		menuRecursos = new JMenu ("Recursos");
		menuRecursos.setMnemonic(KeyEvent.VK_R);
		
		opcionMenu = menuRecursos.add (accAdd);
		opcionMenu = menuRecursos.add (accDelete);
		opcionMenu = menuRecursos.add(accEdit);
		
		menuRecursos.addSeparator();
		
		opcionMenu = menuRecursos.add(accReservar);
		opcionMenu = menuRecursos.add(accTake);
	
		
		return menuRecursos;
		
	}

	
	private class MiAccion extends AbstractAction {
		
		public MiAccion (String texto, Icon imagen, String descrip, Integer nemonic){
			super(texto,imagen);
			
			this.putValue( Action.SHORT_DESCRIPTION ,descrip);
			this.putValue(Action.MNEMONIC_KEY, nemonic);
			this.putValue(ACTION_COMMAND_KEY, texto);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch (e.getActionCommand()){
			case "A�adir": tratarOpci�nA�adir(); break;
			case "Borrar": tratarOpci�nBorrar(); break;
			case "Editar": tratarOpci�nEditar();break;
			case "Reservar": tratarOpci�nReservar();break;
			case "Llevar": System.out.println("Ha elegido Llevar");break;
			}
	
		}

		private void tratarOpci�nReservar() {
			int index = vTabla.getSelectedRow() ;
			RecursoExtendido recurso = tabla.getRecursoAt(index);
			DialogReserva dialogo = new DialogReserva (FormRecursos.this,recurso);
			try {
				FormRecursos.this.tabla.actualizar();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		private void tratarOpci�nEditar() {
			int index = vTabla.getSelectedRow() ;
			RecursoExtendido recurso = tabla.getRecursoAt(index);
			DialogoRecurso dialogo = new DialogoRecurso (FormRecursos.this,recurso, true);
			if (dialogo.isCambioRealizado()){
				   try {
					tabla.actualizar();
					vTabla.setRowSelectionInterval(index, index);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		    }
		}

		private void tratarOpci�nBorrar() {
			RecursoExtendido recurso = tabla.getRecursoAt(vTabla.getSelectedRow());
			   int opcion =JOptionPane.showConfirmDialog(FormRecursos.this, "Vas a eliminar el recurso: "+recurso.getNombre()+" de "+ recurso.getUbicaci�n(),
						"Eliminar recurso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			   if (opcion == JOptionPane.OK_OPTION){
				   try{
					   DAOPrestamos.eliminarPrestamosRecurso(recurso.getId());
					   DAOReservas.eliminarReservasRecurso(recurso.getId());
					   DAORecursos.eliminarRecurso(recurso.getId());
					   tabla.actualizar();
					   vTabla.setRowSelectionInterval(0, 0);
				   }catch(Exception e2){
					   e2.printStackTrace();
				   }
			   }
		}

		private void tratarOpci�nA�adir() {
			DialogoRecurso dialogo = new DialogoRecurso (FormRecursos.this,true);
			if (dialogo.isCambioRealizado()){
				   try {
					tabla.actualizar();
					vTabla.setRowSelectionInterval(0, 0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			   }
		}
	}


	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
