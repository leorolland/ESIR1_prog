package tableau;

import types.Array;
import types.Tableau;


/**
 * Tableau2x<T> 
 * L'utilisation de la l'implémentation Block<T> nous semble la plus adaptée
 * car elle implémente la même interface que Tableau2x, ainsi certaines méthodes sont déjà 
 * implémentées et ne nécessitent pas de modification.
 */
public class Tableau2x<T> implements Tableau<T> {

	private Tableau<T> tab;
	private int capacite;
	
	public Tableau2x(int capacite) {
		assert capacite>0 : "ERREUR : Capacité trop petite.";
		tab = new Block<T>(capacite); 
		this.capacite = capacite;
	}
	
	@Override
	public int size() {
		return tab.size();
	}

	@Override
	public boolean empty() {
		return tab.empty();
	}

	@Override
	public boolean full() {
		return false;
	}
	
	private boolean blockFull() {
		return tab.full();
	}

	@Override
	public T get(int i) {
		assert (i>=0 && i<size()) : "ERREUR : Indice hors tableau.";
		return tab.get(i);
	}

	@Override
	public void set(int i, T v) {
		assert (i>=0 && i<size()) : "ERREUR : Indice hors tableau.";
		tab.set(i, v);
	}
	
	private void doublerTaille() {
		capacite *= 2; // doublement de la capacite
		Block<T> newTab = new Block<T>(capacite);
		// L'implémenation Block utilise un Array<T> comme structure interne, elle est optimisée au parcours par indice. (temps d'accès constant)
		for (int i=0 ; i<tab.size() ; ++i) {
			newTab.push_back(tab.get(i));
		}
		tab = newTab;
	}

	@Override
	public void push_back(T x) {
		if (blockFull()) doublerTaille();
		tab.push_back(x);
	}

	@Override
	public void pop_back() {
		assert !empty() : "ERREUR : Tableau vide.";
		tab.pop_back();
	}

}
