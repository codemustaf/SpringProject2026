package MustufNotesPlayground;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class makeFileCopyMethods {
  public static void main(String[] args) {
    String outputFileName = "MustufNotesPlayground/output.txt";
    String copyFileName = "MustufNotesPlayground/output_copy.txt";

    makeFileCopy(outputFileName, copyFileName);
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
}
