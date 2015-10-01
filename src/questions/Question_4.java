package questions;

import net.sourceforge.jswarm_pso.FitnessFunction;
import particle_swarm_optimisation.GriewankFitnessFunction;
import particle_swarm_optimisation.ParticleSwarmOptimisation;
import particle_swarm_optimisation.RosenbrockFitnessFunction;

public class Question_4 {

	/**
	 * In this question, your task is to use particle swarm optimisation (PSO) to search for the
	 * minimum of the following two functions, where D is the number of variables, i.e. x1, x2, ..., xD.
	 * Function 1: Rosenbrock’s function
	 * Function 2: Griewanks’s function
	 *
	 * For D = 20, do the following:
	 * • Choose appropriate values for c1, c2, w, and population size,
	 * • Determine the fitness function, particle encoding, topology type, and stopping criterion in PSO
	 * • Since PSO is a stochastic process, repeat the experiments 30 times, and report the mean
	 * and standard deviation of your results, i.e. f1(x) or f2(x) values
	 * • Using the same settings in PSO, compare the performance of PSO on the Griewanks’s
	 * function when D = 20 and D = 50
	 * • Analysis your results, and draw your conclusions
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		ParticleSwarmOptimisation pso = new ParticleSwarmOptimisation();

		// Fitness functions
		FitnessFunction rosenbrock = new RosenbrockFitnessFunction();
		FitnessFunction griewank = new GriewankFitnessFunction();

		pso.calculate(rosenbrock);
	}

}
