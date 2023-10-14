package TSP.implementations.forhuman;

import TSP.data.DataPrinter;
import TSP.data.DataProvider;
import TSP.data.Matrix;
import TSP.data.RandomDataGenerator;
import TSP.file.FromFileReader;

import java.util.Scanner;

public class HumanDataProvider implements DataProvider {
    @Override
    public Matrix getData() {
        Scanner scanner = new Scanner(System.in);
        Matrix matrix = null;
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Wczytaj dane z pliku");
            System.out.println("2. Wygeneruj dane losowe");
            System.out.println("3. Wyświetl dane");
            System.out.println("4. Uruchom algorytm i wyświetl wyniki");
            System.out.println("5. Wyjście");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> matrix = (new FromFileReader()).loadFromFile();
                case 2 -> {
                    System.out.println("Podaj liczbę miast:");
                    matrix = (new RandomDataGenerator()).generateData(scanner.nextInt());
                    System.out.println("Dane zostały wygenerowane losowo.");
                }
                case 3 -> (new DataPrinter()).displayData(matrix);
                case 4 -> {
                    return matrix;
                }
                case 5 -> {
                    return null;
                }
                default -> System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
            }
        }
    }


}
