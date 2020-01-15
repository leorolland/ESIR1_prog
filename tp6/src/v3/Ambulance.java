package v3;

public class Ambulance extends Auto {

	public Ambulance(int pers, String immat) {
		super(pers, immat, false);
	}

	public String toString() {
		return "Ambulance - " + getPassagers() + " passagers - " + getLongueur() + "m, immat : " + getImmatriculation(); 
	}
	
	@Override
	public Auto clone() {
		return new Ambulance(getPassagers(), getImmatriculation());
	}
	
}
