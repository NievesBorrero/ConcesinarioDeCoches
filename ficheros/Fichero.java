package concesionarioCoches.ficheros;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.ObjectInputStream;
	import java.io.ObjectOutputStream;
	import java.io.Serializable;
	import java.util.regex.Pattern;

	public class Fichero implements Serializable {

		private static final long serialVersionUID = 1L;
		private static final Pattern PATRON = Pattern.compile("^((\\w)+(\\.obj))$");
		public static File FICHERO = new File("SinTitulo");

		
		/**
		 * Permite crear un fichero nuevo.
		 */
		public static void nuevo(){
			setFichero("SinTitulo.obj");
		}

		/**
		 * Permite guardar un fichero con el nombre deseado.
		 * 
		 * @param objeto
		 * @param nombre
		 * @throws IOException
		 */
		public static void guardarComo(Object objeto, File nombreArchivo) throws IOException {
			FICHERO = comprobarNombre(nombreArchivo);
		}

		/**
		 * Permite guardar los cambios en un fichero.
		 * 
		 * @param objeto
		 * @throws IOException
		 */
		public static void guardar(Object objeto) throws IOException {
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FICHERO))) {
				out.writeObject(objeto);
			}
		}

		/**
		 * Permite abrir un archivo.
		 * 
		 * @param archivo
		 * @throws IOException
		 * @throws ClassNotFoundException
		 */
		public static Object abrir(File archivo) throws IOException, ClassNotFoundException {
			try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
				return in.readObject();
			}
		}

		/**
		 * Comprueba si el nombre del archivo coincide con el
		 * patron, de lo contrario le añade la extension .obj
		 * 
		 * @param archivo
		 * @return
		 */
		public static File comprobarNombre(File archivo) {
			if (PATRON.matcher(archivo.getName()).matches()) {
				return archivo;
			} else {
				setFichero(archivo.getAbsolutePath() + ".obj");
				return FICHERO;
			}

		}
		
		/**
		 * Modifica el fichero
		 * 
		 * @param fichero
		 */
		public static void setFichero(String fichero) {
			Fichero.FICHERO = new File(fichero);
		}

		/**
		 * Devuelve el fichero
		 * 
		 * @return fichero
		 */
		public static File getFichero() {
			return FICHERO;
		}
}
