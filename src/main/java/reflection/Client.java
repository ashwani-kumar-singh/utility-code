package reflection;

import reflection.animal.Bird;
import reflection.animal.Goat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Reflection is an API that is used to examine or modify the behavior of methods, classes, and interfaces at runtime.
 * Java reflection allows us to inspect and/or modify runtime attributes of classes, interfaces,
 * fields and methods. This particularly comes in handy when we don’t know their names at compile time.
 * <p>
 * Disadvantages:
 * 1. Extensibility Features: An application may make use of external, user-defined classes by creating instances
 * of extensibility objects using their fully-qualified names.
 * <p>
 * 2. Debugging and testing tools: Debuggers use the property of reflection to examine private members of classes.
 * <p>
 * 3. Performance Overhead: Reflective operations have slower performance than their non-reflective counterparts,
 * and should be avoided in sections of code that are called frequently in performance-sensitive applications.
 * <p>
 * 4. Exposure of Internals: Reflective code breaks abstractions and therefore may change behavior with
 * upgrades of the platform.
 */
public class Client {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Person person = new Person();
        System.out.println("Field Names: " + ReflectionUtil.getFieldNames(person));

        Goat goat = new Goat("goat");

        System.out.println("******************** Class Names using Class Object ****************************");
        System.out.println("Class Simple Name: " + ReflectionUtil.getSimpleName(goat));
        System.out.println("Class Name: " + ReflectionUtil.getName(goat));
        System.out.println("Canonical Name: " + ReflectionUtil.getCanonicalName(goat));

        System.out.println("******************** Class Names using Class Path ****************************");
        System.out.println("Class Simple Name: " + ReflectionUtil.getSimpleName("reflection.animal.Goat"));
        System.out.println("Class Name: " + ReflectionUtil.getName("reflection.animal.Goat"));
        System.out.println("Canonical Name: " + ReflectionUtil.getCanonicalName("reflection.animal.Goat"));

        System.out.println("********************* Class Modifiers ***************************");
        System.out.println("isPublic: " + ReflectionUtil.isPublic("reflection.animal.Goat"));
        System.out.println("isAbstract: " + ReflectionUtil.isAbstract("reflection.animal.Goat"));
        System.out.println("isPublic: " + ReflectionUtil.isPublic("reflection.animal.Animal"));
        System.out.println("isAbstract: " + ReflectionUtil.isAbstract("reflection.animal.Animal"));

        System.out.println("********************* Package Information ***************************");
        System.out.println("Package: " + ReflectionUtil.getPackage(goat));

        System.out.println("********************* Superclass Information ***************************");
        System.out.println("Superclass: " + ReflectionUtil.getSuperclass(goat));

        System.out.println("********************* Implemented Interface  ***************************");
        System.out.println("Interface: " + Arrays.toString(ReflectionUtil.getInterfaces(goat)));

        System.out.println("********************* Constructors, Methods, Declared Methods and Fields  ***************************");
        Bird bird = new Bird("parrot", true);
        Constructor<?> cons1 = ReflectionUtil.getConstructor(bird);
        System.out.println("Constructor Names: " + cons1);
        Constructor<?> cons2 = ReflectionUtil.getConstructor("reflection.animal.Bird", String.class);
        System.out.println("Constructor Names: " + cons2);
        Constructor<?> cons3 = ReflectionUtil.getConstructor("reflection.animal.Bird", String.class, boolean.class);
        System.out.println("Constructor Names: " + cons3);

        System.out.println("Field Names: " + ReflectionUtil.getFieldNames(bird));
        System.out.println("Method Names: " + ReflectionUtil.getMethodNames(bird));
        System.out.println("Declared Method Names: " + ReflectionUtil.getDeclaredMethodNames(bird));

        System.out.println("********************* Create Instance using Constructors ***************************");
        Bird bird1 = (Bird) cons1.newInstance();
        Bird bird2 = (Bird) cons2.newInstance("Weaver bird");
        Bird bird3 = (Bird) cons3.newInstance("Weaver bird", false);

        System.out.println("*************** Call method using instance created from Construct  ******************");
        bird3.walks();
        bird3.eats();

        System.out.println("************************* Invoking Methods ****************************");
        Method walksMethod = ReflectionUtil.getDeclaredMethod(bird3, "walks");
        System.out.println("Before Setting bird walks: " + (boolean) walksMethod.invoke(bird3));

        Method setWalksMethod = ReflectionUtil.getDeclaredMethod(bird3, "setWalks", boolean.class);
        System.out.println("setWalksMethod: " + setWalksMethod.invoke(bird3, true));
        System.out.println("After Setting bird walks: " + (boolean) walksMethod.invoke(bird3));

        accessPublicStaticFields();

    }

    public static void accessPublicStaticFields() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> birdClass = Class.forName("reflection.animal.Bird");
        Field field = birdClass.getField("CATEGORY");
        //to make private field accessible
        field.setAccessible(true);
        //Field objects is that when it is declared as public static, we don’t need an instance of the class containing them.
        System.out.println("Public static field value: " + field.get(null));
    }
}
