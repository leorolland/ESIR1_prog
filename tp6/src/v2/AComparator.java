package v2;

import java.util.Comparator;

public abstract class AComparator implements Comparator<IVehicule> {

	protected boolean reversed;
	
	public AComparator(boolean reveresed) {
		this.reversed = reversed;
	}
	
	public int compare(IVehicule v1, IVehicule v2) {
		return doCompare(v1, v2);
	}
	
	abstract int doCompare(IVehicule v1, IVehicule v2);
	
}
