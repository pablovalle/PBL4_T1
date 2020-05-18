import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DialogoReservar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public DialogoReservar(JFrame ventana, String titulo, boolean modo,Habitacion habitacion, java.util.Date in, java.util.Date out) {
		super(ventana,titulo,modo);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(4, 1, 0, 0));
		DateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
		String fechaIn=formatter.format(in);
		String fechaOut=formatter.format(out);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JLabel lblHotel = new JLabel("HOTEL");
				lblHotel.setText(habitacion.getHotel());
				panel.add(lblHotel);
				
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 3, 0, 0));
			{
				JLabel lbCiudad = new JLabel("Ciudad: "+habitacion.getCiudad());
				
				panel.add(lbCiudad);
			}
			{
				JLabel lbTipo = new JLabel("Tipo");
				lbTipo.setText(habitacion.getCategoria().toUpperCase());
				panel.add(lbTipo);
			}
			{
				JLabel lbpersonas = new JLabel("Personas: "+ habitacion.getAforo());
				panel.add(lbpersonas);
				
				
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lbDesdetxt = new JLabel("Desde: ");
				JLabel lbDesde = new JLabel();
				lbDesde.setText(String.valueOf(fechaIn));
				
				
				panel.add(lbDesdetxt);
				panel.add(lbDesde);
			}
			{
				JLabel lbHasta = new JLabel();
				JLabel lbHastatxt = new JLabel("Hasta:");
				lbHasta.setText(String.valueOf(fechaOut));
				panel.add(lbHastatxt);
				panel.add(lbHasta);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lbNumhabitacion = new JLabel("Habitacion: "+String.valueOf(habitacion.getNumhabitacion()));
				panel.add(lbNumhabitacion);
			}
			{
				JLabel lbPrecio = new JLabel(String.valueOf(habitacion.getPrecio())+" �/noche");
				panel.add(lbPrecio);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
						boolean result=DAOReservas.crearReserva(habitacion, fechaIn, fechaOut, "irati_mutel");
						if(!result) {
							JOptionPane.showMessageDialog(null, "�NO SE HA PODIDO REALIZAR LA RESERVA!");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
