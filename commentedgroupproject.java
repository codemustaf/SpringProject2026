import java.io.BufferedReader; // Used to efficiently read text from a file
import java.io.FileReader;     // Opens a file for reading
import java.io.FileWriter;     // Writes data to a file
import java.io.IOException;    // Checked exception for file operations
import java.nio.file.Files;    // Utility class for file operations (copy, delete)
import java.nio.file.Paths;    // Helps define file paths
import java.nio.file.StandardCopyOption; // Options for copying files
import java.util.Arrays;       // Utility for array operations

public class commentedgroupproject {

    public static void main(String[] args) {
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
    }

    public static String[] readFile(String fileName) {
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
        // Array to store words longer than 5 characters
        String[] longWords = new String[100];
        int index = 0;

        // Loop through each line
        for (String line : lines) {
            if (line != null) { // Avoid null pointer issues

                // Split line into words based on spaces
                String[] words = line.split(" ");

                for (String word : words) {
                    // Check if word length > 5
                    if (word.length() > 5) {

                        // Prevent array overflow
                        if (index >= longWords.length) {
                            break;
                        }

                        longWords[index++] = word; // Store word
                    }
                }
            }
        }

        // Trim array to actual number of words stored
        return Arrays.copyOf(longWords, index);
    }

    public static void writeFile(String fileName, String[] content) {
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
            // Open file in append mode
            writer = new FileWriter(fileName, true);
            writer.write(content + "\n");

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
            // Copy file, replacing if it already exists
            Files.copy(Paths.get(sourceFileName), Paths.get(destinationFileName),
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(String fileName) {
        try {
            // Delete file if it exists
            Files.deleteIfExists(Paths.get(fileName));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
