package serialization.externalization;

import java.io.*;

public class ExternalizationUtil {
    public static Employee doDeserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (Employee) ois.readObject();
    }

    public static void doSerialize(Employee t1, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(t1);
    }
}
