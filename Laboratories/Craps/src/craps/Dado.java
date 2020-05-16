/*
 * Programaci�n Interactiva 2018-II
 * Paola J. Rodr�guez C. C�digo 941145
 * Proyecto Craps
 */
package craps;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Dado.
 * Esta clase representa un dada y guarda informaci�n de la cara visible del mismo
 */
public class Dado {
    
    /** The cara. Cara visible del dado al ser lanzado */
    private int cara;
    
	/**
	 * Gets the cara. Devuelve el valor de la cara visible el cual se general aleatoriamente
	 *
	 * @return the cara. el valor aleatorio entre 1 y 6
	 */
	public int getCara() {
		Random aleatorio= new Random();
	    cara = aleatorio.nextInt(6)+1;
		return cara;
	}
 
}
