package annotation;

public class Client {
    public static void main(String[] args) throws JsonSerializationException {
        Person person = new Person("soufiane", "cheouati", "34");
        ObjectToJsonConverter serializer = new ObjectToJsonConverter();
        System.out.println(serializer.convertToJson(person));
    }
}
