package concentrese;
/**
 * Esta clase cumple la función de iniciar el juego.<br><br>
 * <b>Responsabilidad: </b>Iniciar el juego haciendo un llamado a la Clase ControlConcentrese.<br><br>
 * <b>Colaboración: </b>{@link concentrese.GUIConcentrese}
 * @author Cesar Alberto Becerra Ramirez (1744338), Juan Sebastian Velasquez (1744936)<br>
 * cesar.becerra@correounivalle.edu.co , juan.velasquez.acevedo@correounivalle.edu.co
 * @since 2018-10-01
 * @version 1.0<br>
 * 2018-10-01
 */

import javax.swing.JFrame;

public class PrincipalConcentrese {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUIConcentrese myGUI = new GUIConcentrese();
		myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
