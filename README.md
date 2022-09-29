# Command-Line Arguments

## Learning Goals

- Introduce passing arguments through the command-line.

## Introduction

Let's step away from File IO for a minute to talk about a feature of Java we
have been ignoring, but might find helpful for working with files: command-line
arguments.

Consider the `main()` method we have been using to execute our Java programs:

```java
class Example {
    public static void main(String[] args) {
        // Write some code here
    }
}
```

Remember a couple modules back when we were learning about methods and discussed
the parameter list of the `main()` method. The `main()` method takes in a
`String` array or arguments. Currently, we haven't been making use of this
array at all... until now that is!

## How to Pass Command-Line Arguments into Java

**Command-line arguments** are just what they sound like - arguments that are
passed into a program through the command line! In Java, we list out the
arguments after we run the `java <classname>` command. Let's look at an example:

```java
public class Example {

    public static void main(String[] args) {
       for (String arg : args) {
           System.out.println(arg);
       }
    }
}
```

Now let's compile the program and run it via the command-line:

```plaintext
javac Example.java
java Example one two three
```

The output of the code above would be:

```plaintext
one
two
three
```

We just successfully passed in 3 arguments to our program via the command-line!
Notice that each of the arguments, "one", "two", and "three", are separated by
a space when entered on the command line and then processed into Java as array
elements.

Now that we know how to pass in command-line arguments, let's show a more
useful example where we actually use the command-line arguments. Perhaps we
want our program to take in some integer values so we can multiply them:

```java
public class Example {

    public static void main(String[] args) {
       int[] numbers = new int[args.length];
       for (int index = 0; index < args.length; index++) {
           numbers[index] = Integer.parseInt(args[index]);
       }

       int product = 1;
       for (int number: numbers) {
           product = product * number;
       }

       System.out.println("The product is: " + product);

    }
}
```

If we run the program above like this:

```plaintext
java Example 1 2 3
```

The output of the program will be:

```plaintext
The product is: 6
```

As we can see in the program above, we take the arguments and parse them into
`int` values that we can multiply. We then iterate through those integers to
come up with a product. So when we pass in the arguments.

Now let's modify this program a little to add some exception handling. For
example, the program would throw a `NumberFormatException` if we were to pass in
arguments like "one", "two", and "three". We also need to handle the case when
the user does not enter in any command-line arguments:

```java
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
```

Now if we neglect to provide command-line arguments for our example code:

```plaintext
java Example
```

We would get this as our output:

```plaintext
No command-line arguments were entered.
```

If we fail to enter an integer in for the command-line arguments:

```plaintext
java Example one two three
```

The program will now handle this error gracefully:

```plaintext
Did not enter in an integer argument.
For input string: "one"
```

## Command-Line Arguments in IntelliJ

This is all great! But what if we are running our program using the IDE IntelliJ
instead of the command-line?

Next to the green play button is an "Edit Run/Debug configurations" dropdown.
Go ahead and click on it and then click "Edit Configurations...":

![edit-configurations](https://curriculum-content.s3.amazonaws.com/java-mod-3/command-line-arguments/edit-configurations.png)

This will bring up the "Edit Run/Debug configurations" dialog box.

![run-debug-configurations-dialog](https://curriculum-content.s3.amazonaws.com/java-mod-3/command-line-arguments/run-debug-configurations-dialog.png)

On the left-hand side, select the correct application to edit. Then enter in
the command-line arguments in the "Program arguments" box:

![program-arguments](https://curriculum-content.s3.amazonaws.com/java-mod-3/command-line-arguments/program-arguments.png)

At the bottom of the dialog box, click the "Apply" button and then the "OK"
button. This will save the run configuration. Now go ahead and run the program!
This should now run the program with the arguments entered.

## Passing Files as Command-Line Arguments

Let's circle back around to File IO now and show how we can even pass in a file
as a command-line argument! Consider the `FileIOMain` class from the previous
lesson:

```java
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileIOMain {
    public static void main(String[] args) {
        String content = "";
        File file = new File("simple.txt");
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
```

Let's modify this to take in a command-line argument where we specify the file!
We'll re-use the `simple.txt` file too for our program to read in.

```java
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileIOMain {
    public static void main(String[] args) {
        // Ensure that an argument was passed in
        if (args.length != 1) {
            System.out.println("Please specify one file path as a command-line argument.");
        } else {
            String content = "";
            // We only care about the first argument, so we can use the syntax `args[0]`
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
```

Now that we have modified the program above to take in a command-line argument,
let's see if it can still read the file we pass in! If running in IntelliJ,
edit the "Run/Debug configurations" and type in the file path under the "Program
Arguments" box.

![pass-in-file](https://curriculum-content.s3.amazonaws.com/java-mod-3/command-line-arguments/pass-in-file-via-args.png)

In this example, we'll go ahead and pass in another relative path of the file.
We could enter an absolute path here as well though! When entering a relative
path, make sure that the path is relative to where the program is being run!

Now we'll go ahead and run it:

```plaintext
Leslie
Ron
Ann

```
