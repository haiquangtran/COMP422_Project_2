package particle_swarm_optimisation;

import java.util.List;


public class GriewankFitnessFunction extends Problem {

	@Override
	public double fitness(List<Double> position) {
		//		double sum1 = 0;
		//        double sum2 = 0;
		//        double fitness = 0;
		//        for (int i = 0; i < position.size(); ++i) {
		//            sum1 += position.get(i) * position.get(i);
		//            sum2 += Math.cos(2 * Math.PI * position.get(i));
		//        }
		//        //m_dFitness
		//        fitness = -20 * Math.exp(-0.2 * Math.sqrt((1.0 / position.size()) * sum1)) - Math.exp((1.0 / position.size()) * sum2) + 20 + Math.E;
		//
		//        return fitness;
		// TODO Auto-generated method stub
		return 0;
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
