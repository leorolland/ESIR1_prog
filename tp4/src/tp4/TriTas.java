package tp4;

public class TriTas {

	/**
	 * ajouter tnb[p] au tas formé par les p premiers éléments du tableau tnb
	 * 
	 * @param tnb : tableau dont les p premiers éléments forment un tas
	 * @param p   : indice de l'élément à ajouter au tas
	 * @pre 1<=p<=tnb.length
	 * @post les p+1 premiers éléments du tableau tnb forment un tas
	 */
	static void ajouterTas(int[] tnb, int p) {
		int ipere;
		if (p % 2 == 0 && p > 0) {
			ipere = (p / 2) - 1;
		} else {
			ipere = p / 2;
		}
		int passage = 0;
		int tmp;
		while (p > 0 && passage < 2 && tnb[ipere] < tnb[p]) {
			tmp = tnb[ipere];
			tnb[ipere] = tnb[p];
			tnb[p] = tmp;
			if (ipere == 1) {
				p = ipere;
				ipere = 0;
				passage++;
			} else {
				if ((ipere % 2) == 0) {
					p = ipere;
					ipere = (p / 2) - 1;
				} else {
					p = ipere;
					ipere = p / 2;
				}
			}
		}
	}

	/**
	 * supprimer l'élément maximum du tas et réorganiser le reste du tas
	 * 
	 * @param tnb : tableau dont les p premiers éléments forment un tas
	 * @param p   : nombre d'éléments dans le tas
	 * @pre 1<p<tnb.length
	 * @post : place de l'élément maximum en tnb[p-1]; les p-1 premiers éléments de
	 *       tnb formen un tas
	 */
	static void supprimerMax(int[] tnb, int p) {
		int tmp = tnb[p - 1];
		tnb[p - 1] = tnb[0];
		tnb[0] = tmp;
		int i = 0;
		boolean fin = false;
		int ifilsgauche = 1;
		int ifilsdroit = 2;
		while ((ifilsdroit < p - 1 || ifilsgauche < p - 1) && !fin
				&& (tnb[ifilsgauche] > tnb[i] || tnb[ifilsdroit] > tnb[i])) {
			if (tnb[ifilsgauche] > tnb[ifilsdroit] && tnb[ifilsgauche] > tnb[i] && ifilsgauche < p - 1) {
				tmp = tnb[ifilsgauche];
				tnb[ifilsgauche] = tnb[i];
				tnb[i] = tmp;
				replacefils(tnb,i);
				i = ifilsgauche;
			} else if (tnb[ifilsdroit] > tnb[i] && ifilsdroit < p - 1 ) {
				tmp = tnb[ifilsdroit];
				tnb[ifilsdroit] = tnb[i];
				tnb[i] = tmp;
				replacefils(tnb,i);
				i = ifilsdroit;
			} else {
				fin = true;
			}
			ifilsgauche = i * 2 + 1;
			ifilsdroit = i * 2 + 2;
		}
	}

	public static void replacefils(int[] tnb, int i) {
		int pere =  getindicepere(tnb, i);
		while(tnb[i] > tnb[pere]) {
			int tmp = tnb[i];
			tnb[i] = tnb[pere];
			tnb[pere] = tmp;
			pere =  getindicepere(tnb, i);
		}
	}
	
	public static int getindicefilsD(int[] tnb, int i) {
		return i * 2 + 2;
	}

	public static int getindicefilsG(int[] tnb, int i) {
		return i * 2 + 1;
	}
	
	public static int getindicepere(int[] tnb, int i) {
		int ipere;
		if (i % 2 == 0 && i > 0) {
			return (i / 2) - 1;
		} else {
			return i / 2;
		}
	}

	/**
	 * trier un tableau d'entiers en ordre croissant avec l'algorithme du tri par
	 * tas
	 * 
	 * @param tnb : tableau à trier
	 * @param nb  : nombre d'éléments dans le tableau
	 * @re 1<=nb<=tnb.lentgh
	 */
	static void trier(int[] tnb, int nb) {
		assert 1 <= nb && nb <= tnb.length : "Vous avez rentré un nb excédant les dimensions du tableau";
		for (int i = 0; i < nb; i++) {
			ajouterTas(tnb, i);
		}
		for (int i = nb; i > 0; i--) {
			supprimerMax(tnb, i);
		}
	}


}