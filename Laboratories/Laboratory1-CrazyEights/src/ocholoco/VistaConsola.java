/**
 * @Autor: Juan Sebastian Velasquez Acevedo(1744936) - Jose David Ortiz Correa (1740634)
 * Correo: juan.velasquez.acevedo@correounivalle.edu.co - jose.david.ortiz@correounivalle.edu.co
 * @Version: 1.0
 * Modificacion: 2018-09-09
 * Creacion: 2018-08-30
 * Colaboracion:Control.java - Carta.java
 * Responsabilidad:Permite al usuario lanzar la carta, e imprime los mensajes en consola. 
 */
package ocholoco;

import java.util.Arrays;
import java.util.Scanner;
import ocholoco.Carta;

import ocholoco.ControlOchoLoco;

public class VistaConsola 
{
	/** Entrada teclado. */
	private Scanner entradaTeclado;
	
	/** Control. */
	private ControlOchoLoco control;
	
	/**
	 * Constructor:
	 * Instancia new vista consola.
	 */
	public VistaConsola()
	{
		entradaTeclado = new Scanner(System.in);
	}
	
	/**
	 * Empezar juego: Este metodo inicia el juego, en el se llaman metodos del ControlOchoLoco y dependiendo de
	 * su logica, asi mismo se imprime. Aqui interactua el usuario con el la logica del juego .  
	 */
	public void empezarJuego()
	{
		System.out.println("Quieres jugar? [Y/N]");
		String opcion = entradaTeclado.nextLine();
		
		while(opcion.equalsIgnoreCase("Y"))
		{
			control = new ControlOchoLoco();
			control.puedePonerCarta(control.getCartasSobrantes().get(0).toString());
			
		    while (!control.getManoJugador().isEmpty() && !control.getManoComputadora().isEmpty() && !control.getCartasSobrantes().isEmpty()) 
		    {
				System.out.println("Quedan del mazo: " + control.getCartasSobrantes().size() + " cartas sobrantes");
				System.out.println("Carta Actual: " + control.getCartaActual().toString());
				//TURNO COMPUTADORA 
				if(control.getTurno() == 1)
				{
					control.puedePonerCarta("");
					System.out.println("La computadora puso: " + control.getCartaActual().toString());
				}
				//TURNO JUGADOR 
				else 
				{
					System.out.println("Cartas Jugador: " + mostrarManoJugador());
					System.out.println("Que carta desea poner?");
					String cartaPoner = entradaTeclado.nextLine();
					
					if(cartaPoner.equalsIgnoreCase("comer"))
					{
						control.comerCarta();
						Carta ultimaCarta = control.getManoJugador().get(control.getManoJugador().size() -1);
						System.out.println("Carta Adquirida: " + ultimaCarta.toString());
						if(decisionSobreCartaComida())
						{
							if (ultimaCarta.getValor().equals("8")) 
								seDioUnOcho(ultimaCarta.toString());
							else
                                control.puedePonerCarta(ultimaCarta.toString());
						}
						control.setTurno(1);
					}
					else if(!cartaPoner.substring(0, 1).equals("8"))
						control.puedePonerCarta(cartaPoner);
					else
						seDioUnOcho(cartaPoner);
				}
		    }
	
			if (control.getCartasSobrantes().isEmpty())
				System.out.println("Se acabaron las cartas!");
			
	        if(control.determinarFinalJuego())
	        	System.out.println("Gana el jugador!");
	        else 
	        	System.out.println("La computadora te acaba de vencer :v!");
		   
			System.out.println("Quiere volver a jugar? [Y/N]");
			opcion = entradaTeclado.nextLine();
		}
	}
	
	/**
	 * Mostrar mano jugador.
	 *
	 * @return the string
	 */
	public String mostrarManoJugador()
	{
		return Arrays.toString(control.getManoJugador().toArray());
	}
	
	/**
	 * Mostrar mano computadora.
	 *
	 * @return the string
	 */
	public String mostrarManoComputadora()
	{
		return Arrays.toString(control.getManoComputadora().toArray());
	}

	
	/**
	 * seDioUnOcho: Imprime y realiza los comandos necesarios para mostrarle y preguntarle al usuario
	 * principalmente a que palo quiere cambiar la carta si se da una carta con valor 8. 
	 *
	 * @param cartaPoner the carta poner
	 */
	public void seDioUnOcho(String cartaPoner)
	{
	    System.out.println("Tu carta es un 8, A que palo la quiere cambiar? [C,D,T,P]");
        String paloACambiar = entradaTeclado.nextLine();
        control.cambiarPalo(cartaPoner, paloACambiar);
	    System.out.println("Cambiaste Palo a : "+ paloACambiar);
	}

	/**
	 * decisionSobreCartaComida: Determina la decision que tome el usuario cuando come 
	 * una carta, dependiendo de su decision as� retorna un booleano.  
	 *
	 * @return true, if successful
	 */
	public boolean decisionSobreCartaComida()
	{
		System.out.println("Has comido. Quieres poner la Carta? [Y/N]");
		String decision = entradaTeclado.nextLine();
		if(decision.equalsIgnoreCase("Y"))
			return true;
		else if (decision.equalsIgnoreCase("N"))
		    return false;
		else
		{
			System.out.println("Comando Invalido");
            decisionSobreCartaComida();
		}
		return false;
	}

}
