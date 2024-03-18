package digitalprocessing;

import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.MovieBox;
import com.coremedia.iso.boxes.TrackBox;
import com.coremedia.iso.boxes.TrackHeaderBox;
import com.googlecode.mp4parser.FileDataSourceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class VideoProcessing {
  public static void main(String[] args) {
    /*getVideoDimensions(imageUrl, );
    final GifImage gif = GifDecoder.read(data);
    final int width = gif.getWidth();
    final int height = gif.getHeight();*/
  }

  public static MultimediaDimension getVideoDimensions(String imageUrl, String uploadPath)
      throws ExecutionException, InterruptedException, IOException {
    String path = String.format("%stemp_%s%s", uploadPath, System.currentTimeMillis(),
        ".mp4");
    File file = new File(path);
    File parent = file.getParentFile();
    parent.mkdirs();
    try (FileOutputStream out = new FileOutputStream(file);
        FileInputStream fis = new FileInputStream(path)) {
      //out.write(response.getResponseBodyAsBytes());
      FileChannel fc = fis.getChannel();
      FileDataSourceImpl fileDataSource = new FileDataSourceImpl(fc);
      IsoFile isoFile = new IsoFile(fileDataSource);
      MovieBox movieBox = isoFile.getMovieBox();
      List<TrackBox> trackBoxes = movieBox.getBoxes(TrackBox.class);
      int width = 0;
      int height = 0;
      for (TrackBox track : trackBoxes) {
        TrackHeaderBox header = track.getTrackHeaderBox();
        int w = (int) header.getWidth();
        int h = (int) header.getHeight();
        if (w == 0 && h == 0) {
          // skip the none-video track
          continue;
        }
        // Get the video width and height
        width = w;
        height = h;
        break;
      }
      return new MultimediaDimension(height,width);
    } finally {
      FileUtils.deleteQuietly(file);
    }
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @ToString(doNotUseGetters = true)
  private static class MultimediaDimension {
    private int height;
    private int width;

    public MultimediaDimension(int height, int width) {
      this.height = height;
      this.width = width;
    }

    public int getHeight() {
      return height;
    }

    public void setHeight(int height) {
      this.height = height;
    }

    public int getWidth() {
      return width;
    }

    public void setWidth(int width) {
      this.width = width;
    }
  }
}
