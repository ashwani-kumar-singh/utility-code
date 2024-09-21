package annotation;

// https://naveen-metta.medium.com/understanding-java-annotations-and-creating-custom-annotations-e87cb77072ed
public class Client {
    public static void main(String[] args) throws JsonSerializationException {
        Person person = new Person("soufiane", "cheouati", "34");
        ObjectToJsonConverter serializer = new ObjectToJsonConverter();
        System.out.println(serializer.convertToJson(person));
    }
}
