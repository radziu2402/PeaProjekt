package atsp;

import atsp.algorithms.Algorithm;
import atsp.algorithms.BruteForce;
import atsp.data.Matrix;
import atsp.data.RandomDataGenerator;
import atsp.data.DataPrinter;
import atsp.file.FromFileReader;

import java.util.Scanner;

public class Menu {
    private final Algorithm algorithm;
    private Matrix matrix;
    private final FromFileReader fromFileReader;
    private final RandomDataGenerator dataGenerator;
    private final DataPrinter dataPrinter;
    private final Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
        this.algorithm = chooseAlgorithm();
        this.fromFileReader = new FromFileReader();
        this.dataGenerator = new RandomDataGenerator();
        this.dataPrinter = new DataPrinter();
    }

    private Algorithm chooseAlgorithm() { //metoda na przyszlosc do kolejnych algorytmow
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
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
            }
        }
    }

    public void run() {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Wczytaj dane z pliku");
            System.out.println("2. Wygeneruj dane losowe");
            System.out.println("3. Wyświetl dane");
            System.out.println("4. Uruchom algorytm i wyświetl wyniki");
            System.out.println("5. Wyjście");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> matrix = fromFileReader.loadFromFile();
                case 2 -> matrix = dataGenerator.generateData();
                case 3 -> dataPrinter.displayData(matrix);
                case 4 -> algorithm.runAlgorithm(matrix);
                case 5 -> {
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
            }
        }
    }
}
