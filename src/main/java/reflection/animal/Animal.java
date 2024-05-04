package reflection.animal;

public abstract class Animal implements Eating {

    public static String CATEGORY = "domestic";
    private String name;

    public Animal() {
    }

    public Animal(String name) {
        this.name = name;
    }

    protected abstract String getSound();

}
