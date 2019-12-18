package tp4;

import java.util.Scanner;

import outilsTris.OutilsTris;

public class Main {

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Saisie d'un fichier
		// SAISIR : /private/student/9/09/18002209/prog/tp4/src/donnees/NOM
		System.out.println("Entrez un nom de fichier : ");
		String nomFichier = sc.nextLine();
//		String nomFichier = "/private/student/9/09/18002209/prog/tp4/src/donnees/donnees_50000_seq";
		// Lecture du fichier
		int[] tab = OutilsTris.lireTableau(nomFichier);
		// Tri du tableau de nombres
		TriRapide.trier(tab, tab.length);
		// Test du tri
		System.out.println(estTrie(tab));
		afficherTableau(tab, tab.length-1);
		// Enregistrement du tableau
		OutilsTris.enregistrerTableau(tab, tab.length, "output.txt");
		
		
		// Tri rapide
		System.out.println("\nTri dicho :");
		int[] tab2 = OutilsTris.lireTableau(nomFichier);
		TriRapide.trier(tab2, tab2.length);
//		afficherTableau(tab2, tab.length-1);
		System.out.println(estTrie(tab));
		System.out.println(estTrie(tab2));
 	}

	public static boolean estTrie(int[] tab) {
		for (int i = 0; i < tab.length-1; i++) {
			if (tab[i] > tab[i+1]) return false;
		}
		return true;
	}
	
	/**
	 * Afficher les nb premiers éléments d'un tableau.
	 * @param tnb		: tableau initialisé
	 * @param nb		: nombre d'éléments en tête du tableau
	 * @pre 0 <= nb <= tnb.length
	 * @post le tableau n'est pas modifié
	 */
	public static void afficherTableau(int[] tnb, int nb) {
		System.out.print("\n Contenu du tableau ("+nb+" valeurs): [ ");
		for (int i = 0; i < nb; i++) {
			System.out.print(tnb[i] + " ");
		}
		System.out.print("]");
	}
//	/private/student/9/09/18002209/prog/tp4/src/donnees/donnees_00097
}
