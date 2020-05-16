/* Esta clase contiene el Graphics del juego.
 * Responsabilidad: Genera una imagen de fondo, la cual se utiliza como base del juego.
 * Colaboraci?n: ninguna.
 * @author Cesar Alberto Becerra Ramirez (1744338)
 * cesar.becerra@correounivalle.edu.co
 * @author Juan Sebastian velasquez (1744936)
 * juan.velasquez.acevedo@correounivalle.edu.co
 * @since 2019-01-02
 * @version 1.0
 * 2019-02-27
 */
package proyectoRuleta;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


// TODO: Auto-generated Javadoc
/**
 * Esta es la clase PanelRuleta.
 */
public class PanelRuleta extends Container
{
	
	/** Esta es una variable de tipo Imagen */
	private Image fondo;
	
	/** se le asigna la imagen de la carpeta imagenes a la variable miFondo */
	private File miFondo = new File("src/imagenes/fondo.png");
	
	/* (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 * Este es el metodo Graphics encargado de dibujar el fondo, y hacer una restriccion en caso de que no exista la imagen dada.
	 */
	public void paint(Graphics g) 
	{		
		try 
		{
			fondo = ImageIO.read(miFondo);
		}
		catch(IOException e) 
		{
			System.out.println("no se encontro la imagen");
		}
		g.drawImage(fondo,0,0,null);
		super.paint(g);

	}
	
}
//FIN DE CLASE
