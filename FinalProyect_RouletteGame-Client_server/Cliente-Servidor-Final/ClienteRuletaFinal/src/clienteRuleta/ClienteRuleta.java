/* Esta clase cumple la función de iniciar el juego de la Ruleta.<br><br>
 * <b>Responsabilidad: </b>Iniciar el juego haciendo un llamado a la Clase VistaProyecto.<br><br>
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


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


// TODO: Auto-generated Javadoc
/**
 * The Class ClienteRuleta.
 */
public class ClienteRuleta extends JFrame implements Runnable{
	
	
	/** The campo id. */
	private JTextField campoId; 
	
	/** The area pantalla. */
	private JTextArea areaPantalla;
	
	/** The conexion. */
	private Socket conexion; 
	
	/** The entrada. */
	private Scanner entrada; 
	
	/** The salida. */
	private Formatter salida; 
	
	/** The host ruleta. */
	private String hostRuleta; 
	
	/** The nombre. */
	private String nombre; 
	
	/** The mi turno. */
	private boolean miTurno;
	
	/** The boton 10. */
	boolean boton10=false;
	
	/** The boton 50. */
	boolean boton50=false;
	
	/** The boton 100. */
	boolean boton100=false;
	
	/** The boton 500. */
	boolean boton500=false;
	
	/** The boton jugar. */
	boolean botonJugar=false;
	
	/** The button pressed. */
	private int buttonPressed=0;
	
	/** The imagen. */
	private ImageIcon imagen;
	
	/** The dinero. */
	private int esteEsY, esteEsX, iD, tiro, dineroActual,dineroApostado,cantidadARestar,dinero,valido;
	
	/** The puedo oprimir. */
	private boolean puedoDarClick,puedoOprimir;
	
	/** The usar otras monedas. */
	private boolean usarOtrasMonedas;
	
	/** The logic. */
	private Logica logic;
	
	/** The ruleta. */
	private Ruleta ruleta;
	
	/** The panel ruleta. */
	private PanelRuleta panelRuleta;
	
	/** The g. */
	private Graphics g;
	
	/** The boton ruleta. */
	private JButton botonRuleta ;
	
	/** The boton moneda 10. */
	private JButton botonMoneda10;
	
	/** The boton moneda 50. */
	private JButton botonMoneda50 ;
	
	/** The boton moneda 100. */
	private JButton botonMoneda100 ;
	
	/** The boton moneda 500. */
	private JButton botonMoneda500 ;
	
	//DATOS JUGADOR 1
	
	/** The apuestas. */
	private ArrayList<Integer>	apuestas ;
	
	/** The lista tipos de apuesta. */
	private ArrayList<Double>	listaTiposDeApuesta ;
	
	/** The lista apuestas. */
	private ArrayList<ArrayList> listaApuestas;
	
	/** The apuesta actual. */
	private ArrayList<Integer> apuestaActual;
	
	/** The ganaste ronda. */
	private boolean ganasteRonda;
	
	/** The dinero restante label. */
	private JLabel apostadoRondaLabel, dineroRestanteLabel;
	
	/** The monto panel. */
	private JPanel montoPanel;
	
	/** The dinero restante. */
	private double tipoDeApuesta,dineroRestante;
	
	/** The Constant CANTIDAD10. */
	public static final int CANTIDAD10 = 10;
	
	/** The Constant CANTIDAD50. */
	public static final int CANTIDAD50 = 50;
	
	/** The Constant CANTIDAD100. */
	public static final int CANTIDAD100 = 100;
	
	/** The Constant CANTIDAD500. */
	public static final int CANTIDAD500 = 500;
	
	/** The identificador. */
	private String identificador;
	
	/** The apuesta. */
	private Apuesta apuesta;
	
	/** The lista de monedas. */
	private ArrayList<Moneda> listaDeMonedas;
	
	/** The moneda. */
	private Moneda moneda;
	
	/** The button press. */
	private int buttonPress;
	
	/** The Constant JUGADOR1. */
	private static final String JUGADOR1 = "Jugador-1"; 
	
	/** The Constant JUGADOR2. */
	private static final String JUGADOR2 = "Jugador-2"; 
	
	private int numeroRuleta=0;
	
	
	

