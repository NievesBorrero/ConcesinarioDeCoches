package concesionarioCoches.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import concesionarioCoches.Coche;
import concesionarioCoches.Concesionario;


public class MostrarConcesionario extends DialogoGeneral {

	
	public MostrarConcesionario(Concesionario concesionario) {
		super(concesionario);
		
		setTitle("Mostrando concesionario...");
		
		textField.setEnabled(false); //Lo inhabilitamos
		
		rbPlata.setEnabled(false);
		rbRojo.setEnabled(false);
	    rbAzul.setEnabled(false);

		comboBoxMarca.setEnabled(false);
		comboBoxModelo.setEnabled(false);
		
		actualizar(concesionario);
		
		
		bIzquierda.setText("Salir");
		bIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		anterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar(concesionario.get(--indice));
				comprobarBotones(concesionario);
			}
		});
		
		bDerecha.setText(">>");
		bDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar(concesionario.get(++indice));
				comprobarBotones(concesionario);
			}
		});
		
	}

	private void actualizar(Concesionario concesionario) {
		mostrar(concesionario.get(0));
		comprobarBotones(concesionario);
	}	
	
	private void comprobarTamanio(Concesionario concesionario) {
		if(concesionario.size()==1){
			anterior.setVisible(false);
			bDerecha.setVisible(false);
		}
	}
	
	/**
	 * Comprueba si el indice se sale de rango y en ese caso inhabilita los botones
	 */
	
	protected void comprobarBotones(Concesionario concesionario) {
		
		comprobarTamanio(concesionario);
		
		if (indice + 1 == concesionario.size())
			bDerecha.setEnabled(false);
		else
			bDerecha.setEnabled(true);

		if (indice - 1 == -1)
			anterior.setEnabled(false);
		else
			anterior.setEnabled(true);
	}
	
	
}