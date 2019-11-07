package rationnel;

import types.Rationnel;
import util.Couple;

/**
 * Implémentation du rationnel en utilisant la nouvelle structure interne Couple
 * Seul les getters et setters ainsi que les constructeurs sont affectés puisque
 * les autres méthodes utilisent les getters/setters pour accéder aux données internes.
 */
public class RationnelCouple implements Rationnel {

	Couple<Integer, Integer> couple;
	
	/**
	   * initialiser un rationnel à partir d'un entier : nb/1
	   * @param nb : valeur du numérateur
	   */
	public RationnelCouple(Integer nb) {
		couple = new Couple<Integer, Integer>(nb, 1);
		System.out.println("RationnelCouple instancié");
	}
	
	/**
	   * initialiser un rationnel avec numerateur et dénominateur
	   * @param num : valeur du numérateur
	   * @param den : valeur du dénominateur
	   * @pre den != 0
	   * @post fraction irréductible et dénominateur > 0
	   */
	public RationnelCouple(int num, int den) {
		assert den != 0;
		int pgcd = pgcd(num, den);
		num = num/pgcd; 
		den = den/pgcd;
		couple = new Couple<Integer, Integer>(num, den);
		System.out.println("RationnelCouple instancié");
	}

	  /**
	   * initialiser un rationnel à partir d'un autre
	   * @param r : rationnel à dupliquer
	   */
	public RationnelCouple(Rationnel r) {
		couple = new Couple<Integer, Integer>(r.getNumerateur(), r.getDenominateur());
		System.out.println("RationnelCouple instancié");
	}

	@Override
	public boolean equals(Rationnel r) {
		return (r.getNumerateur() * this.getDenominateur()) == (r.getDenominateur() * this.getNumerateur());
	}
	
	/**
	 * Calcul du pgcd via l'algorithme d'euclide
	 * @param a premier nombre
	 * @param b second nombre
	 * @return pgcd(a, b)
	 */
	private int pgcd(int a, int b) {
		int t = 1;
		while (b!=0) {
			t = b;
			b = a%b;
			a = t;
		}
		return t;
	}

	@Override
	public Rationnel somme(Rationnel r) {
		int num = (r.getNumerateur() * this.getDenominateur()) + (r.getDenominateur() * this.getNumerateur());
		int denum = r.getDenominateur() * this.getDenominateur();
		// Fraction irréductible
		int pgcd = pgcd(num, denum);
		num /= pgcd;
		denum /= pgcd;
		if (denum < 0) {
			denum = -denum;
			num = -num;
		}
		return new RationnelSimple(num, denum);
	}

	@Override
	public Rationnel inverse() {
		assert this.getNumerateur() != 0;
		return new RationnelSimple(this.getDenominateur(), this.getNumerateur());
	}

	@Override
	public double valeur() {
		return ((double)this.getNumerateur())/((double)this.getDenominateur());
	}

	@Override
	public int getNumerateur() {
		return couple.getFirst();
	}

	@Override
	public int getDenominateur() {
		return couple.getSecond();
	}

	@Override
	public int compareTo(Rationnel autre) {
		if ((autre.getDenominateur() < 0) ^ (this.getDenominateur() < 0)) {
			return (autre.getNumerateur() * this.getDenominateur()) - (autre.getDenominateur() * this.getNumerateur());
		} else {
			return (this.getNumerateur() * autre.getDenominateur()) - (this.getDenominateur() * autre.getNumerateur());
		}
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.getNumerateur()) + "/" + String.valueOf(this.getDenominateur()); 
	}

}
