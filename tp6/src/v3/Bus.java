package v3;

public class Bus extends Vehicule {
	
	static final int TARIF_PASSAGER = 15;
	
	
	public Bus(int pers, String immat, int longueur) {
		super(longueur, pers, immat);
	}

	@Override
	public float calculerTarif() {
		return 200 + 50 * getLongueur() + TARIF_PASSAGER * getPassagers();
	}
	
	public String toString() {
		return "Bus - " + getPassagers() + " passagers - " + getLongueur() + "m " + calculerTarif() + "€ immat: " + getImmatriculation(); 
	}
	
	public Bus clone() {
		return new Bus(getPassagers(), getImmatriculation(), getLongueur());
	}
	
	@Override
	public int compareTo(IVehicule arg0) {
		return this.getLongueur() - arg0.getLongueur();
	} 

}
