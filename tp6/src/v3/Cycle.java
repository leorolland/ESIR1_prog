package v3;

public class Cycle extends Vehicule {

	static final int LONGUEUR = 1;
	static final int PASSAGERS = 1;
	static final int TARIF_PASSAGER = 15;
	
	
	public Cycle() {
		super(LONGUEUR, PASSAGERS, "");
	}
	
	public String toString() {
		return "Auto - " + PASSAGERS + " passagers - " + getLongueur() + "m immat : " + getImmatriculation(); 
	}
	
	@Override
	public Cycle clone() {
		Cycle a = new Cycle();
		return a;
	}

	@Override
	public int compareTo(IVehicule arg0) {
		return this.getLongueur() - arg0.getLongueur();
	} 

}
