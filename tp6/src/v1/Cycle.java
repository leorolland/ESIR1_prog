package v1;

public class Cycle implements IVehicule{

	static final int LONGUEUR = 1;
	static final int PASSAGERS = 1;
	static final int TARIF_PASSAGER = 15;
	
	private String immatriculation;
	
	@Override
	public int getLongueur() {
		return LONGUEUR;
	}

	@Override
	public int getPassagers() {
		return PASSAGERS;
	}

	@Override
	public String getImmatriculation() {
		return immatriculation;
	}

	@Override
	public float calculerTarif() {
		return 20 + TARIF_PASSAGER * PASSAGERS;
	}
	
	public String toString() {
		return "Auto - " + PASSAGERS + " passagers - " + getLongueur() + "m " + calculerTarif() + "€ immat: " + getImmatriculation(); 
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
