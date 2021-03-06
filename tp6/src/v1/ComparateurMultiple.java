package v1;

import java.util.Comparator;

public class ComparateurMultiple implements Comparator<IVehicule> {

	private Comparator<IVehicule> c1;
	private Comparator<IVehicule> c2;
	
	public ComparateurMultiple(Comparator<IVehicule> c1, Comparator<IVehicule> c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
 	@Override
	public int compare(IVehicule o1, IVehicule o2) {
		int diff = c1.compare(o1, o2);
		if (diff == 0) return c2.compare(o1, o2);
		return diff;
	}

}
