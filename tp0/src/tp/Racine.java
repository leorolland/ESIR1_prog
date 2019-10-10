package tp;

public class Racine {

	/**
	 * Calculer une valeur approchée de la racine carrée d'un nombre
	 * @param r : réel dont on veut calculer la racine carrée
	 * @param epsilon : précision de calcul
	 * @pre r >= 0
	 * @pre epsilon > 0
	 * @return un réel a valeur approchée à epsilon près de sqrt(r)
	 * @post a>=0 et |a²-r|<epsilon
	 */
	static double racineCarree(double r, double epsilon) {
		assert r>=0 : "*** Précondition non vérifiée : r >= 0";
		assert epsilon >0 : "*** Précondition non vérifiée : epsilon > 0";
		double racine = r;
		double ecart;
		do {
			racine = (racine + r/racine) / 2;
			ecart = (racine * racine) - r;
		} while (Math.abs(ecart) > epsilon);
		return racine;
	}	
	
}
