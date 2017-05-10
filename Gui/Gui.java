package concesionarioCoches.Gui;
/**
 * @author Nieves María Borrero Barea.
 */
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import concesionarioCoches.Concesionario;


import concesionarioCoches.ficheros.Fichero;
import concesionarioCoches.ficheros.Filtro;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Gui {

	private static final String SIN_TITULO_CONCESIONARIO = "Sin titulo: Concesionario de coches";
	private JFrame frame;
	static Concesionario concesionario= new Concesionario();
	private Filtro filtro = new Filtro(".obj", "Objeto");
	private String tituloConcesionario;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		
		frame.setResizable(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 444, 21);
		frame.getContentPane().add(menuBar);
		frame.setTitle(SIN_TITULO_CONCESIONARIO);
		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic('A');
		menuBar.add(mnArchivo);
		mnArchivo.setToolTipText("Archivo"); 	//Para mostrar una ayuda cuando pasas el ratón por encima
		JMenuItem mntmNuevoConcesionario = new JMenuItem("Nuevo concesionario ");
		
		mntmNuevoConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevoConcesionario();
				
			}
		});
		mntmNuevoConcesionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		mnArchivo.add(mntmNuevoConcesionario);
		
		JMenuItem mntmAbrirConcesionario = new JMenuItem("Abrir concesionario...");
		mntmAbrirConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});
		mnArchivo.add(mntmAbrirConcesionario);
		
		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}
		});
		mnArchivo.add(mntmGuardar);
		
		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarComo();
			}
		});
		mnArchivo.add(mntmGuardarComo);
		
		JSeparator separator_1 = new JSeparator();
		mnArchivo.add(separator_1);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(concesionario.isModificado()){
					Object[] options = { "SI", "NO", "CANCELAR" };
				 	int respuesta = JOptionPane.showOptionDialog(null, "¿Deseas guardar antes de salir?", "No has guardado el archivo",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
					if(respuesta == 0){
						guardarComo();
						System.exit(0);
					}
					else if(respuesta == 1)
						System.exit(0);				
					else
					return;
				}else System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);
		
		JMenu mnCoche = new JMenu("Coche");
		mnCoche.setMnemonic('C');
		menuBar.add(mnCoche);
		
		JMenuItem mntmAlta = new JMenuItem("Alta");
		mntmAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Altas alta = new Altas(concesionario);
			alta.setVisible(true);
			}
		});
		mntmAlta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnCoche.add(mntmAlta);
		
		JMenuItem mntmBaja = new JMenuItem("Baja  ");
		mntmBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bajas bajas = new Bajas(concesionario);
				bajas.setVisible(true);
			}
		});
		mntmBaja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mnCoche.add(mntmBaja);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Mostrar concesionario");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (concesionario.size()==0) {
					JOptionPane.showMessageDialog(frame.getContentPane(),
							"No hay coches para mostrar", "Error", 
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				else{
					MostrarConcesionario mostrar = new MostrarConcesionario(concesionario);
				mostrar.setVisible(true);}
			}
		});
		mnCoche.add(mntmNewMenuItem);
		
		JMenu mnBuscar = new JMenu("Buscar");
		mnBuscar.setMnemonic('B');
		menuBar.add(mnBuscar);
		
		JMenuItem mntmBuscarPorMatrcula = new JMenuItem("Buscar por matrícula");
		mntmBuscarPorMatrcula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (concesionario== null) {
					JOptionPane.showMessageDialog(frame.getContentPane(),
							"No hay coches para mostrar", "Error", 
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				else{
					MostrarPorMatricula mostrarCoche = new MostrarPorMatricula(concesionario);
					mostrarCoche.setVisible(true);}
			}
				
		});
		mnBuscar.add(mntmBuscarPorMatrcula);
		
		JMenuItem mntmBuscarPorColor = new JMenuItem("Buscar por color");
		mntmBuscarPorColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (concesionario== null) {
					JOptionPane.showMessageDialog(frame.getContentPane(),
							"No hay coches para mostrar", "Error", 
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				else{
					ElegirColor mostrarPorColor = new ElegirColor(concesionario);
				mostrarPorColor.setVisible(true);}
			}
			
		});
		mnBuscar.add(mntmBuscarPorColor);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setMnemonic('Y');
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcercaDe acerca= new AcercaDe();
				acerca.setVisible(true);
			}
		});
		mnAyuda.add(mntmAcercaDe);
		
		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mntmAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ayuda ayuda = Ayuda.getInstance();
				ayuda.setVisible(true);
			}
		});
		mnAyuda.add(mntmAyuda);
	

	}
	
	/**
	 * Crea un nuevo concesionario (archivo de extensión.obj)
	 */
	private void nuevoConcesionario() {
		if (concesionario.isModificado()) {
			Object[] options = { "SI", "NO", "CANCELAR" };
			int respuesta = JOptionPane.showOptionDialog(null, "No has guardado, ¿Desea Guardar?", "NO HAS GUARDADO",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (respuesta == 0) {
				guardarComo();
				String titulo = JOptionPane.showInputDialog(null, "Introduce el nombre del concesionario", "Nuevo concesionario", JOptionPane.PLAIN_MESSAGE);
				Fichero.setFichero(titulo);
				concesionario = new Concesionario();
				frame.setTitle(Fichero.FICHERO.getName());
				concesionario.setModificado(false);
			} else if (respuesta == 1) {
				String titulo = JOptionPane.showInputDialog(null, "Introduce el nombre del concesionario", "Nuevo concesionario", JOptionPane.PLAIN_MESSAGE);
				Fichero.setFichero(titulo);
				concesionario = new Concesionario();
				frame.setTitle(Fichero.FICHERO.getName());
				concesionario.setModificado(false);
			} 
			
		}
		
		
		else {
			tituloConcesionario = JOptionPane.showInputDialog(null, "Introduce el nombre del concesionario", "Nuevo concesionario", JOptionPane.PLAIN_MESSAGE);
			if(tituloConcesionario==null)
				Fichero.setFichero("");
			
			else{
			Fichero.setFichero(tituloConcesionario);
			concesionario = new Concesionario();
			frame.setTitle(Fichero.FICHERO.getName());}
			concesionario.setModificado(false);
		}

	}

	/**
	 * Abre un concesionario existente
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void abrir(){
		if (concesionario.isModificado()) {
			Object[] options = { "SI", "NO", "CANCELAR" };
			int respuesta = JOptionPane.showOptionDialog(null, "¿Deseas Guardar?", "Archivo sin guardar",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (respuesta == 0) 
				guardarComo();
			else if (respuesta == 1) {
				try {
					abrirFichero();
				} catch (IOException | ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Error al abrir fichero", "ERROR", JOptionPane.ERROR_MESSAGE);
					Fichero.nuevo();
				}
			} 
		}
		 else {
			try {
				abrirFichero();
			} catch (IOException | ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(null, "Error al abrir fichero", "ERROR", JOptionPane.ERROR_MESSAGE);
				Fichero.nuevo();
			}

		}
	}

	/**
	 * Metodo que crea un FileChooser
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void abrirFichero() throws FileNotFoundException, ClassNotFoundException, IOException {
		JFileChooser fileChooser= new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(filtro);
		if (fileChooser.showDialog(fileChooser, "Abrir Fichero") == JFileChooser.APPROVE_OPTION) {
			Fichero.FICHERO = fileChooser.getSelectedFile();
			concesionario = (Concesionario) Fichero.abrir(fileChooser.getSelectedFile());
			frame.setTitle(Fichero.getFichero().getName());
			concesionario.setModificado(false);

		}
	}
	
	/**
	 * Permite guardar un fichero
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void guardar(){
		if (Fichero.FICHERO.getName().equalsIgnoreCase(tituloConcesionario)) {
			guardarComo();
			concesionario.setModificado(false);
		} 
		else {
			try {
				Fichero.guardar(concesionario);
				concesionario.setModificado(false);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Error al guardar", "ERROR", JOptionPane.ERROR_MESSAGE);
			}

		}
	}
	
	/**
	 * Permite guardar como...
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void guardarComo(){
		JFileChooser fileChooser1= new JFileChooser();
		fileChooser1.setAcceptAllFileFilterUsed(false);
		fileChooser1.addChoosableFileFilter(filtro);

		if (JFileChooser.APPROVE_OPTION == fileChooser1.showDialog(fileChooser1, "Guardar Archivo")) {

			fileChooser1.setAcceptAllFileFilterUsed(false);
			Fichero.comprobarNombre(fileChooser1.getSelectedFile());
			if (Fichero.getFichero().exists()) {
				Object[] options = { "Si", "No" };
				int respuesta = JOptionPane.showOptionDialog(null, "El archivo ya existe, ¿deseas sobreescribirlo?",
						"Guardando", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
						options[0]);
				if (respuesta == 0) {
					try {
						Fichero.guardarComo(concesionario, Fichero.getFichero());
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, "Error al guardar el archivo", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}else{
				guardar();
			}

			frame.setTitle(Fichero.getFichero().getName());
			concesionario.setModificado(false);
		}
	}	
}