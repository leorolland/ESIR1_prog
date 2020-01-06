package v1;

public class Auto implements IVehicule {
	
	static final int LONGUEUR = 2;
	static final int TARIF_PASSAGER = 15;
	
	private int passagers;
	private String immatriculation;
	private boolean isToutTerrain;
	
	public Auto(int pers, String immat, boolean toutTerrain) {
		passagers = pers;
		immatriculation = immat;
		isToutTerrain = toutTerrain;
	}

	@Override
	public int getLongueur() {
		return LONGUEUR;
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
		return (isToutTerrain?350:100) + TARIF_PASSAGER * passagers;
	}
	
	public String toString() {
		return "Auto - " + passagers + " passagers - " + getLongueur() + "m " + calculerTarif() + "€"; 
	}

}
