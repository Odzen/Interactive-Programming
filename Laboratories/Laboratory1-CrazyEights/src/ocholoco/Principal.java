/**
 * @Autor: Juan Sebastian Velasquez Acevedo(1744936) - Jose David Ortiz Correa (1740634)
 * Correo: juan.velasquez.acevedo@correounivalle.edu.co - jose.david.ortiz@correounivalle.edu.co
 * @Version: 1.0
 * Modificacion: 2018-09-09
 * Creacion: 2018-08-30
 * Colaboracion:Vista.java
 * Responsabilidad:Programa Principal, es el encargado de empezar el juego.  
 */

package ocholoco;
import ocholoco.VistaConsola;

public class Principal 
{	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		VistaConsola vistaConsola = new VistaConsola();
		vistaConsola.empezarJuego();
	}
}
