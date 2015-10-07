package particle_swarm_optimisation;

import net.sourceforge.jswarm_pso.FitnessFunction;

public class GriewankFitnessFunction extends FitnessFunction {

	public GriewankFitnessFunction() {
		super(false);
	}

	/**
	 * Fitness function used to evaluate PSO
	 */
	public double evaluate(double dimension[]) {
		// Griewank's function
		return griewanksFunction(dimension);
	}

	/**
	 *  Griewank's function
	 */
	public double griewanksFunction(double dimension[]) {
		double griewanks = 0;
		for (int i = 0; i < dimension.length; i++) {
			if (dimension[i] <= 30 && dimension[i] >= 30) {
				griewanks += (Math.pow(dimension[i], 2)/4000) + getGriewanks(dimension);
			}
		}

		return griewanks;
	}

	/**
	 *  Griewank's function helper.
	 *  Return the second part of the equation.
	 */
	private double getGriewanks(double dimension[]) {
		double griewanks = 0;
		for (int i = 0; i < dimension.length; i++) {
			griewanks = Math.cos(dimension[i]/Math.sqrt(i)) + 1;
		}
		return griewanks;
	}

	@Override
	public String toString() {
		return "Griewank's function";
	}
}
