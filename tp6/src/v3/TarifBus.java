package v3;

public final class TarifBus extends Tarif {
	
	static TarifBus instance = null;
		
	protected TarifBus() {
		super(Tarif.TARIF_FIXE_BUS, Tarif.TARIF_PASSAGER, Tarif.TARIF_VARIABLE_BUS);
	}

	@Override
	float calculerTarif(IVehicule vehicle) {
		return getFixe() + getVariable() * vehicle.getLongueur() + getParPassagers() * vehicle.getPassagers();
	}

	static TarifBus createSingleton() {
		return instance = new TarifBus();
	}
	
	static TarifBus getInstance() {
		return instance;
	}
	
}
