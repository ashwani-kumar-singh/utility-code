package inner.anonymous;

/**
 * It is an inner class without a name and for which only a single object is created.
 * An anonymous inner class can be useful when making an instance of an object with certain "extras"
 * such as overriding methods of a class or interface.
 *
 * 1. A normal class can implement any number of interfaces but the anonymous inner class can
 * implement only one interface at a time.
 * 2. A regular class can extend a class and implement any number of interfaces simultaneously.
 * But anonymous Inner class can extend a class or can implement an interface but not both at a time.
 * 3. For regular/normal class, we can write any number of constructors, but we can’t write any
 * constructor for anonymous Inner class because the anonymous class does not have any name
 * and while defining constructor class name and constructor name must be same.
 * 4. An anonymous class has access to the members of its enclosing class.
 * 5. An anonymous class cannot access local variables in its enclosing scope that are not declared
 *    as final or effectively final.
 * 6. We cannot declare static initializers or member interfaces in an anonymous class.
 * 7. An anonymous class can have static members provided that they are constant variables.
 *
 * Based on declaration and behavior, there are 3 types of anonymous Inner classes:
 *
 * a. Anonymous Inner class that extends a class
 * b. Anonymous Inner class that implements an interface
 * c. Anonymous Inner class that defines inside method/constructor argument
 */

// Java program to illustrate creating an immediate thread. Using Anonymous Inner class that
// extends a Class

class MyThread1 {
    public static void main(String[] args)
    {
        Thread t = new Thread() {
            public void run()
            {
                System.out.println("Child Thread");
            }
        };
        t.start();
        System.out.println("Main Thread");
    }
}


// Java program to illustrate defining a thread. Using Anonymous Inner class that implements an
// interface

class MyThread2 {

    // Main driver method
    public static void main(String[] args)
    {
        Runnable r = new Runnable() {
            public void run()
            {
                System.out.println("Child Thread");
            }
        };
        Thread t = new Thread(r);
        t.start();
        System.out.println("Main Thread");
    }
}


// Java program to illustrate defining a thread. Using Anonymous Inner class that define inside
// argument

// Main class
class MyThread3 {
    public static void main(String[] args)
    {
        Thread t = new Thread(new Runnable() {
            public void run()
            {
                System.out.println("Child Thread");
            }
        });
        t.start();
        System.out.println("Main Thread");
    }

}

