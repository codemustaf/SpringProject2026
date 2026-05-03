
/**
 * This code was assisted by an AI developed by Microsoft. (2024).
 * 
 * References:
 * Microsoft Copilot. (2024). 
 * Generated code assistance. Retrieved from https://www.microsoft.com
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class ExceptionsProcessorWithCheckedExceptionHandling {
    public static void main(String[] args) {
        String inputFileName = "example.txt";
        String outputFileName = "output.txt";
        String copyFileName = "output_copy.txt";

        String[] lines = readFile(inputFileName);
        String[] longWords = processLines(lines);
        writeFile(outputFileName, longWords);
        makeFileCopy(outputFileName, copyFileName);
        appendToFile(copyFileName, "Additional line to append");
        deleteFile(copyFileName); // Delete the copy of the output file

    }

    public static String[] readFile(String fileName) {
        String[] lines = new String[100]; // Assuming a max of 100 lines for simplicity
        int index = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null && index < lines.length) {
                lines[index++] = line;
            }
        } catch (IOException e) {
            e.printStackTrace(); // Specific exception handling
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lines;
    }

    public static String[] processLines(String[] lines) {
        String[] longWords = new String[100]; // Assuming a max of 100 long words for simplicity
        int index = 0;
        for (String line : lines) {
            if (line != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (word.length() > 5) {
                        if (index >= longWords.length) {
                            break; // Simplification for potential exception
                        }
                        longWords[index++] = word;
                    }
                }
            }
        }
        return Arrays.copyOf(longWords, index); // Trim array to actual size
    }

    public static void writeFile(String fileName, String[] content) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, false); // Ensure the file is created fresh each time
            for (String line : content) {
                if (line != null) {
                    writer.write(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Specific exception handling
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

    public static void appendToFile(String fileName, String content) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, true); // Append mode
            writer.write(content + "\n");
        } catch (IOException e) {
            e.printStackTrace(); // Specific exception handling
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
            Files.copy(Paths.get(sourceFileName), Paths.get(destinationFileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace(); // Specific exception handling
        }
    }

    public static void deleteFile(String fileName) {
        try {
            Files.deleteIfExists(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace(); // Specific exception handling
        }
    }
}
