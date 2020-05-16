/*
 * Introducción Swing
 * Manejo de Eventos Básico
 * Profesora Paola J. Rodríguez
 */
package craps;

// TODO: Auto-generated Javadoc
/**
 * The Class ControlCraps. 
 * Clase encargada de manejar la lógica del juego craps.
 *Incluye: Determinar fase del juego, tiro, punto, estado del juego.
 */
public class ControlCraps {
	
	/** Representan el par de dados usados para jugar  */
	private Dado dado1, dado2;
	
	/** tiro es valor de cada lanzamiento.
	 *  punto el valor del punto en caso de establecerlo
	 *  estado estado de juego, esto es ganar, perder, punto
	 *  flag bandera usada para determinar la fase del juego (inicio o punto) */
	private int tiro, punto, estado, flag;
	
	/** The caras. vector usado para poder acceder a la imagen en la vista gráfica*/
	private int[] caras;
	
	/**
	 * Instantiates a new control craps.
	 * Crea los objetos que usa la clase y las variables por defecto.
	 */
	public ControlCraps(){
		dado1 = new Dado();
		dado2 = new Dado();
		caras = new int[2];
		flag=0;
	}
	
	/**
	 * Determina el valor de la cara visible del dado y calcula el valor del tiro.
	 */
	public void calcularTiro(){	
		caras[0] = dado1.getCara();
		caras[1] = dado2.getCara();
		tiro = caras[0] +  caras[1];
	}
	
	
	/**
	 * Determina si el jugador gana, pierde o establece punto
	 * Si gana hace estado = 1
	 * Si pierde hace estado=2
	 * Si hace punto hace estado =3 y flag = 1.
	 */
	
	public void determinarJuego(){
		
	 if(flag==0){
		//Es primer lanzamiento, es decir, fase inicio
		if (tiro==7 || tiro == 11){
			estado=1; //ganó
		}
		else
			if(tiro==2 || tiro==3 || tiro==12){
				estado=2; //perdió
			}
			else{
				estado=3; //Estableció punto, pasa a fase punto
				punto=tiro;
				flag = 1; 
			}
      }
	 else{ 
	    //Ha establecido punto
		rondaPunto(); 
	 }
	  
	}
	
	//Determina si el jugador gana o pierde cuando ha establecido punto
	
	/**
	 * Ronda punto. Este valida las condiciones de la fase punto:
	 * Si el tiro es igual al punto el estado es ganar
	 * Si el tiro es igual a 7 el estado es perder y flag se reinicia
	 */
	private void rondaPunto(){
		if(tiro==punto){
			estado=1; //gana
			flag=0;
		}
		if(tiro==7){
			estado=2; //pierde
			flag=0;
		}
	}
	
	/**
	 * Gets the tiro.
	 *
	 * @return the tiro
	 */
	public int getTiro(){
		return tiro;
	}
	
	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public int getEstado(){
		return estado;
	}
	
	/**
	 * Gets the punto.
	 *
	 * @return the punto
	 */
	public int getPunto(){
		return punto;
	}
	
	/**
	 * Gets the cara dado 1.
	 *
	 * @return the cara dado 1
	 */
	public int getCaraDado1(){
		return caras[0];
	}
	
	/**
	 * Gets the cara dado 2.
	 *
	 * @return the cara dado 2
	 */
	public int getCaraDado2(){
		return caras[1];
	}
}
