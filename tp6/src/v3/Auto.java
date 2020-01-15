package v3;

public class Auto extends Vehicule {

	static final int LONGUEUR = 2;
	static final int TARIF_PASSAGER = 15;
	
	private boolean isToutTerrain;
	
	public Auto(int pers, String immat, boolean toutTerrain) {
		super(LONGUEUR, pers, immat);
		isToutTerrain = toutTerrain;
	}
	
	public String toString() {
		return "Auto - " + getPassagers() + " passagers - " + getLongueur() + "m, immat : " + getImmatriculation(); 
	}
	
	@Override
	public Auto clone() {
		Auto a = new Auto(getPassagers(), getImmatriculation(), isToutTerrain);
		return a;
	}

	public int compareTo(IVehicule arg0) {
		return this.getLongueur() - arg0.getLongueur();
	}

	public boolean isToutTerrain() {
		return isToutTerrain;
	}


	public void setToutTerrain(boolean isToutTerrain) {
		this.isToutTerrain = isToutTerrain;
	}

}
