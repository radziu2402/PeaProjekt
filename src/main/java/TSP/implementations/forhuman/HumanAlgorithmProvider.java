package TSP.implementations.forhuman;

import TSP.algorithms.Algorithm;
import TSP.algorithms.AlgorithmProvider;
import TSP.algorithms.BruteForce;

import java.util.Scanner;

public class HumanAlgorithmProvider implements AlgorithmProvider {
    @Override
    public Algorithm getAlgorithm() {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("Wybierz algorytm");
            System.out.println("1. BruteForce");
            System.out.println("2. Wyjście");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    return new BruteForce();
                }
                case 2 -> {
                    return null;
                }
                default -> System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
            }
        }
    }
}
