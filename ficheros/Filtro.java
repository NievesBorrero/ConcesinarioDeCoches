package concesionarioCoches.ficheros;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class Filtro extends FileFilter{
	
	private String extension;
	private String descripcion;
	
	public Filtro(String extension, String descripcion) {
		setExtension(extension);
		setDescripcion(descripcion);
	}
	

	/**
	 * Devuelve la extensión del fichero
	 * 
	 * @return extension
	 */
	public String getExtension() {
		return extension;
	}


	/**
	 * Modifica la extensión.
	 * @param extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}




	/**Modifica la descripción
	 * 
	 * 
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Override
	public boolean accept(File fichero) {
		if(fichero.isDirectory())
			return true;
		return fichero.getName().endsWith(getExtension());
	}

	@Override
	public String getDescription() {
		return descripcion + String.format(" (*%s)", getExtension());
	}
	
}
