package tableau;

import types.Tableau;

/**
* TableauBlock<T> 
* L'utilisation de la l'implémentation Tableau2x<T> nous semble la plus adaptée
* car elle implémente la même interface que TableauBlock, ainsi certaines méthodes sont déjà 
* implémentées et ne nécessitent pas de modification. De plus, vue que Tableau2x double sa
* taille au fur et à mesure, elle est optimal dans ce cas.
*/
public class TableauBlock<T> implements Tableau<T> {
	
	private Tableau<Tableau<T>> tab;
	private int capaciteBlocks;
	
	public TableauBlock(int capaciteBlocks, int capaciteTableau) {
		assert capaciteTableau>0 : "Capacite blocks négative ou nulle";
		assert capaciteTableau>0 : "Capacite tableau négative ou nulle";
		this.capaciteBlocks = capaciteBlocks;
		tab = new Tableau2x<Tableau<T>>(capaciteTableau);
		ajouterBlock();
	}
	
	public TableauBlock(int capaciteTableau) {
		assert capaciteTableau>0 : "Capacite négative ou nulle";
		this.capaciteBlocks = 128;
		tab = new Tableau2x<Tableau<T>>(capaciteTableau);
		ajouterBlock();
	}
	
	/**
	 * Ajoute un block de données dans le tableau tab
	 */
	private void ajouterBlock() {
		tab.push_back(new Block<T>(capaciteBlocks));
	}

	private Tableau<T> lastBlock() {
		return tab.get(tab.size()-1);
	}
	
	@Override
	public int size() {
		if ( tab.size() != 0) {
			return (tab.size()-1) * capaciteBlocks + lastBlock().size();			
		}
		return 0;
	}

	@Override
	public boolean empty() {
		return size() == 0;
	}

	@Override
	public boolean full() {
		return false;
	}

	/*
	 * Raisonnement pour trouver un élément d'indice i :
	 * - Trouver dans quel Block chercher en calculant le quotient de la division de I par la capacité des blocks.
	 * - Trouver l'indice de l'élément dans le Block d'indice calculé précedemment, en calculant le reste de cette division.
	 */
	
	@Override
	public T get(int i) {
		assert i<size() : "Index supérieur ou égal à la taille";
		int quotient = i/capaciteBlocks;
		int reste = i%capaciteBlocks;
		return tab.get(quotient).get(reste);
	}

	@Override
	public void set(int i, T v) {
		assert i<size() : "Index supérieur ou égal à la taille";
		int quotient = i/capaciteBlocks;
		int reste = i%capaciteBlocks;
		tab.get(quotient).set(reste, v);
	}

	@Override
	public void push_back(T x) {
		if (lastBlock().full()) ajouterBlock();
		lastBlock().push_back(x);
	}

	@Override
	public void pop_back() {
		assert !empty(): "Le tableau est vide.";
		lastBlock().pop_back();
		if (lastBlock().empty()) tab.pop_back(); // On supprime le dernier block.
	}

}
