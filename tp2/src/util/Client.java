package util;

import java.util.Scanner;

import rationnel.RationnelCouple;
import rationnel.RationnelSimple;
import types.Rationnel;

public class Client {

	/**
	 * Instancie un RationnelSimple a partir de données entrées au scanner
	 * 
	 * @param input Scanner d'entrée
	 * @return Rationnel
	 */
	static Rationnel lireRationnel(Scanner input) {
		System.out.println("Entrez un numérateur :");
		int numerateur = input.nextInt();
		System.out.println("Entrez un dénominateur :");
		int denominateur = input.nextInt();
		return makeRationnel(numerateur, denominateur);
	}

	static Rationnel makeRationnel(int num, int den) {
		assert den != 0;
		// Si num est pair
		if (num%2 == 0) {
			// on retourne une instance de RationnelSimple
			return new RationnelSimple(num, den);
		} else {
			// sinon une instance de RationnelCouple			
			return new RationnelCouple(num, den);
		}
	}

	static void afficher(Rationnel[] lesRationnels, int nb) {
		assert 0 <= nb && nb <= lesRationnels.length;
		for (int i = 0; i < nb; i++) {
			System.out.println(lesRationnels[i] + " " + lesRationnels[i].valeur());
		}
	}

	/**
	 * Insérer le rationnel nouveau dans le tableau lesRationnels
	 * @pre : tableau trié (ordre croissant)
	 * @pre : 0 <= nb < lesRationnels.length
	 * @param nouveau
	 * @param lesRationnels tableau trié de rationnels
	 * @param nb            nombre d'éléments dans le tableau avant ajout
	 */
	static void insererRationnels(Rationnel nouveau, Rationnel[] lesRationnels, int nb) {
		assert 0 <= nb && nb < lesRationnels.length;
		int index = nb-1;
		// tant que notre nombre est inférieur a la valeur précédente
		while (index >= 0 && nouveau.compareTo(lesRationnels[index]) < 0) {
			// on décale à droite la valeur précédente
			lesRationnels[index+1] = lesRationnels[index];
			--index;
		}
		lesRationnels[index+1] = nouveau;
	}

	/**
	 * Calcule la somme des nb premiers éléments d'un tableau de rationnels
	 * @pre 0 <= nb <= lesRationnels.length
	 * @param lesRationnels tableau de rationnels
	 * @param nb nombre d'éléments à prendre en compte dans la somme
	 * @return la somme correspondante
	 */
	static Rationnel sommeRationnels(Rationnel[] lesRationnels, int nb) {
		assert 0<=nb && nb<=lesRationnels.length;
		Rationnel sum = makeRationnel(0, 1);
		for (int i = 0; i < nb; i++) {
			sum = lesRationnels[i].somme(sum);
		}
		return sum;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Capacité du tableau : ");
		int capacite = scanner.nextInt();
		
		Rationnel[] lesRationnels = new Rationnel[capacite];
		for (int i = 0; i < lesRationnels.length; i++) {
			insererRationnels(lireRationnel(scanner), lesRationnels, i);
			afficher(lesRationnels, i + 1);
		}
		System.out.println("Somme des rationnels : " + sommeRationnels(lesRationnels, lesRationnels.length));

		
// PREMIERE VERSION
//		Rationnel precedent = new RationnelSimple(0);
//		Rationnel r = lireRationnel(scanner);
//		while (r.getNumerateur() != 0) {
//			System.out.println("Valeur du rationnel lu : " + r);
//			System.out.println("Somme avec précédent   : " + r.somme(precedent));
//			System.out.println("Inverse                : " + r.inverse());
//			System.out.println("Valeur réelle          : " + r.valeur());
//			double comparaison = r.compareTo(precedent);
//			if (comparaison > 0) {
//				System.out.println(r + " >" + precedent);
//			} else if (comparaison < 0) {
//				System.out.println(r + " < " + precedent);
//			} else {
//				System.out.println(r + " = " + precedent);
//			}
//			if (r.equals(precedent)) 
//				System.out.println("Le rationnel est égal au précédent (selon equals)");
//			// Stockage du rationnel actuel et demande du suivant
//			precedent = r;
//			r = lireRationnel(scanner);
//		} 
	}

}
