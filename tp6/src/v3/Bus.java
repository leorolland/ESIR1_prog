package v3;

public class Bus extends Vehicule {
	
	static final int TARIF_PASSAGER = 15;
	
	
	public Bus(int pers, String immat, int longueur) {
		super(longueur, pers, immat);
	}
	
	public String toString() {
		return "Bus - " + getPassagers() + " passagers - " + getLongueur() + "m, immat: " + getImmatriculation(); 
	}
	
	public Bus clone() {
		return new Bus(getPassagers(), getImmatriculation(), getLongueur());
	}
	
	@Override
	public int compareTo(IVehicule arg0) {
		return this.getLongueur() - arg0.getLongueur();
	} 

}
