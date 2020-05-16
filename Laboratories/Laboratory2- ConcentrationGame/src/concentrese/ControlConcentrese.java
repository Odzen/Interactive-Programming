package concentrese;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
// TODO: Auto-generated Javadoc

/**
 * Esta clase cumple la función de ejecutar toda la parte lógica del juego, ella es llamada por la clase {@link concentrese.GUIConcentrese}
 *  y utiliza objetos de la clase {@link concentrese.CartaConcentrese},<br><br>
 * <b>Responsabilidad: </b>Distribuir las cartas, hacer que giren, y realizar
 * la respectiva comparación entre ellas.<br><br>
 * <b>Colaboración: </b>{@link concentrese.CartaConcentrese}
 * @author Cesar Alberto Becerra Ramirez (1744338), Juan Sebastian Velasquez (1744936)<br>
 * cesar.becerra@correounivalle.edu.co , juan.velasquez.acevedo@correounivalle.edu.co
 * @version 1.0<br>
 * 2018-10-01
 * @since 2018-10-01
 */
public class ControlConcentrese {
	
	/** La cartas. */
	private CartaConcentrese[][] cartas;  
	
	/** Las imagenes. */
	private ArrayList<ImageIcon> imagenes;
	
	/** La carta actual. */
	private CartaConcentrese cartaActual;
	
	/** La carta pasada. */
	private CartaConcentrese cartaPasada;
	
	/** Booleano que dice si la carta está en juego o no. */
	private boolean enJuego;
	
	/** La fila. */
	private int fila;
	
	/** El col. */
	private int col;
	
	/** El icono. */
	private ImageIcon icono;
	
	/** La constante de FILAS. */
	private static final int FILAS = 4;
	
	/** La constante de COLUMNAS. */
	private static final int COLUMNAS = 5;
	
	
	/**
	 * Instancias del  control concentrese.
	 */
	ControlConcentrese()
	{
		enJuego=false;
		cartas = new CartaConcentrese[FILAS][COLUMNAS];
		imagenes= new ArrayList<>();
		cartaActual= new CartaConcentrese(fila,col , icono);
		cartaPasada= new CartaConcentrese(fila, col, icono);
	}
	
	/**
	 * Enlistar imagenes, cada imagen tiene como nombre un numero, con esta funcion se enlistan 20
	 * imagenes dependiendo del tema
	 *
	 * @param tema the tema
	 * @return the array list
	 */
	public ArrayList<ImageIcon> enlistarImagenes(String tema)
	{
		for(int item=1; item<=FILAS+COLUMNAS+1; item++ )
		{
			ImageIcon imagenActual= new ImageIcon("src/"+tema+"/"+item+".png");
			imagenes.add(imagenActual);
			imagenes.add(imagenActual);
		}
		return imagenes;
	}

	/**
	 * Distribuir cartas, primero se enlistas las imagenes, luego con un random se crea cada carta y se va guardando
	 * en la matriz de cartas por medio de dos for aninados
	 *
	 * @param tema 
	 * @return La carta concentrese[][]
	 */
	public CartaConcentrese[][] distribuirCartas(String tema)
	{
		ArrayList<ImageIcon> imagenes=enlistarImagenes(tema);
		Random random= new Random();
		for(int fila=0; fila<FILAS; fila++ )
		{
			for(int col=0; col<COLUMNAS; col++)
			{
				int nRandom= random.nextInt(imagenes.size());
				ImageIcon imagenActual= imagenes.get(nRandom);
				cartaActual= new CartaConcentrese(fila,col,imagenActual);
				cartas[fila][col]=cartaActual;
				imagenes.remove(nRandom);
			}
		}
		return cartas;
	}
	
	/**
	 * Cambia la cara de la carta actual, dependiendo si esta abierta o no.
	 *
	 * @param carta
	 * @return La carta concentrese[][]
	 */
	public CartaConcentrese[][] cambiarCarta(CartaConcentrese carta)
	{
		if(carta.isUp())
			return cartas;
		int columna= carta.getCol();
		int fila= carta.getRow();
		cartas[fila][columna].setImagen();
		return cartas;
	}
	
	/**
	 * Evalua todas las posibles combinaciones y comparaciones entre a cartaActual y la cartaPasada, para determinar el estado del juego. 
	 *
	 * @param carta 
	 * @return un entero
	 */
	public int sonPareja(CartaConcentrese carta)
	{
		//si se oprime una a la carta actual de nuevo
		if(carta.equals(cartaPasada))
		{
			enJuego=true;
			return 2;
		}
		//si esta en primer turno 
		if(!enJuego)
		{
			enJuego=true;
			cartaPasada=carta;
			return 0;
		}
		//si la carta actual es igual a la carta pasada
		else if(cartaPasada.getImagen().equals(carta.getImagen()))
		{
			enJuego=false;
			return 1;
		}
		// si el jugador se equivoca 
		enJuego=false;
		return 3;
	}
	
	/**
	 * Se obtiene la carta.
	 *
	 * @return cartas
	 */
	public CartaConcentrese[][] getCartas()
	{
		return cartas;
	}
	
	/**
	 * Lista imagenes.
	 *
	 * @return Un array list
	 */
	public ArrayList<ImageIcon> listaImagenes()
	{
		return imagenes;
	}
	
	/**
	 * Muestra la fila.
	 *
	 * @return filas
	 */
	public static int getFilas()
	{
		return FILAS;
	}
	

	/**
	 * Muestra la columna.
	 *
	 * @return columnas
	 */
	public static int getColumnas()
	{
		return COLUMNAS;
	}
	
    /**
     * Muestra la carta pasada.
     *
     * @return carta pasada
     */
    public CartaConcentrese getCartaPasada()
    {
    	return cartaPasada;
    }
    
    /**
     * Muestra la carta actual.
     *
     * @return carta actual
     */
    public CartaConcentrese getCartaActual()
    {
    	return cartaActual;
    }
    




}
