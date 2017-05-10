package concesionarioCoches.Gui;

/**
 * @author Nieves María Borrero Barea
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionarioCoches.Concesionario;

public class MostrarPorColor extends MostrarConcesionario {

	/**
	 * Create the dialog.
	 */
	public MostrarPorColor(Concesionario concesionario) {
		super(concesionario);
		setTitle("Mostrando coches de color "+getColor());
	}
		

}
