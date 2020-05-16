package concentrese;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

// TODO: Auto-generated Javadoc
/**
 * Esta clase cumple la función de administrar la entradas y salidas del juego. Es la parte grafica del mismo.<br><br>
 * <b>Responsabilidad: </b>Es la encargada de iniciar el juego, aparte de administrar la parte grafica del mismo.
 * se apoya en {@link concentrese.ControlConcentrese} para la realización de funciones logicas, como lo puede ser
 * la comparación de las cartas de la clase {@link concentrese.CartaConcentrese}<br><br>
 * <b>Colaboración: </b>{@link concentrese.ControlConcentrese} ,  {@link concentrese.CartaConcentrese}
 * @author Cesar Alberto Becerra Ramirez (1744338), Juan Sebastian Velasquez (1744936)<br>
 * cesar.becerra@correounivalle.edu.co , juan.velasquez.acevedo@correounivalle.edu.co
 * @version 1.0<br>
 * 2018-10-01
 * @since 2018-10-01
 */
@SuppressWarnings("serial")
public class GUIConcentrese extends JFrame{
	
	/** El control. */
	private ControlConcentrese control;
	
	/** El escucha. */
	private EscuchaEventosAction escucha; 
	
	/** El titulo. */
	private JLabel titulo;
	
	/** El contenedor. */
	private Container contenedor;
	
	/** El marco. */
	private JPanel marco;
	
	/**las cartas. */
	private CartaConcentrese[][] cartas;
	
	/** El tema. */
	private String tema;
	
	/** La carta actual. */
	private CartaConcentrese cartaActual;
	
	/** La carta pasada en ser clickeada, y con la que se realiza la comparación. */
	private CartaConcentrese cartaPasada;
	
	/** El timer. */
	private Timer timer;
	
	/** El contador. Que me permite determinar el final del juego */
	private int contador;
	
	/**
	 * Instancias del  GUI, titulado como concentrese.
	 */
	public GUIConcentrese(){
		initGUI();
		
		setTitle("Concentrese");
		setLocation(0,0);
		pack();
		setResizable(true);
		setVisible(true);
	}
	
	/**
	 * Se inicializan los componentes que sera utilizados para conformar la parte visual. 
	 */
	public void initGUI()
	{

		control=new ControlConcentrese();
	    escucha = new EscuchaEventosAction();
		contenedor = this.getContentPane();
		contenedor.setLayout(new BorderLayout());
		contador= 20;

		int FILAS= ControlConcentrese.getFilas();
		int COLUMNAS= ControlConcentrese.getColumnas();
		timer= new Timer (200,escucha); // temporizador 
		cartas = new CartaConcentrese[FILAS][COLUMNAS]; //se crea una copia de cartas 
		
		tema= "";
	
		
		titulo = new JLabel("Juego Concentrese");
		contenedor.add(titulo,BorderLayout.PAGE_START);
		String[] valoresPosibles = { "futbol", "baloncesto"};
		
		marco = new JPanel();
		marco.setLayout(new GridLayout(4,5));
		contenedor.add(marco,BorderLayout.CENTER);	
		
		// Se guarda la respuesta, la cual puede ser cualquiera del arreglo de valores posibles declarada previamente.
		int respuesta= JOptionPane.showOptionDialog(null,
													"Decida el tema",
													"Concentrese", 
													JOptionPane.DEFAULT_OPTION,
													JOptionPane.QUESTION_MESSAGE,
													null,
													valoresPosibles,
													valoresPosibles[0]);
		//Se guarda el tema escogido por el usuario
		if(respuesta==0)
			tema= "futbol";
		else 
			tema="baloncesto";
		
		
		cartas=control.distribuirCartas(tema);// Se distribuyen las cartas desde control que arroja una matriz y se guardan en la copia de cartas.
		
		//Se asignan los escucha para cada boton y se les cambia la cara, poniendolas hacia abajo
		for(int fila=0; fila<FILAS; fila++ )
		{
			for(int col=0; col<COLUMNAS; col++)
			{
				cartas[fila][col].down();
				cartas[fila][col].addMouseListener(escucha);
				marco.add(cartas[fila][col]);
			}
		}
		setVisible(true);
	}
	
	
	/**
	 * La clase EscuchaEventosAction.
	 */
	private class EscuchaEventosAction extends MouseAdapter implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		public void mouseClicked(MouseEvent e) 
		{
			cartaActual=(CartaConcentrese)e.getSource();
			cartas=control.cambiarCarta(cartaActual);
			cartaPasada=control.getCartaPasada();
			int esPareja= control.sonPareja(cartaActual);
			// si no son pareja entra con el time
			if(esPareja==3)
				timer.start();
			// si son pareja
			else if(esPareja==1)
			{
				cartas[cartaPasada.getRow()][cartaPasada.getCol()].removeMouseListener(escucha);
				cartas[cartaActual.getRow()][cartaActual.getCol()].removeMouseListener(escucha);
				contador-=2;
			}
			// si apenas Empieza
			else if(esPareja==0)
				cartaPasada=cartaActual;
			// si termina el juego 
			if(contador==0)
			{	
				JOptionPane.showConfirmDialog(null, "Ganaste", "Encuentra a la pareja", JOptionPane.PLAIN_MESSAGE);
				int deNuevo= JOptionPane.showConfirmDialog(null, "Quieres volver a Jugar", "Encuentra a la pareja",JOptionPane.YES_NO_OPTION);
				if(deNuevo==0) // quiere volver a jugar, vuelve a iniciar el initGUI
				{
					marco.removeAll();
					initGUI();
				}
				else 
					System.exit(0); // termina y sale 
				
			}
			
			
		}
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		// El timer cuando se equivoca el jugador 
		@Override
		public void actionPerformed(ActionEvent evento)
		{
			cartas[cartaPasada.getRow()][cartaPasada.getCol()].setImagen();
			cartas[cartaActual.getRow()][cartaActual.getCol()].setImagen();
			timer.stop();
			
		}
		
	}
	
	
	
}
