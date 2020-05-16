/**
 * Autor: Juan Sebastian Velasquez Acevedo(1744936)
 * Correo: juan.velasquez.acevedo@correounivalle.edu.co
 * Versión: 1.0
 * Modificación: 2018-08-26
 * Creación: 2018-08-22
 * Responsabilidad: Devolver valor de la cara visible. 
 */
package Game;
import java.util.Random;

public class Dado 
{
	private int cara;
	
	public int getCara()
	{
		Random aleatorio=new Random(); 
		cara= aleatorio.nextInt(6)+1; 
		return cara; 
	} 
	
}
