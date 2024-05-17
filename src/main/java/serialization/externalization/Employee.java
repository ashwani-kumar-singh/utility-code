package serialization.externalization;

// Java Program to illustrate Externalizable

// Importing input output classes

import java.io.*;

public class Employee implements Externalizable {

    // Member variables of this class
    private String name;
    private int age;
    private int jobId;

    // Constructors of this class

    // Constructor 1 -No-argument constructor
    public Employee() {
        // Display message
        System.out.println(
                "Public no-argument constructor");
    }

    // Constructor 2 -Default constructor
    public Employee(String name, int age, int jobId) {

        // This keyword refers to current object itself
        this.name = name;
        this.age = age;
        this.jobId = jobId;
    }

    // Implementing write external method
    public void writeExternal(ObjectOutput out)
            throws IOException {
        // Writing name and age to file
        out.writeObject(name);
        out.writeInt(age);
    }

    // Implementing readExternal method
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        // Reading name from file
        name = (String) in.readObject();
        // Reading age from file
        age = in.readInt();
    }

}

