package questions;

import genetic_programming.SymbolicRegression;
import genetic_programming.SymbolicRegressionProblem;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;

import util.FileLoader;

public class Question_3 {

	/**
	 * In this question, your task is to build a genetic programming system to automatically evolve
	 * a number of genetic programs for the following extended regression problem:
	 * f(x) = ( 1 x + sin x when x > 0 and 2x + x2 + 3.0 , x < 0 )
	 * Consider and describe the terminal set, the function set, the fitness function, the fitness cases,
	 * necessary parameters, and the evolutionary termination criteria. Report the best three genetic
	 * programs evolved and the corresponding performance, and draw your conclusions
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GPConfiguration regressionConfig = new GPConfiguration();
			SymbolicRegressionProblem regressionProblem = new SymbolicRegressionProblem(regressionConfig);
			String regressionPath = FileLoader.getFilePath("xor.conf");
			String[] mainArgs = {regressionPath};

			try {
				regressionProblem.main(mainArgs);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