	/**
	 * Instantiates a new cliente ruleta.
	 *
	 * @param host the host
	 */
	public ClienteRuleta( String host ){

		nombre="";
		nombre = JOptionPane.showInputDialog(null, "Ingrese su nombre", ""); 
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				if ((nombre != null) && (nombre.length() > 0)) { 
					initGUI();
					JOptionPane.showMessageDialog(null,"PRIMERO ESCOJA LA CANTIDAD A APOSTAR, LUEGO ELIJA SUS NUMEROS Y GIRE LA RULETA. BUENA SUERTE");
				}
				else
				{
					nombre="N.N";
					initGUI();
					JOptionPane.showMessageDialog(null,"PRIMERO ESCOJA LA CANTIDAD A APOSTAR, LUEGO ELIJA SUS NUMEROS Y GIRE LA RULETA. BUENA SUERTE");
				}
				
			}
		});
		
		areaPantalla = new JTextArea( 4, 30 ); 
		areaPantalla.setEditable( false );
		add( new JScrollPane( areaPantalla ), BorderLayout.SOUTH );
		
		
		
		hostRuleta = host; 
		campoId = new JTextField(); 
		campoId.setEditable( false );
		add( campoId, BorderLayout.NORTH );
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1217, 690);
		setResizable(true);
		setTitle("Ruleta: The Game");
		iniciarCliente();
	}
	
	
	
	
	/**
	 * Iniciar cliente.
	 */
	public void iniciarCliente(){
		
		try {
			conexion = new Socket(InetAddress.getByName( hostRuleta ), 60000 );
			entrada = new Scanner( conexion.getInputStream() );
			salida = new Formatter( conexion.getOutputStream() );
		}
		catch ( IOException excepcionES ){
			excepcionES.printStackTrace();
		} 
		
		ExecutorService ejecutor = Executors.newFixedThreadPool(1);
		ejecutor.execute(this);

	} 
	

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		identificador = entrada.nextLine(); 
		SwingUtilities.invokeLater(new Runnable() {
										public void run(){
										    campoId.setText( identificador+": " + nombre );
										} 
		}); 	
		miTurno = ( identificador.equals( JUGADOR1 ) ); 
		while ( true ) {
			if ( entrada.hasNextLine() )
			procesarMensaje( entrada.nextLine() );
		}
		
	} 
	
	
	//AQUI CESAR ES DONDE TENES QUE METER ESO DE LAS MONEDAS, CUANDO EL MENSAJE ES MOVIMIENTO TABLERO, SI PODES VER ES UN CONDICIONAL
	
	
	/**
	 * Procesar mensaje.
	 *
	 * @param mensaje the mensaje
	 */
	private void procesarMensaje( String mensaje ){
		//Verifico Id's en el tablero y valido 
		if (mensaje.equals( "Movimiento Tablero" ) || mensaje.equals( "Se oprime boton Jugar" ) ||
				mensaje.equals( "Moneda10" ) ||
				mensaje.equals( "Moneda50") ||
				mensaje.equals( "Moneda100") ||
				mensaje.equals( "Moneda500") ) 
			
		{
			mostrarMensaje( "Movimiento-Valido Jugador1, por favor espere.\n" );
			mostrarMensaje( "MENSAJE CUANDO ENTRA." + mensaje+"\n");
					//AQUI CESAR, ADENTRO DE ESTE CONDICIONAL. SI VES ES MUY PARECIDO A LO QUE HACEMOS EN EL PROYECTO NORMAL
					//SI NECESITAS CLASES LAS COLOCAS COMO PRIVADAS ABAJO DEL TODO, ABAJO ESTA EL GRAPHICS Y TODO
					if(mensaje.equals( "Movimiento Tablero" ))
					{
						
						if(puedoDarClick)
						{
							listaDeMonedas = panelRuleta.getListaMonedas();
							moneda = new Moneda(esteEsX, esteEsY);
							moneda.setBotonPulsado(buttonPress);
							listaDeMonedas.add(moneda);
							panelRuleta.setListaMonedas(listaDeMonedas);
							panelRuleta.repaint();
							dineroRestante = logic.dineroQueSeTiene(cantidadARestar);
							dineroRestanteLabel.setText("Dinero: " + Double.toString(dineroRestante));
							tipoDeApuesta = apuesta.identificarApuesta(iD);
							listaTiposDeApuesta.add(tipoDeApuesta);
							apuesta.identificarApuesta(iD);
							ArrayList<Integer> apuestas = apuesta.estaEsLaApuesta(iD);		
							String listString = (String) apuestas.stream().map(Object::toString).collect(Collectors.joining(", "));
							JOptionPane.showMessageDialog(null,"Numeros Apostados Jugador 1: " + listString);
							listaApuestas.add(apuestas);
							puedoOprimir = true;
							puedoDarClick = false;
							usarOtrasMonedas = true;
						}
					}
					//Se verifican los Botones
					if (mensaje.equals("Se oprime boton Jugar"))
					{

						if(entrada.hasNextInt())
						{

							numeroRuleta=entrada.nextInt();
							entrada.nextLine();
						}
						if(puedoOprimir)
						{
							String randomRuleta= Integer.toString(numeroRuleta);
							JOptionPane.showMessageDialog(null,"La bolita cayó en el número: "+randomRuleta);
							ganasteRonda=logic.comparar(numeroRuleta, listaApuestas,listaTiposDeApuesta,apuestaActual);
							dineroRestanteLabel.setText("Dinero: " + Double.toString(logic.dineroQueSeTiene(0)));							
							listaDeMonedas = panelRuleta.getListaMonedas();
							listaDeMonedas.clear();
							panelRuleta.setListaMonedas(listaDeMonedas);
							panelRuleta.repaint();
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
								miTurno = true;
							}
						}
								 
						if (mensaje.equals("Moneda10"))
						{

							if(entrada.hasNextInt())
							{
								buttonPress=entrada.nextInt();
								entrada.nextLine();
							}
							if(usarOtrasMonedas)
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
						
						if (mensaje.equals("Moneda50"))
						{
							if(entrada.hasNextInt())
							{
								buttonPress=entrada.nextInt();
								entrada.nextLine();
							}
							if(usarOtrasMonedas)
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
						
						if (mensaje.equals("Moneda100"))
						{
							if(entrada.hasNextInt())
							{
								buttonPress=entrada.nextInt();
								entrada.nextLine();
							}
						if(usarOtrasMonedas)
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
						
						if (mensaje.equals("Moneda500"))
						{
							if(entrada.hasNextInt())
							{
								buttonPress=entrada.nextInt();
								entrada.nextLine();
							}
							if(usarOtrasMonedas)
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
		
		
		else if ( mensaje.equals( "Movimiento invalido, intente de nuevo" ) ){
	 		miTurno = true; 
		} 
	
		if ( mensaje.equals( "El oponente hizo movimiento" ) ){
			int x=0;
			int y=0;
			int botonPresionado=0;
			boolean jugar= false;

			if (entrada.hasNextInt())
			{
				x = entrada.nextInt();
				y = entrada.nextInt();
				botonPresionado=entrada.nextInt();
				entrada.nextLine();
				
				listaDeMonedas = panelRuleta.getListaMonedas();
				moneda = new Moneda(x, y);
				moneda.setBotonPulsado(botonPresionado);
				listaDeMonedas.add(moneda);
				panelRuleta.setListaMonedas(listaDeMonedas);
				panelRuleta.repaint();
			}
			
			if (entrada.hasNextBoolean())
			{
				jugar= entrada.nextBoolean();
				if(jugar)
				{
					mostrarMensaje( "El oponente oprimio el boton jugar. Ahora es su turno.\n" );
					miTurno = true;
		
				}
			}
			 
		}

		else
		{
			mostrarMensaje( mensaje + "\n" ); 
		}
		

	
	} 
	

	/**
	 * Enviar ID clic.
	 *
	 * @param iD the i D
	 */
	public void enviarIDClic( int iD ) {
	    	 salida.format( "%d\n", iD ); 
	    	 salida.flush();
	    
	} 
	
	/**
	 * Enviarbutton press.
	 *
	 * @param buttonPress the button press
	 */
	public void enviarbuttonPress( int buttonPress ) {
	   	 salida.format( "%d\n", buttonPress ); 
	   	 salida.flush();
	   
	}
	
	
	/**
	 * Establecer ID actual.
	 *
	 * @param iD the i D
	 */
	public void establecerIDActual( int iD ) {
		this.iD = iD; 
	} 
	
	/**
	 * Establecer XY.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void establecerXY(int x, int y)
	{	
		esteEsX=x;
		esteEsY=y;
	}
	
	/**
	 * Enviar bool boton.
	 *
	 * @param boton the boton
	 */
	public void enviarBoolBoton(boolean boton)
	{
		salida.format( "%b\n", boton );
	    salida.flush();
	}
	
	
	
	/**
	 * Enviar XY.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void enviarXY(int x, int y)
	{
		salida.format( "%d\n", x );
	    salida.format( "%d\n", y );
	    salida.flush();
	}
	
	private void mostrarMensaje( String mensajeAMostrar ){
		SwingUtilities.invokeLater( new Runnable() {
										 public void run() {
											areaPantalla.append( mensajeAMostrar ); 
										 } 
									 }); 
	} 
	

	
	
	
	
	/**
	 * Este es el GUI del vista, en el se inicializan la mayoria de las variables, se añaden los botones que seran utlizados como
	 * monedas, el boton para jugar y añade los respectivos escuchas.
	 */
	private void initGUI()
	{

		esteEsX=0;
		esteEsY=0;
		// inicialización de variables.
		cantidadARestar = 0;
		tipoDeApuesta = 0;
		dineroRestante = 2000;
		//dineroRestanteJ2=2000;
		puedoOprimir= false;
		usarOtrasMonedas = true;
		logic = new Logica();
		ruleta = new Ruleta();
		apuesta=new Apuesta(esteEsX, esteEsY);
		apuestas = new ArrayList<Integer>();
		listaTiposDeApuesta = new ArrayList<Double>();
		listaApuestas = new ArrayList<ArrayList>();
		apuestaActual= new ArrayList<Integer>();
		EventosBotones escuchaBotones = new EventosBotones();
		EventosDelRaton escuchaRaton = new EventosDelRaton();
		
		// Aqui se inicializa el panel que contiene los labels con el dinero apostado y el dinero que se tiene.
		montoPanel=new JPanel();
		montoPanel.setBounds(180,520,200,100);
		montoPanel.setBackground(Color.LIGHT_GRAY);
		apostadoRondaLabel=new JLabel("  Apuesta: "+String.valueOf(logic.getApostado()));
		dineroRestanteLabel=new JLabel("Dinero: "+String.valueOf(logic.getDinero()));
		Font font= new Font(Font.SERIF,Font.BOLD, 20);
		dineroRestanteLabel.setFont(font);
		apostadoRondaLabel.setFont(font);
		dineroRestanteLabel.setForeground(Color.BLACK);
		apostadoRondaLabel.setForeground(Color.BLACK);
		montoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(nombre));
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
		//add(montoPanelJ2);
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
		listaDeMonedas = panelRuleta.getListaMonedas();
		listaDeMonedas.clear();
		panelRuleta.setListaMonedas(listaDeMonedas);
		panelRuleta.repaint();
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

		boolean boton10=false;
		boolean boton50=false;
		boolean boton100=false;
		boolean boton500=false;
		boolean botonJugar=false;

			if(botonPulsado == botonRuleta) 
			{
				botonJugar=true;
				System.out.println("Se oprime botonJugar: ");
				enviarBoolBoton(botonJugar);
				enviarBoolBoton(boton10);
				enviarBoolBoton(boton50);
				enviarBoolBoton(boton100);
				enviarBoolBoton(boton500);
					
			}
		
			/*
			 * los botones moneda determinan el valor por el que el usuario va a apostar
			 */
		
		
			if(botonPulsado == botonMoneda10)
			{
				boton10=true;
				buttonPressed=1;
				System.out.println("Se oprime boton10: ");
				enviarBoolBoton(botonJugar);
				enviarBoolBoton(boton10);
				enviarBoolBoton(boton50);
				enviarBoolBoton(boton100);
				enviarBoolBoton(boton500);
			}
				
			else if(botonPulsado == botonMoneda50)
			{
				boton50=true;
				buttonPressed=2;
				System.out.println("Se oprime boton50: ");
				enviarBoolBoton(botonJugar);
				enviarBoolBoton(boton10);
				enviarBoolBoton(boton50);
				enviarBoolBoton(boton100);
				enviarBoolBoton(boton500);
			}
			else if(botonPulsado == botonMoneda100)
			{
				boton100=true;
				buttonPressed=3;
				System.out.println("Se oprime boton100: ");
				enviarBoolBoton(botonJugar);
				enviarBoolBoton(boton10);
				enviarBoolBoton(boton50);
				enviarBoolBoton(boton100);
				enviarBoolBoton(boton500);
			}
				
			else if(botonPulsado == botonMoneda500)
			{
				boton500=true;
				buttonPressed=4;
				System.out.println("Se oprime boton500: ");
				enviarBoolBoton(botonJugar);
				enviarBoolBoton(boton10);
				enviarBoolBoton(boton50);
				enviarBoolBoton(boton100);
				enviarBoolBoton(boton500);
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
			valido = 0; // se inicializa una apuesta nueva.
			establecerXY(xRaton,yRaton);
			int iDTablero=0;
			Apuesta nuevaApuesta=new Apuesta(xRaton,yRaton);
			iDTablero = nuevaApuesta.evaluarXY();
			iD=iDTablero;
			System.out.println("ID: "+iD);

			System.out.println("IDTablero: "+iD);
			apuesta=nuevaApuesta; 
	
			System.out.println("X: "+esteEsX);
			System.out.println("Y: "+esteEsY);

			enviarXY(esteEsX,esteEsY); 
			enviarIDClic(iD); 

			enviarbuttonPress(buttonPressed );

			

		}
			
			
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	//AQUI CESAR, ESTAN LAS CLASES PRIVADAS, SI NECESITAS METER ALGUNA NUEVA O MODIFICAR
	//PRIMERO HAGA LO DE LAS MONEDAS, LUEGO MIRAMOS LO DE CRONOMETRO
	
	//CLASES PRIVADAS
	
	/**
	 * The Class Moneda.
	 */
	//clase ruleta
	private class Moneda {
		
		/** The y. */
		private int x,y = 0;
		
		/** The icon. */
		private Image icon;
		
		/** The mi icono 10. */
		private File miIcono10 = new File("src/imagenes/10.png");
		
		/** The mi icono 50. */
		private File miIcono50 = new File("src/imagenes/50.png");
		
		/** The mi icono 100. */
		private File miIcono100 = new File("src/imagenes/100.png");
		
		/** The mi icono 500. */
		private File miIcono500 = new File("src/imagenes/500.png");
		
		/** The boton pulsado. */
		private int botonPulsado = 0;
		
		/**
		 * Instantiates a new moneda.
		 *
		 * @param x the x
		 * @param y the y
		 */
		public Moneda(int x, int y) {
			setX(x);
			setY(y);
		}
		
		/**
		 * Pintar.
		 *
		 * @param g the g
		 */
		public void pintar(Graphics g) {
			if(botonPulsado == 1) {
			try {
				icon = ImageIO.read(miIcono10);
			}
			catch(IOException e) {
				System.out.println("no se encontro la imagen");
			}
			g.drawImage(icon,x-8-20,y-50-20,null);
			}
			else if(botonPulsado ==2){
				try {
					icon = ImageIO.read(miIcono50);
				}
				catch(IOException e) {
					System.out.println("no se encontro la imagen");
				}
				g.drawImage(icon,x-8-20,y-50-20,null);
				}
			else if(botonPulsado ==3){
				try {
					icon = ImageIO.read(miIcono100);
				}
				catch(IOException e) {
					System.out.println("no se encontro la imagen");
				}
				g.drawImage(icon,x-8-20,y-50-20,null);
				}
			else if(botonPulsado ==4){
				try {
					icon = ImageIO.read(miIcono500);
				}
				catch(IOException e) {
					System.out.println("no se encontro la imagen");
				}
				g.drawImage(icon,x-8-20,y-50-20,null);
				}
			
		}
		
		/**
		 * Sets the x.
		 *
		 * @param x the new x
		 */
		public void setX(int x) {
			this.x= x;
		}
		
		/**
		 * Sets the y.
		 *
		 * @param y the new y
		 */
		public void setY(int y) {
			this.y= y;
		}
		
		/**
		 * Sets the boton pulsado.
		 *
		 * @param botonPulsado the new boton pulsado
		 */
		public void setBotonPulsado(int botonPulsado) {
			this.botonPulsado= botonPulsado;
		}

	}
	
	/**
	 * The Class Ruleta.
	 */
	private class Ruleta {
		
		/**  El resultado es el valor que genera la ruleta. */
		private int resultado;
		
		/**
		 * Girar ruleta es la funcion que genera el numero aleatorio y se lo asigna a la variable resultado.
		 *
		 * @return entero
		 */
		public int girarRuleta() {
			
			Random tirar = new Random();
		    resultado = tirar.nextInt(37);
		    return resultado;
		}

	}

	//fin de clase ruleta
	
	//Clase PanelRuleta
	
	/**
	 * The Class PanelRuleta.
	 */
	private class PanelRuleta extends Container {
		
		/** The lista monedas. */
		//CAMBIO: SE CREA UN ARRAYLIST DE MONEDAS
		private ArrayList<Moneda> listaMonedas = null;
		
		/** The g. */
		private Graphics g;
		
		/** The fondo. */
		private Image fondo;
		
		/** The mi fondo. */
		private File miFondo = new File("src/imagenes/fondo.png");
		
		/**
		 * Instantiates a new panel ruleta.
		 */
		//CAMBIO:SE AÑADE EL CONSTRUCTOR
		public PanelRuleta(){
			setListaMonedas(new ArrayList<>());
			
		}
		
		/* (non-Javadoc)
		 * @see java.awt.Container#paint(java.awt.Graphics)
		 */
		public void paint(Graphics g) {	
			try {
				fondo = ImageIO.read(miFondo);
			}
			catch(IOException e) {
				System.out.println("no se encontro la imagen");
			}
			g.drawImage(fondo,0,0,null);
			super.paint(g);
			//CAMBIO:SE AÑADE UN FOR QUE RECORRE LAS LISTA DE MONEDAS Y PINTA LOS ELEMENTOS QUE TIENE
			for(Moneda objMoneda: getListaMonedas()) {
				objMoneda.pintar(g);
			}
		}
		
		/**
		 * Gets the lista monedas.
		 *
		 * @return the lista monedas
		 */
		//CAMBIO:FUNCION QUE DEVUELVE LA LISTA DE MONEDAS
		public ArrayList<Moneda> getListaMonedas(){
			return listaMonedas;
		}
		
		/**
		 * Sets the lista monedas.
		 *
		 * @param listaMoneda the new lista monedas
		 */
		//CAMBIO:FUNCION QUE SETEA LA LISTA DE MONEDAS
		public void setListaMonedas(ArrayList<Moneda> listaMoneda){
			this.listaMonedas = listaMoneda;
		}

	}
	//fin de la clase PanelRuleta
	
	
	/**
	 * The Class Apuesta.
	 */
	//Clase Apuesta
	private class Apuesta {
		
		/** The posicion Y. */
		private int posicionX, posicionY; 
		
		/** The i D. */
		private int iD;
		
		/** The i D 1. */
		private int iD1;
		
		/** The i D 2. */
		private int iD2;
		
		/** The i D 3. */
		private int iD3;
		
		/** The i D 4. */
		private int iD4;
		
		/** The i D 5. */
		private int iD5;
		
		/** The i D 6. */
		private int iD6;
		
		/** The apuesta. */
		public ArrayList<Integer> apuesta= new ArrayList<Integer>();
		
		/** The tipo de apuesta. */
		public double tipoDeApuesta;
		
		/** The nombre apuesta. */
		private String nombreApuesta; 
		
		/**
		 * Instantiates a new apuesta.
		 *
		 * @param x the x
		 * @param y the y
		 */
		public Apuesta(int x, int y)
		{
			tipoDeApuesta=0;
			this.posicionX=x;
			this.posicionY=y;
		}
		
		
		
		/**
		 * Evaluar XY.
		 * Por medio de unas coordenadas X y Y dadas por la VistaProyecto se evalua por medio de rangos qué tipo de apuesta es
		 * y dependiendo de esto se le asigna un ID  (identificador) el cual permite utilizar dicha apuesta en otras funciones.
		 *
		 * @return un entero
		 */
		public int evaluarXY() {
			iD = 1;
			iD1 = 1;
			iD2 = 40;
			iD3 = 82;
			iD4 = 134;
			iD5 = 190;
			iD6 = 193;
			
		//reconoce los recuadros de los numeros y de apostar por toda la fila	
			for(int x = 634 + 8;x <= 1116 + 8; x = x + 40) {
				for(int y = 214 + 51;y >= 54 + 51; y = y - 80) {
					if((posicionX >= x && posicionX <= (x + 32))&&(posicionY >= y && posicionY <= (y + 72))) {
						iD = iD1;
					}
					else {
						iD1 = iD1 + 1;
					}
				}
			}
		//reconoce los recuadros de apostar por dos numeros que esten uno al lado del otro
			for(int x = 626 + 8;x <= 1146 +8; x = x + 40) {
				for(int y = 54+ 50;y <= 214 + 50; y = y + 80) {
					if((posicionX >= x && posicionX <= (x + 7))&&(posicionY >= y && posicionY <= (y + 70))) {
						iD = iD2;			
						}
					else {
						iD2 = iD2 + 1;
					}
				}
			}
		//reconoce los recuadros de apostar por dos numeros que esten uno arriba del otro
			for(int x = 634+ 8;x <= 1114+ 8; x = x + 40) {
				for(int y = 46 + 50;y <= 286 + 50; y = y + 80) {
					if((posicionX >= x && posicionX <= (x + 32))&&(posicionY >= y && posicionY <= (y + 7))) {
						iD = iD3; 			
						}
					else {
						iD3 = iD3 + 1;
					}
				}
			}
		//reconoce las esquinas entre recuadros
			for(int x = 626+ 8;x <= 1146+ 8; x = x + 40) {
				for(int y = 46 + 50;y <= 286 + 50; y = y + 80) {
					if((posicionX >= x && posicionX <= (x + 7))&&(posicionY >= y && posicionY <= (y + 7))) {
						iD = iD4;			
						}
					else {
						iD4 = iD4 + 1;
					}
				}
			}
		//reconoce los recuadros de 1 a 12 2 a 12 y 3 a 12
			for(int x = 630 + 8;x <= 950 + 8; x = x + 160) {
					if((posicionX >= x && posicionX <= (x + 160))&&(posicionY >= 292 + 50 && posicionY <= (292 + 50 + 75))) {
						iD = iD5;	
						}
					else {
						iD5 = iD5 + 1;
					}
			}
		//reconoce los recuadros de par impar, rojas, negras bla bla
			for(int x = 630 + 8;x <= 1030 + 8; x = x + 80) {
				if((posicionX >= x && posicionX <= (x + 80))&&(posicionY >= 371 + 50 && posicionY <= (371 + 50 + 79))) {
					iD = iD6;		
					}
				else {
					iD6 = iD6 + 1;
				}
			}
			//reconoce el 0
			if((posicionX >= 590 + 8 && posicionX <= (590 + 8 + 36))&&(posicionY >= 50 + 50 && posicionY <= (50 +50 + 240))) {
				iD = iD6;	
				}
			else {
				iD6 = iD6 + 1;
			}
			
			return iD;
				
		}
		
		/**
		 * Esta es la apuesta.
		 * se encarga de añadir las apuestas a un ArrayList, la funcion determina que numeros debe añadir al ArrayList por medio del ID dado.
		 *
		 * @param iD the i D
		 * @return the array list
		 */
		public ArrayList<Integer> estaEsLaApuesta(int iD) {
			
			ArrayList<Integer> apuesta= new ArrayList<Integer>();
				switch(iD)
				{
				
				case 37: 
				{
					for(int x = 1;x <=34;x = x + 3) {
						apuesta.add(x);
						nombreApuesta="Tercera Columna";
					}
					break;
				}
				
				case 38: 
				{
					for(int x = 2;x <=35;x = x + 3) {
						apuesta.add(x);
						nombreApuesta="Segunda Columna";
					}
					break;
				}
				
				case 39: 
				{
					for(int x = 3;x <=36;x = x + 3) {
						apuesta.add(x);
						nombreApuesta="Primera Columna";
					}
					break;
				}
				case 43: 
				{
					apuesta.add(3);
					apuesta.add(6);
					nombreApuesta="Caballo-3-6";
					break;
				}
				
				case 44: 
				{
					apuesta.add(2);
					apuesta.add(5);
					nombreApuesta="Caballo-2-5";
					break;
				}
				
				case 45: 
				{
					apuesta.add(1);
					apuesta.add(4);
					nombreApuesta="Caballo-1-4";
					break;
				}
				
				case 46: 
				{
					apuesta.add(6);
					apuesta.add(9);
					nombreApuesta="Caballo-3-6";
					break;
				}
				
				case 47: 
				{
					apuesta.add(5);
					apuesta.add(8);
					nombreApuesta="Caballo-5-8";
					break;
				}
				
				case 48: 
				{
					apuesta.add(4);
					apuesta.add(7);
					nombreApuesta="Caballo-4-7";
					break;
				}
				
				case 49: 
				{
					apuesta.add(9);
					apuesta.add(12);
					nombreApuesta="Caballo-9-12";
					break;
				}
				
				case 50: 
				{
					apuesta.add(8);
					apuesta.add(11);
					nombreApuesta="Caballo-8-11";
					break;
				}
				
				case 51: 
				{
					apuesta.add(7);
					apuesta.add(10);
					nombreApuesta="Caballo-7-10";
					break;
				}
				
				case 52: 
				{
					apuesta.add(12);
					apuesta.add(15);
					nombreApuesta="Caballo-12-15";
					break;
				}
				
				case 53: 
				{
					apuesta.add(11);
					apuesta.add(14);
					nombreApuesta="Caballo-11-14";
					break;
				}
				
				case 54: 
				{
					apuesta.add(10);
					apuesta.add(13);
					nombreApuesta="Caballo-10-13";
					break;
				}
				
				case 55: 
				{
					apuesta.add(15);
					apuesta.add(18);
					nombreApuesta="Caballo-15-18";
					break;
				}
				
				case 56: 
				{
					apuesta.add(14);
					apuesta.add(17);
					nombreApuesta="Caballo-14-17";
					break;
				}
				
				case 57: 
				{
					apuesta.add(13);
					apuesta.add(16);
					nombreApuesta="Caballo-13-16";
					break;
				}
				
				case 58: 
				{
					apuesta.add(18);
					apuesta.add(21);
					nombreApuesta="Caballo-18-21";
					break;
				}
				
				case 59: 
				{
					apuesta.add(17);
					apuesta.add(20);
					nombreApuesta="Caballo-17-20";
					break;
				}
				
				case 60: 
				{
					apuesta.add(16);
					apuesta.add(19);
					nombreApuesta="Caballo-16-19";
					break;
				}
				
				case 61: 
				{
					apuesta.add(21);
					apuesta.add(24);
					nombreApuesta="Caballo-21-24";
					break;
				}
				
				case 62: 
				{
					apuesta.add(20);
					apuesta.add(23);
					nombreApuesta="Caballo-20-23";
					break;
				}
				
				case 63: 
				{
					apuesta.add(19);
					apuesta.add(22);
					nombreApuesta="Caballo-19-22";
					break;
				}
				
				case 64: 
				{
					apuesta.add(24);
					apuesta.add(27);
					nombreApuesta="Caballo-24-27";
					break;
				}
				
				case 65: 
				{
					apuesta.add(23);
					apuesta.add(26);
					nombreApuesta="Caballo-23-26";
					break;
				}
				
				case 66: 
				{
					apuesta.add(22);
					apuesta.add(25);
					nombreApuesta="Caballo-22-25";
					break;
				}
				
				case 67: 
				{
					apuesta.add(27);
					apuesta.add(30);
					nombreApuesta="Caballo-27-30";
					break;
				}
				
				case 68: 
				{
					apuesta.add(26);
					apuesta.add(29);
					nombreApuesta="Caballo-26-29";
					break;
				}
				
				case 69: 
				{
					apuesta.add(25);
					apuesta.add(28);
					nombreApuesta="Caballo-25-28";
					break;
				}
				
				case 70: 
				{
					apuesta.add(30);
					apuesta.add(33);
					nombreApuesta="Caballo-30-33";
					break;
				}
				
				case 71: 
				{
					apuesta.add(29);
					apuesta.add(32);
					nombreApuesta="Caballo-29-32";
					break;
				}
				
				case 72: 
				{
					apuesta.add(28);
					apuesta.add(31);
					nombreApuesta="Caballo-28-31";
					break;
				}
				
				case 73: 
				{
					apuesta.add(33);
					apuesta.add(36);
					nombreApuesta="Caballo-33-36";
					break;
				}
				
				case 74: 
				{
					apuesta.add(32);
					apuesta.add(35);
					nombreApuesta="Caballo-32-35";
					break;
				}
				
				case 75: 
				{
					apuesta.add(31);
					apuesta.add(34);
					nombreApuesta="Caballo-31-34";
					break;
				}
				
				case 83: 
				{
					apuesta.add(3);
					apuesta.add(2);
					nombreApuesta="Caballo-3-2";
					break;
				}
				
				case 84: 
				{
					apuesta.add(2);
					apuesta.add(1);
					nombreApuesta="Caballo-2-1";
					break;
				}
				
				case 85: 
				{
					apuesta.add(2);
					apuesta.add(1);
					apuesta.add(3);
					nombreApuesta="Primera fila";
					break;
				}
				
				case 87: 
				{
					apuesta.add(6);
					apuesta.add(5);
					nombreApuesta="Caballo-6-5";
					break;
				}
				
				case 88: 
				{
					apuesta.add(5);
					apuesta.add(4);
					nombreApuesta="Caballo-5-4";
					break;
				}
				
				case 89: 
				{
					apuesta.add(4);
					apuesta.add(5);
					apuesta.add(6);
					nombreApuesta="Segunda Fila";
					break;
				}
				
				case 91: 
				{
					apuesta.add(9);
					apuesta.add(8);
					nombreApuesta="Caballo-9-8";
					break;
				}
				
				case 92: 
				{
					apuesta.add(8);
					apuesta.add(7);
					nombreApuesta="Caballo-8-7";
					break;
				}
				
				case 93: 
				{
					apuesta.add(7);
					apuesta.add(8);
					apuesta.add(9);
					nombreApuesta="Tercera Fila";
					break;
				}
				
				case 95: 
				{
					apuesta.add(12);
					apuesta.add(11);
					nombreApuesta="Caballo-12-11";
					break;
				}
				
				case 96: 
				{
					apuesta.add(11);
					apuesta.add(10);
					nombreApuesta="Caballo-11-10";
					break;
				}
				
				case 97: 
				{
					apuesta.add(10);
					apuesta.add(11);
					apuesta.add(12);
					nombreApuesta="Cuarta Fila";
					break;
				}
				
				case 99: 
				{
					apuesta.add(15);
					apuesta.add(14);
					nombreApuesta="Caballo-15-14";
					break;
				}
				
				case 100: 
				{
					apuesta.add(14);
					apuesta.add(13);
					nombreApuesta="Caballo-14-13";
					break;
				}
				
				case 101: 
				{
					apuesta.add(13);
					apuesta.add(14);
					apuesta.add(15);
					nombreApuesta="Quinta Fila";
					break;
				}
				
				case 103: 
				{
					apuesta.add(18);
					apuesta.add(17);
					nombreApuesta="Caballo-18-17";
					break;
				}
				
				case 104: 
				{
					apuesta.add(17);
					apuesta.add(16);
					nombreApuesta="Caballo-17-16";
					break;
				}
				
				case 105: 
				{
					apuesta.add(16);
					apuesta.add(17);
					apuesta.add(18);
					nombreApuesta="Sexta Fila";
					break;
				}
				
				case 107: 
				{
					apuesta.add(21);
					apuesta.add(20);
					nombreApuesta="Caballo-21-20";
					break;
				}
				
				case 108: 
				{
					apuesta.add(20);
					apuesta.add(19);
					nombreApuesta="Caballo-20-19";
					break;
				}
				
				case 109: 
				{
					apuesta.add(19);
					apuesta.add(20);
					apuesta.add(21);
					nombreApuesta="Septima Fila";
					break;
				}
				
				case 111: 
				{
					apuesta.add(24);
					apuesta.add(23);
					nombreApuesta="Caballo-24-23";
					break;
				}
				
				case 112: 
				{
					apuesta.add(23);
					apuesta.add(22);
					nombreApuesta="Caballo-23-22";
					break;
				}
				
				case 113: 
				{
					apuesta.add(22);
					apuesta.add(23);
					apuesta.add(24);
					nombreApuesta="Octava Fila";
					break;
				}
				
				case 115: 
				{
					apuesta.add(27);
					apuesta.add(26);
					nombreApuesta="Caballo-27-26";
					break;
				}
				
				case 116: 
				{
					apuesta.add(26);
					apuesta.add(25);
					nombreApuesta="Caballo-26-25";
					break;
				}
				
				case 117: 
				{
					apuesta.add(25);
					apuesta.add(26);
					apuesta.add(27);
					nombreApuesta="Novena Fila";
					break;
				}
				
				case 119: 
				{
					apuesta.add(30);
					apuesta.add(29);
					nombreApuesta="Caballo-30-29";
					break;
				}
				
				case 120: 
				{
					apuesta.add(29);
					apuesta.add(28);
					nombreApuesta="Caballo-29-28";
					break;
				}
				
				case 121: 
				{
					apuesta.add(28);
					apuesta.add(29);
					apuesta.add(30);
					nombreApuesta="Decima fila";
					break;
				}
				
				case 123: 
				{
					apuesta.add(33);
					apuesta.add(32);
					nombreApuesta="Caballo-33-32";
					break;
				}
				
				case 124: 
				{
					apuesta.add(32);
					apuesta.add(31);
					nombreApuesta="Caballo-32-31";
					break;
				}
				
				case 125: 
				{
					apuesta.add(31);
					apuesta.add(32);
					apuesta.add(33);
					nombreApuesta="Undecima Fila";
					break;
				}
				
				case 127: 
				{
					apuesta.add(36);
					apuesta.add(35);
					nombreApuesta="Caballo-36-35";
					break;
				}
				
				case 128: 
				{
					apuesta.add(35);
					apuesta.add(34);
					nombreApuesta="Caballo-35-34";
					break;
				}
				
				case 129: 
				{
					apuesta.add(34);
					apuesta.add(35);
					apuesta.add(36);
					nombreApuesta="Doceava Fila";
					break;
				}
				
				case 131: 
				{
					apuesta.add(2);
					apuesta.add(3);
					apuesta.add(5);
					apuesta.add(6);
					apuesta.add(8);
					apuesta.add(9);
					apuesta.add(11);
					apuesta.add(12);
					apuesta.add(14);
					apuesta.add(15);
					apuesta.add(17);
					apuesta.add(18);
					apuesta.add(20);
					apuesta.add(21);
					apuesta.add(23);
					apuesta.add(24);
					apuesta.add(26);
					apuesta.add(27);
					apuesta.add(29);
					apuesta.add(30);
					apuesta.add(32);
					apuesta.add(33);
					apuesta.add(35);
					apuesta.add(36);
					nombreApuesta="Dos Columnas-Superiores";
					break;
				}
				
				case 132: 
				{
					apuesta.add(1);
					apuesta.add(2);
					apuesta.add(4);
					apuesta.add(5);
					apuesta.add(7);
					apuesta.add(8);
					apuesta.add(10);
					apuesta.add(11);
					apuesta.add(13);
					apuesta.add(14);
					apuesta.add(16);
					apuesta.add(17);
					apuesta.add(19);
					apuesta.add(20);
					apuesta.add(22);
					apuesta.add(23);
					apuesta.add(25);
					apuesta.add(26);
					apuesta.add(28);
					apuesta.add(39);
					apuesta.add(31);
					apuesta.add(32);
					apuesta.add(34);
					apuesta.add(35);
					nombreApuesta="Dos Columnas-Inferiores";
					break;
				}
				
				case 135: 
				{
					apuesta.add(0);
					apuesta.add(3);
					apuesta.add(2);
					nombreApuesta="Transversal-0-2-3";
					break;
				}
				
				case 136: 
				{
					apuesta.add(0);
					apuesta.add(1);
					apuesta.add(2);
					nombreApuesta="Transversal-0-1-2";
					break;
				}
				
				case 139: 
				{
					apuesta.add(3);
					apuesta.add(6);
					apuesta.add(2);
					apuesta.add(5);
					nombreApuesta="Caballo-3-6";
					break;
				}
				
				case 140: 
				{
					apuesta.add(1);
					apuesta.add(4);
					apuesta.add(2);
					apuesta.add(5);
					nombreApuesta="Cuadro-1-4-2-5";
					break;
				}
				
				case 141: 
				{
					apuesta.add(1);
					apuesta.add(2);
					apuesta.add(3);
					apuesta.add(4);
					apuesta.add(5);
					apuesta.add(6);
					nombreApuesta="Primera Seisena";
					break;
				}
				
				case 143: 
				{
					apuesta.add(6);
					apuesta.add(9);
					apuesta.add(5);
					apuesta.add(8);
					nombreApuesta="Cuadro-6-9-5-8";
					break;
				}
				
				case 144: 
				{
					apuesta.add(7);
					apuesta.add(4);
					apuesta.add(8);
					apuesta.add(5);
					nombreApuesta="Cuadro-7-4-8-5";
					break;
				}
				
				case 145: 
				{
					apuesta.add(4);
					apuesta.add(5);
					apuesta.add(6);
					apuesta.add(7);
					apuesta.add(8);
					apuesta.add(9);
					nombreApuesta="Segunda Seisena";
					break;
				}
				
				case 147: 
				{
					apuesta.add(9);
					apuesta.add(11);
					apuesta.add(8);
					apuesta.add(12);
					nombreApuesta="Cuadro-9-11-8-12";
					break;
				}
				
				case 148: 
				{
					apuesta.add(10);
					apuesta.add(11);
					apuesta.add(8);
					apuesta.add(7);
					nombreApuesta="Cuadro-10-11-8-7";
					break;
				}
				
				case 149: 
				{
					apuesta.add(7);
					apuesta.add(8);
					apuesta.add(9);
					apuesta.add(10);
					apuesta.add(11);
					apuesta.add(12);
					nombreApuesta="Tercera Seisena";
					break;
				}
				case 151: 
				{
					apuesta.add(12);
					apuesta.add(15);
					apuesta.add(11);
					apuesta.add(14);
					nombreApuesta="Cuadro-12-15-11-14";
					break;
				}
				
				case 152: 
				{
					apuesta.add(10);
					apuesta.add(13);
					apuesta.add(11);
					apuesta.add(14);
					nombreApuesta="Cuadro-10-13-11-14";
					break;
				}
				
				case 153: 
				{
					apuesta.add(13);
					apuesta.add(14);
					apuesta.add(15);
					apuesta.add(10);
					apuesta.add(11);
					apuesta.add(12);
					nombreApuesta="Cuarta Seisena";
					break;
				}
				case 155: 
				{
					apuesta.add(15);
					apuesta.add(18);
					apuesta.add(14);
					apuesta.add(17);
					nombreApuesta="Cuadro-15-18-14-17";
					break;
				}
				
				case 156: 
				{
					apuesta.add(16);
					apuesta.add(13);
					apuesta.add(14);
					apuesta.add(17);
					nombreApuesta="Cuadro-16-13-14-17";
					break;
				}
				
				case 157: 
				{
					apuesta.add(13);
					apuesta.add(14);
					apuesta.add(15);
					apuesta.add(16);
					apuesta.add(17);
					apuesta.add(18);
					nombreApuesta="Quinta Seisena";
					break;
				}
				
				case 159: 
				{
					apuesta.add(18);
					apuesta.add(21);
					apuesta.add(17);
					apuesta.add(20);
					nombreApuesta="Cuadro-21-18-17-20";
					break;
				}
				
				case 160: 
				{
					apuesta.add(19);
					apuesta.add(16);
					apuesta.add(17);
					apuesta.add(20);
					nombreApuesta="Cuadro-19-16-17-20";
					break;
				}
				
				case 161: 
				{
					apuesta.add(19);
					apuesta.add(20);
					apuesta.add(21);
					apuesta.add(16);
					apuesta.add(17);
					apuesta.add(18);
					nombreApuesta="Sexta Seisena";
					break;
				}
				
				case 163: 
				{
					apuesta.add(21);
					apuesta.add(24);
					apuesta.add(20);
					apuesta.add(23);
					nombreApuesta="Cuadro-21-24-20-23";
					break;
				}
				
				case 164: 
				{
					apuesta.add(19);
					apuesta.add(22);
					apuesta.add(20);
					apuesta.add(23);
					nombreApuesta="Cuadro-19-22-20-23";
					break;
				}
				
				case 165: 
				{
					apuesta.add(19);
					apuesta.add(20);
					apuesta.add(21);
					apuesta.add(22);
					apuesta.add(23);
					apuesta.add(24);
					nombreApuesta="Septima Seisena";
					break;
				}
				
				case 167: 
				{
					apuesta.add(24);
					apuesta.add(27);
					apuesta.add(23);
					apuesta.add(26);
					nombreApuesta="Cuadro-24-27-23-26";
					break;
				}
				
				case 168: 
				{
					apuesta.add(25);
					apuesta.add(22);
					apuesta.add(23);
					apuesta.add(26);
					nombreApuesta="Cuadro-25-22-23-26";
					break;
				}
				
				case 169: 
				{
					apuesta.add(25);
					apuesta.add(26);
					apuesta.add(27);
					apuesta.add(22);
					apuesta.add(23);
					apuesta.add(24);
					nombreApuesta="Octava Seisena";
					break;
				}
				
				case 171: 
				{
					apuesta.add(27);
					apuesta.add(30);
					apuesta.add(26);
					apuesta.add(29);
					nombreApuesta="Cuadro-27-30-26-29";
					break;
				}
				
				case 172: 
				{
					apuesta.add(25);
					apuesta.add(28);
					apuesta.add(26);
					apuesta.add(29);
					nombreApuesta="Cuadro-25-28-26-29";
					break;
				}
				
				case 173: 
				{
					apuesta.add(25);
					apuesta.add(26);
					apuesta.add(27);
					apuesta.add(28);
					apuesta.add(29);
					apuesta.add(30);
					nombreApuesta="Novena Seisena";
					break;
				}
				
				case 175: 
				{
					apuesta.add(30);
					apuesta.add(33);
					apuesta.add(29);
					apuesta.add(32);
					nombreApuesta="Cuadro-30-33-29-32";
					break;
				}
				
				case 176: 
				{
					apuesta.add(28);
					apuesta.add(31);
					apuesta.add(29);
					apuesta.add(32);
					nombreApuesta="Cuadro-28-31-29-32";
					break;
				}
				
				case 177: 
				{
					apuesta.add(31);
					apuesta.add(32);
					apuesta.add(33);
					apuesta.add(28);
					apuesta.add(29);
					apuesta.add(30);
					nombreApuesta="Decima Seisena";
					break;
				}
				
				case 179: 
				{
					apuesta.add(33);
					apuesta.add(36);
					apuesta.add(32);
					apuesta.add(35);
					nombreApuesta="Cuadro-33-36-32-35";
					break;
				}
				
				case 180: 
				{
					apuesta.add(31);
					apuesta.add(34);
					apuesta.add(32);
					apuesta.add(35);
					nombreApuesta="Cuadro-31-34-32-35";
					break;
				}
				
				case 181: 
				{
					apuesta.add(31);
					apuesta.add(32);
					apuesta.add(33);
					apuesta.add(34);
					apuesta.add(35);
					apuesta.add(36);
					nombreApuesta="Ultima Seisena";
					break;
				}
				
				
				case 190: 
				{
					apuesta.add(1);
					apuesta.add(2);
					apuesta.add(3);
					apuesta.add(4);
					apuesta.add(5);
					apuesta.add(6);
					apuesta.add(7);
					apuesta.add(8);
					apuesta.add(9);
					apuesta.add(10);
					apuesta.add(11);
					apuesta.add(12);
					nombreApuesta="Primera Docena";
					break;
				}
				
				case 191: 
				{
					apuesta.add(13);
					apuesta.add(14);
					apuesta.add(15);
					apuesta.add(16);
					apuesta.add(17);
					apuesta.add(18);
					apuesta.add(19);
					apuesta.add(20);
					apuesta.add(21);
					apuesta.add(22);
					apuesta.add(23);
					apuesta.add(24);
					nombreApuesta="Segunda Docena";
					break;
				}
				
				case 192: 
				{
					apuesta.add(25);
					apuesta.add(26);
					apuesta.add(27);
					apuesta.add(28);
					apuesta.add(29);
					apuesta.add(30);
					apuesta.add(31);
					apuesta.add(32);
					apuesta.add(33);
					apuesta.add(34);
					apuesta.add(35);
					apuesta.add(36);
					nombreApuesta="Tercera Docena";
					break;
				}
				
				case 193: 
				{
					apuesta.add(1);
					apuesta.add(2);
					apuesta.add(3);
					apuesta.add(4);
					apuesta.add(5);
					apuesta.add(6);
					apuesta.add(7);
					apuesta.add(8);
					apuesta.add(9);
					apuesta.add(10);
					apuesta.add(11);
					apuesta.add(12);
					apuesta.add(13);
					apuesta.add(14);
					apuesta.add(15);
					apuesta.add(16);
					apuesta.add(17);
					apuesta.add(18);
					nombreApuesta="1 a 18";
					break;
				}
				
				case 194: 
				{
					apuesta.add(2);
					apuesta.add(4);
					apuesta.add(6);
					apuesta.add(8);
					apuesta.add(10);
					apuesta.add(12);
					apuesta.add(14);
					apuesta.add(16);
					apuesta.add(18);
					apuesta.add(20);
					apuesta.add(22);
					apuesta.add(24);
					apuesta.add(26);
					apuesta.add(28);
					apuesta.add(30);
					apuesta.add(32);
					apuesta.add(34);
					apuesta.add(36);
					nombreApuesta="Pares";
					break;
				}
				
				case 195: 
				{
					apuesta.add(2);
					apuesta.add(4);
					apuesta.add(6);
					apuesta.add(8);
					apuesta.add(10);
					apuesta.add(11);
					apuesta.add(13);
					apuesta.add(15);
					apuesta.add(17);
					apuesta.add(20);
					apuesta.add(22);
					apuesta.add(24);
					apuesta.add(26);
					apuesta.add(28);
					apuesta.add(29);
					apuesta.add(31);
					apuesta.add(33);
					apuesta.add(35);
					nombreApuesta="Negros";
					break;
				}
				
				case 196: 
				{
					apuesta.add(1);
					apuesta.add(3);
					apuesta.add(5);
					apuesta.add(7);
					apuesta.add(9);
					apuesta.add(12);
					apuesta.add(14);
					apuesta.add(16);
					apuesta.add(36);
					apuesta.add(18);
					apuesta.add(19);
					apuesta.add(21);
					apuesta.add(23);
					apuesta.add(25);
					apuesta.add(27);
					apuesta.add(30);
					apuesta.add(32);
					apuesta.add(34);
					nombreApuesta="Rojos";
					break;
				}
				
				case 197: 
				{
					apuesta.add(1);
					apuesta.add(3);
					apuesta.add(5);
					apuesta.add(7);
					apuesta.add(9);
					apuesta.add(11);
					apuesta.add(13);
					apuesta.add(15);
					apuesta.add(17);
					apuesta.add(19);
					apuesta.add(21);
					apuesta.add(23);
					apuesta.add(25);
					apuesta.add(27);
					apuesta.add(29);
					apuesta.add(31);
					apuesta.add(33);
					apuesta.add(35);
					nombreApuesta="Impares";
					break;
				}
				
				case 198: 
				{
					apuesta.add(19);
					apuesta.add(20);
					apuesta.add(21);
					apuesta.add(22);
					apuesta.add(23);
					apuesta.add(24);
					apuesta.add(25);
					apuesta.add(26);
					apuesta.add(27);
					apuesta.add(28);
					apuesta.add(29);
					apuesta.add(30);
					apuesta.add(31);
					apuesta.add(32);
					apuesta.add(33);
					apuesta.add(34);
					apuesta.add(35);
					apuesta.add(36);
					nombreApuesta="19 a 36";
					break;
				}
				
				case 199: 
				{
					apuesta.add(0);
					nombreApuesta="0";
					break;
				}
				
				default:
				{
					apuesta.add(iD);
					nombreApuesta=Double.toString(iD);
				}
				
				}
				return apuesta;
				}
		
		/**
		 * Identificar apuesta.
		 * Se encarga de identificar por cual numero se debe multiplicar el resultado si se gana, esto lo hace determinando el tipo de apuesta
		 * por medio del ID dado.
		 *
		 * @param ID the id
		 * @return the double
		 */
		//------------------------------------------------------------
		public double identificarApuesta(int ID) {
			if(ID==180 || ID==179|| ID==176 || ID==175|| ID==177|| ID==171|| ID==168|| ID==167|| ID==164 || ID==163||
			   ID==160 || ID==159|| ID==156|| ID==157|| ID==155|| ID==151|| ID==148|| ID==147|| ID==144|| ID==143||ID==140) {
				tipoDeApuesta = 9;
				//cuadro
			}
			else if((ID>=43 && ID <= 75) || ID==83|| ID==84 || ID==87|| ID==88|| ID==91|| ID==168|| ID==92|| ID==95 || ID==96||
					   ID==99 || ID==100|| ID==103|| ID==104|| ID==107|| ID==108|| ID==111|| ID==112|| ID==115|| ID==116||ID==119||
					   ID==120 || ID==123|| ID==124|| ID==127|| ID==128 || ID==139) {
				tipoDeApuesta = 18;
				//caballo
			}
			else if(ID==85|| ID==89 || ID==93|| ID==97|| ID==101|| ID==105|| ID==109|| ID==113 || ID==117||
					   ID==121 || ID==125|| ID==129 || ID==135|| ID==136) {
				tipoDeApuesta = 12;
				//transversal
			}
			else if (ID==141|| ID==149 || ID==145|| ID==153|| ID==157|| ID==161|| ID==165|| ID==169 || ID==173||
			   ID==177 || ID==181) {
				tipoDeApuesta = 6;
				//seisena
			}
			else if(ID==38|| ID==39 || ID==37) {
				tipoDeApuesta = 3;
				//columna
			}
			else if (ID==132|| ID==131) {
				tipoDeApuesta = 1.5;
				//dos columnas
			}
			else if(ID>=190 && ID<= 192) {
				tipoDeApuesta = 3;
				//docena
			}
			else if(ID==195 || ID== 196) {
				tipoDeApuesta = 2;
				//rojo negro
			}
			else if(ID==197 || ID== 194) {
				tipoDeApuesta = 2;
				//per impar
			}
			else if(ID==198 || ID== 193 ) {
				tipoDeApuesta = 2;
				//pasa falta
			}
			else if (ID==1||ID==2||ID==3||ID==4||ID==5||ID==6||ID==7||ID==8||ID==9||ID==10||ID==11||ID==12||ID==13||
					ID==14||ID==15||ID==16||ID==17||ID==18||ID==19||ID==20||ID==21||ID==22||ID==23||ID==24||ID==25||
					ID==26||ID==27||ID==28||ID==29||ID==30||ID==31||ID==32||ID==33||ID==34||ID==35||ID==36||ID==199) {
				tipoDeApuesta = 36;
				//PLENO
			}
			return tipoDeApuesta;
		}
		
	}

	//Fin de clase Apuesta
	
	
	
	//Clase Logica
	
	/**
	 * The Class Logica.
	 */
	private class Logica {
		
		/** The dinero actual. */
		private double dineroActual ; 
		
		/** The dinero apostado. */
		private int dineroApostado ;
		
		/** The tipo de apuesta. */
		private int tipoDeApuesta;
		
		
		
		/**
		 * Instantiates a new logica.
		 */
		public Logica()
		{
			dineroActual = 2000; // El dinero siempre debe se mayor a 10 porque es la cantidad minima que se puede comprar como usuario
			dineroApostado = 0;
			tipoDeApuesta = 0;
		}
		
		
		
		/**
		 * Dinero que se tiene.
		 * Evalua y determina el dinero que tiene el usuario(dinero no apostado).
		 *
		 * @param dineroApostadoRonda the dinero apostado ronda
		 * @return the double
		 */
		public double dineroQueSeTiene(int dineroApostadoRonda) 
		{
			dineroActual = dineroActual - dineroApostadoRonda;
			return dineroActual;
		}
		
		/**
		 * Dinero que se aposto.
		 * Evalua y determina el dinero apostado.(dinero apostado total).
		 *
		 * @param x the x
		 * @return the int
		 */
		public int dineroQueSeAposto(int x) 
		{
			if(dineroApostado == 0) {
				dineroApostado = x;
			}
			else {
				dineroApostado = dineroApostado+ x  ;
			}
			return dineroApostado;
		}

		
			
		/**
		 * Comparar.
		 * Se encarga de evaluar el tiro y compararlo en el Array de Arrays. 
		 * primero evalua el primer Array y mira si algun termino de este es igual al valor dado por la ruleta.
		 * si no es asi se pasa a la siguiente.
		 * Hay un Array por el tipo de apuesta, otro por el valor apostado, y otro por el ID (la apuesta en si).
		 * Al evaluar cual es la apuesta ganadora si se gana se multiplica el valor apostado por el el valor de la apuesta
		 * dependiendo de su tipo. 
		 *
		 * @param tiro the tiro
		 * @param iD the i D
		 * @param tipoDeApuesta the tipo de apuesta
		 * @param apostadoPorRonda the apostado por ronda
		 * @return true, if successful
		 */
		public boolean comparar(int tiro, ArrayList<ArrayList> iD, ArrayList<Double> tipoDeApuesta, ArrayList<Integer> apostadoPorRonda) 
		{
			boolean ganar = false;
			for(int i = 0; i <= iD.size() -1; i++)
			{
				ArrayList<Integer> listaApuestas= iD.get(i);
				double cantidadMultiplicar = tipoDeApuesta.get(i);
				int apuestaRonda=apostadoPorRonda.get(i);
				for(int j=0; j<= listaApuestas.size() -1; j++)
				{
					int numeroApostado=listaApuestas.get(j);
					if(numeroApostado == tiro) 
					{
						ganar = true;
						dineroActual = dineroActual + (apuestaRonda * cantidadMultiplicar);
					}
					else
						dineroActual = dineroActual;
				}
			}
			dineroApostado = 0;
			return ganar;
		}
		
		/**
		 * Gets the dinero.
		 * Se obtiene el dinero Actual
		 * @return the dinero
		 */
		public double getDinero()
		{
			return dineroActual;
		}
		
		/**
		 * Gets the apostado.
		 * Se obtiene el dinero apostado
		 * @return the apostado
		 */
		public int getApostado()
		{
			return dineroApostado;
		}

	}
	//Fin de clase logica
	
	//FIN DE CLASES PRIVADAS
}

//Fin de clase cliente

	
	


