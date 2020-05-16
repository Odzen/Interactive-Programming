/* Esta es la clase Logica del proyecto.<br><br>
 * Responsabilidad: Esta clase se encarga de comparar las apuestas con el valor dado por la ruleta,
 * determinar el valor que se apuesta y el valor resultante despues de jugar y por ultimo, se encarga de determinar
 * cuando gana o pierde el jugador.
 * Colaboración: ninguna.
 * @author Cesar Alberto Becerra Ramirez (1744338)
 * cesar.becerra@correounivalle.edu.co
 * @author Juan Sebastian velasquez (1744936)
 * juan.velasquez.acevedo@correounivalle.edu.co
 * @since 2019-01-02
 * @version 1.0
 * 2019-02-27
 */
package proyectoRuleta;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class Logica.
 */
public class Logica {
	
	/** The dinero actual. */
	private double dineroActual ; 
	
	/** The dinero apostado. */
	private int dineroApostado ;
	
	/** The tipo de apuesta. */
	private int tipoDeApuesta;
	
	
	
	/**
	 * Instantiates a new logica.
	 */
	public Logica()
	{
		dineroActual = 2000; // El dinero siempre debe se mayor a 10 porque es la cantidad minima que se puede comprar como usuario
		dineroApostado = 0;
		tipoDeApuesta = 0;
	}
	
	
	
	/**
	 * Dinero que se tiene.
	 * Evalua y determina el dinero que tiene el usuario(dinero no apostado).
	 *
	 * @param dineroApostadoRonda the dinero apostado ronda
	 * @return the double
	 */
	public double dineroQueSeTiene(int dineroApostadoRonda) 
	{
		dineroActual = dineroActual - dineroApostadoRonda;
		return dineroActual;
	}
	
	/**
	 * Dinero que se aposto.
	 * Evalua y determina el dinero apostado.(dinero apostado total).
	 *
	 * @param x the x
	 * @return the int
	 */
	public int dineroQueSeAposto(int x) 
	{
		if(dineroApostado == 0) {
			dineroApostado = x;
		}
		else {
			dineroApostado = dineroApostado+ x  ;
		}
		return dineroApostado;
	}

	
		
	/**
	 * Comparar.
	 * Se encarga de evaluar el tiro y compararlo en el Array de Arrays. 
	 * primero evalua el primer Array y mira si algun termino de este es igual al valor dado por la ruleta.
	 * si no es asi se pasa a la siguiente.
	 * Hay un Array por el tipo de apuesta, otro por el valor apostado, y otro por el ID (la apuesta en si).
	 * Al evaluar cual es la apuesta ganadora si se gana se multiplica el valor apostado por el el valor de la apuesta
	 * dependiendo de su tipo. 
	 *
	 * @param tiro the tiro
	 * @param iD the i D
	 * @param tipoDeApuesta the tipo de apuesta
	 * @param apostadoPorRonda the apostado por ronda
	 * @return true, if successful
	 */
	public boolean comparar(int tiro, ArrayList<ArrayList> iD, ArrayList<Double> tipoDeApuesta, ArrayList<Integer> apostadoPorRonda) 
	{
		boolean ganar = false;
		for(int i = 0; i <= iD.size() -1; i++)
		{
			ArrayList<Integer> listaApuestas= iD.get(i);
			double cantidadMultiplicar = tipoDeApuesta.get(i);
			int apuestaRonda=apostadoPorRonda.get(i);
			for(int j=0; j<= listaApuestas.size() -1; j++)
			{
				int numeroApostado=listaApuestas.get(j);
				if(numeroApostado == tiro) 
				{
					ganar = true;
					dineroActual = dineroActual + (apuestaRonda * cantidadMultiplicar);
				}
				else
					dineroActual = dineroActual;
			}
		}
		dineroApostado = 0;
		return ganar;
	}
	
	/**
	 * Gets the dinero.
	 * Se obtiene el dinero Actual
	 * @return the dinero
	 */
	public double getDinero()
	{
		return dineroActual;
	}
	
	/**
	 * Gets the apostado.
	 * Se obtiene el dinero apostado
	 * @return the apostado
	 */
	public int getApostado()
	{
		return dineroApostado;
	}
	
	/**
	 * estaEnRango.
	 * Se encarga se saber si las coordenadas dadas se encuentran dentro del rango permisible para apostar
	 * se utiliza posteriormente en un escuha del vista.
	 * @param y 
	 * @param x 
	 * @return true, if successful
	 */
	
	public boolean estaEnRango (int x, int y)
	{
		boolean sePuede=true;;
		if(((x >= 550 + 8 && x <= 1165 + 8)&&(y >= 40 + 30 && y <= 296 + 30))||
				((x >= 630 + 8 && x <= 1112 + 8)&&(y >= 296 + 30 && y <= 451 + 30)))
			sePuede=true;
		else
			sePuede=false;
		return sePuede;
		
	}
	
	/**
	 * estaEnRango.
	 * Se encarga se saber si el numero identificador de apuesta iD se encuentra entre los permitidos para apostar
	 * se utiliza posteriormente en vista. 
	 * @param iD 
	 * @return true, if successful
	 */
	
	public boolean apuestaValida (int iD)
	{
		boolean valido=true;;
		if(iD==40 || iD==41 || iD==42 ||iD==76 ||iD==77 ||iD==78 ||iD==79 ||iD==80 ||iD==81 ||iD==82 ||
				iD==86 ||iD==90 ||iD==94 ||iD==98 ||iD==102 ||iD==106 ||iD==110 ||iD==114 ||iD==118 ||iD==122 ||
				iD==126 ||iD==130 ||iD==133 ||iD==134 ||iD==137 ||iD==138 ||iD==142 ||iD==146 ||iD==150 ||iD==154 ||
				iD==158 ||iD==162 ||iD==166 ||iD==170 ||iD==174 ||iD==178 ||iD==182 ||iD==183 ||iD==184 ||iD==185 ||
				iD==186 ||iD==187 ||iD==188 ||iD==189 )
			valido=false;
		else
			valido=true;
		return valido;
		
	}

}
//FIN DE CLASE
