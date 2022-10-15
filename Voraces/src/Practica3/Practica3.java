//Pr�ctica 3 de Metodolog�a, David Arias Escribano y Sandra Ciudad Moreno, 2�B
package Practica3;

import java.util.*;

/*************************************************************************************************************************
 *
 * Class Name: Practica3 Author/s name: David Arias Escribano y Sandra Ciudad Moreno 
 * Release/Creation date: 30/03/2021 
 * Class version: 1.1 
 * Class description: Clase que corresponde al main de la pr�ctica 3, correspondiente
 * al algoritmo voraz de Star Wars del laboratorio de metodolog�a, que contiene todos los m�todos diferentes necesarios para realizar la distribuci�n de los
 * contenedores entre los eopies (utilizando el algoritmo nombrado anteriormente), as� como la simulaci�n de las 7 noches.
 *
 **************************************************************************************************************************/

public class Practica3 {
	final static Scanner Teclado = new Scanner(System.in);

	/***********************************************************************************************************************
	 *
	 * Method name: main 
	 * Description of the Method: En el m�todo main del programa se realizan las llamadas a los distintos m�todos que asignan a los eopies
	 * los contenedores correspondientes, adem�s de la simulaci�n de las 7 noches. 
	 * Required Files: No se necesita ning�n fichero para la ejecuci�n del programa. 
	 * Exceptions: se captura InputMismatchException.
	 * 
	 ************************************************************************************************************************/

