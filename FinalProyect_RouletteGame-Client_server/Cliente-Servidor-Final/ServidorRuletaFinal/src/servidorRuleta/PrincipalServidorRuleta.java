/* Esta clase cumple la función de iniciar el servidor del juego de la Ruleta.<br><br>
 * <b>Responsabilidad: </b>Iniciar el Servidor del juego de la ruleta y ejecutarlo.<br><br>
 * <b>Colaboración: </b>{@link null}
 * @author Cesar Alberto Becerra Ramirez (1744338)<br>
 * cesar.becerra@correounivalle.edu.co
 * @author Juan Sebastian velasquez (1744936)<br>
 * juan.velasquez.acevedo@correounivalle.edu.co
 * @since 2019-01-02
 * @version 2.0<br>
 * 2019-04-10
 */
package servidorRuleta;

import javax.swing.JFrame;

// TODO: Auto-generated Javadoc
/**
 * The Class PrincipalServidorRuleta.
 */
public class PrincipalServidorRuleta {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServidorRuleta aplicacion = new ServidorRuleta();
		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aplicacion.execute();
	}

}
