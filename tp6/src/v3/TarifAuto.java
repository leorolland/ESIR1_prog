package v3;

public class TarifAuto extends Tarif {

	static TarifAuto instance = null;
		
	protected TarifAuto() {
		super(Tarif.TARIF_FIXE_AUTO, Tarif.TARIF_PASSAGER, 0);
	}

	@Override
	float calculerTarif(IVehicule vehicle) {
		return (((Auto) vehicle).isToutTerrain()?getVariable():getFixe()) + getParPassagers() * vehicle.getPassagers();
	}

	static TarifAuto createSingleton() {
		return instance = new TarifAuto();
	}
	
	static TarifAuto getInstance() {
		return instance;
	}
	
}
