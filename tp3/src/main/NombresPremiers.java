package main;

import java.util.Random;
import java.util.Scanner;

import tableau.Block;
import tableau.Tableau2x;
import types.Tableau;

public class NombresPremiers {
	
	public static boolean estPremier(int nbr, Tableau<Integer> premiersNbr) {
		boolean fin = true;
		int i= 0;
		while(i < premiersNbr.size()) {
			if (nbr % premiersNbr.get(i) == 0) {
				return false;
			}
			i++;
		}
		return fin;
	}
	
	public static int calculerNombresPremiers(int nb, Tableau<Integer> premiersNb) {
		int i = 2;
		while (i < nb && !premiersNb.full()) {
			if(estPremier(i,premiersNb)) {
				premiersNb.push_back(i);
			}
			i++;
		}
		if(!premiersNb.full()) {
			return i;
		}
		return nb;
	}
	
	public static void main(String[] args) {		
//		Tableau<Integer> tab = new Block<Integer>(100);
		Tableau<Integer> tab = new Tableau2x<Integer>(100);
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrez la borne supérieure : ");
		int N = sc.nextInt();
		int dernierTeste = calculerNombresPremiers(N, tab);
		System.out.println("Dernier entier testé : " + dernierTeste);
		afficherTableau(tab);
		// 2e partie
		Tableau<Integer> randomTab = remplirHasard(dernierTeste);
		afficherTableau(randomTab);
	}
	
	public static <T> void afficherTableau(Tableau<T> t) {
		System.out.print("[");
		for (int i = 0; i < t.size(); i++) {
			System.out.print(t.get(i).toString() + ", ");			
		}
		System.out.println("]");
	}
	
	public static Tableau<Integer> remplirHasard(int nb) {
		Tableau<Integer> t = new Block<Integer>(nb);
		Random r = new Random();
		while (!t.full()) t.push_back(r.nextInt(nb));
		return t;
	}
	
	public static int eliminerPresents(Tableau<Integer> t1, Tableau<Integer> t2) {
		int elesuppr = 0;
		int i = 0;
		while (i < t1.size()) {
			int courant = t1.get(i);
			if(estPresent(courant,t2,t2.size())) {
				// L'élément courant est présent dans T2
				// On place inverse l'élément avec le dernier et on supprime le dernier.
				t1.set(i, t1.get(t1.size()-1));
				t1.set(t1.size()-1, courant);
				t1.pop_back();
				elesuppr++;
			} else {
				++i;
			}
		}
		return elesuppr;
	}
	
	public static int rechDichotomique(int e, Tableau<Integer> t, int n) {
		// Bornes de recherche
		int inf = 0;
		int sup = n-1;
		int m = ( inf + sup ) / 2;
		// Tant que les bornes ne sont pas inversées
		while (inf <= sup) {
			if (t.get(m) == e) return m;
			if (e > t.get(m)) {
				inf = m+1;
			} else {
				sup = m-1;
			}
			m = ( inf + sup ) / 2;
 		}
		return -1;
	}

	public static boolean estPresent(int x, Tableau<Integer> block, int length) {
		return rechDichotomique(x,block,block.size()) != -1;
	}
	
	
}
