package concesionarioCoches.Gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JSeparator;

public class Ayuda extends JDialog {
	private static Ayuda ayuda;

	/**
	 * Create the dialog.
	 */
	private Ayuda() {
		setResizable(false);
		setModal(false);
		setBounds(100, 100, 450, 334);
		getContentPane().setLayout(null);
		
		JTextArea txtrConcesionarioDeCoches = new JTextArea();
		txtrConcesionarioDeCoches.setBounds(12, 12, 426, 310);
		getContentPane().add(txtrConcesionarioDeCoches);
		txtrConcesionarioDeCoches.setText("CONCESIONARIO DE COCHES\n\nBienvenid@ a la guía de ayuda del Concesionario de Coches.\n\nEl menú archivo permite guardar, guardar como..., abrir o crear \nun nuevo concesionario.\n\nmenú Coche:\n\nAltas: permite introducir coches en el concesionario.\n\nBajas: permite borrar coches del concesionario.\n\nMostrar concesionario: nos muestra los coches disponibles en \nel concesionario.\n\nmenú buscar: nos ofrece dos opciones de búsqueda, por \nmatrícula y por color.\n\n\n\n");
	}
	
	public static Ayuda getInstance(){
		if(ayuda==null)
			ayuda=new Ayuda();
		return ayuda;
		
	}
}
