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
package servidorRuleta;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

// TODO: Auto-generated Javadoc
/**
 * The Class ServidorRuleta.
 */
public class ServidorRuleta extends JFrame{
	
	/** The area salida. */
	private JTextArea areaSalida; 
	
	/** The jugador actual. */
	private int jugadorActual; 
	
	/** The jugadores. */
	private Jugador[] jugadores; 
	
	/** The servidor. */
	private ServerSocket servidor; 
	
	/** The ejecutar juego. */
	private ExecutorService ejecutarJuego; 
	
	/** The bloqueo juego. */
	private Lock bloqueoJuego; 
	
	/** The otro jugador conectado. */
	private Condition otroJugadorConectado; 
	
	/** The turno otro jugador. */
	private Condition turnoOtroJugador;
	
	/** The Constant JUGADORES. */
	private static final String[] JUGADORES = { "Jugador-1", "Jugador-2"}; 
	
	/** The Constant JUGADOR2. */
	private static final int JUGADOR2 = 1; 
	
	/** The Constant JUGADOR1. */
	private static final int JUGADOR1 = 0; 

	/** The jugador movio ruleta. */
	private boolean jugadorPuedeEscuchar=false;
	
	
	/** The ruleta. */
	private Ruleta ruleta;
	
	int aleatorio=0;
	int aleatorioAnterior=0;
	
	
	public ServidorRuleta() {
		
		super( "Servidor Juego Ruleta" ); 
		ejecutarJuego = Executors.newFixedThreadPool(2); 
		bloqueoJuego = new ReentrantLock(); 
		otroJugadorConectado = bloqueoJuego.newCondition(); 
		turnoOtroJugador = bloqueoJuego.newCondition(); 
		jugadores = new Jugador[2]; 		
		jugadorActual = JUGADOR1; // establece el primer jugador como el jugador actual
		jugadorPuedeEscuchar=false;
		try{
			servidor = new ServerSocket( 60000, 2 ); 
		} 
		catch ( IOException excepcionES ){
			excepcionES.printStackTrace();
			System.exit(1); 
		} 
		
		ruleta=new Ruleta();
		areaSalida = new JTextArea(); 
		areaSalida.setEditable(false);
		add( areaSalida, BorderLayout.CENTER );
		areaSalida.setText( "Servidor esperando conexiones\n" );
		setSize( 300, 300 ); 
		setVisible( true );
	}
	
	/**
	 * Execute.
	 */
	public void execute() {

		for ( int i = 0; i < jugadores.length; i++ ) {
			try {
				jugadores[i] = new Jugador(servidor.accept(), i); 
				ejecutarJuego.execute(jugadores[i]); 
			} 
			catch ( IOException excepcionES ) {
				excepcionES.printStackTrace();
				System.exit(1);
			} 
		} 
		bloqueoJuego.lock(); // bloquea el juego para avisar al subproceso del jugador 1 que inicie
	
		try {
			jugadores[JUGADOR1].establecerSuspendido(false);
			otroJugadorConectado.signal(); 
		} 
		finally{
			bloqueoJuego.unlock(); 
		} 
	} 
	
