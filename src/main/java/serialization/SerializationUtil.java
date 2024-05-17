package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtil {

    // Do serialize the Java object and save it to a file
    public static void doSerialize(Object obj, String outputFile) throws IOException {
        FileOutputStream fileToWrite = new FileOutputStream(outputFile);
        ObjectOutputStream objToWrite = new ObjectOutputStream(fileToWrite);
        objToWrite.writeObject(obj);
        fileToWrite.close();
    }

    // Do deserialize the Java object from a given file
    public static Object doDeserialize(String inputFile) throws IOException,
            ClassNotFoundException {
        FileInputStream fileToRead = new FileInputStream(inputFile);
        ObjectInputStream objToRead = new ObjectInputStream(fileToRead);
        Object obj = objToRead.readObject();
        objToRead.close();
        return obj;
    }
}
