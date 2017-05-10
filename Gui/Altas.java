package concesionarioCoches.Gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import concesionarioCoches.CocheYaExisteException;
import concesionarioCoches.ColorNoValidoException;
import concesionarioCoches.Concesionario;
import concesionarioCoches.MatriculaNoValidaException;
import concesionarioCoches.Modelo;

public class Altas extends DialogoGeneral {
	
	public Altas(Concesionario concesionario) {
		super(concesionario);
		setTitle("Altas");
		anterior.setVisible(false);
		bIzquierda.setText("a\u00f1adir");
		limpiar();
		bIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					concesionario.annadir(textField.getText().toUpperCase(), getColor(),
							(Modelo) comboBoxModelo.getSelectedItem());
					limpiar();
					concesionario.setModificado(true);	
				}catch (CocheYaExisteException e1) {
					JOptionPane.showMessageDialog(contentPanel,
							e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch(MatriculaNoValidaException e3){
					JOptionPane.showMessageDialog(contentPanel,
							e3.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				
				}catch(ColorNoValidoException e4){
					JOptionPane.showMessageDialog(contentPanel,
							e4.getMessage(),"Error",
							JOptionPane.ERROR_MESSAGE);			
				}catch (Exception ex){
					JOptionPane.showMessageDialog(contentPanel,
							"Error", "",
							JOptionPane.ERROR_MESSAGE);
				} 
				
			}
		});
		
		bDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); // PARA SALIR DIRECTAMENTE 
			}
		});
	}
	

}
