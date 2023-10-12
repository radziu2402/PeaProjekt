package data;

import java.util.Random;
import java.util.Scanner;

public class RandomDataGenerator {
    public Matrix generateData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbę miast:");
        int numCities = scanner.nextInt();

        int[][] distanceMatrix = new int[numCities][numCities];
        Random random = new Random();

        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                if (i == j) {
                    distanceMatrix[i][j] = -1;
                } else {
                    distanceMatrix[i][j] = random.nextInt(100) + 1; // Zakres od 1 do 100 dla przykładu
                }
            }
        }

        Matrix data = new Matrix(distanceMatrix);
        System.out.println("Dane zostały wygenerowane losowo.");
        return data;
    }
}
