package MustufNotesPlayground;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Helps printing array
import java.util.Arrays;

public class readFileMethod {
  public static void main(String[] args) {
    String inputFileName = "MustufNotesPlayground/example.txt";
    String[] lines = readFile(inputFileName);
    System.out.println(Arrays.toString(lines));
  }

  public static String[] readFile(String fileName) {
    // Create array to store lines (fixed size for simplicity)
    /**
     * ! What if the file has more than 100 lines
     */
    String[] lines = new String[100];
    int index = 0;

    BufferedReader reader = null;

    try {
      // Open file for reading
      /**
       * ! Does the file exist?
       * ! Is it empty?
       * ! Is the file txt format or other format?
       */
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
}
