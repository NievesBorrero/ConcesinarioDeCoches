package concesionarioCoches;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

/*
 * No pueden existir dos coches con la misma matr�cula en el almac�n del concesinario
 * no puede a�adirse un coche al concecionario con alguno de sus atributos inv�lidos. Han de conocerse todas sus caracter�sticas 
 * Ninguno de los valores puede ser por defecto
 */
/**
 * Representa un concesionario de coches.
 * 
 * L�gicamente, no podr� a�adirse un coche inv�lido almac�n del concesinario)
 * 
 * Han de conocerse todas sus caracter�sticas Ninguno de los valores puede ser
 * por defecto
 * 
 * @author Mar�aLourdes
 * 
 */
public class Concesionario implements Serializable {
	/**
	 * Almac�n de los coches del concesionario
	 */
	private ArrayList<Coche> almacen;
	/**
	 * Nombre del concesionario
	 */
	private final String nombre = "IES Gran Capit�n";
	
	private boolean modificado;

	
	
/**
 * Crea un nuevo concesionario a partir de un ArrayList.
 * @param concesionario
 */
	public Concesionario(ArrayList<Coche> concesionario) {
		almacen= concesionario;
	}

/**
 * Crea un nuevo concesionario vacio.
 */
	
	public Concesionario(){
		almacen = new ArrayList<Coche>();
	}

	// P:Por qu� no se necesita que annadir devuelva boolean??????
	// P:Por qu� no se especifican todas las excepciones de forma
	// expl�cita??????
	/**
	 * A�ade un coche al concesinario
	 * 
	 * @param matricula
	 *            Matr�cula del coche a a�adir
	 * @param color
	 *            Color del coche a a�adir
	 * @param modelo
	 *            Modelo del coche a a�adir
	 * @throws Exception
	 *             Si no se ha podido a�adir el coche al concesionario, porque
	 *             ya hay otro con la misma matr�cula o porque faltan datos
	 */
	public void annadir(String matricula, Color color, Modelo modelo)
			throws Exception{
		// Coche coche = Coche.instanciarCoche(matricula, color, modelo);

		// if (coche == null || almacen.contains(coche))
		// return false;
		// return almacen.add(coche);
		Coche coche = new Coche(matricula, color, modelo);
		if (!almacen.contains(coche))
			almacen.add(coche);
		else
			throw new CocheYaExisteException(
					"El coche ya existe en el concesionario. ");
	}

	/**
	 * Elimina un coche del concesinario
	 * 
	 * @param matricula
	 *            Matr�cula del coche a eliminar
	 * @throws MatriculaNoValidaException
	 *             Si la matr�cula no es v�lida en su formato
	 * @return true si se ha eliminado. false en otro caso
	 */
	public boolean eliminar(String matricula) throws MatriculaNoValidaException {
		return almacen.remove(new Coche(matricula));
	}

	/**
	 * Devuelve el n�mero de coches en el almac�n del concesionario
	 * 
	 * @return N�mero de coches en el almac�n del concesionario
	 */
	public int size() {
		return almacen.size();
	}

	/**
	 * Devuelve el Coche del almac�n indicado por la matr�cula
	 * 
	 * @param matricula
	 *            Matr�cula a buscar
	 * @return Coche contenido en el almac�n. null si no existe
	 * @throws MatriculaNoValidaException
	 *             Si la matr�cula no es v�lida
	 */
	public Coche get(String matricula) throws MatriculaNoValidaException,
			CocheNoExisteException {
		// Coche coche = Coche.instanciarCoche(matricula);
		// int index = almacen.indexOf(new Coche(matricula));
		// if (index != -1) {
		// P: qu� sucede si el coche no est� en el concesionario?

		try {
			return almacen.get(almacen.indexOf(new Coche(matricula)));
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new CocheNoExisteException(
					"El coche no est� en el concesionario.");
		}

		// }
		// return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Concesionario " + nombre + "[almacen=" + almacen + "]";
	}

	public ArrayList<Coche> getCochesColor(Color color) {
		ArrayList<Coche> arrCochesColor = new ArrayList<Coche>();
		for (Coche coche : almacen) {
			if (coche.getColor() == color)
				arrCochesColor.add(coche);
		}
		return arrCochesColor;
	}
	/**
	 * Devuelve el coche por indice
	 * @param i
	 * @return
	 */

	public Coche get(int i) {
		return almacen.get(i);
	}
	
	public boolean isEmpty(){
		return almacen.isEmpty();
	}
	
	/**
	 * Indica si el concesionario ha sido modificado.
	 * 
	 * @return true o false.
	 */
	public boolean isModificado() {
		return modificado;
	}

	/**
	 * Modifica el valor de modificado.
	 * 
	 * @param true o false.
	 */
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
}	
		
	

}
