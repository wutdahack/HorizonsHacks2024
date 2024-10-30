import java.text.DecimalFormat;
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
            System.out.println("What operation would you like to use? (+/add, -/subtract */multiply, ((/)/divide), nth root/exponentiation, percentage)");
            String answer = scanner.nextLine().toLowerCase();
            switch (answer) {
                case "+":
                case "add":
                case "addition":
                    curOperation = BasicOperations.Operations.ADD;
                    break;
                case "-":
                case "subtract":
                case "subtraction":
                case "minus":
                    curOperation = BasicOperations.Operations.SUBTRACT;
                    break;
                case "*":
                case "multiply":
                case "multiplication":
                case "times":
                    curOperation = BasicOperations.Operations.MULTIPLY;
                    break;
                case "÷":
                case "/":
                case "divide":
                case "division":
                    curOperation = BasicOperations.Operations.DIVIDE;
                    break;
                case "nth root":
                case "root":
                case "sqrt":
                case "sqrt()":
                case "cbrt":
                case "cbrt()":
                case "exponent":
                case "^":
                case "exponentiation":
                    curOperation = BasicOperations.Operations.ROOTEXP;
                    break;
                case "%":
                case "percentage":
                    curOperation = BasicOperations.Operations.PERCENTAGE;
                    break;
                default:
                    System.out.println("That's not a valid option, please try again.");
                    break;
            }
        } while (curOperation == null);

        if (curOperation != BasicOperations.Operations.ROOTEXP && curOperation != BasicOperations.Operations.PERCENTAGE) {
            do {
                if (numsList.isEmpty()) {
                    System.out.println("Please enter a number.");
                } else {
                    System.out.println("Please enter another number.");
                }
                double num;
                while(!scanner.hasNextDouble()) {
                    System.out.println("That isn't a number, please enter a number.");
                    scanner.next();
                }
                num = scanner.nextDouble();
                scanner.nextLine();
                if (curOperation == BasicOperations.Operations.DIVIDE && num == 0) {
                    System.out.println("You cannot divide by 0.");
                } else {
                    numsList.add(num);
                }
                if (numsList.size() >= 2) {
                    boolean doneEntering = false;
                    while (!doneEntering) {
                        System.out.println("Are you done entering numbers? (yes/no)");
                        String ans = scanner.nextLine().toLowerCase();
                        switch (ans) {
                            case "y":
                            case "yes":
                                doneEntering = true;
                                enteredNums = true;
                                break;
                            case "n":
                            case "no":
                                doneEntering = true;
                                break;
                            default:
                                System.out.println("That's not a valid option, please try again.");
                                break;
                        }
                    }
                }

            } while (!enteredNums);
        } else if (curOperation == BasicOperations.Operations.ROOTEXP) {
            do {
                boolean enteredBase = false;
                do {
                    System.out.println("Please enter the base number.");
                    double num;
                    while(!scanner.hasNextDouble()) {
                        System.out.println("That isn't a number, please enter the base number.");
                        scanner.next();
                    }
                    num = scanner.nextDouble();
                    scanner.nextLine();
                    if (num >= 0) {
                        numsList.add(num);
                        enteredBase = true;
                    } else {
                        System.out.println("You cannot find the root of a negative number, please try again.");
                    }
                } while (!enteredBase);
                boolean enteredExponent = false;
                do {
                    System.out.println("Please enter the exponent. (Enter 1 if performing nth root)");
                    double num;
                    while(!scanner.hasNextDouble()) {
                        System.out.println("That isn't a number, please enter the exponent.");
                        scanner.next();
                    }
                    num = scanner.nextDouble();
                    scanner.nextLine();
                    numsList.add(num);
                    enteredExponent = true;
                    enteredNums = true;
                    
                } while (!enteredExponent);
                boolean enteredRoot = false;
                do {
                    System.out.println("Please enter the root. (Enter 1 if performing exponentiation)");
                    double num;
                    while(!scanner.hasNextDouble()){
                        System.out.println("That isn't a number, please enter the root.");
                        scanner.next();
                    }
                    num = scanner.nextDouble();
                    scanner.nextLine();
                    if (num != 0) {
                        numsList.add(num);
                        enteredRoot = true;
                    } else {
                        System.out.println("Your root cannot be 0, please try again.");
                    }
                } while (!enteredRoot);
            } while (!enteredNums);
        } else if (curOperation == BasicOperations.Operations.PERCENTAGE) {
            do {
                System.out.println("Please enter the base number you want to find a percentage of.");
                double num;
                while(!scanner.hasNextDouble()) {
                    System.out.println("That isn't a number, please enter the base number.");
                    scanner.next();
                }
                num = scanner.nextDouble();
                scanner.nextLine();
                numsList.add(num);
                System.out.println("Please enter the percentage you want to find.");
                while(!scanner.hasNextDouble()) {
                    System.out.println("That isn't a number, please enter the base number.");
                    scanner.next();
                }
                num = scanner.nextDouble();
                scanner.nextLine();
                numsList.add(num);
                enteredNums = true;
            } while (!enteredNums);
        }

        scanner.close();

        Double[] numArray = numsList.toArray(new Double[0]);

        switch (curOperation) {
            case ADD:
                result = BasicOperations.add(numArray);
                break;
            case SUBTRACT:
                result = BasicOperations.subtract(numArray);
                break;
            case MULTIPLY:
                result = BasicOperations.multiply(numArray);
                break;
            case DIVIDE:
                result = BasicOperations.divide(numArray);
                break;
            case ROOTEXP:
                result = BasicOperations.rootexp(numArray);
                break;
            case PERCENTAGE:
                result = BasicOperations.percentage(numArray);
                break;
        }

        // formatting the number so it only shows up to 3 decimal places
        DecimalFormat df = new DecimalFormat("#.###");
        result = Double.parseDouble(df.format(result));

        System.out.println("Your result is: " + result + " (up to 3 decimal places)");

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

        public static double rootexp(Double[] nums) {
            double base = nums[0];
            double exponent = nums[1];
            double root = nums[2];
            return Math.pow(base, exponent / root);
        }

        public static double percentage(Double[] nums) {
            double base = nums[0];
            double multiplier = nums [1] / 100;
            return multiplier * base;
        }

        public enum Operations {
            ADD,
            SUBTRACT,
            MULTIPLY,
            DIVIDE,
            ROOTEXP,
            PERCENTAGE
        }
    }

}