	public static void main(String[] args) {

		int numeroEopies = 0, numeroContenedores = 0;
		double litros_totales = 0;
		int opcionMenuEopies = 0;
		boolean opcionSalir = false;

		System.out.println("----------------------Bienvenido al Planeta Tatooine----------------------\n");

		do {
			System.out.println("Introduzca el n�mero de Contenedores:");
			numeroContenedores = Teclado.nextInt(); //Pedimos al usuario el n�mero de Contenedores
			System.out.println("Introduzca el n�mero de Eopies, teniendo en cuenta que debe ser menor que el n�mero de Contenedores:");
			numeroEopies = Teclado.nextInt(); //Pedimos al usuario el n�mero de Eopies
			if (numeroContenedores <= numeroEopies) { //Se controla que el n�mero de Eopies debe ser menor que el n�mero de Contenedores
				System.out.println("Por favor, recuerde que el n�mero de Eopies debe ser menor que el n�mero de Contenedores.");
			}
		} while (numeroContenedores <= numeroEopies);
		
		do {
			try {
			mostrarMenuEopies(); //mostramos el men�
			opcionMenuEopies = Teclado.nextInt();

			switch (opcionMenuEopies) { //permitimos al usuario seleccionar entre la ejecuci�n normal o la simulaci�n de las 7 noches
			
				case 1: //Ejecuci�n normal del programa
					// Arrays de contenedores y eopies:
					Contenedor[] vector_contenedores = new Contenedor[numeroContenedores];
					Eopie[] vector_eopies = new Eopie[numeroEopies];
					litros_totales = 0;

					System.out.println("Existen un total de " + numeroContenedores + " contenedores y " + numeroEopies + " eopies.\n");
					vector_contenedores = generar_contenedores(numeroContenedores, vector_contenedores);
				
					vector_eopies = generar_eopies(numeroEopies, vector_eopies);

					System.out.println("\nProceso de asignaci�n de Contenedores a Eopies: ");
					for (int i = 0; i < numeroEopies; i++) {
						voraz(vector_contenedores, vector_eopies);
					}

					System.out.println("\nListado de Eopies con los vol�menes de sus Contenedores asignados: ");
					for (int i = 0; i < numeroEopies; i++) {
						System.out.println(vector_eopies[i].toString()); //Se muestran los Eopies y su informaci�n
					}

					System.out.println("\nLos Eopies que se han quedado sin carga asignada son los siguientes: ");
					for (int i = 0; i < numeroEopies; i++) {
						eopies_vacios(vector_eopies[i]);
						litros_totales += vector_eopies[i].get_volumenLlevado(); //Se calculan los litros totales de agua que transportan los Eopies
					}

					System.out.println("\nLa carga total transportada por los Eopies es de: " + litros_totales + " litros.");
				
				break;
				
				case 2: //Simulaci�n de las 7 noches
					siete_noches(numeroEopies, numeroContenedores);
				break;
				
				case 3: //Salir del programa
					opcionSalir = true;
					System.out.println("Gracias por usar nuestros servicios.");
				break;
				default:
					System.out.println("Opci�n incorrecta, por favor, seleccione una opci�n v�lida del men�.");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Error, no se ha introducido un valor num�rico.");
				Teclado.next();
			}
		} while (!opcionSalir); //Permitimos al usuario seleccionar otra opci�n mientras no salga del programa
		
	}
	
	// M�todo mostrarMenuEopies:
	/******************************************************************************************************************************
	*
	* Method name: mostrarMenuEopies() 
	* Description of the Method: Este m�todo muestra el men� del programa.
	* Return value: void, no devuelve nada.
	* 
	******************************************************************************************************************************/
	public static void mostrarMenuEopies() {
	
		System.out.println();
		System.out.println("Por favor, seleccione una opci�n:");
		System.out.println("1. Realizar una ejecuci�n normal del programa.");
		System.out.println("2. Realizar la simulaci�n de las 7 noches.");
		System.out.println("3. Salir del programa.");

	}

	// M�todo myRandom:
	/******************************************************************************************************************************
	 *
	 * Method name: myRandom() 
	 * Description of the Method: Este m�todo genera un
	 * n�mero aleatorio entre el m�nimo y el m�ximo que le pasamos como par�metros.
	 * Calling arguments: min, correspondiente al valor 1, hace referencia al valor
	 * m�nimo que puede ser generado aleatoriamente. 
	 * max, corresponde al valor 50 y hace referencia al valor m�ximo que puede ser generado aleatoriamente. 
	 * Return value: double, retorna el double generado aleatoriamente.
	 * 
	 ******************************************************************************************************************************/
	public static double myRandom(double min, double max) {
		Random r = new Random();
		return (r.nextInt((int) ((max - min) * 10 + 1)) + min * 10) / 10.0;
	}
	
	// M�todo generar_contenedores:
	/******************************************************************************************************************************
	*
	* Method name: generar_contenedores() 
	* Description of the Method: Este m�todo rellena el vector de Contenedores con objetos Contenedor cuyo volumen
	* se genera de manera aleatoria, posteriormente los muestra.
	* Calling arguments: numeroContenedores, un int correspondiente al n�mero de contenedores introducidos. 
	* vector_contenedores, corresponde al vector de objetos Contenedor. 
	* Return value: Contenedor[], devuelve el vector de Contenedores con los objetos Contenedor creados e introducidos.
	* 
	******************************************************************************************************************************/
	public static Contenedor[] generar_contenedores(int numeroContenedores, Contenedor[] vector_contenedores) {
		
		double volumenContenedor = 0;
		System.out.println("Listado de Contenedores disponibles: ");
		for (int i = 0; i < numeroContenedores; i++) {
			volumenContenedor = myRandom(1, 50); //generamos el volumen de cada Contenedor de manera aleatoria
			vector_contenedores[i] = new Contenedor(i, volumenContenedor, false);  //Se crea el vector de Contenedores
			System.out.println(vector_contenedores[i].toString()); //Se muestran los Contenedores disponibles
		}
		
		return vector_contenedores;
	}
	
	// M�todo generar_eopies:
	/******************************************************************************************************************************
	*
	* Method name: generar_eopies() 
	* Description of the Method: Este m�todo rellena el vector de Eopies con objetos Eopie cuyo volumen soportado
	* se genera de manera aleatoria.
	* Calling arguments: numeroEopies, un int correspondiente al n�mero de eopies introducidos. 
	* vector_eopies, corresponde al vector de objetos Eopie. 
	* Return value: Eopie[], devuelve el vector de Eopies con los objetos Eopie creados e introducidos.
	* 
	******************************************************************************************************************************/
	
	public static Eopie[] generar_eopies(int numeroEopies, Eopie[] vector_eopies) {
		
		double volumenSoportado = 0;
		double volumenLlevado = 0;
		for (int i = 0; i < numeroEopies; i++) {
			volumenSoportado = myRandom(1, 50); //generamos el volumen que soporta cada Eopie de manera aleatoria
			vector_eopies[i] = new Eopie(i, volumenSoportado, volumenLlevado, false); //Se crea el vector de Eopies
		}
		
		return vector_eopies;
	}

	// M�todo voraz:
	/********************************************************************************************************************************
	 *
	 * Method name: voraz() 
	 * Description of the Method: En este m�todo se desarrolla
	 * el algoritmo voraz, el cual nos permite seleccionar siempre al eopie que m�s
	 * litros puede soportar (eopie_fuerte) y que no est� siendo ya utilizado, de
	 * manera que podremos asignar contenedores primero a los eopies m�s fuertes, y
	 * dejar para el final a los eopies que soportan una cantidad de agua menor.
	 * Posteriormente, se mostrar� informaci�n de los mismos como la posici�n del
	 * eopie o el volumen soportado (en litros) mediante el String cadena. 
	 * Calling arguments: Contenedor[] vector_contenedores, que posee tantos contenedores como introduce el usuario al ejecutar el programa, cada uno con sus
	 * respectivos atributos (numero_contenedor, volumen y utilizado). 
	 * Eopie[] vector_eopies, que posee tantos eopies como introduce el usuario al iniciar
	 * el programa, cada uno con sus atributos (numero_eopie, columenSoportado, volumenLlevado y utilizado) 
	 * Return value: void, no retorna nada.
	 * 
	 ********************************************************************************************************************************/
	public static void voraz(Contenedor[] vector_contenedores, Eopie[] vector_eopies) {
		double eopie_fuerte = 0;
		int posicion_eopie = 0;
		String cadena = "";

		for (int i = 0; i < vector_eopies.length; i++) {
			if (!vector_eopies[i].get_utilizado()) { //Se comprueba primero que el Eopie est� libre
				if (vector_eopies[i].get_volumenSoportado() > eopie_fuerte) {
					eopie_fuerte = vector_eopies[i].get_volumenSoportado();
					posicion_eopie = i;
				}
			}

		}
		cadena = "Al Eopie " + posicion_eopie + ", que soporta un volumen de "
				+ vector_eopies[posicion_eopie].get_volumenSoportado() + " litros, ";
		solucion(vector_eopies[posicion_eopie], vector_eopies, vector_contenedores, cadena);
	}

	// M�todo solucion
	/*********************************************************************************************************************************
	 *
	 * Method name: solucion() 
	 * Description of the Method: Este m�todo nos permitir�
	 * asignar contenedores a eopies, comprobando previamente si estos ya est�n
	 * siendo utilizados y si los eopies pueden soportar su volumen. Posteriormente,
	 * asignaremos el mayor volumen posible a cada eopie e indicaremos que est�
	 * siendo ya utilizado para el transporte de agua, mostrando los datos del mismo
	 * (posici�n y volumen del contenedor) 
	 * Calling arguments: Eopie eopie, hace referencia a un objeto eopie de la clase Eopie. 
	 * Eopie[] vector_eopies, como en el m�todo anterior, hace referencia al vector de eopies con sus atributos y n�mero de eopies seleccionado por el usuario. 
	 * Contenedor[] vector_contenedores, igual que en el m�todo anterior, hace referencia al vector de contenedores con los atributos que estos poseen y el n�mero de
	 * contenedores (introducido por el usuario). 
	 * String cadena, que hace referencia a la cadena del m�todo anterior la cual mostraba la posici�n del eopie y la
	 * cantidad de litros que este soporta. En este m�todo se continua rellenando la cadena con el contenedor que se le asigna y el volumen (en litros) de dicho
	 * contenedor que soportar� cada eopie. 
	 * Return value: void, no retorna nada.
	 * 
	 *********************************************************************************************************************************/
	public static void solucion(Eopie eopie, Eopie[] vector_eopies, Contenedor[] vector_contenedores, String cadena) {
		double contenedor_vol_mayor = 0;
		int posicion_contenedor = 0;

		for (int i = 0; i < vector_contenedores.length; i++) {
			if (!vector_contenedores[i].get_utilizado()) { //Se comprueba primero que el Contenedor est� sin asignar
				if (vector_contenedores[i].get_volumen() <= eopie.volumenSoportado) { //Se comprueba que el Eopie soporte el Contenedor
					if (vector_contenedores[i].get_volumen() > contenedor_vol_mayor) {
						contenedor_vol_mayor = vector_contenedores[i].get_volumen();
						posicion_contenedor = i;
						eopie.set_utilizado(true);
					}
				}
			}
		}
		eopie.set_volumenLlevado(contenedor_vol_mayor);
		vector_contenedores[posicion_contenedor].set_utilizado(true);
		cadena += "se le asigna el Contenedor " + posicion_contenedor + ", que tiene un volumen de "
				+ vector_contenedores[posicion_contenedor].get_volumen() + " litros.";

		if (eopie.get_utilizado()) {
			System.out.println(cadena);
		}
	}

	//
	/********************************************************************************************************************************
	 *
	 * Method name: eopies_vacios() 
	 * Description of the Method: Este m�todo muestra los Eopies que no tienen ninguna carga asignada.
	 * Calling arguments: Eopie eopie 
	 * Return value: void, no retorna nada.
	 * 
	 ********************************************************************************************************************************/
	public static void eopies_vacios(Eopie eopie) {
		if (!eopie.get_utilizado()) {
			System.out.println(eopie.toString());
		}
	}
	
	// M�todo siete_noches:
	/******************************************************************************************************************************
	*
	* Method name: siete_noches() 
	* Description of the Method: Este m�todo realiza la simulaci�n de las siete noches.
	* Calling arguments: numeroEopies, un int correspondiente al n�mero de eopies introducidos. 
	* numeroContenedores, un int correspondiente al n�mero de contenedores introducidos.
	* Return value: void, no retorna nada.
	* 
	******************************************************************************************************************************/
	public static void siete_noches(int numeroEopies, int numeroContenedores) {
		
		Contenedor[] vector_contenedores = new Contenedor[numeroContenedores];
		Eopie[] vector_eopies = new Eopie[numeroEopies];
		double litros_totales = 0; //variable que representa los litros llevados por cada noche
		double litros_noches = 0;  //variable que representa la acumulaci�n de litros llevados hasta esa noche incluida
		System.out.println("Existen un total de " + numeroContenedores + " contenedores y " + numeroEopies + " eopies.\n");
		
		for (int i = 0; i < 7; i++) { //Realizamos siete ejecuciones, una para cada noche
			litros_totales = 0;
			System.out.println("");
			System.out.println("------------------------------------ NOCHE N�MERO: " + (i+1) + "---------------------------------------");
			
			vector_eopies = generar_eopies(numeroEopies, vector_eopies);
		    vector_contenedores = generar_contenedores(numeroContenedores, vector_contenedores);
		    
		    System.out.println("\nProceso de asignaci�n de Contenedores a Eopies: ");
			for (int j = 0; j < numeroEopies; j++) {
				voraz(vector_contenedores, vector_eopies);
			}

			System.out.println("\nListado de Eopies con los vol�menes de sus Contenedores asignados: ");
			for (int k = 0; k < numeroEopies; k++) {
				System.out.println(vector_eopies[k].toString()); //Se muestran los Eopies y su informaci�n
			}
			
			System.out.println("\nLos Eopies que se han quedado sin carga asignada son los siguientes: ");
			for (int m = 0; m < numeroEopies; m++) {
				eopies_vacios(vector_eopies[m]);
				litros_totales += vector_eopies[m].get_volumenLlevado(); //Se calculan los litros totales de agua que transportan los Eopies
			}

			System.out.println("\nLa carga total transportada por los Eopies esta noche es de: " + litros_totales + " litros.");
			litros_noches += litros_totales; //Vamos acumulando los litros llevados en cada noche
			System.out.println("");
			System.out.println("Hasta esta noche, se han conseguido transportar " + litros_noches + " litros acumulados. ");
		}
		System.out.println("");
		System.out.println("Los litros totales repartidos en las 7 noches son: " + litros_noches + " litros.");
	}

}
