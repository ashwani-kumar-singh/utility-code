package buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * <a href="https://www.geeksforgeeks.org/difference-between-java-io-and-java-nio/">...</a>
 */
public class NIODemo {

    public static void main(String[] args) throws IOException {
        FileOutputStream fout = new FileOutputStream("dest.txt");
        FileChannel writeChannel = fout.getChannel();
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        String message = "this is a test string";
        writeBuffer.put(message.getBytes());
        writeBuffer.flip();
        writeChannel.write(writeBuffer);
        System.out.println("file written successfully");


        FileInputStream fin = new FileInputStream("dest.txt");
        FileChannel readChannel = fin.getChannel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int result = readChannel.read(readBuffer);
        System.out.println("file read successfully: " + result);

    }
}
