package TSP.menu;

import TSP.measurement.AlgorithmMeasurement;
import TSP.algorithms.Algorithm;
import TSP.algorithms.BruteForce;
import TSP.data.Matrix;
import TSP.data.RandomDataGenerator;
import TSP.data.DataPrinter;
import TSP.file.FromFileReader;
import TSP.measurement.MeasuredAlghorithmResult;

import java.util.Arrays;
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
            MeasuredAlghorithmResult result = null;
            AlgorithmMeasurement algorithmMeasurement = new AlgorithmMeasurement();
            try {
                switch (choice) {
                    case 1 -> matrix = fromFileReader.loadFromFile();
                    case 2 -> {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Podaj liczbę miast:");
                        matrix = dataGenerator.generateData(scanner.nextInt());
                        System.out.println("Dane zostały wygenerowane losowo.");
                    }
                    case 3 -> dataPrinter.displayData(matrix);
                    case 4 -> result = algorithmMeasurement.measureAlgorithm(algorithm, matrix);
                    case 5 -> {
                        scanner.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if(result != null) {
                System.out.println("Najkrótsza trasa: " + Arrays.toString(result.getAlgorithmResult().getBestTour()));
                System.out.println("Długość trasy: " + result.getAlgorithmResult().getMinCost());
                System.out.println("Czas wykonania: " + result.getExecutionTime() + " ms");
            }
        }
    }
}
