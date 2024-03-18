package zipandunzip;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GZIPOperation {

  public static void main(String[] args) throws IOException {
    try(
        FileOutputStream outputStream     = new FileOutputStream("/Users/ashwanisingh/Documents/codes/ashwaniti/test-project/src/main/java/zipandunzip/zipTest/myfile.zip");
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream)
    ) {
      HttpServletResponse response;
      byte[] data = "hello my name is ashwani".getBytes();
      gzipOutputStream.write(data);
    }
  }
}
