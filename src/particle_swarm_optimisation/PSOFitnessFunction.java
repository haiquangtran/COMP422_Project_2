package particle_swarm_optimisation;

import net.sourceforge.jswarm_pso.FitnessFunction;

public class PSOFitnessFunction extends FitnessFunction {

	public PSOFitnessFunction() {
		super(false);
	}

	public double evaluate(double position[]) {
		double x1 = position[0];
		double x2 = position[1];
		return 20.0 + (x1 * x1) + (x2 * x2) - 10.0 * (Math.cos(2 * Math.PI * x1) + Math.cos(2 * Math.PI * x2));
//		return position[0] + position[1];
	}

}
