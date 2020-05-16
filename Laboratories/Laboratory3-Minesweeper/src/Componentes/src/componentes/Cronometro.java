/* Este es el cronometro del busca minas<br><br>
 * <b>Responsabilidad: </b>
 * 
 * 
 * <br><br>
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
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

// TODO: Auto-generated Javadoc
/**
 * The Class timer.
 */
public class Cronometro extends JLabel implements Runnable{
	
	/** The font. */
	private Font font;
	
	/** The time string. */
	private String timeString;
	
	/** The segundos. */
	private int horas,minutos,segundos;
	
	/** The encendido. */
	private boolean encendido;
	
	/** The timer thread. */
	private Thread timerThread;
	
	
	
	/**
	 * Instantiates a new timer.
	 */
	public Cronometro()
	{

		
		setVisible(true);
		
		horas=0;
		minutos=0;
		segundos=0;
		timeString="00:00:00";
		
		encendido=true;
		
		Font font = new Font("arial",Font.BOLD + Font.ITALIC, 24);
		setFont(font);
		setForeground(Color.WHITE);
		setOpaque(false);
	    setText(timeString);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{
		//System.out.println("Estado timer:"+encendido);
		try
		{
			while(encendido)
			{
				setTime();
				Thread.sleep(1000);
				setText(timeString);
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * Sets the time.
	 * 
	 * El encargado de setear el tiempo y dejarlo en formato de horas-minutos-segundos
	 */
	public void setTime()
	{
		
		segundos++;
		if(segundos>59)
		{
			segundos=0;
			segundos++;
			minutos++;
		}
		if(minutos>59 )
		{
			minutos=0;
			horas++;
		}
		if(horas>59)
		{
			horas=0;
		}
		timeString=String.format("%02d:%02d:%02d", horas,minutos,segundos);
	}
	
	/**
	 * Checks if is on.
	 *
	 * @return true, if is on
	 */
	public boolean isOn()
	{
		if(encendido)
			return true;
		else
			return false;
	}
	
	/**
	 * Sets the estado.
	 */
	public void setEstado()
	{
		if(encendido)
			encendido=false;
		else 
			encendido=true;
	}
	
	/**
	 * Gets the tiempo.
	 *
	 * @return the tiempo
	 */
	public int getTiempo()
	{
		int tiempoTotal;
		
		tiempoTotal= (horas*3600)+(minutos*60)+segundos;
		return tiempoTotal;
	}
	
	/**
	 * Gets the segundos.
	 *
	 * @return the segundos
	 */
	public int getSegundos()
	{
		return segundos;
	}
	
	/**
	 * Reset.
	 */
	public void reset()
	{
		
		horas=0;
		minutos=0;
		segundos=0;
		timeString="00:00:00";
		encendido=true;
	}
	
	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public boolean getEstado()
	{
		return encendido;
	}
}
