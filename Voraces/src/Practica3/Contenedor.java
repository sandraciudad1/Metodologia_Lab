//Pr�ctica 3 de Metodolog�a, David Arias Escribano y Sandra Ciudad Moreno, 2�B
package Practica3;

/***************************************************************************************
 * 
 * Class Name: Contenedor
 * Author/s name: David Arias Escribano, Sandra Ciudad Moreno
 * Release/Creation date: 30/03/2021
 * Class version: 1.1
 * Class description: Clase que contiene los datos correspondientes de los Contenedores
 * 
 **************************************************************************************/

public class Contenedor {
	
	int numero_contenedor;
	double volumen;
	boolean utilizado;
	
	/************************************************************************************
	*
	* Method name: Contenedor
	*
	* Description of the Method: Es el m�todo constructor de la clase Contenedor
	* 
	* Calling arguments: numero_contenedor, volumen y utilizado (atributos de Contenedor)
	* 
	************************************************************************************/
	
	//Definimos la clase Contenedor con los atributos anteriores
	public Contenedor(int numero_contenedor, double volumen, boolean utilizado) { 
		this.numero_contenedor = numero_contenedor;
		this.volumen = volumen;
		this.utilizado = utilizado;

	}
	
	//Declaramos los m�todos getter y setter
	
	//GETTERS
	
	public int get_numero_contenedor() {
		return numero_contenedor;
	}
	
	public double get_volumen() {
		return volumen;
	}
	
	public boolean get_utilizado() {
		return utilizado;
	}
	
	
	//SETTERS
	
	public void set_numero_contenedor(int numero_contenedor) {
		this.numero_contenedor = numero_contenedor;
	}
	
	public void set_volumen(double volumen) {
		this.volumen = volumen;
	}
	
	public void set_utilizado(boolean utilizado) {
		this.utilizado = utilizado;
	}
	
	
	//Metodo toString, que devolver� en una cadena los atributos de cada Contenedor junto a su valor asignado.
	public String toString() {
		String cadena;
		cadena = "Contenedor n�mero: " + numero_contenedor + ", el cual tiene un volumen de: " + volumen + " litros. ";
		return cadena;
	}
	
}
