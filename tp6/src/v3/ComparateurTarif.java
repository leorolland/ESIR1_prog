package v3;

import java.util.Comparator;
import java.util.Map;

public class ComparateurTarif extends AComparator {
	
	private Map<String, Tarif> tarif;
	
	public ComparateurTarif(boolean inverse, Map<String, Tarif> tarif) {
		super(inverse);
		this.tarif = tarif;
	}

	@Override
	int doCompare(IVehicule a, IVehicule b) {
		double tarifA = tarif.get(a.getImmatriculation()).calculerTarif(a);
		double tarifB = tarif.get(b.getImmatriculation()).calculerTarif(b);
		if (!reversed) return (int) (tarifA - tarifB);
		else return (int) (tarifB - tarifA);
	}

}
