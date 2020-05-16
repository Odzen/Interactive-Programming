/**
 * @Autor: Juan Sebastian Velasquez Acevedo(1744936) - Jose David Ortiz Correa (1740634)
 * Correo: juan.velasquez.acevedo@correounivalle.edu.co - jose.david.ortiz@correounivalle.edu.co
 * @Version: 1.0
 * Modificacion: 2018-09-09
 * Creacion: 2018-08-30
 * Colaboracion:
 * Responsabilidad: Clase Carta que guarda los atributos como palo, valor y verifica si puede poner una carta encima de otra.   
 */
package ocholoco;

public class Carta 
{
	
	/** El valor. */
	private String valor;
	
	/** El palo. */
	private String palo;

	/**
	 *Constructor: 
	 *
	 * @param valor the valor
	 * @param palo the palo
	 */
	public Carta(String valor, String palo)
	{
		this.valor = valor;
		this.palo = palo; 
	}
	

	/* toString: Comprime el valor y el palo, y lo presenta como una cadena de caracteres. 
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return valor + palo;
	}
	
	/**
	 * puedePonerCartaEncima: En este metodo evalua los casos en los que se puede poner una carta encima de otra
	 * aqui es donde se evalua si el valor es 8 o no.
	 * @param carta the carta
	 * @return true, if successful
	 */
	public boolean puedePonerCartaEncima(Carta carta)
	{
		if (carta != null) 
		{
			if(carta.getValor().equalsIgnoreCase("8"))
			{
				if(this.valor.equalsIgnoreCase("A"))
					return false;
				else 
					return true; 
			}
			else if (this.valor.equalsIgnoreCase(carta.getValor()) || this.palo.equalsIgnoreCase(carta.getPalo()))
				return true;
			else
				return false; 
		}
		
		return false;
	}
	
	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public String getValor()
	{
		return valor;
	}
	
	/**
	 * Sets the palo.
	 *
	 * @param palo the new palo
	 */
	public void setPalo(String palo)
	{
		this.palo = palo;
	}
	
	/**
	 * Gets the palo.
	 *
	 * @return the palo
	 */
	public String getPalo()
	{
		return palo;
	}
}
