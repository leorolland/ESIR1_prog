package tp5;

public class TriRapide {

	static int partager(int [] T, int binf, int bsup) {
		final int binfOrigin = binf;
		// Echanger le pivot avec le dernier
		int valPivot = T[binf++];
		while (binf <= bsup) {
			if (T[binf] > valPivot && T[bsup] <= valPivot) {
				// Cas ou les deux sont mal placés
				// On swap
				int tmp = T[binf];
				T[binf] = T[bsup];
				T[bsup] = tmp;
				// Maintenant les deux sont bien placés
				--bsup;++binf;
			}
			// Cas ou binf est est mal placé et bsup bien placé
			else if (T[binf] > valPivot) --bsup;
			// Cas ou bsup est mal placé et binf bien placé
			else if (T[bsup] <= valPivot) ++binf;
			// Cas ou les deux elts sont bien placés
			else {--bsup;++binf;}
		}
		// On place le pivot à bsup
		T[binfOrigin] = T[bsup];
		T[bsup] = valPivot;
		return bsup;
	}
	
	static void triRapide(int [] T, int binf, int bsup) {
		if (binf>=bsup) return;
		int pivot = partager(T, binf, bsup);
		triRapide(T, binf, pivot-1);
		triRapide(T, pivot+1, bsup);
	}
	
	public static void trier(int [] T, int nb) {
		triRapide(T, 0, nb-1);
	}
	
}
