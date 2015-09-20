package questions;

import java.io.File;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;

import util.FileLoader;
import genetic_programming.SymbolicRegression;

public class Question_1 {


	/**
	 *
	 * Question 1:
	 *
	 * For the xor problem with the four following patterns:
	 * Input 1 Input 2 Desired Output
	 * 1 1 0
	 * 1 0 1
	 * 0 1 1
	 * 0 0 0
	 *
	 * Use GP techniques for this task. Consider the terminal set and function set, design
	 * a fitness function, determine parameters and evolutionary process termination criteria,
	 * and report three best genetic programs that perfectly perform this task.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GPConfiguration xorConfig = new GPConfiguration();
			SymbolicRegression xorProblem = new SymbolicRegression(xorConfig);
			String xorPath = FileLoader.getFilePath("xor.conf");
			String[] mainArgs = {xorPath};

			try {
				xorProblem.main(mainArgs);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
