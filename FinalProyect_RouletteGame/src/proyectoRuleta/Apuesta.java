/* Esta es la clase Apuesta, se encarga de evaluar el tablero, las coordenadas, y las respectivas apuestas. 
 * Responsabilidad: Esta clase es la encargada de determinar que tipo de apuesta se hace teniendo en cuenta las 
 * coordenadas X y Y, ademas de determinar que numeros se deben agregar con cada apuesta, y por cuanto se debe multiplicar
 * el resultado si se gana.
 * Colaboración: ninguna.
 * @author Cesar Alberto Becerra Ramirez (1744338)
 * cesar.becerra@correounivalle.edu.co
 * @author Juan Sebastian velasquez (1744936)
 * juan.velasquez.acevedo@correounivalle.edu.co
 * @since 2019-01-02
 * @version 1.0
 * 2019-02-27
 */
package proyectoRuleta;
import java.util.ArrayList;


// TODO: Auto-generated Javadoc
/**
 * Esta es la calse apuesta.
 */
public class Apuesta {
	
	/** The posicion Y. */
	private int posicionX, posicionY; 
	
	/** The i D. */
	private int iD;
	
	/** The i D 1. */
	private int iD1;
	
	/** The i D 2. */
	private int iD2;
	
	/** The i D 3. */
	private int iD3;
	
	/** The i D 4. */
	private int iD4;
	
	/** The i D 5. */
	private int iD5;
	
	/** The i D 6. */
	private int iD6;
	
	/** The apuesta. */
	public ArrayList<Integer> apuesta= new ArrayList<Integer>();
	
	/** The tipo de apuesta. */
	public double tipoDeApuesta;
	
	/** The nombre apuesta. */
	private String nombreApuesta; 
	
	/**
	 * Instantiates a new apuesta.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Apuesta(int x, int y)
	{
		tipoDeApuesta=0;
		this.posicionX=x;
		this.posicionY=y;
	}
	
	
	
	/**
	 * Evaluar XY.
	 * Por medio de unas coordenadas X y Y dadas por la VistaProyecto se evalua por medio de rangos qué tipo de apuesta es
	 * y dependiendo de esto se le asigna un ID  (identificador) el cual permite utilizar dicha apuesta en otras funciones.
	 *
	 * @return un entero
	 */
	public int evaluarXY() {
		iD = 1;
		iD1 = 1;
		iD2 = 40;
		iD3 = 82;
		iD4 = 134;
		iD5 = 190;
		iD6 = 193;
		
	//reconoce los recuadros de los numeros y de apostar por toda la fila	
		for(int x = 634 + 8;x <= 1116 + 8; x = x + 40) {
			for(int y = 214 + 31;y >= 54 + 31; y = y - 80) {
				if((posicionX >= x && posicionX <= (x + 32))&&(posicionY >= y && posicionY <= (y + 72))) {
					iD = iD1;
				}
				else {
					iD1 = iD1 + 1;
				}
			}
		}
	//reconoce los recuadros de apostar por dos numeros que esten uno al lado del otro
		for(int x = 626 + 8;x <= 1146 +8; x = x + 40) {
			for(int y = 54+ 30;y <= 214 + 30; y = y + 80) {
				if((posicionX >= x && posicionX <= (x + 7))&&(posicionY >= y && posicionY <= (y + 70))) {
					iD = iD2;			
					}
				else {
					iD2 = iD2 + 1;
				}
			}
		}
	//reconoce los recuadros de apostar por dos numeros que esten uno arriba del otro
		for(int x = 634+ 8;x <= 1114+ 8; x = x + 40) {
			for(int y = 46 + 30;y <= 286 + 30; y = y + 80) {
				if((posicionX >= x && posicionX <= (x + 32))&&(posicionY >= y && posicionY <= (y + 7))) {
					iD = iD3; 			
					}
				else {
					iD3 = iD3 + 1;
				}
			}
		}
	//reconoce las esquinas entre recuadros
		for(int x = 626+ 8;x <= 1146+ 8; x = x + 40) {
			for(int y = 46 + 30;y <= 286 + 30; y = y + 80) {
				if((posicionX >= x && posicionX <= (x + 7))&&(posicionY >= y && posicionY <= (y + 7))) {
					iD = iD4;			
					}
				else {
					iD4 = iD4 + 1;
				}
			}
		}
	//reconoce los recuadros de 1 a 12 2 a 12 y 3 a 12
		for(int x = 630 + 8;x <= 950 + 8; x = x + 160) {
				if((posicionX >= x && posicionX <= (x + 160))&&(posicionY >= 292 + 30 && posicionY <= (292 + 30 + 75))) {
					iD = iD5;	
					}
				else {
					iD5 = iD5 + 1;
				}
		}
	//reconoce los recuadros de par impar, rojas, negras bla bla
		for(int x = 630 + 8;x <= 1030 + 8; x = x + 80) {
			if((posicionX >= x && posicionX <= (x + 80))&&(posicionY >= 371 + 30 && posicionY <= (371 + 30 + 79))) {
				iD = iD6;		
				}
			else {
				iD6 = iD6 + 1;
			}
		}
		//reconoce el 0
		if((posicionX >= 590 + 8 && posicionX <= (590 + 8 + 36))&&(posicionY >= 50 + 30 && posicionY <= (50 +30 + 240))) {
			iD = iD6;	
			}
		else {
			iD6 = iD6 + 1;
		}
		
		return iD;
			
	}
	
