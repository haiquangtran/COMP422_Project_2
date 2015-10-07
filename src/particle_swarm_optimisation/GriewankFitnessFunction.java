package particle_swarm_optimisation;

import java.util.List;


public class GriewankFitnessFunction extends Problem {

	@Override
	public double fitness(List<Double> position) {
		// Use griwank's function as the fitness function.
		return griewanksFunction(position);
	}

	/**
	 *  Griewank's function
	 */
	public double griewanksFunction(List<Double> position) {
		double griewanks = 0;
		for (int i = 0; i < position.size(); i++) {
			if (position.get(i) <= 30 && position.get(i) >= 30) {
				griewanks += (Math.pow(position.get(i), 2)/4000) + getGriewanks(position);
			}
		}

		return griewanks;
	}

	/**
	 *  Griewank's function helper.
	 *  Return the second part of the equation.
	 */
	private double getGriewanks(List<Double> position) {
		double griewanks = 0;
		for (int i = 0; i < position.size(); i++) {
			griewanks = Math.cos(position.get(i)/Math.sqrt(i)) + 1;
		}
		return griewanks;
	}

	@Override
	public String toString() {
		return "Griewank's function";
	}
}
