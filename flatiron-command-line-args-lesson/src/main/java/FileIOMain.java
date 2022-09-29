import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileIOMain {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please specify one file path as a command-line argument.");
        } else {
            String content = "";
            File file = new File(args[0]);
            try (Scanner reader = new Scanner(file)) {
                while (reader.hasNextLine()) {
                    content = content.concat(reader.nextLine());
                    content = content.concat("\n");
                }

                System.out.println(content);

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}