	/**
	 * Esta es la apuesta.
	 * se encarga de añadir las apuestas a un ArrayList, la funcion determina que numeros debe añadir al ArrayList por medio del ID dado.
	 *
	 * @param iD the i D
	 * @return the array list
	 */
	public ArrayList<Integer> estaEsLaApuesta(int iD) {
		
		ArrayList<Integer> apuesta= new ArrayList<Integer>();
			switch(iD)
			{
			
			case 37: 
			{
				for(int x = 1;x <=34;x = x + 3) {
					apuesta.add(x);
					nombreApuesta="Tercera Columna";
				}
				break;
			}
			
			case 38: 
			{
				for(int x = 2;x <=35;x = x + 3) {
					apuesta.add(x);
					nombreApuesta="Segunda Columna";
				}
				break;
			}
			
			case 39: 
			{
				for(int x = 3;x <=36;x = x + 3) {
					apuesta.add(x);
					nombreApuesta="Primera Columna";
				}
				break;
			}
			case 43: 
			{
				apuesta.add(3);
				apuesta.add(6);
				nombreApuesta="Caballo-3-6";
				break;
			}
			
			case 44: 
			{
				apuesta.add(2);
				apuesta.add(5);
				nombreApuesta="Caballo-2-5";
				break;
			}
			
			case 45: 
			{
				apuesta.add(1);
				apuesta.add(4);
				nombreApuesta="Caballo-1-4";
				break;
			}
			
			case 46: 
			{
				apuesta.add(6);
				apuesta.add(9);
				nombreApuesta="Caballo-3-6";
				break;
			}
			
			case 47: 
			{
				apuesta.add(5);
				apuesta.add(8);
				nombreApuesta="Caballo-5-8";
				break;
			}
			
			case 48: 
			{
				apuesta.add(4);
				apuesta.add(7);
				nombreApuesta="Caballo-4-7";
				break;
			}
			
			case 49: 
			{
				apuesta.add(9);
				apuesta.add(12);
				nombreApuesta="Caballo-9-12";
				break;
			}
			
			case 50: 
			{
				apuesta.add(8);
				apuesta.add(11);
				nombreApuesta="Caballo-8-11";
				break;
			}
			
			case 51: 
			{
				apuesta.add(7);
				apuesta.add(10);
				nombreApuesta="Caballo-7-10";
				break;
			}
			
			case 52: 
			{
				apuesta.add(12);
				apuesta.add(15);
				nombreApuesta="Caballo-12-15";
				break;
			}
			
			case 53: 
			{
				apuesta.add(11);
				apuesta.add(14);
				nombreApuesta="Caballo-11-14";
				break;
			}
			
			case 54: 
			{
				apuesta.add(10);
				apuesta.add(13);
				nombreApuesta="Caballo-10-13";
				break;
			}
			
			case 55: 
			{
				apuesta.add(15);
				apuesta.add(18);
				nombreApuesta="Caballo-15-18";
				break;
			}
			
			case 56: 
			{
				apuesta.add(14);
				apuesta.add(17);
				nombreApuesta="Caballo-14-17";
				break;
			}
			
			case 57: 
			{
				apuesta.add(13);
				apuesta.add(16);
				nombreApuesta="Caballo-13-16";
				break;
			}
			
			case 58: 
			{
				apuesta.add(18);
				apuesta.add(21);
				nombreApuesta="Caballo-18-21";
				break;
			}
			
			case 59: 
			{
				apuesta.add(17);
				apuesta.add(20);
				nombreApuesta="Caballo-17-20";
				break;
			}
			
			case 60: 
			{
				apuesta.add(16);
				apuesta.add(19);
				nombreApuesta="Caballo-16-19";
				break;
			}
			
			case 61: 
			{
				apuesta.add(21);
				apuesta.add(24);
				nombreApuesta="Caballo-21-24";
				break;
			}
			
			case 62: 
			{
				apuesta.add(20);
				apuesta.add(23);
				nombreApuesta="Caballo-20-23";
				break;
			}
			
			case 63: 
			{
				apuesta.add(19);
				apuesta.add(22);
				nombreApuesta="Caballo-19-22";
				break;
			}
			
			case 64: 
			{
				apuesta.add(24);
				apuesta.add(27);
				nombreApuesta="Caballo-24-27";
				break;
			}
			
			case 65: 
			{
				apuesta.add(23);
				apuesta.add(26);
				nombreApuesta="Caballo-23-26";
				break;
			}
			
			case 66: 
			{
				apuesta.add(22);
				apuesta.add(25);
				nombreApuesta="Caballo-22-25";
				break;
			}
			
			case 67: 
			{
				apuesta.add(27);
				apuesta.add(30);
				nombreApuesta="Caballo-27-30";
				break;
			}
			
			case 68: 
			{
				apuesta.add(26);
				apuesta.add(29);
				nombreApuesta="Caballo-26-29";
				break;
			}
			
			case 69: 
			{
				apuesta.add(25);
				apuesta.add(28);
				nombreApuesta="Caballo-25-28";
				break;
			}
			
			case 70: 
			{
				apuesta.add(30);
				apuesta.add(33);
				nombreApuesta="Caballo-30-33";
				break;
			}
			
			case 71: 
			{
				apuesta.add(29);
				apuesta.add(32);
				nombreApuesta="Caballo-29-32";
				break;
			}
			
			case 72: 
			{
				apuesta.add(28);
				apuesta.add(31);
				nombreApuesta="Caballo-28-31";
				break;
			}
			
			case 73: 
			{
				apuesta.add(33);
				apuesta.add(36);
				nombreApuesta="Caballo-33-36";
				break;
			}
			
			case 74: 
			{
				apuesta.add(32);
				apuesta.add(35);
				nombreApuesta="Caballo-32-35";
				break;
			}
			
			case 75: 
			{
				apuesta.add(31);
				apuesta.add(34);
				nombreApuesta="Caballo-31-34";
				break;
			}
			
			case 83: 
			{
				apuesta.add(3);
				apuesta.add(2);
				nombreApuesta="Caballo-3-2";
				break;
			}
			
			case 84: 
			{
				apuesta.add(2);
				apuesta.add(1);
				nombreApuesta="Caballo-2-1";
				break;
			}
			
			case 85: 
			{
				apuesta.add(2);
				apuesta.add(1);
				apuesta.add(3);
				nombreApuesta="Primera fila";
				break;
			}
			
			case 87: 
			{
				apuesta.add(6);
				apuesta.add(5);
				nombreApuesta="Caballo-6-5";
				break;
			}
			
			case 88: 
			{
				apuesta.add(5);
				apuesta.add(4);
				nombreApuesta="Caballo-5-4";
				break;
			}
			
			case 89: 
			{
				apuesta.add(4);
				apuesta.add(5);
				apuesta.add(6);
				nombreApuesta="Segunda Fila";
				break;
			}
			
			case 91: 
			{
				apuesta.add(9);
				apuesta.add(8);
				nombreApuesta="Caballo-9-8";
				break;
			}
			
			case 92: 
			{
				apuesta.add(8);
				apuesta.add(7);
				nombreApuesta="Caballo-8-7";
				break;
			}
			
			case 93: 
			{
				apuesta.add(7);
				apuesta.add(8);
				apuesta.add(9);
				nombreApuesta="Tercera Fila";
				break;
			}
			
			case 95: 
			{
				apuesta.add(12);
				apuesta.add(11);
				nombreApuesta="Caballo-12-11";
				break;
			}
			
			case 96: 
			{
				apuesta.add(11);
				apuesta.add(10);
				nombreApuesta="Caballo-11-10";
				break;
			}
			
			case 97: 
			{
				apuesta.add(10);
				apuesta.add(11);
				apuesta.add(12);
				nombreApuesta="Cuarta Fila";
				break;
			}
			
			case 99: 
			{
				apuesta.add(15);
				apuesta.add(14);
				nombreApuesta="Caballo-15-14";
				break;
			}
			
			case 100: 
			{
				apuesta.add(14);
				apuesta.add(13);
				nombreApuesta="Caballo-14-13";
				break;
			}
			
			case 101: 
			{
				apuesta.add(13);
				apuesta.add(14);
				apuesta.add(15);
				nombreApuesta="Quinta Fila";
				break;
			}
			
			case 103: 
			{
				apuesta.add(18);
				apuesta.add(17);
				nombreApuesta="Caballo-18-17";
				break;
			}
			
			case 104: 
			{
				apuesta.add(17);
				apuesta.add(16);
				nombreApuesta="Caballo-17-16";
				break;
			}
			
			case 105: 
			{
				apuesta.add(16);
				apuesta.add(17);
				apuesta.add(18);
				nombreApuesta="Sexta Fila";
				break;
			}
			
			case 107: 
			{
				apuesta.add(21);
				apuesta.add(20);
				nombreApuesta="Caballo-21-20";
				break;
			}
			
			case 108: 
			{
				apuesta.add(20);
				apuesta.add(19);
				nombreApuesta="Caballo-20-19";
				break;
			}
			
			case 109: 
			{
				apuesta.add(19);
				apuesta.add(20);
				apuesta.add(21);
				nombreApuesta="Septima Fila";
				break;
			}
			
			case 111: 
			{
				apuesta.add(24);
				apuesta.add(23);
				nombreApuesta="Caballo-24-23";
				break;
			}
			
			case 112: 
			{
				apuesta.add(23);
				apuesta.add(22);
				nombreApuesta="Caballo-23-22";
				break;
			}
			
			case 113: 
			{
				apuesta.add(22);
				apuesta.add(23);
				apuesta.add(24);
				nombreApuesta="Octava Fila";
				break;
			}
			
			case 115: 
			{
				apuesta.add(27);
				apuesta.add(26);
				nombreApuesta="Caballo-27-26";
				break;
			}
			
			case 116: 
			{
				apuesta.add(26);
				apuesta.add(25);
				nombreApuesta="Caballo-26-25";
				break;
			}
			
			case 117: 
			{
				apuesta.add(25);
				apuesta.add(26);
				apuesta.add(27);
				nombreApuesta="Novena Fila";
				break;
			}
			
			case 119: 
			{
				apuesta.add(30);
				apuesta.add(29);
				nombreApuesta="Caballo-30-29";
				break;
			}
			
			case 120: 
			{
				apuesta.add(29);
				apuesta.add(28);
				nombreApuesta="Caballo-29-28";
				break;
			}
			
			case 121: 
			{
				apuesta.add(28);
				apuesta.add(29);
				apuesta.add(30);
				nombreApuesta="Decima fila";
				break;
			}
			
			case 123: 
			{
				apuesta.add(33);
				apuesta.add(32);
				nombreApuesta="Caballo-33-32";
				break;
			}
			
			case 124: 
			{
				apuesta.add(32);
				apuesta.add(31);
				nombreApuesta="Caballo-32-31";
				break;
			}
			
			case 125: 
			{
				apuesta.add(31);
				apuesta.add(32);
				apuesta.add(33);
				nombreApuesta="Undecima Fila";
				break;
			}
			
			case 127: 
			{
				apuesta.add(36);
				apuesta.add(35);
				nombreApuesta="Caballo-36-35";
				break;
			}
			
			case 128: 
			{
				apuesta.add(35);
				apuesta.add(34);
				nombreApuesta="Caballo-35-34";
				break;
			}
			
			case 129: 
			{
				apuesta.add(34);
				apuesta.add(35);
				apuesta.add(36);
				nombreApuesta="Doceava Fila";
				break;
			}
			
			case 131: 
			{
				apuesta.add(2);
				apuesta.add(3);
				apuesta.add(5);
				apuesta.add(6);
				apuesta.add(8);
				apuesta.add(9);
				apuesta.add(11);
				apuesta.add(12);
				apuesta.add(14);
				apuesta.add(15);
				apuesta.add(17);
				apuesta.add(18);
				apuesta.add(20);
				apuesta.add(21);
				apuesta.add(23);
				apuesta.add(24);
				apuesta.add(26);
				apuesta.add(27);
				apuesta.add(29);
				apuesta.add(30);
				apuesta.add(32);
				apuesta.add(33);
				apuesta.add(35);
				apuesta.add(36);
				nombreApuesta="Dos Columnas-Superiores";
				break;
			}
			
			case 132: 
			{
				apuesta.add(1);
				apuesta.add(2);
				apuesta.add(4);
				apuesta.add(5);
				apuesta.add(7);
				apuesta.add(8);
				apuesta.add(10);
				apuesta.add(11);
				apuesta.add(13);
				apuesta.add(14);
				apuesta.add(16);
				apuesta.add(17);
				apuesta.add(19);
				apuesta.add(20);
				apuesta.add(22);
				apuesta.add(23);
				apuesta.add(25);
				apuesta.add(26);
				apuesta.add(28);
				apuesta.add(39);
				apuesta.add(31);
				apuesta.add(32);
				apuesta.add(34);
				apuesta.add(35);
				nombreApuesta="Dos Columnas-Inferiores";
				break;
			}
			
			case 135: 
			{
				apuesta.add(0);
				apuesta.add(3);
				apuesta.add(2);
				nombreApuesta="Transversal-0-2-3";
				break;
			}
			
			case 136: 
			{
				apuesta.add(0);
				apuesta.add(1);
				apuesta.add(2);
				nombreApuesta="Transversal-0-1-2";
				break;
			}
			
			case 139: 
			{
				apuesta.add(3);
				apuesta.add(6);
				apuesta.add(2);
				apuesta.add(5);
				nombreApuesta="Caballo-3-6";
				break;
			}
			
			case 140: 
			{
				apuesta.add(1);
				apuesta.add(4);
				apuesta.add(2);
				apuesta.add(5);
				nombreApuesta="Cuadro-1-4-2-5";
				break;
			}
			
			case 141: 
			{
				apuesta.add(1);
				apuesta.add(2);
				apuesta.add(3);
				apuesta.add(4);
				apuesta.add(5);
				apuesta.add(6);
				nombreApuesta="Primera Seisena";
				break;
			}
			
			case 143: 
			{
				apuesta.add(6);
				apuesta.add(9);
				apuesta.add(5);
				apuesta.add(8);
				nombreApuesta="Cuadro-6-9-5-8";
				break;
			}
			
			case 144: 
			{
				apuesta.add(7);
				apuesta.add(4);
				apuesta.add(8);
				apuesta.add(5);
				nombreApuesta="Cuadro-7-4-8-5";
				break;
			}
			
			case 145: 
			{
				apuesta.add(4);
				apuesta.add(5);
				apuesta.add(6);
				apuesta.add(7);
				apuesta.add(8);
				apuesta.add(9);
				nombreApuesta="Segunda Seisena";
				break;
			}
			
			case 147: 
			{
				apuesta.add(9);
				apuesta.add(11);
				apuesta.add(8);
				apuesta.add(12);
				nombreApuesta="Cuadro-9-11-8-12";
				break;
			}
			
			case 148: 
			{
				apuesta.add(10);
				apuesta.add(11);
				apuesta.add(8);
				apuesta.add(7);
				nombreApuesta="Cuadro-10-11-8-7";
				break;
			}
			
			case 149: 
			{
				apuesta.add(7);
				apuesta.add(8);
				apuesta.add(9);
				apuesta.add(10);
				apuesta.add(11);
				apuesta.add(12);
				nombreApuesta="Tercera Seisena";
				break;
			}
			case 151: 
			{
				apuesta.add(12);
				apuesta.add(15);
				apuesta.add(11);
				apuesta.add(14);
				nombreApuesta="Cuadro-12-15-11-14";
				break;
			}
			
			case 152: 
			{
				apuesta.add(10);
				apuesta.add(13);
				apuesta.add(11);
				apuesta.add(14);
				nombreApuesta="Cuadro-10-13-11-14";
				break;
			}
			
			case 153: 
			{
				apuesta.add(13);
				apuesta.add(14);
				apuesta.add(15);
				apuesta.add(10);
				apuesta.add(11);
				apuesta.add(12);
				nombreApuesta="Cuarta Seisena";
				break;
			}
			case 155: 
			{
				apuesta.add(15);
				apuesta.add(18);
				apuesta.add(14);
				apuesta.add(17);
				nombreApuesta="Cuadro-15-18-14-17";
				break;
			}
			
			case 156: 
			{
				apuesta.add(16);
				apuesta.add(13);
				apuesta.add(14);
				apuesta.add(17);
				nombreApuesta="Cuadro-16-13-14-17";
				break;
			}
			
			case 157: 
			{
				apuesta.add(13);
				apuesta.add(14);
				apuesta.add(15);
				apuesta.add(16);
				apuesta.add(17);
				apuesta.add(18);
				nombreApuesta="Quinta Seisena";
				break;
			}
			
			case 159: 
			{
				apuesta.add(18);
				apuesta.add(21);
				apuesta.add(17);
				apuesta.add(20);
				nombreApuesta="Cuadro-21-18-17-20";
				break;
			}
			
			case 160: 
			{
				apuesta.add(19);
				apuesta.add(16);
				apuesta.add(17);
				apuesta.add(20);
				nombreApuesta="Cuadro-19-16-17-20";
				break;
			}
			
			case 161: 
			{
				apuesta.add(19);
				apuesta.add(20);
				apuesta.add(21);
				apuesta.add(16);
				apuesta.add(17);
				apuesta.add(18);
				nombreApuesta="Sexta Seisena";
				break;
			}
			
			case 163: 
			{
				apuesta.add(21);
				apuesta.add(24);
				apuesta.add(20);
				apuesta.add(23);
				nombreApuesta="Cuadro-21-24-20-23";
				break;
			}
			
			case 164: 
			{
				apuesta.add(19);
				apuesta.add(22);
				apuesta.add(20);
				apuesta.add(23);
				nombreApuesta="Cuadro-19-22-20-23";
				break;
			}
			
			case 165: 
			{
				apuesta.add(19);
				apuesta.add(20);
				apuesta.add(21);
				apuesta.add(22);
				apuesta.add(23);
				apuesta.add(24);
				nombreApuesta="Septima Seisena";
				break;
			}
			
			case 167: 
			{
				apuesta.add(24);
				apuesta.add(27);
				apuesta.add(23);
				apuesta.add(26);
				nombreApuesta="Cuadro-24-27-23-26";
				break;
			}
			
			case 168: 
			{
				apuesta.add(25);
				apuesta.add(22);
				apuesta.add(23);
				apuesta.add(26);
				nombreApuesta="Cuadro-25-22-23-26";
				break;
			}
			
			case 169: 
			{
				apuesta.add(25);
				apuesta.add(26);
				apuesta.add(27);
				apuesta.add(22);
				apuesta.add(23);
				apuesta.add(24);
				nombreApuesta="Octava Seisena";
				break;
			}
			
			case 171: 
			{
				apuesta.add(27);
				apuesta.add(30);
				apuesta.add(26);
				apuesta.add(29);
				nombreApuesta="Cuadro-27-30-26-29";
				break;
			}
			
			case 172: 
			{
				apuesta.add(25);
				apuesta.add(28);
				apuesta.add(26);
				apuesta.add(29);
				nombreApuesta="Cuadro-25-28-26-29";
				break;
			}
			
			case 173: 
			{
				apuesta.add(25);
				apuesta.add(26);
				apuesta.add(27);
				apuesta.add(28);
				apuesta.add(29);
				apuesta.add(30);
				nombreApuesta="Novena Seisena";
				break;
			}
			
			case 175: 
			{
				apuesta.add(30);
				apuesta.add(33);
				apuesta.add(29);
				apuesta.add(32);
				nombreApuesta="Cuadro-30-33-29-32";
				break;
			}
			
			case 176: 
			{
				apuesta.add(28);
				apuesta.add(31);
				apuesta.add(29);
				apuesta.add(32);
				nombreApuesta="Cuadro-28-31-29-32";
				break;
			}
			
			case 177: 
			{
				apuesta.add(31);
				apuesta.add(32);
				apuesta.add(33);
				apuesta.add(28);
				apuesta.add(29);
				apuesta.add(30);
				nombreApuesta="Decima Seisena";
				break;
			}
			
			case 179: 
			{
				apuesta.add(33);
				apuesta.add(36);
				apuesta.add(32);
				apuesta.add(35);
				nombreApuesta="Cuadro-33-36-32-35";
				break;
			}
			
			case 180: 
			{
				apuesta.add(31);
				apuesta.add(34);
				apuesta.add(32);
				apuesta.add(35);
				nombreApuesta="Cuadro-31-34-32-35";
				break;
			}
			
			case 181: 
			{
				apuesta.add(31);
				apuesta.add(32);
				apuesta.add(33);
				apuesta.add(34);
				apuesta.add(35);
				apuesta.add(36);
				nombreApuesta="Ultima Seisena";
				break;
			}
			
			
			case 190: 
			{
				apuesta.add(1);
				apuesta.add(2);
				apuesta.add(3);
				apuesta.add(4);
				apuesta.add(5);
				apuesta.add(6);
				apuesta.add(7);
				apuesta.add(8);
				apuesta.add(9);
				apuesta.add(10);
				apuesta.add(11);
				apuesta.add(12);
				nombreApuesta="Primera Docena";
				break;
			}
			
			case 191: 
			{
				apuesta.add(13);
				apuesta.add(14);
				apuesta.add(15);
				apuesta.add(16);
				apuesta.add(17);
				apuesta.add(18);
				apuesta.add(19);
				apuesta.add(20);
				apuesta.add(21);
				apuesta.add(22);
				apuesta.add(23);
				apuesta.add(24);
				nombreApuesta="Segunda Docena";
				break;
			}
			
			case 192: 
			{
				apuesta.add(25);
				apuesta.add(26);
				apuesta.add(27);
				apuesta.add(28);
				apuesta.add(29);
				apuesta.add(30);
				apuesta.add(31);
				apuesta.add(32);
				apuesta.add(33);
				apuesta.add(34);
				apuesta.add(35);
				apuesta.add(36);
				nombreApuesta="Tercera Docena";
				break;
			}
			
			case 193: 
			{
				apuesta.add(1);
				apuesta.add(2);
				apuesta.add(3);
				apuesta.add(4);
				apuesta.add(5);
				apuesta.add(6);
				apuesta.add(7);
				apuesta.add(8);
				apuesta.add(9);
				apuesta.add(10);
				apuesta.add(11);
				apuesta.add(12);
				apuesta.add(13);
				apuesta.add(14);
				apuesta.add(15);
				apuesta.add(16);
				apuesta.add(17);
				apuesta.add(18);
				nombreApuesta="1 a 18";
				break;
			}
			
			case 194: 
			{
				apuesta.add(2);
				apuesta.add(4);
				apuesta.add(6);
				apuesta.add(8);
				apuesta.add(10);
				apuesta.add(12);
				apuesta.add(14);
				apuesta.add(16);
				apuesta.add(18);
				apuesta.add(20);
				apuesta.add(22);
				apuesta.add(24);
				apuesta.add(26);
				apuesta.add(28);
				apuesta.add(30);
				apuesta.add(32);
				apuesta.add(34);
				apuesta.add(36);
				nombreApuesta="Pares";
				break;
			}
			
			case 195: 
			{
				apuesta.add(2);
				apuesta.add(4);
				apuesta.add(6);
				apuesta.add(8);
				apuesta.add(10);
				apuesta.add(11);
				apuesta.add(13);
				apuesta.add(15);
				apuesta.add(17);
				apuesta.add(20);
				apuesta.add(22);
				apuesta.add(24);
				apuesta.add(26);
				apuesta.add(28);
				apuesta.add(29);
				apuesta.add(31);
				apuesta.add(33);
				apuesta.add(35);
				nombreApuesta="Negros";
				break;
			}
			
			case 196: 
			{
				apuesta.add(1);
				apuesta.add(3);
				apuesta.add(5);
				apuesta.add(7);
				apuesta.add(9);
				apuesta.add(12);
				apuesta.add(14);
				apuesta.add(16);
				apuesta.add(36);
				apuesta.add(18);
				apuesta.add(19);
				apuesta.add(21);
				apuesta.add(23);
				apuesta.add(25);
				apuesta.add(27);
				apuesta.add(30);
				apuesta.add(32);
				apuesta.add(34);
				nombreApuesta="Rojos";
				break;
			}
			
			case 197: 
			{
				apuesta.add(1);
				apuesta.add(3);
				apuesta.add(5);
				apuesta.add(7);
				apuesta.add(9);
				apuesta.add(11);
				apuesta.add(13);
				apuesta.add(15);
				apuesta.add(17);
				apuesta.add(19);
				apuesta.add(21);
				apuesta.add(23);
				apuesta.add(25);
				apuesta.add(27);
				apuesta.add(29);
				apuesta.add(31);
				apuesta.add(33);
				apuesta.add(35);
				nombreApuesta="Impares";
				break;
			}
			
			case 198: 
			{
				apuesta.add(19);
				apuesta.add(20);
				apuesta.add(21);
				apuesta.add(22);
				apuesta.add(23);
				apuesta.add(24);
				apuesta.add(25);
				apuesta.add(26);
				apuesta.add(27);
				apuesta.add(28);
				apuesta.add(29);
				apuesta.add(30);
				apuesta.add(31);
				apuesta.add(32);
				apuesta.add(33);
				apuesta.add(34);
				apuesta.add(35);
				apuesta.add(36);
				nombreApuesta="19 a 36";
				break;
			}
			
			case 199: 
			{
				apuesta.add(0);
				nombreApuesta="0";
				break;
			}
			
			default:
			{
				apuesta.add(iD);
				nombreApuesta=Double.toString(iD);
			}
			
			}
			return apuesta;
			}
	
