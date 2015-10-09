package particle_swarm_optimisation;

import java.util.List;

public class RosenbrockFitnessFunction extends Problem {

	public RosenbrockFitnessFunction() {
		setMinimization(true);
		setMinDomain(-30);
		setMaxDomain(30);
	}

	@Override
	public double fitness(List<Double> position) {
		// Use rosenbrock's function as the fitness function.
		return rosenbrocksFunction(position);
	}

	/**
	 * Rosenbrock's function
	 *
	 */
	public double rosenbrocksFunction(List<Double> position) {
		double rosenbrocks = 0;

		for (int i = 0; i < position.size()-1; i++) {
			if (position.get(i) <= 30 && position.get(i) >= -30) {
				rosenbrocks += 100 * Math.pow((Math.pow(position.get(i), 2)-(position.get(i+1))), 2) + Math.pow((position.get(i)-1), 2);
			}
		}

		return rosenbrocks;
	}

	@Override
	public String toString() {
		return "Rosenbrock's function";
	}

}
