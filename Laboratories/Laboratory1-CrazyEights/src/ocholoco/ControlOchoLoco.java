/**
 * @Autor: Juan Sebastian Velasquez Acevedo(1744936) - Jose David Ortiz Correa (1740634)
 * Correo: juan.velasquez.acevedo@correounivalle.edu.co - jose.david.ortiz@correounivalle.edu.co
 * @Version: 1.0
 * Modificacion: 2018-09-09
 * Creacion: 2018-08-30
 * Colaboracion:Carta.java
 * Responsabilidad:Verificar cuando se puede poner la carta, encargado de la logica del juego
 * y operaciones con las barajas.  
 */

package ocholoco;

import ocholoco.Baraja;
import ocholoco.Carta;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class ControlOchoLoco.
 */
public class ControlOchoLoco 
{
	/** The baraja. */
	private Baraja baraja;
	
	/** The mano computadora. */
	private ArrayList<Carta> manoJugador, manoComputadora;
	
	/** The carta actual. */
	private Carta cartaActual;
	
	/** The turno. */
	private int turno; //0: Jugador 1: Computadora

	/**
	 * Constructor:
	 * Instantiates a new control ocho loco.
	 */
	public ControlOchoLoco()
	{
		baraja = new Baraja();
		manoJugador = baraja.getManoJugador();
		manoComputadora = baraja.getManoJugador();
		turno = 1;
	}
	
	/**
	 * puedePonerCarta: Evalua todos los casos en los que se puede poner la carta
	 * y determina las acciones logicas que debe realizar tanto el jugador como la computadora
	 *
	 * @param cartaPoner the carta poner
	 */
	public void puedePonerCarta(String cartaPoner)
	{
		Carta cartaBuscada;
		//turno jugador
		if (turno == 0) 
		{
			cartaBuscada = baraja.getCartaBuscada(manoJugador, cartaPoner);
			if (cartaActual.puedePonerCartaEncima(cartaBuscada))
				ponerCarta(cartaPoner);
		}
		//turno pc
		else
		{
			if (cartaActual != null)
			{
				for (int i = 0; i < manoComputadora.size(); i++)
				{
					String cartaBuscadaString = manoComputadora.get(i).toString();
					cartaBuscada = baraja.getCartaBuscada(manoComputadora, cartaBuscadaString); 
					if (cartaActual.puedePonerCartaEncima(cartaBuscada))
					{
						ponerCarta(cartaBuscadaString);
						break;
					}
					else
					{
						if (i == manoComputadora.size() - 1)
						{
							comerCarta();
							turno = 0;
							break;
						}
					}
				}
			}
			else
				ponerCarta(cartaPoner);
		}
	}
	
	/**
	 * ponerCarta: Dependiendo del turno, el metodo busca una carta y la remueve de la mano que le pertenece.
	 *
	 * @param carta the carta
	 */
	public void ponerCarta(String carta)
	{
		if (turno == 0 && baraja.getCartaBuscada(manoJugador, carta) != null)
		{
			cartaActual = baraja.getCartaBuscada(manoJugador, carta);
			manoJugador.remove(baraja.buscarCarta(manoJugador, carta));
			turno = 1;
		}
		else if (turno == 1 && baraja.getCartaBuscada(manoComputadora, carta) != null)
		{
			cartaActual = baraja.getCartaBuscada(manoComputadora, carta);
			manoComputadora.remove(baraja.buscarCarta(manoComputadora, carta));
			turno = 0;
		}
		else 
		{
			if(cartaActual==null)
			{
				cartaActual = baraja.getCartaBuscada(baraja.getBaraja(), carta);
				manoJugador.remove(baraja.buscarCarta(baraja.getBaraja(), carta));
			}
		}
	}

	
	/**
	 * comerCarta: Obtiene una carta random de la baraja y 
	 * la añade al arreglo de la mano del Jugador. 
	 */
	public void comerCarta()
	{
		if(!baraja.getBaraja().isEmpty())
		{
			if (turno == 0)
				manoJugador.add(baraja.getCartaRandom());
			else
				manoComputadora.add(baraja.getCartaRandom());
		}
	}


	/**
	 * Sets the palo.
	 *
	 * @param cartaPoner the carta poner
	 * @param paloACambiar the palo A cambiar
	 */
	public void cambiarPalo(String cartaPoner, String paloACambiar)
    {
        Carta cartaBuscada = baraja.getCartaBuscada(manoJugador, cartaPoner);
        cartaBuscada.setPalo(paloACambiar);
        ponerCarta(cartaBuscada.toString());
    }


	/**
	 * Gets the cartas sobrantes.
	 *
	 * @return the cartas sobrantes
	 */
	public ArrayList<Carta> getCartasSobrantes()
	{
		return baraja.getBaraja();
	}
	
	public boolean determinarFinalJuego()
	{
		if(manoJugador.isEmpty() || manoComputadora.isEmpty() || baraja.getBaraja().isEmpty())
		{
			if(manoJugador.size() < manoComputadora.size())
			  return true;
			else
				return false;
		}
	    return false; 
	}

	
	/**
	 * Gets the carta actual.
	 *
	 * @return the carta actual
	 */
	public Carta getCartaActual()
	{
		return cartaActual;
	}
	
	/**
	 * Sets the turno.
	 *
	 * @param turno the new turno
	 */
	public void setTurno(int turno)
	{
		this.turno = turno;
	}
	
	/**
	 * Gets the turno.
	 *
	 * @return the turno
	 */
	public int getTurno()
	{
		return turno;
	}
	
	/**
	 * Gets the mano jugador.
	 *
	 * @return the mano jugador
	 */
	public ArrayList<Carta> getManoJugador()
	{
		return manoJugador;
	}
	
	/**
	 * Gets the mano computadora.
	 *
	 * @return the mano computadora
	 */
	public ArrayList<Carta> getManoComputadora()
	{
		return manoComputadora;
	}
}
