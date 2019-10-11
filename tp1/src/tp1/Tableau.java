package tp1;

import java.util.Scanner;

public class Tableau {

	/**
	 * Initialiser un tableau avec les valeurs d'une suite de nombres entiers 
	 * lus au clavier, la suite est terminée par valFin
	 * @param tnb		: tableau de nombres (déjà crée) à initialiser
	 * @param valFin	: valeur qui met fin à la saisie; ne doit pas être dans le tableau !
	 * @param entree	: scanner d'entrée ou se fait la lecture
	 * @post			: le tableau contient N nombres entiers (0<=N<=tnb.length)
	 * @return
	 */
	public static int lireTableau(int[] tnb, int valFin, Scanner entree) {
		int cursor = 0;
		while (cursor < tnb.length) {
			// get user input
			int input = entree.nextInt();
			if (input != valFin) {
				// Valid input, insert to array
				tnb[cursor] = input;
			} else {
				// End input, break condition
				return cursor;
			}
			cursor++;
		}
		return cursor;
	}
	
	/**
	 * Afficher les nb premiers éléments d'un tableau.
	 * @param tnb		: tableau initialisé
	 * @param nb		: nombre d'éléments en tête du tableau
	 * @pre 0 <= nb <= tnb.length
	 * @post le tableau n'est pas modifié
	 */
	public static void afficherTableau(int[] tnb, int nb) {
		System.out.println("Contenu du tableau ("+nb+" valeurs: [ ");
		for (int i = 0; i < nb; i++) {
			System.out.print(tnb[i] + " ");
		}
		System.out.print("]");
	}
	
}
