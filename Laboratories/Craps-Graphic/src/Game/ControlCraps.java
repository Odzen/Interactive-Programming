/**
 * Autor: Juan Sebastian Velasquez Acevedo(1744936)
 * Correo: juan.velasquez.acevedo@correounivalle.edu.co
 * Versión: 1.0
 * Modificación: 2018-08-27
 * Creación: 2018-08-22
 * Colaboracion:Dado.java
 * Responsabilidad:Calcula el valor del lanzamiento, determina el estado de juego. Además tiene métodos en los que
 * devuelve el valor del tiro, estado, bandera y punto. 
 * tiro, 
 */

package Game;
import Game.Dado;


public class ControlCraps 
{
	private Dado dado1;
	private Dado dado2;
	private int estado; 
	private int punto; 
	private int tiro; 
	private int bandera;  
	
	public ControlCraps()
	{
		dado1=new Dado();
		dado2=new Dado();
        estado=0;
        bandera=0;
	}
	
	public void calcularTiro ()
	{
		int valor1=dado1.getCara();
		int valor2=dado2.getCara();
		tiro= valor1+valor2;	
	}
	
	public void determinar()
	{
		if (bandera==0)
		{
		tiro = getTiro();
		  if (tiro==2 || tiro==3 || tiro==12)
		    estado = 0; //PERDIO
		    else if (tiro==7|| tiro==11)
			  estado = 1; //GANO
		      else{
			    estado = 2;//PUNTO
			    punto=tiro; 
			    bandera=1;
		    }
		}
		else 
	      rondaPunto();

	} 
	
	public void rondaPunto()
	{
	  if(punto==tiro)
	    estado=1;
	  else if (tiro==7)
	    estado = 0;
	}
	
	public int getPunto()
	{
	  return punto;
	}
	
	public int getTiro()
	{
	  return tiro;
	}
	
	public int getEstado()
	{
	  return estado; 
	}
	
	public int getbandera()
	{
	  return bandera;
	}
	

	
	

}
