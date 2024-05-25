package functionalinterface;

/**
 * A functional interface is an interface that contains only one abstract method. They can have only
 * one functionality to exhibit.
 *
 * Functional Interface is additionally recognized as Single Abstract Method Interfaces (SAM) interfaces.
 *
 * There is no need to use the abstract keyword as it is optional to use the abstract keyword because, by default,
 * the method defined inside the interface is abstract only.
 * From Java 8 onwards, lambda expressions can be used to represent
 * the instance of a functional interface. A functional interface can have any number of default
 * methods. Runnable, ActionListener, and Comparable are some of the examples of functional interfaces.
 *
 * @link <a href="https://www.geeksforgeeks.org/functional-interfaces-java/">...</a>
 * <a href="https://www.baeldung.com/java-8-functional-interfaces">...</a>
 * <a href="https://1drv.ms/w/s!Am8pK68xxrsT7hRe-fL-r5u6tTTM?e=d0qKoa">...</a>
 */

// Functional interface annotation restrict us from defining more than 1 abstract class as it throws compile time error.
@FunctionalInterface
public interface Bird {
    void canFly();

    default void getHeight() {
        // default method implementation
    }

    static void canEat(){
        // static method implementation
    }

    String toString(); // object class method
}
