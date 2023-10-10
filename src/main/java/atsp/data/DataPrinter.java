package atsp.data;

public class DataPrinter {
    public void displayData(Matrix data) {
        if (data == null) {
            System.out.println("Brak danych do wyświetlenia.");
        } else {
            int[][] distanceMatrix = data.getDistanceMatrix();
            for (int i = 0; i < distanceMatrix.length; i++) {
                for (int j = 0; j < distanceMatrix[i].length; j++) {
                    System.out.print(distanceMatrix[i][j] + "\t");
                }
                System.out.println();
            }
        }
    }
}
