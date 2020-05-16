/* Esta clase cumple la función de iniciar los dos clientes del juego de la Ruleta.<br><br>
 * <b>Responsabilidad: </b>Inicializa un cliente dependiendo del puerto dado.<br><br>
 * <b>Colaboración: </b>{@link null}
 * @author Cesar Alberto Becerra Ramirez (1744338)<br>
 * cesar.becerra@correounivalle.edu.co
 * @author Juan Sebastian velasquez (1744936)<br>
 * juan.velasquez.acevedo@correounivalle.edu.co
 * @since 2019-01-02
 * @version 2.0<br>
 * 2019-04-10
 */
package clienteRuleta;

import javax.swing.JFrame;


// TODO: Auto-generated Javadoc
/**
 * The Class PrincipalClienteRuleta.
 */
public class PrincipalClienteRuleta {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClienteRuleta aplicacion; // declara la aplicaciÃ³n cliente
		// si no hay argumentos de lÃ­nea de comandos
		if ( args.length == 0 ){
			aplicacion = new ClienteRuleta("127.0.0.1"); // localhost
		}
		
		else {
			aplicacion = new ClienteRuleta(args[0]); // usa args
		}
		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
