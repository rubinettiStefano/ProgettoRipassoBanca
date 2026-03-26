package libreria;

import java.util.Scanner;

public class Console 
{
	private static Scanner keyboard = new Scanner(System.in);

	/**
	 * Sottoprogramma per la stampa di un messaggio o di una espressione
	 * @param message, qualunque cosa
	 */
	public static void print(Object message)
	{
		System.out.println(message);
	}
	
	/**
	 * Sottoprogramma per leggere un intero da console. Permette di leggere SOLO numeri interi
	 * @return un numero intero
	 */
	public static int readInt() 
	{
		return Integer.parseInt(readString());
	}

	/**
	 * Sottoprogramma per leggere una riga da console.
	 * @return
	 */
	public static String readString() 
	{
		return keyboard.nextLine();
	}

	/**
	 * Sottoprogramma per leggere un numero qualunque, anche con la virgola, da console. Usare il punto invece della virgola!
	 * @return
	 */
	public static double readDouble() 
	{
		return Double.parseDouble(readString());
	}
	
	
	
	
}
