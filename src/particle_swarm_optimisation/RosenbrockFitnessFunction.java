package particle_swarm_optimisation;

import net.sourceforge.jswarm_pso.FitnessFunction;

public class RosenbrockFitnessFunction extends FitnessFunction {

	public RosenbrockFitnessFunction() {
		super(false);
	}

	/**
	 * Fitness function used to evaluate PSO
	 */
	public double evaluate(double dimension[]) {
		// Rosenbrock's function
		return rosenbrocksFunction(dimension);
	}

	/**
	 * Rosenbrock's function
	 *
	 */
	public double rosenbrocksFunction(double dimension[]) {
		double rosenbrocks = 0;
		//TODO: Check why D-1 (Check the loop)
		for (int i = 0; i < dimension.length; i++) {
			if (dimension[i] <= 30 && dimension[i] >= -30) {
				rosenbrocks += 100 * Math.pow((Math.pow(dimension[i], 2)-(dimension[i]+1)), 2) + Math.pow((dimension[i]-1), 2);
			}
		}

		return rosenbrocks;
	}

	@Override
	public String toString() {
		return "Rosenbrock's function";
	}

}
