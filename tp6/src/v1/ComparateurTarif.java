package v1;

import java.util.Comparator;

public class ComparateurTarif implements Comparator<IVehicule> {
	
	private boolean inverse;
	
	public ComparateurTarif(boolean inverse) {
		this.inverse = inverse;
	}

	@Override
	public int compare(IVehicule a, IVehicule b) {
		if (!inverse) return (int) (a.calculerTarif() - b.calculerTarif());
		else return (int) (b.calculerTarif() - a.calculerTarif());
	}

}
