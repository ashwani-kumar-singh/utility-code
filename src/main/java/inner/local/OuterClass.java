package inner.local;

/**
 * Local Inner Classes are the inner classes that are defined inside a block. Generally, this block
 * is a method body. Sometimes this block can be a for loop or an if clause. Local Inner classes are
 * not a member of any enclosing classes. They belong to the block they are defined within, due
 * to which local inner classes cannot have any access modifiers associated with them. However,
 * they can be marked as final or abstract. This class has access to the fields of the class
 * enclosing it. Local inner class must be instantiated in the block they are defined in.
 * <p>
 * Rules of Local Inner Class:
 * <p>
 * 1. The scope of the local inner class is restricted to the block they are defined in.
 * 2. A local inner class cannot be instantiated from outside the block where it is created in.
 * 3. Till JDK 7, the Local inner class can access only the final local variable of the enclosing block.
 * However, From JDK 8, it is possible to access the non-final local variable of enclosing block
 * in the local inner class.
 * 4. A local class has access to the members of its enclosing class.
 * 5. Local inner classes can extend an abstract class or implement an interface.
 */
public class OuterClass {
    private void getValue() {
        // Note that local variable(sum) must be final till JDK 7 hence this code will work
        // only in JDK 8
        int sum = 20;

        // Local inner Class inside method
        class Inner {
            public int divisor;
            public int remainder;

            public Inner() {
                divisor = 4;
                remainder = sum % divisor;
            }

            private int getDivisor() {
                return divisor;
            }

            private int getRemainder() {
                return sum % divisor;
            }

            private int getQuotient() {
                System.out.println("Inside inner class");
                return sum / divisor;
            }
        }

        Inner inner = new Inner();
        System.out.println("Divisor = " + inner.getDivisor());
        System.out.println("Remainder = " + inner.getRemainder());
        System.out.println("Quotient = " + inner.getQuotient());
    }

    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        outer.getValue();
    }
}
