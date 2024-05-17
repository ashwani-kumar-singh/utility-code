package generics;

// java generic class
public class GenericType<T> {
    private T object;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    // java generic methods
    public static <T> boolean isEqual(GenericType<T> obj1, GenericType<T> obj2){
        return obj1.getObject().equals(obj2.getObject());
    }

    //Java Generics Bounded Type Parameters
    public static <T extends Comparable<T>> int compare(T t1, T t2){
        return t1.compareTo(t2);
    }


}
