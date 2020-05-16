/* Esta es la clase VistaProyecto,en ella se añade toda la parte grafica .
 * Responsabilidad: Esta calse se encarga de mostrar en pantalla por medio del JFrame los distintos elmentos graficos
 * (botones, imagenes, etc), y en esta clase se ecuentran las funciones principales del juego. Tiene colaboracion con casi todas
 * las otras clases del proyecto, ya que las utiliza para hacer que el mismo funcione.
 * Colaboración: PanelRuleta, Ruleta, Logica, Apuesta
 * {@link proyectoRuleta.PanelRuleta}
 * {@link proyectoRuleta.Ruleta}
 * {@link proyectoRuleta.Logica}
 * {@link proyectoRuleta.Apuesta}
 * @author Cesar Alberto Becerra Ramirez (1744338)
 * cesar.becerra@correounivalle.edu.co
 * @author Juan Sebastian velasquez (1744936)
 * juan.velasquez.acevedo@correounivalle.edu.co
 * @since 2019-01-02
 * @version 1.0
 * 2019-02-27
 */
package proyectoRuleta;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

// TODO: Auto-generated Javadoc
/**
 * The Class VistaProyecto.
 */
public class VistaProyecto extends JFrame {
	
	/** The imagen. */
	private ImageIcon imagen;
	
	/** The dinero. */
	private int esteEsY, esteEsX, iD, tiro, dineroActual,dineroApostado,cantidadARestar,dinero,valido;
	
	/** The dinero restante. */
	private double tipoDeApuesta,dineroRestante;
	
	/** The puedo oprimir. */
	private boolean puedoDarClick,puedoOprimir;
	
	/** The usar otras monedas. */
	private boolean usarOtrasMonedas;
	
	/** The logic. */
	private Logica logic;
	
	/** The ruleta. */
	private Ruleta ruleta;
	
	/** The apuestas. */
	private ArrayList<Integer>	apuestas ;
	
	/** The lista tipos de apuesta. */
	private ArrayList<Double>	listaTiposDeApuesta ;
	
	/** The lista apuestas. */
	private ArrayList<ArrayList> listaApuestas;
	
	/** The panel ruleta. */
	private PanelRuleta panelRuleta;
	
	/** The g. */
	public Graphics g;
	
	/** The boton ruleta. */
	public JButton botonRuleta ;
	
	/** The boton moneda 10. */
	public JButton botonMoneda10;
	
	/** The boton moneda 50. */
	public JButton botonMoneda50 ;
	
	/** The boton moneda 100. */
	public JButton botonMoneda100 ;
	
	/** The boton moneda 500. */
	public JButton botonMoneda500 ;
	
	/** The apuesta actual. */
	private ArrayList<Integer> apuestaActual;
	
	/** The ganaste ronda. */
	private boolean ganasteRonda;
	
	/** The dinero restante label. */
	private JLabel apostadoRondaLabel, dineroRestanteLabel;
	
	/** The monto panel. */
	private JPanel montoPanel;
	
	/** The Constant CANTIDAD10. */
	public static final int CANTIDAD10 = 10;
	
	/** The Constant CANTIDAD50. */
	public static final int CANTIDAD50 = 50;
	
	/** The Constant CANTIDAD100. */
	public static final int CANTIDAD100 = 100;
	
	/** The Constant CANTIDAD500. */
	public static final int CANTIDAD500 = 500;
	

	
	/**
	 * Este es el constructor del Vista(crea la ventana).
	 */

