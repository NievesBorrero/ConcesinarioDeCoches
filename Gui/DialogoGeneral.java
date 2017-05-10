package concesionarioCoches.Gui;
/**
 * @author Nieves María Borrero Barea
 */
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import concesionarioCoches.Coche;
import concesionarioCoches.CocheNoExisteException;
import concesionarioCoches.CocheYaExisteException;
import concesionarioCoches.Color;
import concesionarioCoches.ColorNoValidoException;
import concesionarioCoches.Concesionario;
import concesionarioCoches.Marca;
import concesionarioCoches.MatriculaNoValidaException;
import concesionarioCoches.Modelo;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class DialogoGeneral extends JDialog {
	
	private static final long serialVersionUID = 1L;

	protected final JPanel contentPanel = new JPanel();
	protected JTextField textField;
	protected JLabel lblMatricula;
	protected JLabel lblColor;
	protected JLabel lblMarca;
	protected JLabel lblModelo;
	protected JRadioButton rbPlata;
	protected JRadioButton rbRojo;
	protected JRadioButton rbAzul;
	protected final ButtonGroup buttonGroup = new ButtonGroup();
	protected JComboBox<Marca> comboBoxMarca;
	protected JComboBox<Modelo> comboBoxModelo;
	protected JButton bIzquierda;
	protected JButton anterior;

	protected int indice; 

	protected JButton bDerecha;

	public DialogoGeneral(Concesionario concesionario) {
		super();
		setResizable(true);
		setModal(true);
		setBounds(100, 100, 388, 232);

		lblMatricula = new JLabel("Matr\u00EDcula");
		lblMatricula.setBounds(26, 11, 76, 30);

		textField = new JTextField();
		textField.setBounds(106, 16, 177, 20);
		textField.setColumns(10);

		lblColor = new JLabel("Color");
		lblColor.setBounds(26, 58, 49, 14);

		rbPlata = new JRadioButton("Plata");
		rbPlata.setBounds(106, 54, 65, 23);
		buttonGroup.add(rbPlata);

		rbRojo = new JRadioButton("Rojo");
		rbRojo.setBounds(173, 54, 65, 23);
		buttonGroup.add(rbRojo);

		rbAzul = new JRadioButton("Azul");
		rbAzul.setBounds(238, 54, 65, 23);
		buttonGroup.add(rbAzul);

		lblMarca = new JLabel("Marca");
		lblMarca.setBounds(26, 99, 49, 14);
		
		getContentPane().setLayout(null);
		comboBoxMarca=new JComboBox<Marca>();
		comboBoxMarca.setBounds(106, 95, 92, 22);
		comboBoxModelo = new JComboBox<Modelo>();
		comboBoxModelo.setBounds(106, 138, 92, 22);
		

		lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(26, 142, 76, 14);

		
		comboBoxMarca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				comboBoxModelo.setModel(new DefaultComboBoxModel(getModelo(comboBoxMarca)));
			}
		});
		comboBoxMarca.setModel(new DefaultComboBoxModel(Marca.values()));
		comboBoxModelo.setModel(new DefaultComboBoxModel(getModelo(comboBoxMarca)));

		bIzquierda = new JButton();

		bIzquierda.setBounds(154, 175, 105, 23);

		bDerecha = new JButton("Salir");
		bDerecha.setBounds(271, 175, 105, 23);

		getContentPane().setLayout(null);
		getContentPane().add(lblMarca);
		getContentPane().add(lblModelo);
		getContentPane().add(lblColor);
		getContentPane().add(lblMatricula);
		getContentPane().add(rbPlata);
		getContentPane().add(rbRojo);
		getContentPane().add(rbAzul);
		getContentPane().add(bIzquierda);
		getContentPane().add(bDerecha);
		getContentPane().add(comboBoxMarca);
		getContentPane().add(comboBoxModelo);
		getContentPane().add(textField);
		
		anterior = new JButton("<<");
		anterior.setBounds(26, 175, 91, 23);
		getContentPane().add(anterior);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}

	

	/**
	 * Recorre un array de la enum de modelos, comprobando si la marca
	 * seleccionada se corresponde con la marca de cada modelo, si es así, la
	 * añade a un arrayList de modelos que luego devuelve convertido en array.
	 * 
	 * @param comboBoxMarca
	 * @return array con los modelos de esa marca.
	 */
	private Object[] getModelo(JComboBox comboBoxMarca) {
		Marca marca = (Marca) comboBoxMarca.getSelectedItem();
		ArrayList<Modelo> modelos = new ArrayList<Modelo>(); 
		for (Modelo m : Modelo.values())		
			if (m.getMarca() == marca) // Creo un ArrayList con los modelos a partir del ArrayList 
										//de la enum	
				modelos.add(m);
		return modelos.toArray();
	}

	/**
	 * Según el botón seleccionado devuelve un color.
	 * 
	 * @return color.
	 */
	protected Color getColor() {
		if (rbPlata.isSelected())
			return Color.PLATA;
		else if (rbRojo.isSelected())
			return Color.ROJO;
		else if (rbAzul.isSelected())
			return Color.AZUL;
		else
			return null;
	}
	
	void limpiar(){
		textField.setText(null);
		buttonGroup.clearSelection();
		comboBoxMarca.setSelectedItem(null);
		comboBoxModelo.setSelectedItem(null);
	}
	
	protected void mostrar(Coche coche) {
		textField.setText(coche.getMatricula());
		
		comboBoxMarca.setSelectedItem(coche.getModelo().getMarca());
				
		comboBoxModelo.setSelectedItem(coche.getModelo());
				
		switch (coche.getColor()) {
		case PLATA:
			rbPlata.setSelected(true);
			break;
		case ROJO:
			rbRojo.setSelected(true);
			break;
		case AZUL:
			rbAzul.setSelected(true);
		}
	}

}