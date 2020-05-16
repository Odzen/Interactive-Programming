/**
 * @Autor: Juan Sebastian Velasquez Acevedo(1744936) - Jose David Ortiz Correa (1740634)
 * Correo: juan.velasquez.acevedo@correounivalle.edu.co - jose.david.ortiz@correounivalle.edu.co
 * @Version: 1.0
 * Modificacion: 2018-09-09
 * Creacion: 2018-08-30
 * Colaboracion: Carta.java
 * Responsabilidad: Clase Baraja, principalmente crea una lista de objetos Cartas
 * y le asigna los valores 
 */
package ocholoco;

import java.util.ArrayList;
import java.util.Random;

public class Baraja 
{
	
	/** The generador. */
	private Random generador;
	private ArrayList<Carta> listaCartas;
	
	/** Constructor:*/
	public Baraja()
	{
		generador = new Random();
		listaCartas = new ArrayList<Carta>();
		crearBarajaCompleta();
	}
	
	/**
	 * Crear baraja completa:
	 */
	public void crearBarajaCompleta()
	{
		for (int i = 0; i < 4; i++) 
		{
			for (int j = 1; j <= 13; j++)
			{
				String valorActual = "";
				if (j == 1)
					valorActual = "A";
				else if(j == 11)
					valorActual = "J";
				else if(j == 12)
					valorActual = "Q";
				else if(j == 13)
					valorActual = "K";
				else
					valorActual = String.valueOf(j);
				
				switch (i) 
				{
				case 0:
					listaCartas.add(new Carta(valorActual, "D"));
				break;
				
				case 1:
					listaCartas.add(new Carta(valorActual, "C"));
				break;
				
				case 2:
					listaCartas.add(new Carta(valorActual, "P"));
				break;
				
				case 3:
					listaCartas.add(new Carta(valorActual, "T"));
				break;

				default:
				break;
				}
			}
		}
	}
	
	/**
	 * Gets the baraja.
	 *
	 * @return the baraja
	 */
	public ArrayList<Carta> getBaraja()
	{
		return listaCartas;
	}
	
	/**
	 * Gets the mano jugador.
	 *
	 * @return the mano jugador
	 */
	public ArrayList<Carta> getManoJugador()
	{
		ArrayList<Carta> manoJugador = new ArrayList<>();
		
		for (int i = 0; i < 8; i++)
			manoJugador.add(getCartaRandom());
		
		return manoJugador;
	}

	/**
	 * Gets the carta random.
	 *
	 * @return the carta random
	 */
	public Carta getCartaRandom()
	{
		int indexCarta = generador.nextInt(listaCartas.size());
		Carta cartaEscogida = listaCartas.get(indexCarta);
		listaCartas.remove(indexCarta);
		return cartaEscogida;
	}
	
	/**
	 * buscarCarta:
	 *
	 * @param baraja the baraja
	 * @param cartaBuscada the cartaBuscada
	 * @return the int
	 */
	public int buscarCarta(ArrayList<Carta> baraja, String cartaBuscada)
	{
		for (int i = 0; i < baraja.size(); i++)
			if (cartaBuscada.equalsIgnoreCase(baraja.get(i).toString()))
				return i;
		
		return -1;
	}
	
	/**
	 * Gets the cartaBuscada:
	 *
	 * @param baraja the baraja
	 * @param cartaBuscada the cartaBuscada
	 * @return the cartaBuscada
	 */
	public Carta getCartaBuscada(ArrayList<Carta> baraja, String cartaBuscada)
	{
		int carta = buscarCarta(baraja, cartaBuscada);
		if (carta != -1)
			return baraja.get(carta);
		
		return null;
	}
	
}
