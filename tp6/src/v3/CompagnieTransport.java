package v3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CompagnieTransport {

	public static void main(String[] args) {
		
		// Instanciation des singletons
		TarifAmbulance.createSingleton();
		TarifAuto.createSingleton();
		TarifBus.createSingleton();
		TarifCycle.createSingleton();
		
		Map<String, Tarif> tarifs = new HashMap<String, Tarif>();
		
		Ferry ferry = new Ferry(500, 300, tarifs);
		
		while (!ferry.estPlein()) {
			IVehicule v;
			Random r = new Random();
			char c = (char)(r.nextInt(26) + 'a');
			String immat = String.valueOf(c);
			double seed = Math.random(); 
			if (seed < 0.25) {
				v = new Auto(1+(int)(Math.random()*10), immat, Math.random() < 0.5);
				tarifs.put(immat, TarifAuto.getInstance());
			} else if (seed < 0.5  ) {
				v = new Bus(1+(int)(Math.random()*50), immat, 5+(int)(Math.random()*20));
				tarifs.put(immat, TarifBus.getInstance());
			} else if (seed < 0.75) {
				v = new Ambulance(1+(int)(Math.random()*50), immat);
				tarifs.put(immat, TarifAmbulance.getInstance());
			} else {
				v = new Cycle();
				tarifs.put("", TarifCycle.getInstance());
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
		
		ferry.trierCompMultiple(compLongueur, new ComparateurMultiple(new ComparateurTarif(true, tarifs), compImmat));
		
		System.out.println(ferry.toString());
	}

}
