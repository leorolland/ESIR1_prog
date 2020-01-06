package tp5;

import types.ABinHuffman;
import types.Couple;
import types.ListeABH;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import outilsHuffman.OutilsHuffman;

/**
 * Réalisation du codage d'un texte par la méthode de Huffman
 */

public class CodageHuffman {
	public static void main(String[] args) {
		// ------------------------------------------------------------------------
		// 0. Saisir le nom du fichier à coder (À FAIRE)
		// ------------------------------------------------------------------------
		String nomFichier = "poemes.txt";

		// ------------------------------------------------------------------------
		// 1. Lire le texte (DONNÉ)
		// ------------------------------------------------------------------------
		char[] texte = OutilsHuffman.lireFichier(nomFichier);

		// ------------------------------------------------------------------------
		// 2. Calculer la table des fréquences des caractères (À FAIRE)
		// ------------------------------------------------------------------------
		final int[] tableFrequences = calculerFrequences(texte);

		// ------------------------------------------------------------------------
		// 3. Enregistrer la table de fréquences dans le fichier de sortie (DONNÉ)
		// ------------------------------------------------------------------------
		OutilsHuffman.enregistrerTableFrequences(tableFrequences, nomFichier + ".code");

		// ------------------------------------------------------------------------
		// 4. Construire l'arbre de codage de Huffman (DONNÉ - À FAIRE)
		// ------------------------------------------------------------------------
		ABinHuffman arbreCodageHuffman = construireArbreHuffman(tableFrequences);

		// ------------------------------------------------------------------------
		// Afficher l'arbre de codage de Huffman (DÉJÀ FAIT)
		// ------------------------------------------------------------------------
		System.out.println("Arbre de Huffman associé au texte " + nomFichier);
		DecodageHuffman.afficherHuffman(arbreCodageHuffman);

		// ------------------------------------------------------------------------
		// 5. Construire la table de codage associée (À FAIRE)
		// ------------------------------------------------------------------------
		String[] tablecodage = construireTableCodage(arbreCodageHuffman);

		// ------------------------------------------------------------------------
		// 5.1. afficher la table de codage (À FAIRE)
		// ------------------------------------------------------------------------
		System.out.println("Table de codage associée au texte " + nomFichier);
		afficherTableCodage(tablecodage);

		// ------------------------------------------------------------------------
		// 6. coder le texte avec l'arbre de Huffman (À FAIRE)
		// ------------------------------------------------------------------------
		StringBuilder texteCode = coderTexte(texte, tablecodage);

		// ------------------------------------------------------------------------
		// 7. enregistrer le texte codé (DONNÉ)
		// ------------------------------------------------------------------------
		OutilsHuffman.enregistrerTexteCode(texteCode, nomFichier + ".code");

		// ------------------------------------------------------------------------
		// xx. calculer et afficher les stats (À FAIRE)
		// ------------------------------------------------------------------------
		// calculer la taille du fichier non codé
		// calculer la taille du fichier codé

	}

	/**
	 * 2. calculer la fréquence d'apparition de chaque caractère
	 * 
	 * @param tcar tableau des caractères du texte
	 * @return tableau de fréquence des caractères, indicé par les caractères
	 */
	public static int[] calculerFrequences(char[] tcar) {
		int[] out = new int[256];
		for (int j = 0; j < tcar.length; j++) {
			++out[(int)tcar[j]];
		}
		return out;
	}

	/**
	 * 4. construire un arbre de codage de Huffman par sélection et combinaison des
	 * éléments minimaux
	 * 
	 * @param tableFrequences table des fréquences des caractères
	 * @return arbre de codage de Huffman
	 */
	public static ABinHuffman construireArbreHuffman(int[] tableFrequences) {
		// Création des arbres binaires initiaux
		ListeABH a = faireListeAbinHuffman(tableFrequences);
		// Tri de la liste en fonction de la fréquence, croissante
		System.out.println("Arbres initiaux : ");
		System.out.println(a.toString());
		// Construction de l'arbre de Huffman
		while (a.size() != 1) {
			ListIterator<ABinHuffman> al = a.listIterator();
			// fusion des deux premiers
			ABinHuffman premier = al.next(); al.remove();
			ABinHuffman second = al.next(); al.remove();
			int nouvFreq = premier.getValeur().deuxieme() + second.getValeur().deuxieme();
			Couple<Character, Integer> c = new Couple(".", nouvFreq);
			ABinHuffman fusion = OutilsHuffman.consArbre(c, premier, second);
			// insertion de l'arbre fusionné
			// Décalage de l'itérateur jusqu'à trouver une valeur supérieure
			while (al.hasNext() && nouvFreq >= al.next().getValeur().deuxieme()) {}
			// Le curseur est après une valeur supérieure, on recule et on insère
			if (al.hasNext() && al.hasPrevious()) al.previous();
			al.add(fusion);
		}
		return a.getFirst();
	}
	
	/**
	 * 4.1 Faire une liste triée dont chaque élément est un arbreBinaire<br>
	 * comprenant un unique sommet dont l'étiquette est un couple <caractère,
	 * fréquence>, trié par fréquence croissante
	 * 
	 * @param tableFrequences : table des fréquences des caractères
	 * @return la liste triée
	 */
	public static ListeABH faireListeAbinHuffman(int[] tableFrequences) {
		ListeABH arbresInitiaux = new ListeABH();
		// Recherche et insertion de la valeur de plus haute fréquence
		// et d'ordre alphabétique le plus élevé
		// Insertion de la droite vers la gauche
		int max = 0;
		int indiceMax = 0;
		do {
			max = 0;
			// Recherche du maximum donné en (indiceMax, max)
			for (int i = 0; i < tableFrequences.length; i++) {
				if (tableFrequences[i] >= max) {
					max = tableFrequences[i];
					indiceMax = i;
				}
			}
			if (max != 0) {
				// Insertion du nouvel arbre binaire
				Couple<Character, Integer> c = new Couple((char) indiceMax, tableFrequences[indiceMax]);
				ABinHuffman a = new ABinHuffman();
				a.setValeur(c);
				arbresInitiaux.addFirst(a);
				// Suppression de l'élément du tableau tableFrequences
				tableFrequences[indiceMax] = 0;
			}
			//System.out.println(max);
		} while(max != 0);
		return arbresInitiaux;
	}

	/**
	 * 5. construire la table de codage correspondant à l'arbre de Huffman
	 * 
	 * @param abinHuff : arbre de Huffman
	 * @return table de (dé)codage indicé par lex caractères
	 */
	public static String[] construireTableCodage(ABinHuffman abinHuff) {
		String[] output = new String[256];
		List<Couple<Character, String>> listeCouples = DecodageHuffman.getAssocChar(abinHuff);
		listeCouples.forEach(couple -> output[(int) couple.premier()] = couple.deuxieme());
		return output;
	}

	/**
	 * 5.1. Afficher la table de codage associée au texte
	 * 
	 * @param tablecodage : table de codage associée au texte
	 */
	public static void afficherTableCodage(String[] tablecodage) {
		for (int i = 0; i < tablecodage.length; i++) {
			if (tablecodage[i] != null) System.out.println("" + (char) i + " " + tablecodage[i]);
		}
	}

	/**
	 * 6. Coder un texte à l'aide de la table de codage
	 * 
	 * @param texte       à coder
	 * @param tablecodage : table de codage associée au texte
	 * @return texte codé
	 */
	public static StringBuilder coderTexte(char[] texte, String[] tablecodage) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < texte.length; i++) {
			sb.append(tablecodage[(int)texte[i]]);
		}
		return sb;
	}
}// CodageHuffman
