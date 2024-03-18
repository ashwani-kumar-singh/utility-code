package htmlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class STRINGToHTML {
  public static void main(String[] args) {
    try(Stream<String> stream = Files.lines( Paths.get(
        "/Users/ashwanisingh/Documents/codes/ashwani/test-project/src/main/java/htmlparser/HtmlToString"), StandardCharsets.UTF_8)) {
      String file = "/Users/ashwanisingh/Documents/codes/ashwani/test-project/src/main/java/htmlparser/StringToHtml.html";
      OutputStream os = new FileOutputStream(file);
      PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
      StringBuilder contentBuilder = new StringBuilder();
      stream.forEach(s -> contentBuilder.append(s).append("\n"));
      Document html = Jsoup.parse(contentBuilder.toString());
      writer.write(html.outerHtml());
      writer.close();
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
