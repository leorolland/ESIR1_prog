package v3;

public class TarifCycle extends Tarif {

	static TarifCycle instance = null;
	
	protected TarifCycle() {
		super(Tarif.TARIF_FIXE_CYCLE, 0, 0);
	}

	@Override
	float calculerTarif(IVehicule vehicle) {
		return getFixe();
	}

	static TarifCycle createSingleton() {
		return instance = new TarifCycle();
	}
	
	static TarifCycle getInstance() {
		return instance;
	}
	
}
