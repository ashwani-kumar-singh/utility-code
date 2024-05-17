package serialization.externalization;

/**
 *
 Using Java Externalizable Interface for Serialization
 The default serialization method is not secure and has performance issues. You can see the list below to check the
 performance issues with default serialization.

 a. Serialization depends on the recursion mechanism. When serialization of a child class object starts, it triggers
 the serialization of other instance variables from the chain of parent classes which continues until it reaches
 the Object class of these variables. It leads to a lot of overheads.
 b. While serializing an object class description information is attached to the stream. Lots of data and metadata
 stretch down the performance.
 c. Serialization also needs a serial version ID for tracking class-level changes. If you don’t set it manually,
 serialization calculates it automatically by going through all the fields and methods.
 d. The more the size of the class more will be the time to calculate the value. So this is again a potential \
 performance issue.

 We can solve all of the above issues with the Externalization interface.
 What is the Externalizable interface and how is it better than the Serializable interface?
 Externalization is an extension of the Serializable interface. If you want to externalize an object, then your
 class needs to implement the Externalizable interface and a default public constructor.

 In the Externalization process, only the identity of the class is added to the serialization stream. And, the class is
 responsible for the storage and retrieval of the instance information. It gives complete control of what to add and
 what to leave during serialization. The basic serialization doesn’t offer similar flexibility.


 */
public class Client {
    public static void main(String[] args) throws Exception {

        Employee t1 = new Employee("Ram", 35, 23675);
        String fileName = "abc.ser";
        // Serialization of object
       ExternalizationUtil. doSerialize(t1, fileName);

        // Deserialization
        Employee t2 = ExternalizationUtil.doDeserialize(fileName);

        // Display message
        System.out.println(t2);
    }

}
