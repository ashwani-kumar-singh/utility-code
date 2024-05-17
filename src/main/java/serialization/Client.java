package serialization;

import java.io.IOException;

/**
 * The Concept of Serialization in Java
 * Java serialization is the process of converting an object into a stream of bytes or byte arrays.
 * The byte array represents the class of the object, the version of the object, and the internal
 * state of the object.
 *
 * Deserialization is the process of rebuilding the byte stream into a live Java Object that must
 * be usable.
 *
 * Purpose: Stashing large data into local file, data transmission, persistence, deep cloning,
 * cross jvm communication
 *
 * Serialization with Inheritance:
 * When the parent class implements the Serializable interface, the child class does it automatically.
 * If the parent class doesn’t implement the Serializable interface, then its state won’t transform into a
 * byte stream while serializing the child class instance.
 *
 * @link:
 * <a href="https://techbeamers.com/java-serialization-tutorial/">...</a>
 * <a href="https://www.baeldung.com/java-serialization-readobject-vs-readresolve">...</a>
 * <a href="https://www.geeksforgeeks.org/difference-between-serializable-and-externalizable-in-java-serialization/">...</a>
 */
public class Client {

    public static void main(String[] args) {

        String outputFile="serialization_demo.txt";
        ProductDetails def = new ProductDetails();
        def.setProduct("testProduct");
        def.setFeature("testFeature");
        def.setFeatureCount(10);

        // Serialize the object into a file.
        try {
            SerializationUtil.doSerialize(def, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Deserialize from a file into an object.
        ProductDetails defNext = null;
        try {
            defNext = (ProductDetails) SerializationUtil.doDeserialize(outputFile);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("def():\n --"+"\n  |\n  "+def);
        System.out.println(System.lineSeparator());
        System.out.println("defNext():\n --"+"\n  |\n  "+defNext);
    }
}