	/**
	 * Identificar apuesta.
	 * Se encarga de identificar por cual numero se debe multiplicar el resultado si se gana, esto lo hace determinando el tipo de apuesta
	 * por medio del ID dado.
	 *
	 * @param ID the id
	 * @return the double
	 */
	//------------------------------------------------------------
	public double identificarApuesta(int ID) {
		if(ID==180 || ID==179|| ID==176 || ID==175|| ID==177|| ID==171|| ID==168|| ID==167|| ID==164 || ID==163||
		   ID==160 || ID==159|| ID==156|| ID==157|| ID==155|| ID==151|| ID==148|| ID==147|| ID==144|| ID==143||ID==140) {
			tipoDeApuesta = 9;
			//cuadro
		}
		else if((ID>=43 && ID <= 75) || ID==83|| ID==84 || ID==87|| ID==88|| ID==91|| ID==168|| ID==92|| ID==95 || ID==96||
				   ID==99 || ID==100|| ID==103|| ID==104|| ID==107|| ID==108|| ID==111|| ID==112|| ID==115|| ID==116||ID==119||
				   ID==120 || ID==123|| ID==124|| ID==127|| ID==128 || ID==139) {
			tipoDeApuesta = 18;
			//caballo
		}
		else if(ID==85|| ID==89 || ID==93|| ID==97|| ID==101|| ID==105|| ID==109|| ID==113 || ID==117||
				   ID==121 || ID==125|| ID==129 || ID==135|| ID==136) {
			tipoDeApuesta = 12;
			//transversal
		}
		else if (ID==141|| ID==149 || ID==145|| ID==153|| ID==157|| ID==161|| ID==165|| ID==169 || ID==173||
		   ID==177 || ID==181) {
			tipoDeApuesta = 6;
			//seisena
		}
		else if(ID==38|| ID==39 || ID==37) {
			tipoDeApuesta = 3;
			//columna
		}
		else if (ID==132|| ID==131) {
			tipoDeApuesta = 1.5;
			//dos columnas
		}
		else if(ID>=190 && ID<= 192) {
			tipoDeApuesta = 3;
			//docena
		}
		else if(ID==195 || ID== 196) {
			tipoDeApuesta = 2;
			//rojo negro
		}
		else if(ID==197 || ID== 194) {
			tipoDeApuesta = 2;
			//per impar
		}
		else if(ID==198 || ID== 193 ) {
			tipoDeApuesta = 2;
			//pasa falta
		}
		else if (ID==1||ID==2||ID==3||ID==4||ID==5||ID==6||ID==7||ID==8||ID==9||ID==10||ID==11||ID==12||ID==13||
				ID==14||ID==15||ID==16||ID==17||ID==18||ID==19||ID==20||ID==21||ID==22||ID==23||ID==24||ID==25||
				ID==26||ID==27||ID==28||ID==29||ID==30||ID==31||ID==32||ID==33||ID==34||ID==35||ID==36||ID==199) {
			tipoDeApuesta = 36;
			//PLENO
		}
		return tipoDeApuesta;
	}
	
}

//FIN DE CLASE
