package tableau;

import types.Array;
import types.Tableau;

public class Block<T> implements Tableau<T> {

	private Array<T> tab;
	private int taille = 0;
	
	public Block(int capacite) {
		assert capacite>0 : "ERREUR : Capacité trop petite.";
		tab = new Array<T>(capacite);
	}
	
	@Override
	public T get(int i) {
		assert (i>=0 && i<size()) : "ERREUR : Indice hors tableau.";
		return tab.get(i);
	}
	
	@Override
	public void set(int i, T val) {
		assert (i>=0 && i<size()) : "ERREUR : Indice hors tableau.";
		tab.set(i, val);
	}

	@Override
	public int size() {
		return taille;
	}

	@Override
	public boolean empty() {
		return taille==0;
	}

	@Override
	public boolean full() {
		return taille==tab.length();
	}

	@Override
	public void push_back(T x) {
		assert !full() : "ERREUR : Tableau plein.";
		tab.set(taille++, x);
	}

	@Override
	public void pop_back() {
		assert !empty() : "ERREUR : Tableau vide.";
		--taille;
	}
	
}
