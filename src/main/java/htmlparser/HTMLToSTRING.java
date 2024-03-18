package htmlparser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class HTMLToSTRING {

  public static void main(String[] args) {
    try (Stream<String> stream = Files.lines( Paths.get( "/Users/ashwanisingh/Documents/codes/ashwani/test-project/src/main/java/htmlparser"
        + "/StringToHtml.html"), StandardCharsets.UTF_8))
    {
      StringBuilder contentBuilder = new StringBuilder();
      stream.forEach(s -> contentBuilder.append(s).append("\n"));
      final File f = new File("/Users/ashwanisingh/Documents/codes/ashwani/test-project/src/main/java/htmlparser/HtmlToString");
      PrintWriter writer = new PrintWriter(f);
      writer.write(contentBuilder.toString());
      writer.close();
      writer.flush();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
