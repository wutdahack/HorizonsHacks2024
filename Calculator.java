import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {

    public static void main(String args[]) {

        /*
            TODO: move all this to a method in basic operations class
            so that the user can choose to perform a different action
            such as performing formulae and stuff like that
         */

        BasicOperations.Operations curOperation = null;
        ArrayList<Double> numsList = new ArrayList<Double>();
        boolean enteredNums = false;
        double result = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("What operation would you like to use? (+/add, -/subtract */multiply, ÷/divide)");
            String answer = scanner.nextLine().toLowerCase();
            switch (answer) {
                case "+", "add", "addition" -> curOperation = BasicOperations.Operations.ADD;
                case "-", "subtract", "subtraction", "minus" -> curOperation = BasicOperations.Operations.SUBTRACT;
                case "*", "multiply", "multiplication", "times" -> curOperation = BasicOperations.Operations.MULTIPLY;
                case "÷", "/", "divide", "division" -> curOperation = BasicOperations.Operations.DIVIDE;
                default -> System.out.println("That's not a valid option, please try again.");
            }
        } while (curOperation == null);

        do {
            System.out.println("Please enter a number.");
            double num = scanner.nextDouble();
            scanner.nextLine();
            numsList.add(num);
            if (numsList.size() >= 2) {
                boolean doneEntering = false;
                while (!doneEntering) {
                    System.out.println("Are you done entering numbers? (yes/no)");
                    String ans = scanner.nextLine().toLowerCase();
                    switch (ans) {
                        case "y", "yes" -> {
                            doneEntering = true;
                            enteredNums = true;
                        }
                        case "n", "no" -> doneEntering = true;
                        default -> System.out.println("That's not a valid option, please try again.");
                    }
                }
            }

        } while (!enteredNums);

        scanner.close();

        Double[] numArray = numsList.toArray(new Double[0]);

        switch (curOperation) {
            case ADD -> result = BasicOperations.add(numArray);
            case SUBTRACT -> result = BasicOperations.subtract(numArray);
            case MULTIPLY -> result = BasicOperations.multiply(numArray);
            case DIVIDE -> result = BasicOperations.divide(numArray);
        }

        System.out.println("Your result is: " + result);

    }


    private static class BasicOperations {
        public static double multiply(Double[] nums) {
            double result = nums[0];
            for (int i = 1; i < nums.length; i++) {
                result *= nums[i];
            }
            return result;
        }

        public static double divide(Double[] nums) {
            double result = nums[0];
            for (int i = 1; i < nums.length; i++) {
                result /= nums[i];
            }
            return result;
        }

        public static double add(Double[] nums) {
            double result = nums[0];
            for (int i = 1; i < nums.length; i++) {
                result += nums[i];
            }
            return result;
        }

        public static double subtract(Double[] nums) {
            double result = nums[0];
            for (int i = 1; i < nums.length; i++) {
                result -= nums[i];
            }
            return result;
        }

        public enum Operations {
            ADD,
            SUBTRACT,
            MULTIPLY,
            DIVIDE
        }
    }

}