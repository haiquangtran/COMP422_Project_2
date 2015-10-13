import questions.*;


public class MainClass {

	public static void main(String[] args) {
		if (args.length > 0) {
			String question = args[0];
			String fileName = null;

			if (args.length == 2) {
				fileName = args[1].trim();
			}

			if (question.equalsIgnoreCase("q1")) {
				Question_1 q1 = new Question_1();
			} else if (question.equalsIgnoreCase("q2") && fileName != null) {
				Question_2 q2 = new Question_2(fileName);
			} else if (question.equalsIgnoreCase("q3")) {
				Question_3 q3 = new Question_3();
			} else if (question.equalsIgnoreCase("q4") && fileName != null) {
				Question_4 q4 = new Question_4(fileName);
			} else if (question.equalsIgnoreCase("q5") && fileName != null) {
				Question_5 q5 = new Question_5(fileName);
			} else if (question.equalsIgnoreCase("q6") && fileName != null) {
				Question_6 q6 = new Question_6(fileName);
			} else {
				System.out.println("No Options Found.");
				printInstructions();
			}
		} else {
			printInstructions();
		}
	}

	private static void printInstructions() {
		System.out.println("Enter q[question number] and [option] according to the below available options:");
		System.out.println("Question 1 = q1");
		System.out.println("Question 2 = q2 [digits00 || digits05 || digits10 || digits15 || digits20 || digits30 || digits40 || digits50 || digits60]");
		System.out.println("Question 3 = q3");
		System.out.println("Question 4 = q4 [rosenbrock || griewank]");
		System.out.println("Question 5 = q5 [wine || balance]");
		System.out.println("Question 6 = q6 [wbcd || sonar]");
	}

}
