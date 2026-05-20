package Methods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class makeFileCopyMethods {
  public static void main(String[] args) {
    String outputFileName = "Methods/output.txt";

    // ! The ? causes invalidPathException
    String copyFileName = "Methods/output_copy?.txt";

    makeFileCopy(outputFileName, copyFileName);
  }

  public static void makeFileCopy(String sourceFileName, String destinationFileName) {
    try {

      // ! Preventing NullPointerException
      if (sourceFileName == null)
        throw new IllegalArgumentException("Source file name cannot be null.");
      if (destinationFileName == null)
        throw new IllegalArgumentException("Destination file name cannot be null.");

      // Copy file, replacing if it already exists
      Path sourcePath = Paths.get(sourceFileName);
      Path destinationPath = Paths.get(destinationFileName);

      Files.copy(sourcePath, destinationPath,
          StandardCopyOption.REPLACE_EXISTING);

    } catch (IOException e) {
      e.printStackTrace();
    } catch (InvalidPathException e) {
      System.out.println("Invalid path format.");
      e.printStackTrace();
    }
  }
}
