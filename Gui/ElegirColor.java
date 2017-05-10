package concesionarioCoches.Gui;
/**
 * @author Nieves María Borrero Barea
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;


import concesionarioCoches.Color;
import concesionarioCoches.Concesionario;


public class ElegirColor extends JDialog {

	private final JPanel contentPanel = new JPanel();


	private JLabel lblColor;
	private JRadioButton rbPlata;
	private JRadioButton rbRojo;
	private JRadioButton rbAzul;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton aceptar;
	private JButton salir;
	private MostrarPorColor mostrarPorColor;

	/**
	 * Create the dialog.
	 */
	public ElegirColor(Concesionario concesionario) {
		setTitle("Elige un color");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 298, 109);
		
		lblColor = new JLabel("Color");
		lblColor.setBounds(20, 11, 49, 14);
		
		rbPlata = new JRadioButton("Plata");
		rbPlata.setBounds(100, 7, 65, 23);
		buttonGroup.add(rbPlata);
		
		rbRojo = new JRadioButton("Rojo");
		rbRojo.setBounds(167, 7, 65, 23);
		buttonGroup.add(rbRojo);
		
		rbAzul = new JRadioButton("Azul");
		rbAzul.setBounds(232, 7, 65, 23);
		buttonGroup.add(rbAzul);
		
		aceptar = new JButton("Aceptar");
		aceptar.setBounds(85, 48, 101, 23);
		aceptar.setEnabled(false); //Permanecerá inhabilitado hasta que se seleccione un color
		
		rbPlata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptar.setEnabled(true);
			}
		});
		
		rbRojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptar.setEnabled(true);
				
			}
		});
		
		rbAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptar.setEnabled(true);
				
			}

		});
		
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = getColor();
				
				Concesionario concesionarioColor = new Concesionario(concesionario.getCochesColor(color));
				if (concesionarioColor.isEmpty()) {
						JOptionPane.showMessageDialog(contentPanel,
								"No tenemos ningun coche de ese color.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					mostrarPorColor = new MostrarPorColor(concesionarioColor);
					mostrarPorColor.setVisible(true);
			}
		});
		
		salir = new JButton("Salir");
		salir.setBounds(198, 48, 88, 23);
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		getContentPane().setLayout(null);
		getContentPane().add(lblColor);
		getContentPane().add(aceptar);
		getContentPane().add(salir);
		getContentPane().add(rbPlata);
		getContentPane().add(rbRojo);
		getContentPane().add(rbAzul);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	
	private Color getColor() {
		if (rbPlata.isSelected())
			return Color.PLATA;
		else if (rbRojo.isSelected())
			return Color.ROJO;
		else if (rbAzul.isSelected())
			return Color.AZUL;
		else
			return null;
	}


}
