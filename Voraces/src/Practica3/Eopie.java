//Pr�ctica 3 de Metodolog�a, David Arias Escribano y Sandra Ciudad Moreno, 2�B
package Practica3;

/******************************************************************************** 
 * 
 * Class Name: Eopie
 * Author/s name: David Arias Escribano, Sandra Ciudad Moreno
 * Release/Creation date: 30/03/2021
 * Class version: 1.1
 * Class description: Clase que contiene los datos correspondientes de los Eopies
 * 
 ********************************************************************************/

public class Eopie {
	
	int numero_Eopie;
	double volumenSoportado; 
	double volumenLlevado;
	boolean utilizado;
	
	/******************************************************************************************************
	*
	* Method name: Eopie
	*
	* Description of the Method: Es el m�todo constructor de la clase Eopie
	* 
	* Calling arguments: numero_Eopie, volumenSoportado, volumenLlevado y utilizado (atributos de Eopie)
	* 
	******************************************************************************************************/
	
	//Definimos la clase Eopie con los atributos anteriores
	public Eopie(int numero_Eopie, double volumenSoportado, double volumenLlevado, boolean utilizado) { 
		this.numero_Eopie = numero_Eopie;
		this.volumenSoportado = volumenSoportado;
		this.volumenLlevado = volumenLlevado;
		this.utilizado = utilizado;
		
	}
	
	//Declaramos los m�todos getter y setter
	//GETTERS
	public int get_numero_Eopie() {
		return numero_Eopie;
	}
	
	public double get_volumenSoportado() {
		return volumenSoportado;
	}

	public double get_volumenLlevado() {
		return volumenLlevado;
	}
	
	public boolean get_utilizado() {
		return utilizado;
	}
	
	//SETTERS
	public void set_numero_Eopie(int numero_Eopie) {
		this.numero_Eopie = numero_Eopie;
	}
	
	public void set_volumenSoportado(double volumenSoportado) {
		this.volumenSoportado = volumenSoportado;
	}
	
	public void set_volumenLlevado(double volumenLlevado) {
		this.volumenLlevado = volumenLlevado;
	}
	
	public void set_utilizado(boolean utilizado) {
		this.utilizado = utilizado;
	}
	
	//Metodo toString, que devolver� en una cadena los atributos de cada Eopie junto a su valor asignado.
	public String toString() {
		String cadena;
		cadena = "Eopie con ID: " + numero_Eopie + ", el cual soporta: " + volumenSoportado + " litros, y lleva cargado el Contenedor de: " + volumenLlevado + " litros. Se ha empleado ya: " + utilizado + ". ";
		return cadena;
	}
	
}
