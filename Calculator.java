import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalcFunctions runningFunction = null;
        do {
            System.out.println("Choose between these functions"); // idk what to say here tbh
            System.out.println("1. Basic Operations");
            System.out.println("2. Random Number Generation");


            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "basic operations":
                case "1":
                case "1. basic operations":
                    runningFunction = CalcFunctions.BASIC_OPERATIONS;
                    break;
                case "random number generation":
                case "rng":
                case "rngs":
                case "2":
                case "2. random number generation":
                    runningFunction = CalcFunctions.RNG;
                    break;
                default:
                    System.out.println("That isn't a valid option, please try again.");
                    break;
            }
        } while (runningFunction == null);

        switch (runningFunction) {
            case BASIC_OPERATIONS:
                BasicOperations.run();
                break;
            case RNG:
                RandomNumberGeneration.run();
                break;
        }
    }

    private enum CalcFunctions {
        BASIC_OPERATIONS,
        RNG
    }

    private static class BasicOperations {

        public static void run() {
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

    private static class RandomNumberGeneration {

        public static Random random = new Random();
       static int amountOfNumsGenerated;
        public static void run() {

            Scanner scanner = new Scanner(System.in);
            NumType numType = null;
            boolean enteredNums;
            ArrayList<Integer> intList = null;
            ArrayList<Double> doubleList = null;
            int[] intResults;
            double[] doubleResults;

            do {
                System.out.println("Would you like to generate integers or decimal numbers?");
                String ans = scanner.nextLine().toLowerCase();
                switch (ans) {
                    case "integers":
                    case "integer":
                    case "int":
                    case "whole number":
                    case "whole numbers":
                    case "normal number":
                    case "normal numbers":
                        numType = NumType.INTEGER;
                        break;
                    case "decimal":
                    case "decimals":
                    case "decimal number":
                    case "decimal numbers":
                    case "doubles":
                    case "double":
                    case "float":
                    case "floats":
                    case "floating point":
                    case "floating point number":
                    case "floating point numbers":
                    case "real":
                    case "reals":
                        numType = NumType.DECIMAL;
                        break;
                    default:
                        System.out.println("That is not a valid option, please try again.");
                        break;
                }
            } while (numType == null);

            do {
                System.out.println("How many numbers do you want to generate?");
                while (!scanner.hasNextInt()) {
                    System.out.println("That isn't a valid answer, please enter a whole number that's over 0.");
                    scanner.next();
                }
                amountOfNumsGenerated = scanner.nextInt();
                if (amountOfNumsGenerated < 0) {
                    amountOfNumsGenerated = 1;
                    System.out.println("1 random number will be generated");
                }
                scanner.nextLine();
                if (numType == NumType.INTEGER) {
                    System.out.println("What is the minimum value you want to be able to generate (inclusive)?");
                    while (!scanner.hasNextInt()) {
                        System.out.println("That isn't a valid answer, please enter a whole number/integer.");
                        scanner.next();
                    }
                    int minVal = scanner.nextInt();
                    intList = new ArrayList<Integer>();
                    intList.add(minVal);
                    System.out.println("What is the maximum value you want to be able to generate (exclusive)?");
                    while (!scanner.hasNextInt()) {
                        System.out.println("That isn't a valid answer, please enter a whole number/integer that is above the minimum value.");
                        scanner.next();
                    }
                    int maxVal = scanner.nextInt();
                    if (maxVal <= minVal) {
                        maxVal = minVal + 1;
                        System.out.println("The maximum value will be set to " + (minVal + 1));
                    }
                    intList.add(maxVal);
                    enteredNums = true;
                } else {
                    System.out.println("What is the minimum value you want to be able to generate (inclusive)?");
                    while (!scanner.hasNextDouble()) {
                        System.out.println("That isn't a valid answer, please enter a number.");
                        scanner.next();
                    }
                    double minVal = scanner.nextDouble();
                    doubleList = new ArrayList<Double>();
                    doubleList.add(minVal);
                    System.out.println("What is the maximum value you want to be able to generate (exclusive)?");
                    while (!scanner.hasNextDouble()) {
                        System.out.println("That isn't a valid answer, please enter a number that's above the minimum value.");
                        scanner.next();
                    }
                    double maxVal = scanner.nextDouble();
                    if (maxVal <= minVal) {
                        maxVal = minVal + 1;
                        System.out.println("The maximum value will be set to " + (minVal + 1));
                    }
                    doubleList.add(maxVal);
                    enteredNums = true;
                }
            } while (!enteredNums);

            scanner.close();

            switch (numType) {
                case INTEGER:
                    Integer[] inputIntArray = intList.toArray(new Integer[0]);
                    intResults = randomInt(inputIntArray).toArray();
                    if (intResults.length == 1) {
                        System.out.println("The number generated was " + intResults[0]);
                    } else {
                        System.out.println("The numbers generated were:");
                        for (int num : intResults) {
                            System.out.println(num);
                        }
                    }
                    break;
                case DECIMAL:
                    Double[] inputDoubleArray = doubleList.toArray(new Double[0]);
                    doubleResults = randomDouble(inputDoubleArray).toArray();
                    DecimalFormat df = new DecimalFormat("#.###");
                    if (doubleResults.length == 1) {
                        System.out.println("The number (to 3 decimal places) generated was " + df.format(doubleResults[0]));
                    } else {
                        System.out.println("The numbers (to 3 decimal places) generated were:");
                        for (double num : doubleResults) {
                            System.out.println(df.format(num));
                        }
                    }
                    break;
            }

        }

        public static DoubleStream randomDouble(Double[] nums) {
            double min = nums[0];
            double max = nums[1];
            return random.doubles(amountOfNumsGenerated, min, max);
        }

        public static IntStream randomInt(Integer[] nums) {
            int min = nums[0];
            int max = nums[1];
            return random.ints(amountOfNumsGenerated, min, max);
        }

        private enum NumType {
            INTEGER,
            DECIMAL
        }
    }

}