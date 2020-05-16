/* Esta es la GUI del busca minas.<br><br>
 * <b>Responsabilidad: </b>Es la encargada de accionar toda la parte grafica del juego. Permite al usuario seleccionar
 * la casilla, ademas de determinar la vicotira o derrota, revisar todos lo casos posibles
 * (si hay mina, si  hay minas vecinas), y reiniciar el juego.<br><br>
 * <b>Colaboración: 
 * </b>{@link BuscaMinas.Celda}
 * </b>{@link Componentes.timer}
 * </b>{@link Componentes.LectoEscritura}
 * </b>{@link Componentes.EtiquetaTitulo}
 * @author Cesar Alberto Becerra Ramirez (1744338)<br>
 * cesar.becerra@correounivalle.edu.co
 * @author Juan Sebastian velasquez (1744936)<br>
 * juan.velasquez.acevedo@correounivalle.edu.co
 * @since 2019-03-10
 * @version 1.0<br>
 * 2019-03-13
 */
package buscaMinas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import buscaMinas.Celda;
import componentes.Cronometro;
import componentes.EtiquetaTitulo;
import componentes.LectoEscritura;

// TODO: Auto-generated Javadoc

/**
 * The Class GUIMinas.
 */
public class GUIMinas extends JFrame {

	/** The Constant GRIDSIZE. */
	// Atributos
	public static final int GRIDSIZE = 10;
	
	/** The Constant TOTALMINAS. */
	public static final int TOTALMINAS = 10;
	
	/** The cronometro. */
	private Cronometro cronometro;

	/** The etiqueta titulo. */
	private EtiquetaTitulo etiquetaTitulo,jugador1;

	/** The panel titulo. */
	private JPanel panelTitulo;

	/** The panel central. */
	private JPanel panelCentral;
	
	/** The panel lateral. */
	private JPanel panelLateral;

	/** The celdas. */
	private Celda[][] celdas;

	/** The gui minas container. */
	private Container guiMinasContainer;

	/** The escucha accion. */
	private EscuchaAccion escuchaAccion;
	
	/** The ejecutor. */
	private ExecutorService ejecutor;
	
	/** The tiempo total. */
	private int tiempoTotal;
	
	/** The nombre. */
	private String nombre;
	
	/** The fecha. */
	private GregorianCalendar fecha;
	
	/** The reiniciar. */
	private JButton reiniciar; 
	
	/** The imagen bandera. */
	private ImageIcon imagenFeliz, imagenTriste, imagenBandera;
	
	/** The lecto escritura. */
	private LectoEscritura lectoEscritura;
	
	/** The casilla sin minas. */
	private int casillaSinMinas;
	
	/** The lista. */
	private List<String> listaHall, lista;

	// Mï¿½todos

