package concesionarioCoches.Gui;

import javax.swing.JOptionPane;

import concesionarioCoches.CocheNoExisteException;
import concesionarioCoches.Concesionario;
import concesionarioCoches.MatriculaNoValidaException;
import concesionarioCoches.Coche;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostrarPorMatricula extends DialogoGeneral {
	/**
	 * Create the dialog.
	 */
	public MostrarPorMatricula(Concesionario concesionario) {
		super(concesionario);			
		setTitle("Busqueda de coche...");
		anterior.setVisible(false);
		
		textField.setEnabled(true);
		
		rbPlata.setEnabled(false);
		rbRojo.setEnabled(false);
	    rbAzul.setEnabled(false);

		comboBoxMarca.setEnabled(false);
		comboBoxModelo.setEnabled(false);
		
		bIzquierda.setText("Buscar");
		
		bIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coche coche = null;
				try {
					coche = concesionario.get(textField.getText().toUpperCase());
				} catch (MatriculaNoValidaException | CocheNoExisteException ex) {
					JOptionPane.showMessageDialog(contentPanel,
							"No tenemos ningun coche con esa matricula.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} 
				
				mostrar(coche);
			}
		});
		
		bDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
			}
		});

		
		
	}
	
}
