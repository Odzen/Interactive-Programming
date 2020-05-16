/* Estas son las celdas del busca minas<br><br>
 * <b>Responsabilidad: </b>Determinar si en la celda que se dio click hay una bomba, si está bloqueada, y en dado
 * caso de que no se cumpla lo anterior sea falso permite voltear las fichas para descubrir la cantidad de minas
 * cercanas<br><br>
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
package buscaMinas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

// TODO: Auto-generated Javadoc
/**
 * The Class Celda.
 */
public class Celda extends JButton {
	
	/** The Constant SIZE. */
	private static final int SIZE = 50;

	/** The minas cercanas. */
	int fila, col, minasCercanas;
	
	/** The bloqueado. */
	boolean mina, destapado, bloqueado;

	/**
	 * Instantiates a new celda.
	 *
	 * @param fila the fila
	 * @param col the col
	 */
	public Celda(int fila, int col) {
		this.fila = fila;
		this.col = col;
		minasCercanas = 0;
		mina = false;
		destapado = false;
		bloqueado = false;

		Dimension size = new Dimension(SIZE, SIZE);
		this.setPreferredSize(size);
	}

	/**
	 * Checks if is mina.
	 *
	 * @return true, if is mina
	 */
	public boolean isMina() {
		return mina;
	}

	/**
	 * Sets the mina.
	 *
	 * @param mina the new mina
	 */
	public void setMina(boolean mina) {
		this.mina = mina;
		setText("");
	}

	/**
	 * Gets the fila.
	 *
	 * @return the fila
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * Gets the col.
	 *
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Hay minas cercanas.
	 *
	 * @return true, if successful
	 */
	public boolean hayMinasCercanas() {
		return minasCercanas > 0;
	}

	/**
	 * Checks if is destapado.
	 *
	 * @return true, if is destapado
	 */
	public boolean isDestapado() {
		return destapado;
	}

	/**
	 * Increse mina count.
	 */
	public void increseMinaCount() {
		minasCercanas++;
	}

	/**
	 * Reset.
	 */
	public void reset() {
		mina = false;
		destapado = false;
		bloqueado=false;
		minasCercanas=0;
		setText("");
		setBackground(null);
	}
	
	/**
	 * Bloquear.
	 *
	 * @param isBloqueado the is bloqueado
	 */
	public void bloquear(boolean isBloqueado) {
		bloqueado = isBloqueado;
	}
	
	/**
	 * Gets the bloquear.
	 *
	 * @return the bloquear
	 */
	public boolean getBloquear() {
		return bloqueado;
	}

	/**
	 * Destapar.
	 *
	 * @param destapar the destapar
	 */
	public void destapar(boolean destapar) {
		destapado = destapar;
		if (destapado && bloqueado == false) {
			if (isMina()) {
				setBackground(new Color(68,78,75));
			} else {
				
				Font font= new Font("Arial", Font.BOLD+ Font.ITALIC,18);
				setFont(font);
				setBackground(Color.LIGHT_GRAY);
				if (hayMinasCercanas()) {
					setText(String.valueOf(minasCercanas));
					switch(minasCercanas)
					{
					case 1:
					{
						setForeground(Color.BLUE);
						break;
					}
					case 2:
					{
						setForeground(Color.GREEN.darker());
						break;
					}
					case 3:
					{
						setForeground(Color.RED);
						break;
					}
					case 4:
					{
						setForeground(Color.BLUE.darker());
						break;
					}
					case 5:
					{
						setForeground(Color.RED.darker());
						break;
					}
					case 6:
					{
						setForeground(Color.CYAN.darker());
						break;
					}
					case 7:
					{
						setForeground(Color.BLACK);
						break;
					}
					case 8:
					{
						setForeground(Color.GRAY.darker());
						break;
					}
					default:
					{
						break;
					}
					}
				}
			}
		} 
		else {
			setBackground(null);
			setText("");
		}
	}

}// fin clase Celda