	/**
	 * Instantiates a new GUI minas. Constructor de la GUI, el cual permite
	 * establecer la estructura base de la clase, teniendo en cuenta que el
	 * redimesionamiento se acopla al tamaï¿½o de los componentes contenidos en la
	 * ventana.
	 */
	public GUIMinas() {

		nombre="";
		nombre = JOptionPane.showInputDialog(null, "Ingrese su nombre", ""); 
		if ((nombre != null) && (nombre.length() > 0)) { 
			initGUI();
			setMinas();
		}
		else 
			System.exit(0);
			setTitle("Busca Minas");
			setSize(700, 600);
			setResizable(false);
			setVisible(true);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Inits the GUI. Metodo que inicializa los componenes grï¿½ficos necesarios para
	 * la creaciï¿½n de la vista
	 */
	private void initGUI() {
		

		tiempoTotal=0;
		guiMinasContainer = getContentPane();
		guiMinasContainer.setLayout(new BorderLayout());
		lectoEscritura=new LectoEscritura();
		lista = new ArrayList<String>();
		casillaSinMinas=GRIDSIZE*GRIDSIZE-TOTALMINAS;
        fecha = new GregorianCalendar();
		escuchaAccion = new EscuchaAccion();
		reiniciar=new JButton();
    	imagenFeliz= new ImageIcon("src/img/cara_feliz.png");
    	imagenTriste= new ImageIcon("src/img/cara_feliz.png");
    	imagenBandera = new ImageIcon("src/img/bandera.png");
		reiniciar.setIcon(imagenFeliz);
		reiniciar.setBounds(240,0,30,30);
		reiniciar.addActionListener(escuchaAccion);
		add(reiniciar);
		/*
		Dimension tamano= new Dimension(20,20);	
		reiniciar.setPreferredSize(tamano);
		*/
		panelTitulo = new JPanel(new BorderLayout());
		panelTitulo.setBackground(Color.BLACK);
		etiquetaTitulo = new EtiquetaTitulo("Busca Minas");
		cronometro=new Cronometro();
		panelTitulo.add(etiquetaTitulo, BorderLayout.WEST);
		panelTitulo.add(cronometro, BorderLayout.EAST);
		ejecutor= Executors.newFixedThreadPool(1);
		guiMinasContainer.add(panelTitulo, BorderLayout.PAGE_START);
		panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
		celdas = new Celda[GRIDSIZE][GRIDSIZE];
		panelLateral = new JPanel();
		panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
		panelLateral.setBackground(Color.black);

		for (int i = 0; i < GRIDSIZE; i++) {
			for (int j = 0; j < GRIDSIZE; j++) {
				celdas[i][j] = new Celda(i, j);
				celdas[i][j].addMouseListener((MouseListener) escuchaAccion);
				panelCentral.add(celdas[i][j]);
			}
		}

		guiMinasContainer.add(panelCentral, BorderLayout.CENTER);
		guiMinasContainer.add(panelLateral, BorderLayout.EAST);
		resetPanelLateral(panelLateral);
		
		
	}

	/**
	 * The Class EscuchaAccion.
	 */
	private class EscuchaAccion extends MouseAdapter implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		public void mouseClicked(MouseEvent e) {
			ejecutor.execute(cronometro);
			Celda celdaSeleccionada = (Celda) e.getSource();
			int BotonDelMouse = (int) e.getButton();
			Color isBandera = celdaSeleccionada.getBackground();
			
			
			boolean estaDestapado = celdaSeleccionada.isDestapado();
			if(BotonDelMouse == 1)
			{
				int fila = celdaSeleccionada.getFila();
				int col = celdaSeleccionada.getCol();	
			    checkCelda(fila, col);
			    ganaste(fila, col);
			}
			else if(BotonDelMouse == 3 && estaDestapado == false )
			{
				if(isBandera != Color.pink)
				{
				int fila = celdaSeleccionada.getFila();
				int col = celdaSeleccionada.getCol();
				celdas[fila][col].setBackground(Color.pink);
				celdas[fila][col].setIcon(imagenBandera);
				celdas[fila][col].bloquear(true);
				
				
				}
				else
				{
					int fila = celdaSeleccionada.getFila();
					int col = celdaSeleccionada.getCol();
					celdas[fila][col].setBackground(null);
					celdas[fila][col].setIcon(null);
					celdas[fila][col].bloquear(false);
				}
			}
			
		}
		
		public void actionPerformed(ActionEvent evento) {
			// TODO Auto-generated method stub
			
			JButton boton=(JButton) evento.getSource();
			
			if(boton==reiniciar)
			{
				otraRonda();
				
			}
			
		}
	}

	
	private void resetPanelLateral(JPanel panel)
	{
		lista = lectoEscritura.getTexto();
		
		if(!lista.isEmpty())
		{
			for(int i =0;i<lista.size();i++) {
					String nombrePuntaje = lista.get(i);
					String[] parts = nombrePuntaje.split("\t");
					String tiempo = parts[1];
					String nombre = parts[0];
					String lugar = Integer.toString(i+1);
					
					jugador1 = new EtiquetaTitulo (lugar+". "+nombre+": "+tiempo+" seg");
					panel.add(jugador1);	
			}
		}
	}
	
	/**
	 * Ganaste.
	 *
	 * @param fila the fila
	 * @param col the col
	 */
	private void ganaste(int fila, int col) {
		if (!celdas[fila][col].isMina() && casillaSinMinas == 0 ) {
			celdas[fila][col].destapar(true);
			tiempoTotal=cronometro.getTiempo();
			reiniciar.setIcon(imagenTriste);
			cronometro.setEstado();
			listaHall = lectoEscritura.hallDeLaFama(nombre, tiempoTotal);
			String mensaje = "Ganaste, muy buen trabajo";
			mostrarMensaje(mensaje);
		}
	}

