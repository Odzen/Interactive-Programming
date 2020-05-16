/* Esta funcion es la encargada de hacer el Hall de la fama del busca minas<br><br>
 * <b>Responsabilidad: </b>Interactuar con un archivo .txt por medio de funciones e lectura y escritura. 
 * Se encarga de almacenar en dicho archivo la informacion de los mejores tiempos obtenidos por los jugadores.<br><br>
 * <b>Colaboración: 
 * </b>ninguna.
 * @author Cesar Alberto Becerra Ramirez (1744338)<br>
 * cesar.becerra@correounivalle.edu.co
 * @author Juan Sebastian velasquez (1744936)<br>
 * juan.velasquez.acevedo@correounivalle.edu.co
 * @since 2019-03-10
 * @version 1.0<br>
 * 2019-03-13
 */
package componentes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


// TODO: Auto-generated Javadoc
/**
 * The Class LectoEscritura.
 */
public class LectoEscritura{
	
	/** The lista de puntos. */
	List<String> listaDePuntos = new ArrayList<String>();
	
	/** The lista de puntos 2. */
	List<String> listaDePuntos2 = new ArrayList<String>();
	
	/** The frase. */
	String frase = new String();
	
	/** The mi buffer. */
	private BufferedReader miBuffer;

	/**
	 * Hall de la fama.
	 * es la funcion principal de LectoEscritura, se encarga de recibir el llamado de GUI al ganar el juego.
	 * esta funcion evalua el tiempo alcanzado por el jugador con cada tiempo guardado en el archivo txt, y
	 * si este es menor a alguno lo ubica en esa posicion, todo esto por medio de las otras funciones de
	 * LectoEscritura.
	 *
	 * @param nombre the nombre
	 * @param tiempo the tiempo
	 * @return the list
	 */
	public List<String> hallDeLaFama(String nombre, int tiempo) {
		listaDePuntos = lee(listaDePuntos2);
		buenPuntaje(tiempo, nombre);
		escribir(listaDePuntos);
		listaDePuntos2.clear();
		return listaDePuntos;
	}
	
	/**
	 * Gets the texto.
	 * Esta funcion se encarga de leer el archivo .txt y retornar una lista con sus elementos
	 * para ser mostrados en pantalla posteriormente. Funciona por medio de la funcion lee 
	 *
	 * @return the texto
	 */
	public List<String> getTexto() {
		List<String> lista =new ArrayList<String>(); 
		List<String> lista2 =new ArrayList<String>(); 
		lista = lee(lista2);
		return lista;
	}
	
	/**
	 * Buen puntaje.
	 * Es la funcion encargada de determinar si un elemento debe ser añadido al hall o no.
	 * Esto lo hace comparando el puntaje con el del hall por medio de la funcion comparar
	 * @param puntaje the puntaje
	 * @param nombre the nombre
	 */
	public void buenPuntaje(int puntaje,String nombre ) {
		if(listaDePuntos.isEmpty()) {
			listaDePuntos.add(nombre+ "\t"+ puntaje);
		}
		else if(listaDePuntos.size()<5) {
			int indice = comparar(listaDePuntos, puntaje);
			if(indice != -1) {
				listaDePuntos.add(indice, (nombre+ "\t"+ puntaje));
			}
			else {
				listaDePuntos.add(nombre+ "\t"+ puntaje);
			}
			
		}
		else if(listaDePuntos.size()==5) {
			int indice = comparar(listaDePuntos, puntaje);
			if(indice != -1) {
				listaDePuntos.add(indice, (nombre+ "\t"+ puntaje));
				listaDePuntos.remove(5);
			}
			else {
				listaDePuntos.add(nombre+ "\t"+ puntaje);
				listaDePuntos.remove(5);
			}
			
		}
			
		}
	
	/**
	 * Comparar.
	 *Esta funcion extrae los elementos del archivo .txt y compara los tiempos que tienen con el que se le suministro.
	 *Si el tiempo suministrado es menor que alguno retorna la posicion en la que debe ser colocado.
	 * @param listaDePuntos the lista de puntos
	 * @param puntaje the puntaje
	 * @return the int
	 */
	public int comparar(List<String> listaDePuntos, int puntaje) {

		for(int i =0;i<listaDePuntos.size();i++) {
			String nombrePuntaje = listaDePuntos.get(i);
			String[] parts = nombrePuntaje.split("\t");
			String tiempo = parts[1];
			int tiempoActual = Integer.parseInt(tiempo);
			if(puntaje <= tiempoActual) {
				return i;
			}
			else if (i==listaDePuntos.size() -1 && puntaje > tiempoActual) {
				return i+1;
			}
				
		}
		return -1;
		
	}
	
	/**
	 * Escribir.
	 *Es la funcion encargada de sobre escribir la informacion del archivo .txt
	 * @param listaDePuntos the lista de puntos
	 */
	public void escribir(List<String> listaDePuntos) {
			try {
				Path path = Paths.get("src/files/ganadores.txt");
					Files.write(path, listaDePuntos);	
				}
				
			catch(IOException e) {
				System.out.println("no se encontro el archivo");
			}
		}
	
	/**
	 * Lee.
	 *Es la funcion encargada de leer la informacion del archivo .txt
	 * @param listaDePuntos2 the lista de puntos 2
	 * @return the list
	 */
	public List<String> lee(List<String> listaDePuntos2) {
		try {
			FileReader entrada = new FileReader("src/files/ganadores.txt");
			miBuffer = new BufferedReader(entrada);
			String linea ="";
			while(linea != null) {
				linea=miBuffer.readLine();
				if(linea != null) {
					listaDePuntos2.add(linea);
				}
				
			}
			
		}
		catch(IOException e) {
			System.out.println("no se encontro el archivo");
		}
		return listaDePuntos2;
	}
}
