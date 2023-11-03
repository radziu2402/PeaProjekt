package TSP.implementations.forhuman;

import TSP.algorithms.*;

import java.util.Scanner;

public class HumanAlgorithmProvider implements AlgorithmProvider {
    @Override
    public Algorithm getAlgorithm() {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("Wybierz algorytm");
            System.out.println("1. BruteForce");
            System.out.println("2. BranchAndBound");
            System.out.println("3. DynamicProgramming");
            System.out.println("4. Wyjście");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    return new BruteForce();
                }
                case 2 -> {
                    return new BranchAndBound();
                }
                case 3 -> {
                    return new DynamicProgramming();
                }
                case 4 -> {
                    return null;
                }
                default -> System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
            }
        }
    }
}
