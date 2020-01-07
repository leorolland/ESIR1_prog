package v2;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Ferry {
	
	private int capa;
	private int capaPassa;
	private List<IVehicule> vehicules;
	private int used;
	private int usedPassa;
	private float tarifCumules;
	
	public Ferry(int kappa, int passa) {
		capa = kappa;
		capaPassa = passa;
		vehicules = new LinkedList<IVehicule>();
		used = 0;
		usedPassa = 0;
		tarifCumules = 0F;
	}
	
	public boolean ajouter(IVehicule v) {
		if (v.getLongueur() + used <= capa && v.getPassagers() + usedPassa <= capaPassa) {
			used += v.getLongueur();
			usedPassa += v.getPassagers();
			vehicules.add(v);
			tarifCumules += v.calculerTarif();
			return true;
		}
		return false;
	}
	
	public boolean estPlein() {
		return used>=capa || usedPassa>=capaPassa;
	}
	
	public void trier() {
		Collections.sort(vehicules);
	}
	
	public void trierParTarif(boolean inverse) {
		Collections.sort(vehicules, new ComparateurTarif(inverse));
	}
	
	public void trierParLongueur() {
		Comparator<IVehicule> c = new Comparator<IVehicule>() {
			public int compare(IVehicule o1, IVehicule o2) {
				return (int) (o2.getLongueur() - o1.getLongueur());
			}
		};
		Collections.sort(vehicules, c);
	}
	
	public void trierCompMultiple(Comparator<IVehicule> c1, Comparator<IVehicule> c2) {
		Collections.sort(vehicules, new ComparateurMultiple(c1, c2));
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<IVehicule> it = vehicules.iterator();
		while (it.hasNext()) {
			sb.append(it.next().toString());
			sb.append('\n');
		}
		sb.append("tarif total : " + tarifCumules + "€");
		return sb.toString();
	}
	
}
