package serialization.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Singleton implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
    // the readResolve() method is used to replace the object that is created during
    // deserialization with a different object. We can use readResolve() to ensure that the deserialized
    // object is the same as the existing instance for a singleton instance.
    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }
}
