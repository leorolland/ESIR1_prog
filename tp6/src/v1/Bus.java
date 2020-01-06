package v1;

public class Bus implements IVehicule {
	
	static final int TARIF_PASSAGER = 15;
	
	private int longueur;
	private int passagers;
	private String immatriculation;
	
	public Bus(int pers, String immat, int longueur) {
		passagers = pers;
		immatriculation = immat;
		this.longueur = longueur;
	}

	@Override
	public int getLongueur() {
		return longueur;
	}

	@Override
	public int getPassagers() {
		return passagers;
	}

	@Override
	public String getImmatriculation() {
		return immatriculation;
	}

	@Override
	public float calculerTarif() {
		return 200 + 50 * longueur + TARIF_PASSAGER * passagers;
	}
	
	public String toString() {
		return "Bus - " + passagers + " passagers - " + getLongueur() + "m " + calculerTarif() + "€"; 
	}

}
