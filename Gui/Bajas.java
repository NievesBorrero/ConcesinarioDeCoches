package concesionarioCoches.Gui;
/**
 * /**
 * @author Nieves María Borrero Barea
 */
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import concesionarioCoches.Coche;
import concesionarioCoches.CocheNoExisteException;
import concesionarioCoches.Concesionario;
import concesionarioCoches.MatriculaNoValidaException;

public class Bajas extends DialogoGeneral {

	public Bajas(Concesionario concesionario) {
		super(concesionario);
		setTitle("Bajas");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 388, 232);
		anterior.setVisible(false);
		
		rbPlata.setEnabled(false);
		rbRojo.setEnabled(false);
		rbAzul.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		comboBoxModelo.setEnabled(false);
		
		bIzquierda.setText("Eliminar");
		
		bIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coche coche = null;
				try {
					coche = concesionario.get(textField.getText().toUpperCase());
				} catch (MatriculaNoValidaException|CocheNoExisteException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							"No se ha podido eliminar.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} 
				if (coche != null) {
					mostrar(coche);
					int n = JOptionPane.showOptionDialog(contentPanel,
							"Estas seguro de que desea eliminarlo?", "Confirmar",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
					
					switch (n) {
					case JOptionPane.YES_OPTION:
						try {
							concesionario.eliminar(textField.getText());
							concesionario.setModificado(true);
							limpiar();
						} catch (MatriculaNoValidaException e1) {
							JOptionPane.showMessageDialog(contentPanel, "aqui no deberia entrar");
						}
					
						break;
					}
				}
			}
		});
		
		bDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		
	}	
	

}
