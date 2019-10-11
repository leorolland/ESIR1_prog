package tp1;

import java.util.Scanner;

/**
 * TP1
 * @author 18002209 - Léo ROLLAND
 */
public class TriDicho {

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
		System.out.print("\n Contenu du tableau ("+nb+" valeurs): [ ");
		for (int i = 0; i < nb; i++) {
			System.out.print(tnb[i] + " ");
		}
		System.out.print("]");
	}
	
	/*
	 * 1.3 : TRI PAR INSERTION
	 */
	
	/**
	 * Génère un tableau d'entiers trié contenant les valeurs du tableau donné	
	 * @param t		: tableau d'entiers à trier
	 * @pre 0 <= n < t.length
	 * @return le tableau trié correspondant
	 */
	public static int[] triInsertion(int[] t, int n) {
		assert (0 <= n && n <= t.length) : "condition 0 <= n < t.length non respectée";
		for (int head = 1; head < n; head++) {
			int valueToSort=t[head];
			// find index where to place the new value
			int indexToPlace=0;
			while (valueToSort > t[indexToPlace]) indexToPlace++;
			// shift values at right to the new inserted value
			for (int i=head; i>indexToPlace; i--) t[i] = t[i-1];
			// place new value
			t[indexToPlace] = valueToSort;
		}
		return t;
	}
	/* QUESTIONS :
	 * 
	 * 1) Est-ce que le programme de test permet de garantir que votre fonction effectue un tri des éléments d'un
tableau ? Expliquez.
	 * -> Non, car les tests ne sont pas exaustifs. 
	 * Etre exaustif représente souvent beaucoup trop de possibilités, aussi il convient
	 * de tester les cas ambigus en priorité, les limites, et les cas de variables non initialisés.
	 * 
	 * 2) Expliquez pourquoi le programme de test ne permet pas de garantir que votre fonction réalise ce tri avec
l'algorithme demandé.
	 * -> JUnit ne peut vérifier que la sortie d'une fonction en fonction de ses entrées. 
	 * Ainsi, plusieurs méthodes peuvent donner un même résultat pour les mêmes paramètres d'entrée
	 * JUnit ne peut donc différencier deux algorithmes implémentant la même fonction en utilisant les assertions.
	 */
	
	/*
	 * 1.4 : INTERCLASSEMENT DE DEUX TABLEAUX TRIES
	 */
	
	/**
	 * Fusionne deux tableaux triés en un seul tableau trié contenant les valeurs des deux.
	 * @param t1 Premier tableau trié
	 * @param t2 Second tableau trié
	 * @param nb1 Nombre de valeurs du premier tableau
	 * @param nb2 Nombre de valeurs du second tableau
	 * @return le tableau trié contenant les elements des deux tableaux
	 */
	public static int[] interclasser(int[] t1, int[] t2, int nb1, int nb2) {
		int index1 = 0;
		int index2 = 0;
		int[] merge = new int[nb1 + nb2];
		for (int i=0; i < merge.length; i++) {
			// we will insert the smallest value between the arrays into merge.
			if (index2 >= nb2 || ( index1 < nb1 && t1[index1] < t2[index2] )) {
				merge[i] = t1[index1];
				index1++;
			} else {
				merge[i] = t2[index2];
				index2++;
			}
		}
		return merge;
	}
	
	/* 1.5 RECHERCHE DICHOTOMIQUE
	 */
	
	/*
	 * 1.5.2 ) 
	 * - Si e < T[m] : Les éléments de T[j] tels que m <= j < N sont 
	 * strictement supérieurs à e car le tableau T est trié.
	 * - T[M] a déjà été testé (noté dans paragraphe 1 : 
	 * "regarder si l'élément cherché est au milieu du tableau")
	 * les nouvelles bornes de cette moitié sont donc 
	 * inf(inchangé) et T[m-1] si m-1 != inf (sinon l'élément nest pas trouvé.)
	 * 
	 * 1.5.3 )
	 * - Si e > T[m] : Les éléments de T[i] tels que 0 <= i <= m sont
	 * strictement inférieurs à e car le tableau T est trié.
	 * - T[M] a déjà été testé (noté dans paragraphe 1 : 
	 * "regarder si l'élément cherché est au milieu du tableau")
	 * les nouvelles bornes de cette moitié sont donc 
	 * T[m+1] et sup(inchangé) si m+1 != inf (sinon l'élément nest pas trouvé.)
	 * 
	 */
	
	/**
	 * Recherche l'index de la valeur e dans les n premières valeurs de t
	 * @param e		: valeur recherchée
	 * @param t		: tableau trié
	 * @param n		: nombre de valeurs à traiter
	 * @pre 		: 0 <= n <= t.length
	 * @return
	 */
	public static int rechDichotomique(int e, int[] t, int n) {
		assert (0<=n && n<=t.length) : "Erreur: condition 0 <= n <= t.length non vérifiée.";
		// Bornes de recherche
		int inf = 0;
		int sup = n-1;
		int m = ( inf + sup ) / 2;
		// Tant que les bornes ne sont pas inversées
		while (inf <= sup) {
			if (t[m] == e) return m;
			if (e > t[m]) {
				inf = m+1;
			} else {
				sup = m-1;
			}
			m = ( inf + sup ) / 2;
 		}
		return -1;
	}
	
	public static void main(String [] args) {
		// DEBOGUAGE EXO 1
		int[] t = {9, 3, 5, 4, 1, 9, 11, 6};
		int[] out = TriDicho.triInsertion(t, t.length);
		TriDicho.afficherTableau(out, out.length);
		// DEBOGUAGE EXO 2
		int[] t1 = {1, 3, 5, 7};
		int[] t2 = {0, 2, 4};
		out = TriDicho.interclasser(t1, t2, 2, t2.length);
		TriDicho.afficherTableau(out, out.length);
	}
	
}
