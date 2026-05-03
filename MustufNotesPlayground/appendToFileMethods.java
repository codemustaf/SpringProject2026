package MustufNotesPlayground;

import java.io.FileWriter;
import java.io.IOException;

public class appendToFileMethods {
  public static void main(String[] args) {
    String outputFile = "MustufNotesPlayground/output.txt";
    appendToFile(outputFile, "Additional line to append");
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
}
