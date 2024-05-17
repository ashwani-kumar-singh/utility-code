package serialization.inheritance;

import serialization.SerializationUtil;

import java.io.IOException;


/** * Serialization with Inheritance:
 1. When the parent class implements the Serializable interface, the child class does it automatically.
 2.If the parent class doesn’t implement the Serializable interface, then its state won’t transform into a
   byte stream while serializing the child class instance.
 */

public class Client {

    public static void main(String[] args) {

        String fileName = "childclass.txt";

        ChildClass childClass = new ChildClass();
        childClass.setProductId(21);
        childClass.setProduct("Blog");
        childClass.setBrand("TechBeamers");

        try {
            SerializationUtil.doSerialize(childClass, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            ChildClass newChild = (ChildClass) SerializationUtil.doDeserialize(fileName);
            System.out.println("ChildClass output:  \n  |\n   --" + newChild);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
