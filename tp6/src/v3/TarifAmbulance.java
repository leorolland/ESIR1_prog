package v3;

public class TarifAmbulance extends Tarif {
	
	static TarifAmbulance instance = null;
	
	protected TarifAmbulance() {
		super(Tarif.TARIF_FIXE_AMBULANCE, 0, 0);
	}

	@Override
	float calculerTarif(IVehicule vehicle) {
		return getFixe();
	}

	static TarifAmbulance createSingleton() {
		return instance = new TarifAmbulance();
	}
	
	static TarifAmbulance getInstance() {
		return instance;
	}
}
