/* Estos son los parametros que tienen las etiqueta.<br><br>
 * <b>Responsabilidad: </b>Asignarle ciertos para metros como tipografia color y tamaño a las diferentes etiquetas
 * que se usaran en el GUI.<br><br>
 * <b>Colaboración: 
 * </b>ninguna.
 * @author Cesar Alberto Becerra Ramirez (1744338)<br>
 * cesar.becerra@correounivalle.edu.co
 * @author Juan Sebastian velasquez (1744936)<br>
 * juan.velasquez.acevedo@correounivalle.edu.co
 * @since 2019-03-10
 * @version 1.0<br>
 * 2019-03-13
 */
package componentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class EtiquetaTitulo.
 */
public class EtiquetaTitulo extends JLabel {
	
	/**
	 * Instantiates a new etiqueta titulo.
	 *
	 * @param texto the texto
	 */
	public EtiquetaTitulo(String texto) {
		Font font = new Font("arial",Font.BOLD + Font.ITALIC, 24);
		setFont(font);
		setForeground(Color.WHITE);
		setHorizontalAlignment(JLabel.CENTER);
		setOpaque(false);
	    setText(texto);
	}

}