	/**
	 * Check celda.
	 *
	 * @param fila the fila
	 * @param col the col
	 */
	private void checkCelda(int fila, int col) {
		if (celdas[fila][col].isMina() && !celdas[fila][col].getBloquear()) {
			celdas[fila][col].destapar(true);
			tiempoTotal=cronometro.getTiempo();
			reiniciar.setIcon(imagenTriste);
			cronometro.setEstado();
			String mensaje = "Explotaste una mina, deseas jugar otra ronda";
			mostrarMensaje(mensaje);
		} else {
			check(fila, col);
		}
	}

	/**
	 * Check.
	 *
	 * @param fila the fila
	 * @param col the col
	 */
	private void check(int fila, int col) {
		if (fila >= 0 && fila <= GRIDSIZE - 1 && col >= 0 && col <= GRIDSIZE - 1 && !celdas[fila][col].isMina()
				&& !celdas[fila][col].isDestapado()&& !celdas[fila][col].getBloquear()) {
			casillaSinMinas--;
			celdas[fila][col].destapar(true);
			if (!celdas[fila][col].hayMinasCercanas()) {
				checkVecinos(fila, col);
			}
		}
	}

	/**
	 * Check vecinos.
	 *
	 * @param fila the fila
	 * @param col the col
	 */
	private void checkVecinos(int fila, int col) {
		check(fila - 1, col);
		check(fila + 1, col);
		check(fila, col - 1);
		check(fila, col + 1);
		check(fila - 1, col + 1);
		check(fila - 1, col - 1);
		check(fila + 1, col - 1);
		check(fila + 1, col + 1);
	}

	/**
	 * Mostrar mensaje.
	 *
	 * @param mensaje the mensaje
	 */
	private void mostrarMensaje(String mensaje) {
		int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Jugar otra vez?", JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {
			otraRonda();

		} else {
			System.exit(0);
		}
	}

	/**
	 * Otra ronda.
	 */
	private void otraRonda() {
		for (int i = 0; i < GRIDSIZE; i++) {
			for (int j = 0; j < GRIDSIZE; j++) {
				celdas[i][j].reset();
				cronometro.reset();
				ejecutor.execute(cronometro);
				tiempoTotal = 0;
				celdas[i][j].setIcon(null);
				casillaSinMinas = GRIDSIZE*GRIDSIZE-TOTALMINAS;
				panelLateral.removeAll();
				resetPanelLateral(panelLateral);

				
			}
		}
		setMinas();
		
	}

	/**
	 * Sets the minas.
	 */
	private void setMinas() {
		Random random = new Random();
		int i = 1;
		do {

			int randomFila = random.nextInt(GRIDSIZE);
			int randomCol = random.nextInt(GRIDSIZE);

			if (!celdas[randomFila][randomCol].isMina()) {
				celdas[randomFila][randomCol].setMina(true);
				// celdas[randomFila][randomCol].destapar(true);
				aumentarMinas(randomFila, randomCol);
				i++;
			}
		} while (i <= TOTALMINAS);
	}

	/**
	 * Aumentar minas.
	 *
	 * @param fila the fila
	 * @param col the col
	 */
	private void aumentarMinas(int fila, int col) {
		aumentarContadorMinas(fila - 1, col);
		aumentarContadorMinas(fila + 1, col);
		aumentarContadorMinas(fila, col - 1);
		aumentarContadorMinas(fila, col + 1);
		aumentarContadorMinas(fila - 1, col + 1);
		aumentarContadorMinas(fila - 1, col - 1);
		aumentarContadorMinas(fila + 1, col - 1);
		aumentarContadorMinas(fila + 1, col + 1);
	}

	/**
	 * Aumentar contador minas.
	 *
	 * @param fila the fila
	 * @param col the col
	 */
	private void aumentarContadorMinas(int fila, int col) {
		if (fila >= 0 && fila <= GRIDSIZE - 1 && col >= 0 && col <= GRIDSIZE - 1) {
			celdas[fila][col].increseMinaCount();
			// celdas[fila][col].destapar(true);
		}
	}
}// fin GUIMinas class
