package serialization.singleton;

import org.junit.Assert;

import java.io.*;

/**
 * During deserialization, the new object gets created from the streamed object.
 */
public class Client {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        // Serialization
        FileOutputStream fos = new FileOutputStream("singleton.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Singleton actualSingletonObject = Singleton.getInstance();
        oos.writeObject(actualSingletonObject);

        // Deserialization
        Singleton deserializedSingletonObject = null;
        FileInputStream fis = new FileInputStream("singleton.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        deserializedSingletonObject = (Singleton) ois.readObject();
        //we can see that both objects are different, breaking the design pattern
        Assert.assertNotEquals(actualSingletonObject.hashCode(), deserializedSingletonObject.hashCode());

    }
}
