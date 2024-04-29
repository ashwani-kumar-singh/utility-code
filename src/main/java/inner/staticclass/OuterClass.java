package inner.staticclass;

/**
 * In the case of normal or regular inner classes, without an outer class object existing,
 * there cannot be an inner class object. i.e., an object of the inner class is always strongly
 * associated with an outer class object. But in the case of static nested class, Without an outer
 * class object existing, there may be a static nested class object. i.e., an object of a static
 * nested class is not strongly associated with the outer class object. As with class methods and
 * variables, a static nested class is associated with its outer class. And like static class methods,
 * a static nested class cannot refer directly to instance variables or methods defined in its
 * enclosing class: it can use them only through an object reference. They are accessed using the
 * enclosing class name.
 */
public class OuterClass {
    static int outerX = 10;
    int outerY = 20;
    private static final int outerPrivate = 30;

    static class StaticNestedClass {
        void display() {
            // can access static member of outer class
            System.out.println("outerX = " + outerX);

            // can access private static member of outer class
            System.out.println("outer_private = " + outerPrivate);

            // The following statement will give compilation error as static nested class
            // cannot directly access non-static members
            // System.out.println("outerY = " + outerY);

            // Therefore create object of the outer class to access the non-static member
            OuterClass out = new OuterClass();
            System.out.println("outerY = " + out.outerY);

        }
    }

    public static void main(String[] args) {
        // accessing a static nested class
        OuterClass.StaticNestedClass nestedObject = new OuterClass.StaticNestedClass();
        nestedObject.display();
    }

}
