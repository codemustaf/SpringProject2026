package MustufNotesPlayground;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class deleteFileMethods {
  public static void main(String[] args) {
    String copyFileName = "MustufNotesPlayground/output_copy.txt";
    deleteFile(copyFileName);
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
