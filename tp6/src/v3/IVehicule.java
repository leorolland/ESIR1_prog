package v3;

public interface IVehicule extends Cloneable, Comparable<IVehicule> {

	public int getLongueur();
	public int getPassagers();
	public String getImmatriculation();
	public String toString();
	
}
