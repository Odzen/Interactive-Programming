/**
 * Autor: Juan Sebastian Velasquez Acevedo(1744936)
 * Correo: juan.velasquez.acevedo@correounivalle.edu.co
 * Versión: 1.0
 * Modificación: 2018-08-27
 * Creación: 2018-08-22
 * Colaboracion:ControlCraps.java
 * Resposabilidad: Permite al usuario lanzar los dados, terminar el juego e imprime datos en consola. 
 */

package Game;

import java.util.Scanner;
import Game.ControlCraps;


public class Vista 
{
	private ControlCraps control;
	private Scanner entradaPeriferica; 
	private String opcionJugador;
	private String seguirJuego;
	private String salirDelJuego; 
	
	public Vista()
	{
	  entradaPeriferica=new Scanner (System.in); 
	  seguirJuego="S";
	  salirDelJuego="N";
	  opcionJugador="";
	  
	}
	
	public void inicializar()
	{
	  System.out.println("¿Nuevo Juego? ["+ seguirJuego+ " o " + salirDelJuego +"]");
	  opcionJugador= entradaPeriferica.nextLine();
	  while(opcionJugador.equals(seguirJuego))
	  {
	  control= new ControlCraps();
	  control.calcularTiro();
	  control.determinar();
	  System.out.println("Lanzamiento=" + control.getTiro());
      if(control.getEstado()==0)
	    System.out.println("Craps, perdiste");
	  else if(control.getEstado()==1)
	    System.out.println("Natural, ganaste");
		  else
		  {
		    System.out.println("Punto=" +control.getPunto() + "\n" + "¿Continuar?");
		    opcionJugador= entradaPeriferica.nextLine();
		    if(opcionJugador.equals(seguirJuego ))
		    {
		      while(control.getEstado()==2)
		      {
			    control.calcularTiro();
			    control.determinar();
				System.out.println("El nuevo tiro es=" + control.getTiro());
				System.out.println("punto="+ control.getPunto());
				System.out.println("Tiro="+ control.getTiro());
				if(control.getEstado()==1 && control.getbandera()==1)
				{
			      System.out.println("Ganaste");
			      break;
				}
			    else if(control.getEstado()==0 && control.getbandera()==1)
			    {
				  System.out.println("Perdiste");
				  break; 
			    }              
			   } 
		     }
		   }	
		   System.out.println("¿Nuevo Juego? ["+ seguirJuego+ " o " + salirDelJuego +"]");
		   opcionJugador= entradaPeriferica.nextLine();
		}
	}
}