	public VistaProyecto()
	{
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	initGUI();
		    }
		});

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1217, 690);
		setResizable(true);
		setTitle("Ruleta: The Game");
		JOptionPane.showMessageDialog(null,"PRIMERO ESCOJA LA CANTIDAD A APOSTAR, LUEGO ELIJA SUS NUMEROS Y GIRE LA RULETA. BUENA SUERTE"); 
		
	}
	
	/**
	 * Este es el GUI del vista, en el se inicializan la mayoria de las variables, se añaden los botones que seran utlizados como
	 * monedas, el boton para jugar y añade los respectivos escuchas.
	 */
	private void initGUI()
	{
		
		// inicialización de variables.
		cantidadARestar = 0;
		tipoDeApuesta = 0;
		dineroRestante = 2000;
		puedoOprimir= false;
		usarOtrasMonedas = true;
		logic = new Logica();
		ruleta = new Ruleta();
		apuestas = new ArrayList<Integer>();
		listaTiposDeApuesta = new ArrayList<Double>();
		listaApuestas = new ArrayList<ArrayList>();
		apuestaActual= new ArrayList<Integer>();
		EventosBotones escuchaBotones = new EventosBotones();
		EventosDelRaton escuchaRaton = new EventosDelRaton();
		
		// Aqui se inicializa el panel que t¿contiene los labels con el dinero apostado y el dinero que se tiene.
		montoPanel=new JPanel();
		montoPanel.setBounds(350,520,200,100);
		montoPanel.setBackground(Color.LIGHT_GRAY);
		
		apostadoRondaLabel=new JLabel("  Apuesta: "+String.valueOf(logic.getApostado()));
		dineroRestanteLabel=new JLabel("Dinero: "+String.valueOf(logic.getDinero()));
		
		Font font= new Font(Font.SERIF,Font.BOLD, 20);
		dineroRestanteLabel.setFont(font);
		apostadoRondaLabel.setFont(font);
		dineroRestanteLabel.setForeground(Color.BLACK);
		apostadoRondaLabel.setForeground(Color.BLACK);
		
		montoPanel.add(dineroRestanteLabel);
		montoPanel.add(apostadoRondaLabel);
		
		
		//Inicializacion de componenetes graficos.
		panelRuleta = new PanelRuleta();
		botonRuleta = new JButton();
		botonMoneda10 = new JButton();
		botonMoneda50 = new JButton();
		botonMoneda100 = new JButton();
		botonMoneda500 = new JButton();
		
		//Se setean los componentes
		botonRuleta.setBounds(50, 500, 100, 100);
		botonMoneda10.setBounds(630, 500, 100, 100);
		botonMoneda50.setBounds(735, 500, 100, 100);
		botonMoneda100.setBounds(840, 500, 100, 100);
		botonMoneda500.setBounds(945, 500, 100, 100);
		botonRuleta.setOpaque(false);
		botonRuleta.setContentAreaFilled(false);
		botonRuleta.setBorderPainted(false);
		botonMoneda10.setOpaque(false);
		botonMoneda10.setContentAreaFilled(false);
		botonMoneda10.setBorderPainted(false);
		botonMoneda50.setOpaque(false);
		botonMoneda50.setContentAreaFilled(false);
		botonMoneda50.setBorderPainted(false);
		botonMoneda100.setOpaque(false);
		botonMoneda100.setContentAreaFilled(false);
		botonMoneda100.setBorderPainted(false);
		botonMoneda500.setOpaque(false);
		botonMoneda500.setContentAreaFilled(false);
		botonMoneda500.setBorderPainted(false);
		
		
		
		//Se añaden botones al Frame
		add(montoPanel);
		add(botonRuleta);
		add(botonMoneda10);
		add(botonMoneda50);
		add(botonMoneda100);
		add(botonMoneda500);
		add(panelRuleta);
		
		
		//Se añaden escuchas respectivos
		botonRuleta.addActionListener(escuchaBotones);
		botonMoneda10.addActionListener(escuchaBotones);
		botonMoneda50.addActionListener(escuchaBotones);
		botonMoneda100.addActionListener(escuchaBotones);
		botonMoneda500.addActionListener(escuchaBotones);
		addMouseListener(escuchaRaton);
	}

	
	//Reinicia todos los valores necesarios para empezar un nuevo juego
	/**
	 * Reiniciar.
	 */
	// Esto ocurre cuando el jugador pierde y desea volver a jugar.
	public void reiniciar()
	{
		cantidadARestar = 0;
		tipoDeApuesta = 0;
		dineroRestante = 2000;
		puedoOprimir= false;
		usarOtrasMonedas = true;
		logic = new Logica();
		ruleta = new Ruleta();
		apuestas = new ArrayList<Integer>();
		listaTiposDeApuesta = new ArrayList<Double>();
		listaApuestas = new ArrayList<ArrayList>();
		apuestaActual= new ArrayList<Integer>();
		dineroRestanteLabel.setText("Dinero: " + Double.toString(logic.dineroQueSeTiene(0)));
		apostadoRondaLabel.setText("Apostado: " + Double.toString(0));
	}
	
	
	/**
	 * Aviso pierde juego.
	 * Esta funcion evalua si el dinero que tienes es menor que 10, como 10 es el valor minimo para apostar si es menor que este
	 * se acabo el juego y el jugador pierde.
	 * La funcion logic.dineroQueSeTiene(0)==0 realmente es un get que saca el dinero del jugador
	 * 
	 * @return true, if successful
	 */
	public boolean avisoPierdeJuego()
	{
		boolean perdioJuego=false;
		if(logic.dineroQueSeTiene(0)==0 || logic.dineroQueSeTiene(0)<10)
		{	
			perdioJuego=true;
			int deNuevo= JOptionPane.showConfirmDialog(null, "Perdiste el juego, ya no te queda dinero para apostar :c  ¿Quieres volver a Jugar?", "Ruleta The game",JOptionPane.YES_NO_OPTION);
			if(deNuevo==0) 
			{
				reiniciar();
			}
			else 
				System.exit(0); // termina y sale 
		}
		return perdioJuego;
	}
	
	
	/**
	 * determina si se puede apostar.
	 * 
	 * @return true, if successful
	 */
	public boolean sePuedeApostar()
	{
		boolean sePuede=true;
		if( logic.dineroQueSeTiene(0)==0 || logic.dineroQueSeTiene(0) - dinero <0)
			sePuede=false;
		else 
			sePuede=true;
		return sePuede;
	}
	
	/**
	 * The Class EventosBotones.
	 * Este es el escucha de los botones.
	 */

	private class EventosBotones implements ActionListener 
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 * Hay 5 botones, 4 corresponden a monedas y uno es utilizado para iniciar el juego.
		 * los comandos de cada boton estan restringidos por medio de condionales, que determinan que botones se 
		 * deben presionar en cada etapa del juego( determina el orden).
		 * Ademas determina cuando el juego termina.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
		Object botonPulsado = e. getSource();
		/*boton encargado de "jugar", es decir, se encarga de girar la ruleta(generar un numero aleatorio) y comparar con las apuestas
		 * dadas.
		*/
		if(botonPulsado == botonRuleta) 
		{
			if(puedoOprimir) 
			{
				int x20 = ruleta.girarRuleta();
				String randomRuleta= Integer.toString(x20);
				JOptionPane.showMessageDialog(null,"La bolita cayó en el número: "+randomRuleta);

				ganasteRonda=logic.comparar(x20, listaApuestas,listaTiposDeApuesta,apuestaActual);
				dineroRestanteLabel.setText("Dinero: " + Double.toString(logic.dineroQueSeTiene(0)));
						
				listaTiposDeApuesta.clear();
				apuestaActual.clear();
				apuestas.clear();
				listaApuestas.clear();
				puedoOprimir = false;
				
				if(ganasteRonda)
				{	
					int deNuevo= JOptionPane.showConfirmDialog(null, "Ganaste la ronda ¿Quieres seguir Jugando?", "Ruleta The game",JOptionPane.YES_NO_OPTION);
					dineroRestante =logic.dineroQueSeTiene(0);
					if(deNuevo==0) 
						apostadoRondaLabel.setText("Apostado: " + Double.toString(0));
					else 
						System.exit(0); // termina y sale 
				}
				
				else if(!ganasteRonda)
				{	
					JOptionPane.showConfirmDialog(null, "Perdiste la ronda", "Ruleta The game", JOptionPane.PLAIN_MESSAGE);
					boolean perdioJuego=avisoPierdeJuego();
					if(!perdioJuego)
					{
						int deNuevo= JOptionPane.showConfirmDialog(null, "Quieres seguir Jugando", "Ruleta The game",JOptionPane.YES_NO_OPTION);
						if(deNuevo==0)
							apostadoRondaLabel.setText("Apostado: " + Double.toString(0));
						else 
							System.exit(0); // termina y sale 
					}
				}

			}
				
		}
		/*
		 * los botones moneda determinan el valor por el que el usuario va a apostar
		 */
		if(botonPulsado == botonMoneda10)
		{
			if(usarOtrasMonedas ) 
			{
				if(dineroRestante - 10 >= 0) 
				{
				avisoPierdeJuego();
				dinero =logic.dineroQueSeAposto(CANTIDAD10);
				apuestaActual.add(CANTIDAD10);
				cantidadARestar = CANTIDAD10;
				apostadoRondaLabel.setText("Apostado: " + Double.toString(dinero));
				puedoDarClick = true;
				usarOtrasMonedas = false;
				}
				else 
				{
					JOptionPane.showMessageDialog(null,"No tienes dinero para apostar esta cantidad, intenta con otra");
				}
			}
		}
			
		else if(botonPulsado == botonMoneda50)
		{
			if(usarOtrasMonedas ) 
			{
				if(dineroRestante - 50 >= 0) 
				{
					avisoPierdeJuego();
					dinero =logic.dineroQueSeAposto(CANTIDAD50);
					apuestaActual.add(CANTIDAD50);
					cantidadARestar = CANTIDAD50;
					apostadoRondaLabel.setText("Apostado: " + Double.toString(dinero));
					puedoDarClick = true;
					usarOtrasMonedas = false;
				}
				else 
				{
					JOptionPane.showMessageDialog(null,"No tienes dinero para apostar esta cantidad, intenta con otra");
				}
					
			}
	
		}
		else if(botonPulsado == botonMoneda100)
		{
			if(usarOtrasMonedas ) 
			{
				if(dineroRestante - 100 >= 0) 
				{
					avisoPierdeJuego();
					dinero =logic.dineroQueSeAposto(CANTIDAD100);
					apuestaActual.add(CANTIDAD100);
					cantidadARestar = CANTIDAD100;
					apostadoRondaLabel.setText("Apostado: " + Double.toString(dinero));
					puedoDarClick = true;
					usarOtrasMonedas = false;
				}
				else 
				{
					JOptionPane.showMessageDialog(null,"No tienes dinero para apostar esta cantidad, intenta con otra");
				}
			}
		}
			
		else if(botonPulsado == botonMoneda500)
		{
			if(usarOtrasMonedas ) 
			{
				if(dineroRestante - 500 >= 0) 
				{
					avisoPierdeJuego();
					dinero =logic.dineroQueSeAposto(CANTIDAD500);
					apuestaActual.add(CANTIDAD500);
					cantidadARestar = CANTIDAD500;
					apostadoRondaLabel.setText("Apostado: " + Double.toString(dinero));
					puedoDarClick = true;
					usarOtrasMonedas = false;
				}
				else 
				{
					JOptionPane.showMessageDialog(null,"No tienes dinero para apostar esta cantidad, intenta con otra");
				}
			}
		}
	}
		
	}
			
	
	/**
	 * The Class EventosDelRaton.
	 * El escucha del raton se encarga de obtener las cordenadas X y Y en el momento de dar click.
	 * por medio de estas coordenadas llama a la logica para determinar el ID, y con este ID la apuesta.
	 */
	//---------------------------------------------------------------------------------------------------------------------------------
	private class EventosDelRaton extends MouseAdapter{
			
		/* (non-Javadoc)
		 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		public void mouseClicked(MouseEvent e) {
			int xRaton = e.getX();
			int yRaton = e.getY();
			esteEsY = yRaton;
			esteEsX = xRaton;
			valido = 0;
			Apuesta nuevaApuesta=new Apuesta(esteEsX,esteEsY); // se inicializa una apuesta nueva.
			iD = nuevaApuesta.evaluarXY();
			
			if(puedoDarClick) 
			{
				if(logic.estaEnRango (esteEsX, esteEsY) && logic.apuestaValida(iD)) 
				{
					dineroRestante = logic.dineroQueSeTiene(cantidadARestar);
					dineroRestanteLabel.setText("Dinero: " + Double.toString(dineroRestante));
					tipoDeApuesta = nuevaApuesta.identificarApuesta(iD);
					listaTiposDeApuesta.add(tipoDeApuesta);
					nuevaApuesta.identificarApuesta(iD);
					ArrayList<Integer> apuestas = nuevaApuesta.estaEsLaApuesta(iD);
					
					String listString = (String) apuestas.stream().map(Object::toString).collect(Collectors.joining(", "));
					JOptionPane.showMessageDialog(null,"Numeros Apostados: " + listString);
						
					listaApuestas.add(apuestas);
					puedoOprimir = true;
					puedoDarClick = false;
					usarOtrasMonedas = true;
				}
	
			}
		}
			
			
	}
}

//FIN DE CLASE
	


