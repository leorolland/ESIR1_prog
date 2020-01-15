package v3;

public abstract class Tarif {
	
	static final float TARIF_PASSAGER = (float) (15 * 1.10);
	
	static final float TARIF_FIXE_AMBULANCE = 0;
	static final float TARIF_FIXE_AUTO = 100;
	static final float TARIF_FIXE_CYCLE = 20;
	
	static final float TARIF_VARIABLE_BUS = 50;
	static final float TARIF_VARIABLE_AUTO = 350 * 2; //doublement tarif tout terrain
	
	static final float TARIF_FIXE_BUS = 200;
	
	private float fixe;
	private float parPassagers;
	private float variable;

	public float getFixe() {
		return fixe;
	}

	public void setFixe(float fixe) {
		this.fixe = fixe;
	}

	public float getParPassagers() {
		return parPassagers;
	}

	public void setParPassagers(float parPassagers) {
		this.parPassagers = parPassagers;
	}

	public float getVariable() {
		return variable;
	}

	public void setVariable(float variable) {
		this.variable = variable;
	}

	/**
	 * @param fix Tarif fixe
	 * @param passagers Tarif par passagers
	 * @param variable Tarif variable
	 */
	protected Tarif(float fix, float passagers, float variable) {
		fixe = fix;
		parPassagers = passagers;
		this.variable = variable;
	}
	
	abstract float calculerTarif(IVehicule vehicle);
	
}
