package concesionarioCoches.Gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AcercaDe extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AcercaDe() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setModal(true);
		
		JLabel lblAutoraNievesMara = new JLabel("Autor/a: Nieves Mar\u00EDa Borrero Barea");
		lblAutoraNievesMara.setBounds(10, 87, 269, 14);
		contentPanel.add(lblAutoraNievesMara);
		
		JLabel lblAsignatura = new JLabel("Asignatura: Programaci\u00F3n");
		lblAsignatura.setBounds(10, 132, 175, 14);
		contentPanel.add(lblAsignatura);
		
		JLabel lblNombreConcesionarioDe = new JLabel("Titulo: Concesionario de Coches");
		lblNombreConcesionarioDe.setBounds(10, 38, 175, 14);
		contentPanel.add(lblNombreConcesionarioDe);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton aceptar = new JButton("Aceptar");
				aceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				aceptar.setActionCommand("OK");
				buttonPane.add(aceptar);
				getRootPane().setDefaultButton(aceptar);
			}
		}
	}
}
