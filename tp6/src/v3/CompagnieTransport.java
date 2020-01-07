package v3;

import java.util.Comparator;
import java.util.Random;

public class CompagnieTransport {

	public static void main(String[] args) {
		
		Ferry ferry = new Ferry(500, 300);
		
		while (!ferry.estPlein()) {
			IVehicule v;
			Random r = new Random();
			char c = (char)(r.nextInt(26) + 'a');
			String immat = String.valueOf(c);
			if (Math.random() < 0.5) {
				v = new Auto(1+(int)(Math.random()*10), immat, Math.random() < 0.5);
			} else {
				v = new Bus(1+(int)(Math.random()*50), immat, 5+(int)(Math.random()*20));
			}
			ferry.ajouter(v);
		}
		
		
		Comparator<IVehicule> compLongueur = new Comparator<IVehicule>() {
			public int compare(IVehicule o1, IVehicule o2) {
				return (int) (o2.getLongueur() - o1.getLongueur());
			}
		};
		
		// longueure croissante, tarif décroissants, immatriculation croissante
		Comparator<IVehicule> compImmat = (IVehicule v1, IVehicule v2) -> v1.getImmatriculation().compareTo(v2.getImmatriculation());
		
		ferry.trierCompMultiple(compLongueur, new ComparateurMultiple(new ComparateurTarif(true), compImmat));
		
		System.out.println(ferry.toString());
	}

}
