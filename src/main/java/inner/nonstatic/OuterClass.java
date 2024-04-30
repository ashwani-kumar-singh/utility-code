package inner.nonstatic;

/**
 *  * There are certain advantages associated with inner classes are as follows:
 *  *
 *  * a. Making code clean and readable.
 *  * b. Encapsulation: Private methods of the outer class can be accessed, so bringing a new dimension
 *  * and making it closer to the real world.
 *  * c. Optimizing the code module.
 *
 * We can not have a static method in a nested inner class because an inner class is implicitly
 * associated with an object of its outer class so it cannot define any static method for itself.
 * But Since JAVA Version 16 we can have static members in our inner class also.
 *
 */
public class OuterClass {
    // static member
    static int outerX = 10;
    // instance(non-static) member
    int outerY = 20;
    // private member
    private final int outerPrivate = 30;

    // inner class
    class InnerClass {

        void display() {
            // can access static member of outer class
            System.out.println("outerX = " + outerX);

            // can also access non-static member of outer class
            System.out.println("outerY = " + outerY);

            // can also access a private member of the outer class
            System.out.println("outerPrivate = " + outerPrivate);
        }
    }

    public static void main(String[] args) {
        // accessing an inner class
        OuterClass outerObject = new OuterClass();
        OuterClass.InnerClass innerObject = outerObject.new InnerClass();
        innerObject.display();
    }

}
