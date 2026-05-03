package MustufNotesPlayground;

import java.io.FileWriter;
import java.io.IOException;

public class writeFileMethod {
  public static void main(String[] args) {
    String outputFileName = "MustufNotesPlayground/output.txt";
    String[] longWords = {
        "Here's",
        "second",
        "longer",
        "exceptional.",
        "should",
        "limits",
        "string",
        "processing",
        "capabilities.",
        "Another",
        "regular",
        "random",
        "words."
    };

    writeFile(outputFileName, longWords);
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
}