	/**
	 * Mostrar mensaje.
	 *
	 * @param mensajeAMostrar the mensaje A mostrar
	 */
	private void mostrarMensaje( final String mensajeAMostrar ){
		SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				areaSalida.append( mensajeAMostrar ); // agrega el mensaje
			} 
		} 
		); 
	} 
	
	/**
	 * Validar boton.
	 *
	 * @param jugador the jugador
	 * @param botonJugar the boton jugar
	 * @return true, if successful
	 */
	public boolean validarBoton( int jugador,boolean botonJugar) {
		while ( jugador != jugadorActual ){
			bloqueoJuego.lock(); 
			try{
				turnoOtroJugador.await(); 
			} 
			catch ( InterruptedException excepcion ){
				excepcion.printStackTrace();
			} 
			finally{
				bloqueoJuego.unlock(); 
			} 
		} 
		
		//AQUI TOCA EVALUAR BIEN, PORQUE AL OPRIMIR EL BOTON NO GESTIONA BIEN LOS TURNOS
		
		//AQUI CAMBIE
		if(botonJugar && jugadorActual==JUGADOR2)
		{
			aleatorio=ruleta.girarRuleta();
			aleatorioAnterior=aleatorio;
			jugadorActual = ( jugadorActual + 1 ) % 2;
			jugadores[ jugadorActual ].otroJugadorMovioEnBoton(botonJugar);
			bloqueoJuego.lock();
			try {
				turnoOtroJugador.signal(); // Despierta el hilo del jugador expectante
			} 
			finally {
				bloqueoJuego.unlock(); 
			} 
		
			return true;
		}
		
		else if(botonJugar && jugadorActual==JUGADOR1)
		{
			aleatorio=aleatorioAnterior;
			jugadorActual = ( jugadorActual + 1 ) % 2;
			jugadores[ jugadorActual ].otroJugadorMovioEnBoton(botonJugar);
			bloqueoJuego.lock();
			try {
				turnoOtroJugador.signal(); // Despierta el hilo del jugador expectante
			} 
			finally {
				bloqueoJuego.unlock(); 
			} 
		
			return true;
		}
		
		
		
		
		
		else 
		{
			  return false; 
		}
		
	}
	
	/**
	 * Validar tablero.
	 *
	 * @param x the x
	 * @param y the y
	 * @param iD the i D
	 * @param jugador the jugador
	 * @param botonPressed the boton pressed
	 * @return true, if successful
	 */
	public boolean validarTablero(int x, int y , int iD, int jugador, int botonPressed)
	{
		
		while ( jugador != jugadorActual ){
			bloqueoJuego.lock(); 
			try{
				turnoOtroJugador.await(); 
			} 
			catch ( InterruptedException excepcion ){
				excepcion.printStackTrace();
			} 
			finally{
				bloqueoJuego.unlock(); 
			} 
		}
		
		

		
		mostrarMensaje("X que recibe servidor: "+x+ "\n");
		mostrarMensaje("Y que recibe servidor: "+y+ "\n");
		mostrarMensaje( estaEnRango(x,y)  + " Rango-Valida " + apuestaValida( iD )+ "\n" );
		
		if(estaEnRango(x,y) && apuestaValida( iD ))
		{
			int numeroJugador=( jugadorActual + 1 ) % 2;
			jugadores[ numeroJugador ].otroJugadorMovioEnTablero( x,y,botonPressed); // si la apuesta es valida del otro jugador, entonces se mandan las coordenadas 
			return true;
		}
		else
			return false;
	}
	
	
	
	/**
	 * Apuesta valida.
	 *
	 * @param iD the i D
	 * @return true, if successful
	 */
	public boolean apuestaValida (int iD)
	{
		boolean valido=true;;
		if(iD==40 || iD==41 || iD==42 ||iD==76 ||iD==77 ||iD==78 ||iD==79 ||iD==80 ||iD==81 ||iD==82 ||
				iD==86 ||iD==90 ||iD==94 ||iD==98 ||iD==102 ||iD==106 ||iD==110 ||iD==114 ||iD==118 ||iD==122 ||
				iD==126 ||iD==130 ||iD==133 ||iD==134 ||iD==137 ||iD==138 ||iD==142 ||iD==146 ||iD==150 ||iD==154 ||
				iD==158 ||iD==162 ||iD==166 ||iD==170 ||iD==174 ||iD==178 ||iD==182 ||iD==183 ||iD==184 ||iD==185 ||
				iD==186 ||iD==187 ||iD==188 ||iD==189 )
			valido=false;
		else
			valido=true;
		return valido;
		
	}
	
	/**
	 * Esta en rango.
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, if successful
	 */
	public boolean estaEnRango (int x, int y)
	{
		boolean sePuede=true;;
		if(((x >= 550 + 8 && x <= 1165 + 8)&&(y >= 40 + 30 && y <= 296 + 30))||
				((x >= 630 + 8 && x <= 1112 + 8)&&(y >= 296 + 30 && y <= 451 + 30)))
			sePuede=true;
		else
			sePuede=false;
		return sePuede;
		
	}
	
	

	
	/**
	 * Se termino juego.
	 *
	 * @return true, if successful
	 */
	public boolean seTerminoJuego(){
		// como ejercicio Completar el  metodo para determinar si termina el juego
		return false; 
	} 
	
	//clases privadas

	/**
	 * The Class Jugador.
	 */
	private class Jugador implements Runnable {
		
		/** The conexion. */
		//atributos
		private Socket conexion; 
		
		/** The entrada. */
		private Scanner entrada; 
		
		/** The salida. */
		private Formatter salida; 
		
		/** The numero jugador. */
		private int numeroJugador;
		
		/** The jugador. */
		private String jugador; 
		
		/** The suspendido. */
		private boolean suspendido = true; 

		/** The boton pressed. */
		private int botonPressed=0;
	
		/**
		 * Instantiates a new jugador.
		 *
		 * @param socket the socket
		 * @param numero the numero
		 */
		//metodos
		public Jugador( Socket socket, int numero ){
			numeroJugador = numero; 
			jugador = JUGADORES[ numeroJugador ]; 
			conexion = socket; 
			
			try {
				entrada = new Scanner( conexion.getInputStream() );
				salida = new Formatter( conexion.getOutputStream() );
				salida.flush();
			} 
			catch ( IOException excepcionES ){
				excepcionES.printStackTrace();
				System.exit( 1 );
			} 
		} 
			
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		public void run(){
			try{
				mostrarMensaje( jugador + " conectado\n" );
				salida.format( "%s\n", jugador ); 
				salida.flush(); 
		        
				if ( numeroJugador == JUGADOR1 ){
					salida.format( "%s\n%s", "Jugador 1 conectado","Esperando al otro jugador\n" );
					salida.flush(); 
					bloqueoJuego.lock(); 

					try{
						while( suspendido ){
							otroJugadorConectado.await(); // espera al jugador O
						} 
					} 
					catch ( InterruptedException excepcion ){
						excepcion.printStackTrace();
					} 
					finally {
						bloqueoJuego.unlock(); 
					} 
					
					
					salida.format( "El otro jugador se conecto. Ahora es su turno.\n" );
					salida.flush(); 
				} 
				else {
					salida.format( "El jugador 2 se conecto, por favor espere\n" );
					salida.flush(); 
				} 
		
				while ( !seTerminoJuego() ) {
					int iD=0;
					int x=0;
					int y=0;
					boolean botonJugar=false;
					boolean botonMoneda10 = false;
					boolean botonMoneda50=false;
					boolean botonMoneda100=false;
					boolean botonMoneda500=false;
					mostrarMensaje( "Entero: "+entrada.hasNextInt() + "\n" );
					mostrarMensaje( "Booleano: "+entrada.hasNextBoolean() + "\n" );
					if ( entrada.hasNextInt() ){
						x = entrada.nextInt();
						y = entrada.nextInt();
						iD = entrada.nextInt();
						botonPressed=entrada.nextInt();
						mostrarMensaje( "RecibidoX: "+x + "\n" );
						mostrarMensaje( "RecibidoY: "+y + "\n" );
						mostrarMensaje( "RecibidoiD: "+iD + "\n" );
						mostrarMensaje( "RecibidoBttonPressed: "+botonPressed + "\n" );
						mostrarMensaje( "ValidoTablero: "+validarTablero(x, y , iD, numeroJugador,botonPressed) + "\n" );
						if(validarTablero(x, y , iD, numeroJugador,botonPressed))
						{
							salida.format( "Movimiento Tablero\n" ); 
							salida.flush();
						}
					}
					
					if (entrada.hasNextBoolean())
					{
						botonJugar=entrada.nextBoolean();
						botonMoneda10=entrada.nextBoolean();
						botonMoneda50=entrada.nextBoolean();
						botonMoneda100=entrada.nextBoolean();
						botonMoneda500=entrada.nextBoolean();
					}
					

					
					
					mostrarMensaje( "ValidoBoton: "+validarBoton(numeroJugador, botonJugar) + "\n" );
					
					if(validarBoton(numeroJugador, botonJugar))
					{
						jugadorBoton (aleatorio);
					}
					
					else if(botonMoneda10 || botonMoneda50 || botonMoneda100 || botonMoneda500 || botonJugar)
					{
						if(botonMoneda10)
						{
							int botonPressed=1;
							mostrarMensaje( "RecibidoBttonPressed2: "+botonPressed + "\n" );
							jugadorMovio10(botonPressed);
						}
						if(botonMoneda50)
						{
							int botonPressed=2;
							jugadorMovio50(botonPressed);
						}
						if(botonMoneda100)
						{
							int botonPressed=3;
							jugadorMovio100(botonPressed);
						}
						if(botonMoneda500)
						{ 
							int botonPressed=4;
							jugadorMovio500(botonPressed);
						}
						
					}
					
					else{
						salida.format( "Movimiento invalido, intente de nuevo" + "\n" );
						salida.flush(); 
					}
		
					
				} 
			} // fin de try
			finally {
				try {
					conexion.close();
				} catch ( IOException excepcionES ){
					excepcionES.printStackTrace();
					System.exit( 1 );
				}
			}
		}
	
	/**
	 * Establecer suspendido.
	 *
	 * @param estado the estado
	 */
	public void establecerSuspendido( boolean estado ){
		suspendido = estado; 
	} 

	/**
	 * Otro jugador movio en tablero.
	 *
	 * @param x the x
	 * @param y the y
	 * @param botonPresionado the boton presionado
	 */
	public void otroJugadorMovioEnTablero( int x ,int y, int botonPresionado){
		salida.format( "El oponente hizo movimiento" + "\n" );
		salida.format( "%d\n", x );
		salida.format( "%d\n", y );
		salida.format( "%d\n", botonPresionado );
		salida.flush();
	} 
	
	/**
	 * Otro jugador movio en boton.
	 *
	 * @param botonJugar the boton jugar
	 */
	public void otroJugadorMovioEnBoton(boolean botonJugar){
		salida.format( "El oponente hizo movimiento" + "\n" );
		salida.format( "%b\n", botonJugar );
		salida.flush();
	} 

	/**
	 * Jugador movio 10.
	 *
	 * @param botonPress the boton press
	 */
	public void jugadorMovio10(int botonPress){

		salida.format( "Moneda10\n");
		salida.flush();
		salida.format( "%d\n", botonPress ); 
		salida.flush();
	} 
	

	
	/**
	 * Jugador movio 50.
	 *
	 * @param botonPress the boton press
	 */
	public void jugadorMovio50(int botonPress){

		salida.format( "Moneda50\n");
		salida.flush();
		salida.format( "%d\n", botonPress ); 
		salida.flush();
	} 
	

	/**
	 * Jugador movio 100.
	 *
	 * @param botonPress the boton press
	 */
	public void jugadorMovio100(int botonPress){

		salida.format( "Moneda100\n");
		salida.flush();
		salida.format( "%d\n", botonPress ); 
		salida.flush();
	} 
	

	/**
	 * Jugador movio 500.
	 *
	 * @param botonPress the boton press
	 */
	public void jugadorMovio500(int botonPress){

		salida.format( "Moneda500\n");
		salida.flush();
		salida.format( "%d\n", botonPress ); 
		salida.flush();
	} 
	
	public void jugadorBoton (int aleatorio){

		salida.format( "Se oprime boton Jugar\n");
		salida.format( "%d\n", aleatorio ); 
		salida.flush();
	} 

  } // fin de la clase Jugador
	
	//clase ruleta
	private class Ruleta {
		
		/** El resultado es el valor que genera la ruleta */
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
				
}//Fin clase Servidor


