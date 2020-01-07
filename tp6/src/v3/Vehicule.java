package v3;

public abstract class Vehicule implements IVehicule {
	
	private int longueur;
	private int passagers;
	private String immatriculation;
	
	public Vehicule(int l, int p, String i) {
		longueur = l; passagers = p; immatriculation = i;
	}

	public int getLongueur() {
		return longueur;
	}
	
	public int getPassagers() {
		return passagers;
	}

	@Override
	public String getImmatriculation() {
		return immatriculation;
	}

	public abstract float calculerTarif();

}
