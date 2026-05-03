package MustufNotesPlayground;

import java.util.Arrays;

public class processLinesMethod {
  public static void main(String[] args) {
    String[] lines = {
        "This is the first line of the file.",
        "Here's the second line with more text.",
        "The third line has some longer words like exceptional.",
        "Short line.",
        "A very long line that should test the limits of string processing capabilities.",
        "Another regular line with some random words.",
        "End of the file."
    };
    String[] longWords = processLines(lines);
    System.out.println(Arrays.toString(longWords));
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
}
