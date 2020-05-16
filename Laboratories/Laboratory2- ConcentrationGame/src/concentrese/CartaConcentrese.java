package concentrese;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
// TODO: Auto-generated Javadoc

/**
 * Esta clase cumple la función de representar las cartas utilizadas en el juego.<br><br>
 * <b>Responsabilidad: </b>Crear una carta con una fila, columna e imagen, se debe permitir que dicha imagen cambie, 
 * tambien debe retornar la misma si se le solicita.<br><br>
 * <b>Colaboración: </b>Ninguna.
 * @author Cesar Alberto Becerra Ramirez (1744338), Juan Sebastian Velasquez (1744936)<br>
 * cesar.becerra@correounivalle.edu.co , juan.velasquez.acevedo@correounivalle.edu.co
 * @version 1.0<br>
 * 2018-10-01
 * @since 2018-10-01
 */

@SuppressWarnings("serial")
public class CartaConcentrese extends JButton{
	
    /** La constante MAXSIZE. */
    public static final int MAXSIZE=400;
 
    /** El row. */
    private int row;
    
    /** El col. */
    private int col;
	
	/** La imagen al voltear. */
	private ImageIcon imagen;
	
	/** La imagien por defecto. */
	private  ImageIcon imagenDefecto;
    
    /**
     * Estas son las instacnias de las cartas.
     *
     * @param row 
     * @param col 
     * @param imagen 
     */
    CartaConcentrese(int row, int col, ImageIcon imagen)
    {
    	this.imagen= imagen;
    	imagenDefecto= new ImageIcon("src/futbol/i.png");
		this.row= row;
		this.col=col;
		
		Dimension tamano= new Dimension(MAXSIZE,MAXSIZE);	
		this.setPreferredSize(tamano);
    }
    
    /**
     * Set de la imagen.
     */
    public void setImagen()
    {
		if(this.isUp())
			down();
		else 
			up();
    }

    /**
     * Función para obtener la imagen de la carta.
     *
     * @return the imagen
     */
    public ImageIcon getImagen()
    {
    	return imagen;
    }
    
	/**
	 * Si la carta está abierta.
	 */
	public void up() 
	{
		this.setIcon(imagen);
	}
	
	/**
	 * Si la carta está cerrada.
	 */
	public void down() 
	{
		this.setIcon(imagenDefecto);
	}
	
    /**
     * Comprueba si la carta esta abierta.
     *
     * @return true, si la carta está arriba.
     */
    public boolean isUp()
    {
    	if(this.getIcon().equals(imagenDefecto))
    		return false;
    	else 
    		return true;
    }
    
    /**
     * Gets el row.
     *
     * @return row
     */
    public int getRow()
    {
    	return row;
    }

    
    /**
     * Gets el col.
     *
     * @return col
     */
    public int getCol()
    {
    	return col;
    }
	
	

}
