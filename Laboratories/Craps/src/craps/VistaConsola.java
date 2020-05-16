/*
 * Introducción Swing
 * Manejo de Eventos Básico
 * Profesora Paola J. Rodríguez
 */
package craps;

import java.util.Scanner;

import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * The Class VistaConsola.
 */
public class VistaConsola {
	
	/** The pregunta. */
	private String pregunta;
	
	/** The craps. */
	private ControlCraps craps;
	
	/** The entrada escaner. */
	private Scanner entradaEscaner;
	
	/**
	 * Instantiates a new vista consola.
	 */
	public VistaConsola(){
		craps = new ControlCraps();
		entradaEscaner = new Scanner (System.in);
	}
	
	/**
	 * Iniciar juego.
	 */
	public void iniciarJuego(){
		System.out.println ("Desea lanzar los dados? y/n:");
		pregunta=entradaEscaner.nextLine();
		
		if(pregunta.equals("y")){
			craps.calcularTiro();
			System.out.printf("Dado 1 = %d Dado 2 = %d Tiro de Salida = %d \n",craps.getCaraDado1(),craps.getCaraDado2(),craps.getTiro());
			craps.determinarJuego();
			
			switch (craps.getEstado()){
			
			case 1: System.out.println("Natural has Ganado!!");
			        break;
			        
			case 2: System.out.println("Craps has perdido :(");
			        break;
			        
			case 3: System.out.printf("Estableciste Punto = %d , debes lazanzar nuevamente!! \n",craps.getPunto());
					while(craps.getEstado()==3){
						System.out.println("Deseas lanzar? y/n");
						pregunta=entradaEscaner.nextLine();
						if(pregunta.equals("y")){
							craps.calcularTiro();
							System.out.printf("Dado 1 = %d Dado 2 = %d Tiro = %d \n",craps.getCaraDado1(),craps.getCaraDado2(),craps.getTiro());
							craps.determinarJuego();
						}
						else{
							System.out.println("Perdiste, por abandonar el juego :(");
							break;
						}
					}
					if(craps.getEstado()==1){
						System.out.println("Lograste nuevamente el punto, Ganaste :)");
					}
					else{
						System.out.println("Ooops, Perydiste :(");
					}
					break;
			}
			seguirJuego();
		}
		else{
			System.out.println("Ok, Bye :y(");
		}
	}

	/**
	 * Seguir juego.
	 */
	private void seguirJuego(){
		System.out.println("Quieres Volver a Jugar? y/n");
		pregunta=entradaEscaner.nextLine();
		if(pregunta.equals("y")){
			iniciarJuego();
		}
		else{
			System.out.println("Bye!!");
		}
	}
}
