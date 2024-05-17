package generics;

/**
 * Generics was added in Java 5 to provide compile-time type checking and removing risk of
 * ClassCastException that was common while working with collection classes.
 * The whole collection framework was re-written to use generics for type-safety.
 *
 * List list = new ArrayList();
 * list.add("abc");
 * list.add(new Integer(5)); //OK
 *
 * for(Object obj : list){
 * 	//type casting leading to ClassCastException at runtime
 *     String str=(String) obj;
 *     Above code compiles fine but throws ClassCastException at runtime because we are trying to cast Object in the
 *     list to String whereas one of the element is of type Integer.
 * }
 */
public class Client {
    public static void main(String[] args) {
        GenericType<String> type = new GenericType<>();
        type.setObject("string object");

        System.out.println("Type of object: " + type.getObject());

        GenericType<String> obj1 = new GenericType<>();
        obj1.setObject("string object");

        GenericType<String> obj2 = new GenericType<>();
        obj2.setObject("string object");

        System.out.println("are objects equals: "+ GenericType.isEqual(obj1, obj2));

        GenericType<String> myClass1 = new GenericType<>();
        GenericType<Object> myClass2 = new GenericType<Object>();
        //myClass2=myClass1; // compilation error since GenericType<String> is not a GenericType<Object>

    }
}
