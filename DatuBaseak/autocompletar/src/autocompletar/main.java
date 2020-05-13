package autocompletar;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class main extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static final String COMMIT_ACTION = "commit";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		textField = new JTextField();
		textField.setFocusTraversalKeysEnabled(false);
		
		List<String >keywords = new ArrayList<String>(5);
        keywords.add("bilbo");
        keywords.add("bubai");
        keywords.add("murcia");
        keywords.add("larrabasterra independiente");
		panel.add(textField);
		Autocomplete autoComplete = new Autocomplete(textField, keywords);
		textField.getDocument().addDocumentListener(autoComplete);
		textField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
		textField.getActionMap().put(COMMIT_ACTION, autoComplete.new CommitAction());
		textField.setColumns(10);
	}

}
