/*
 * Introducción Swing
 * Manejo de Eventos Básico
 * Profesora Paola J. Rodríguez
 */

package craps;

import javax.swing.JFrame;

// TODO: Auto-generated Javadoc
/**
 * The Class Principal. Encarga de ejecutar el programa
 * invoca a la vista
 */
public class Principal {

	/**
	 * The main method.
	 *
	 * @param args the arguments. No se usan en este programa
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*VistaConsola consola = new VistaConsola();
		consola.iniciarJuego();*/
		
		GUICraps myGUI = new GUICraps();
		myGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

}
