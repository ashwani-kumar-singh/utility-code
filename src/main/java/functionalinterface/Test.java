package functionalinterface;

@FunctionalInterface
interface Square {
    int calculate(int x);
}

class Test {
    public static void main(String [] args) {
        // lambda expression to define the calculate method
        Square s = (int x) -> x * x;
        // parameter passed and return type must be same as defined in the prototype
        int ans = s.calculate(5);
        System.out.println(ans);
    }
}
