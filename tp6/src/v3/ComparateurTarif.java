package v3;

import java.util.Comparator;

public class ComparateurTarif extends AComparator {
		
	public ComparateurTarif(boolean inverse) {
		super(inverse);
	}

	@Override
	int doCompare(IVehicule a, IVehicule b) {
		if (!reversed) return (int) (a.calculerTarif() - b.calculerTarif());
		else return (int) (b.calculerTarif() - a.calculerTarif());
	}

}
