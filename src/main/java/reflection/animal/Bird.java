package reflection.animal;

public class Bird extends Animal {
    private boolean walks;

    public Bird() {
        super("bird");
    }

    public Bird(String name, boolean walks) {
        super(name);
        setWalks(walks);
    }

    public Bird(String name) {
        super(name);
    }

    @Override
    protected String getSound() {
        return "chirp";
    }

    @Override
    public String eats() {
        return "insects";
    }

    public boolean walks() {
        return walks;
    }

    public boolean isWalks() {
        return walks;
    }

    public void setWalks(boolean walks) {
        this.walks = walks;
    }

}
