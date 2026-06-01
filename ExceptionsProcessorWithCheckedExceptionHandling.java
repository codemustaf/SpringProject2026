
/**
 * This code was assisted by an AI developed by Microsoft. (2024).
 * 
 * References:
 * Microsoft Copilot. (2024). 
 * Generated code assistance. Retrieved from https://www.microsoft.com
 */

import java.io.BufferedReader; // Used to efficiently read text from a file
import java.io.FileReader; // Opens a file for reading
import java.io.FileWriter; // Writes data to a file
import java.io.IOException; // Checked exception for file operations
import java.nio.file.Files; // Utility class for file operations (copy, delete)
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths; // Helps define file paths
import java.nio.file.StandardCopyOption; // Options for copying files
import java.util.Arrays; // Utility for array operations

public class ExceptionsProcessorWithCheckedExceptionHandling {
    public static void main(String[] args) {
        try {
            // Define file names
            String inputFileName = "example.txt";
            String outputFileName = "output.txt";
            String copyFileName = "output_copy.txt";

            // Step 1: Read file contents into an array
            String[] lines = readFile(inputFileName);

            // Step 2: Process lines to extract words longer than 5 characters
            String[] longWords = processLines(lines);

            // Step 3: Write results to output file
            writeFile(outputFileName, longWords);

            // Step 4: Make a copy of the output file
            makeFileCopy(outputFileName, copyFileName);

            // Step 5: Append an extra line to the copied file
            appendToFile(copyFileName, "Additional line to append");

            // Step 6: Delete the copied file
            deleteFile(copyFileName);

        } catch (InvalidPathException e) {
            printProgramError(e);
        } catch (IllegalArgumentException e) {
            printProgramError(e.getMessage());
        } catch (SecurityException e) {
            printProgramError("File access denied by Java security settings: " + e.getMessage());
        }
    }

