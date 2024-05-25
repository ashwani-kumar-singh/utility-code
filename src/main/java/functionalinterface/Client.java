package functionalinterface;

import functionalinterface.impl.Eagle;

public class Client {
    public static void main(String[] args) {
        Bird bird = new Eagle();
        bird.canFly();

        // using anonymous class
        Bird anonymousBird = new Bird() {
            @Override
            public void canFly() {
                System.out.println("Anonymous class: Eagle Bird Implementation");
            }
        };

        anonymousBird.canFly();

        // using lambda expression | lambda expression is a way of implementing function interface
        Bird lambdaBird = () -> System.out.println("Lambda Expression: Eagle Bird Implementation");
        lambdaBird.canFly();
    }
}
