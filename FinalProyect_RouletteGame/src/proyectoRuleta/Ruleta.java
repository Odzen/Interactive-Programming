/* Esta clase simula ser una ruleta.
 * Responsabilidad: Generar un numero aleatorio entre 0 y 36, este será utilizado para realizar las comparaciones.
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

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * Esta es la clase ruleta.
 */
public class Ruleta {
	
	/** El resultado es el valor que genera la ruleta */
	private int resultado;
	
	/**
	 * Girar ruleta es la funcion que genera el numero aleatorio y se lo asigna a la variable resultado.
	 *
	 * @return entero
	 */
	public int girarRuleta() {
		
		Random tirar = new Random();
	    resultado = tirar.nextInt(37);
	    return resultado;
	}

}

//FIN DE CLASE
