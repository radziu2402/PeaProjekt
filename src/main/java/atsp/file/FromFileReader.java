package atsp.file;

import atsp.data.Matrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FromFileReader {
    public Matrix loadFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę pliku z danymi:");
        String fileName = scanner.nextLine();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            System.out.println("Plik otwarto pomyslnie!");
            String line;
            int numCities = 0;
            int[][] distanceMatrix = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("DIMENSION")) {
                    numCities = Integer.parseInt(line.split(":")[1].trim());
                    distanceMatrix = new int[numCities][numCities];
                } else if (line.startsWith("EDGE_WEIGHT_SECTION")) {
                    for (int i = 0; i < numCities; i++) {
                        line = br.readLine();
                        String[] values = line.trim().split("\\s+");
                        for (int j = 0; j < numCities; j++) {
                            distanceMatrix[i][j] = Integer.parseInt(values[j]);
                        }
                    }
                    break;
                }
            }

            System.out.println("Dane zostały wczytane.");
            return new Matrix(distanceMatrix);
        } catch (IOException e) {
            System.out.println("Nie udało sie otworzyć pliku!");
        }
        return null;
    }
}