    public static String[] readFile(String fileName) {
        validateTextFilePath(fileName, "Input file");

        // Create array to store lines (fixed size for simplicity)
        String[] lines = new String[100];
        int index = 0;

        BufferedReader reader = null;

        try {
            // Open file for reading
            reader = new BufferedReader(new FileReader(fileName));
            String line;

            // Read each line until file ends or array is full
            while ((line = reader.readLine()) != null && index < lines.length) {
                lines[index++] = line; // Store line and increment index
            }

        } catch (IOException e) {
            // Handles file-related errors (checked exception)
            e.printStackTrace();

        } finally {
            // Always runs: ensures file is closed
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return lines; // Return array (may contain nulls)
    }

    public static String[] processLines(String[] lines) {
        try {
            // ? Guard clause: reject null argument before it causes NullPointerException
            if (lines == null) {
                throw new IllegalArgumentException("Lines array cannot be null.");
            }

            // Array to store words longer than 5 characters
            String[] longWords = new String[100];
            int index = 0;

            // ADDED: Becomes true once the array reaches capacity to prevent multiple
            // warnings
            boolean arrayWarning = false;

            // Loop through each line
            for (String line : lines) {
                if (line != null) { // Avoid null pointer issues

                    // Split line into words based on spaces
                    String[] words = line.split(" ");

                    for (String word : words) {
                        // Check if word length > 5
                        if (word.length() > 5) {

                            // Prevent array overflow
                            // ADDED: Warning when data is lost
                            if (index >= longWords.length) {
                                if (arrayWarning == false) {
                                    System.out.println(
                                            "WARNING: Maximum long word storage reached. All further long words will be skipped.");
                                    arrayWarning = true;
                                }
                                break;
                            }

                            longWords[index++] = word; // Store word
                        }
                    }
                }
            }

            // ADDED: IllegalStateException
            // Activates if there are no long words found. This won't break the program, but
            // the purpose of the program won't be satisfied
            if (index == 0) {
                throw new IllegalStateException("WARNING: No long words were found in the input file.");
            }

            // Trim array to actual number of words stored
            return Arrays.copyOf(longWords, index);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid argument in processLines: " + e.getMessage());
            return new String[0];
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            return new String[0];
        }
    }

    public static void writeFile(String fileName, String[] content) {
        validateTextFilePath(fileName, "Output file");

        FileWriter writer = null;

        try {
            // Create or overwrite file
            writer = new FileWriter(fileName, false);

            for (String line : content) {
                if (line != null) {
                    writer.write(line + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            // Ensure file is closed
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void appendToFile(String fileName, String content) {
        FileWriter writer = null;

        try {
            validateTextFilePath(fileName, "Copy file");

            // Open file in append mode
            writer = new FileWriter(fileName, true);
            writer.write(content + "\n");

        } catch (InvalidPathException e) {
            printWarning("Append skipped because the copy file path is invalid: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            printWarning("Append skipped because of an invalid argument: " + e.getMessage());
        } catch (SecurityException e) {
            printWarning("Append skipped because file access was denied: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void makeFileCopy(String sourceFileName, String destinationFileName) {
        try {
            Path sourcePath = validateTextFilePath(sourceFileName, "Source file");
            Path destinationPath = validateTextFilePath(destinationFileName, "Copy file");

            // Copy file, replacing if it already exists
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

        } catch (InvalidPathException e) {
            printWarning("Copy skipped because the source or destination path is invalid: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            printWarning("Copy skipped because of an invalid argument: " + e.getMessage());
        } catch (SecurityException e) {
            printWarning("Copy skipped because file access was denied: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(String fileName) {
        try {
            Path path = validateTextFilePath(fileName, "Copy file");

            // Delete file if it exists
            Files.deleteIfExists(path);

        } catch (InvalidPathException e) {
            printWarning("Delete skipped because the copy file path is invalid: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            printWarning("Delete skipped because of an invalid argument: " + e.getMessage());
        } catch (SecurityException e) {
            printWarning("Delete skipped because file access was denied: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * printProgramError(message) - takes error message from thrown unchecked
     * exception and prints it alongside the message
     * "Program ended before completing file processing." This method is intended to
     * be used in the catch block
     * 
     * @param message message to be printed
     */
    public static void printProgramError(String message) {
        System.out.println("ERROR: " + message);
        System.out.println("Program ended before completing file processing.");
    }

    /**
     * Method overload for InvalidPathException messsage
     * 
     * @param e
     */
    public static void printProgramError(InvalidPathException e) {
        System.out.println("ERROR: Invalid file path provided: " + e.getInput() + ". Reason: " + e.getReason());
        System.out.println("Program ended before completing file processing.");
    }

    /**
     * Prints warning
     * 
     * @param message
     */
    public static void printWarning(String message) {
        System.out.println("WARNING: " + message);
    }

    /**
     * validateTextFilePath(fileName, fileRole) - takes in the file name then checks
     * if it's null, empty, or does not end with ".txt"
     * If an exception is thrown, main catches it and prints an error message before
     * the program ends:
     * null: "File name cannot be null"
     * empty: "File name cannot be empty"
     * does not end with ".txt": "File must be a text (.txt) file. Current file:
     * [file name]"
     * 
     * @param fileName string fileName to be checked
     * @param fileRole string to designate file type for fileName
     * 
     * @throws IllegalArgumentException if fileName is null, empty, or is not a text
     *                                  file
     */
    public static Path validateTextFilePath(String fileName, String fileRole) {
        // if file is null
        if (fileName == null) {
            throw new IllegalArgumentException(fileRole + " name cannot be null.");
        }

        // if file name is empty
        if (fileName.trim().isEmpty()) {
            throw new IllegalArgumentException(fileRole + " name cannot be empty.");
        }

        // if file is not a text file
        if (!fileName.toLowerCase().endsWith(".txt")) {
            throw new IllegalArgumentException(fileRole + " must be a text (.txt) file. Current file: " + fileName);
        }

        // Throw InvalidPathException, if invalid path is given
        return Paths.get(fileName);
    }
}
