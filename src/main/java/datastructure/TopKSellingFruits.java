package datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TopKSellingFruits {

    private static void topKSellingFruits(int n, int k, List<String> fruitNames, List<Double> priceList,
                                          List<Integer> quantityList) {
        if (n < 1) {
            System.out.println("Value of n must be greater than zero");
            return;
        }

        if (k < 1) {
            System.out.println("Value of K must be greater than zero");
            return;
        }

        if (k > n) {
            System.out.println("Number of different types of fruits can not be less than value of K");
            return;
        }

        if (n == 1) {
            System.out.println("Top " + k + " selling fruits in order: \n" + fruitNames.get(0));
            return;
        }

        boolean allEqual = true;
        for (int i = 0; i < n - 1; i++) {
            if (priceList.get(i) * quantityList.get(i) != priceList.get(i + 1) * quantityList.get(i + 1)) {
                allEqual = false;
                break;
            }
        }
        if (allEqual) {
            System.out.println("Top " + k + " selling fruits in order: ");
            for (int j = n - 1; k > 0 && j >= 0; j--, k--) {
                System.out.println(fruitNames.get(j));
            }
            return;
        }

        PriorityQueue<Fruit> pq = new PriorityQueue<>((p1, p2) ->
                Double.compare(p2.quantity * p2.price, p1.quantity * p1.price));


        for (int i = 0; i < n; i++) {
            pq.add(new Fruit(fruitNames.get(i), priceList.get(i), quantityList.get(i)));
        }

        System.out.println("Top " + k + " selling fruits in order: ");
        while (!pq.isEmpty()) {
            if (k == 0) {
                break;
            }
            System.out.println(pq.poll().name);
            k--;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number(N) of different types of Fruits: ");
        int n = sc.nextInt();

        System.out.println("Enter the value of K: ");
        int k = sc.nextInt();

        List<String> fruitsNames = new ArrayList<>();
        List<Double> priceList = new ArrayList<>();
        List<Integer> quantityList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter Fruit Name: ");
            fruitsNames.add(sc.next());
            System.out.println("Enter Fruit Price: ");
            priceList.add(sc.nextDouble());
            System.out.println("Enter Fruit Quantity: ");
            quantityList.add(sc.nextInt());
        }

        topKSellingFruits(n, k, fruitsNames, priceList, quantityList);
    }

    static class Fruit {
        String name;
        Double price;
        Integer quantity;

        Fruit() {
        }

        public Fruit(String name, Double price, Integer quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
    }

}
