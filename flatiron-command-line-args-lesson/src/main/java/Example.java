public class Example {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No command-line arguments were entered.");
        } else {
            int[] numbers = new int[args.length];
            try {

                for (int index = 0; index < args.length; index++) {
                    numbers[index] = Integer.parseInt(args[index]);
                }

                int product = 1;
                for (int number: numbers) {
                    product = product * number;
                }

                System.out.println("The product is: " + product);

            } catch(NumberFormatException exception) {
                System.err.println("Did not enter in an integer argument.");
                System.err.println(exception.getMessage());
            }
        }
    }
}
