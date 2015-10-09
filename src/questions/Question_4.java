package questions;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import net.sourceforge.jswarm_pso.FitnessFunction;
import particle_swarm_optimisation.BasicVelocityClamp;
import particle_swarm_optimisation.GriewankFitnessFunction;
import particle_swarm_optimisation.NewMath;
import particle_swarm_optimisation.Particle;
import particle_swarm_optimisation.Problem;
import particle_swarm_optimisation.RingTopology;
import particle_swarm_optimisation.RosenbrockFitnessFunction;
import particle_swarm_optimisation.StarTopology;
import particle_swarm_optimisation.Swarm;
import util.FileLoader;

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
		// Fitness functions
		Problem rosenbrock = new RosenbrockFitnessFunction();
		Problem griewank = new GriewankFitnessFunction();

		// Minimize
		rosenbrock.setMinimization(true);
		griewank.setMinimization(true);

		int number_of_particles = 50;
		int number_of_iterations = 1000;
		int number_of_runs = 30;
		int d = 50;
		double c1 = 1.49618;
		double c2 = 1.49618;
		double w = 0.7298;

		Swarm s = new Swarm();

		s.setProblem(griewank);
		s.setTopology(new StarTopology());
		// s.setTopology(new RingTopology(4));

		s.setVelocityClamp(new BasicVelocityClamp());

		for (int i = 0; i < number_of_particles; ++i) {
			Particle p = new Particle();
			p.setSize(d);
			p.setC1(c1);
			p.setC2(c2);

			p.setInertia(w);
			s.addParticle(p);
		}

		/**
		 *  // start operate PSO
		 */
		/** Save the results
		 */
		double Fitness_Runs_Iterations[][] = new double[number_of_runs][number_of_iterations]; // get bestfitness in each iterate in each run
		double AverageFitnessRuns[] = new double[number_of_iterations]; // average best fitness in each iterate in all runs
		double BestFitnessRuns[] = new double[number_of_runs];    // final get best fitness in each run
		/** start PSO
		 */
		for (int r = 0; r < number_of_runs; ++r) {

			s.initialize();
			for (int i = 0; i < number_of_iterations; ++i) {
				s.iterate();
				// System.out.println("BEST=" + s.getParticle(11).getPersonalFitness());
				// System.out.println("Average=" + NewMath.Averageitera(s));

				/**
				 *  // get bestfitness in every iterate for different topology except star
				 */
				Particle best_particle = null;
				double best_fitness = s.getProblem().getWorstFitness();
				for (int j = 0; j < s.numberOfParticles(); ++j) {
					if (s.getProblem().isBetter(s.getParticle(j).getPersonalFitness(), best_fitness)) {
						best_particle = s.getParticle(j);
						best_fitness = best_particle.getPersonalFitness();
					}
				}
				Fitness_Runs_Iterations[r][i] = best_fitness;
				// BestFitnessIterations[i] = best_fitness;
				/**
				 *  // get bestfitness in each iterate
				 */
			}//end all iterations

			BestFitnessRuns[r] = Fitness_Runs_Iterations[r][number_of_iterations - 1];
			/**
			 *  // get best fitness in each run
			 */
		}//end all runs


		// get best fitness from final best fitness in all runs
		double Bestbest_fitness = s.getProblem().getWorstFitness();
		int bestrun = 0;
		for (int r = 0; r < number_of_runs; ++r) {
			if (s.getProblem().isBetter(BestFitnessRuns[r], Bestbest_fitness)) {
				bestrun = r;
				Bestbest_fitness = BestFitnessRuns[bestrun];
				// best_particle = s.getParticle(j);
			}
		}
		System.out.println("Best Run: " + bestrun);
		System.out.println("Best result of " + number_of_runs + " runs is: " + Bestbest_fitness);
		System.out.println("Average of best results of " + number_of_runs + " runs is: " + NewMath.Best_Mean_STD(BestFitnessRuns)[0]);
		System.out.println("Standard Deviation of best results of " + number_of_runs + " runs is: " + NewMath.Best_Mean_STD(BestFitnessRuns)[1]);

		AverageFitnessRuns = NewMath.AverageRunIterations(Fitness_Runs_Iterations);  // average best fitness in each iterate in all runs

		/** Output the results to tex
		 */
		// Print all the best fitness in all the runs to BestfitnessRuns.txt
		// Print all the best fitness in all the runs to Plot.txt, which are used to plot the averagefitness--iter figure
		try {
			// File path=new File("F:/test");
			File myFilePath = new File(FileLoader.getFilePath("BestfitnessRuns.txt"));
			File myFilePath2 = new File(FileLoader.getFilePath("Plot.txt"));

			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			if (!myFilePath2.exists()) {
				myFilePath2.createNewFile();
			}

			FileWriter pfile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(pfile);

			FileWriter pfile2 = new FileWriter(myFilePath2);
			PrintWriter myFile2 = new PrintWriter(pfile2);

			for (int r = 0; r < number_of_runs; ++r) {
				myFile.println(BestFitnessRuns[r]);
			}
			pfile.close();
			for (int i = 0; i < number_of_iterations; ++i) {
				myFile2.println(AverageFitnessRuns[i]);
				// System.out.println(AverageFitnessRuns[i]);
			}
			pfile2.close();

		} catch (Exception e) {
			System.out.println("eorr");
		}
	}
}
