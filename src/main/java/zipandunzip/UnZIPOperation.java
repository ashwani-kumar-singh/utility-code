package zipandunzip;

import net.lingala.zip4j.core.ZipFile;

public class UnZIPOperation {

  public static void main(String[] args){
    String source =
        "/Users/ashwanisingh/Documents/codes/ashwani/test-project/src/main/java/zipandunzip/zipTest/template.zip";
    String destination = "/Users/ashwanisingh/Documents/codes/ashwani/test-project/src/main/java/zipandunzip";
    try {
      ZipFile zipFile = new ZipFile(source);
      zipFile.extractAll(destination);
    } catch (net.lingala.zip4j.exception.ZipException e) {
      e.printStackTrace();
    }
  }
}
