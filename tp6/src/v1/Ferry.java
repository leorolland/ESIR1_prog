package v1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Ferry {
	
	private int capa;
	private int capaPassa;
	private List<IVehicule> vehicules;
	private int used;
	private float tarifCumules;
	
	public Ferry(int kappa, int passa) {
		capa = kappa;
		capaPassa = passa;
		vehicules = new LinkedList<IVehicule>();
		used = 0;
		tarifCumules = 0F;
	}
	
	public boolean ajouter(IVehicule v) {
		if (v.getLongueur() + used <= capa) {
			used += v.getLongueur();
			vehicules.add(v);
			tarifCumules += v.calculerTarif();
			return true;
		}
		return false;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator it = vehicules.iterator();
		while (it.hasNext()) {
			sb.append(it.next().toString());
			sb.append('\n');
		}
		sb.append("tarif total : " + tarifCumules + "€");
		return sb.toString();
	}
	
}
