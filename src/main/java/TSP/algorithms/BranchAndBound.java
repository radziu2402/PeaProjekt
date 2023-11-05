package TSP.algorithms;

import TSP.data.Matrix;

import java.util.ArrayList;

import static java.lang.Integer.MAX_VALUE;

public class BranchAndBound implements Algorithm {
    private boolean run = true;
    private final ArrayList<ArrayList<Integer>> nodes = new ArrayList<>();

    @Override
    public AlgorithmResult runAlgorithm(Matrix graph) throws IncorrectDataException {
        run = true;
        if (graph == null) {
            throw new IncorrectDataException("Najpierw wczytaj lub wygeneruj dane.");
        } else {
            int[][] firstCopyOfMatrix, tempMatrix, veryTempMatrix;
            firstCopyOfMatrix = copy(graph.getDistanceMatrix());
            int firstReductionCost = reduceMatrix(firstCopyOfMatrix); //wstępna redukcja macierzy i zapamiętanie jej kosztu

            int tempCost;
            for (int i = 1; i < firstCopyOfMatrix.length; i++) { //sprawdzenie pierwszych wierzcholków
                tempMatrix = copy(firstCopyOfMatrix);
                tempCost = getCost(firstCopyOfMatrix, 0, i);
                insertInfinity(tempMatrix, 0, i);
                tempCost += reduceMatrix(tempMatrix);
                tempCost += firstReductionCost;
                ArrayList<Integer> tempArray = new ArrayList<>();
                tempArray.add(tempCost);
                tempArray.add(i);
                nodes.add(tempArray);
            }
            while (nodes.get(findSmallestFromArray(nodes)).size() < graph.size && run) {
                int tempActual = findSmallestFromArray(nodes);
                boolean[] isUsed = new boolean[graph.size];
                markUsedNodes(isUsed, nodes.get(tempActual));

                tempMatrix = copy(firstCopyOfMatrix);
                insertInfinity(tempMatrix, 0, nodes.get(tempActual).get(1));
                reduceMatrix(tempMatrix);
                for (int i = 1; i < nodes.get(tempActual).size() - 1; i++) { //obliczenie aktualnie wykonywanej macierzy
                    if(!run){
                        return new AlgorithmResult(graph.size, new int[]{}, nodes.get(findSmallestFromArray(nodes)).get(0), getName());
                    }
                    insertInfinity(tempMatrix, nodes.get(tempActual).get(i), nodes.get(tempActual).get(i + 1));
                    reduceMatrix(tempMatrix);
                }
                for (int temp = nextUnused(isUsed); temp != -1; ) {
                    if(!run){
                        return new AlgorithmResult(graph.size, new int[]{}, nodes.get(findSmallestFromArray(nodes)).get(0), getName());
                    }
                    veryTempMatrix = copy(tempMatrix);
                    tempCost = nodes.get(tempActual).get(0);
                    tempCost += getCost(veryTempMatrix, nodes.get(tempActual).get(nodes.get(tempActual).size() - 1), temp);
                    insertInfinity(veryTempMatrix, nodes.get(tempActual).get(nodes.get(tempActual).size() - 1), temp);
                    tempCost += reduceMatrix(veryTempMatrix);
                    ArrayList<Integer> tempArray = new ArrayList<>();
                    tempArray.add(tempCost);
                    for (int j = 1; j < nodes.get(tempActual).size(); j++) {
                        if(!run){
                            return new AlgorithmResult(graph.size, new int[]{}, nodes.get(findSmallestFromArray(nodes)).get(0), getName());
                        }
                        tempArray.add(nodes.get(tempActual).get(j));
                    }
                    tempArray.add(temp);
                    nodes.add(tempArray);
                    temp = nextUnused(isUsed);
                }
                nodes.remove(tempActual);
            }
            if(!run){
                return new AlgorithmResult(graph.size, new int[]{}, nodes.get(findSmallestFromArray(nodes)).get(0), getName());
            }
            int[] bestTour = new int[nodes.get(findSmallestFromArray(nodes)).size()];
            bestTour[0] = 0;
            for (int i = 1; i < bestTour.length; i++) {
                bestTour[i] = nodes.get(findSmallestFromArray(nodes)).get(i);
            }
            return new AlgorithmResult(graph.size, bestTour, nodes.get(findSmallestFromArray(nodes)).get(0), getName());
        }
    }

    private int nextUnused(boolean[] isUsed) { //zwraca pierwszy niewykorzystany wcześniej wierzchołek w rozpatrywanej ścieżce
        int firstUnused = -1;
        for (int i = 0; i < isUsed.length; i++) { //rozwinięcie pierwszego niewykorzystanego węzła
            if (!isUsed[i]) {
                firstUnused = i;
                isUsed[i] = true;
                return firstUnused;
            }
        }
        return firstUnused;
    }

    private void markUsedNodes(boolean[] isUsed, ArrayList<Integer> array) { //zaznacza w podanej tablicy wszystkie wykorzystane wierzchołki, aby nie używać ich ponownie na podstawie arraylisty
        isUsed[0] = true;
        for (int i = 1; i < array.size(); i++) {
            isUsed[array.get(i)] = true;
        }
    }

    private int reduceMatrix(int[][] matrix) { //modyfikuje aktualną tablicę !!!!!! // funkcja redukująca podaną macierz
        int totalHorizontal = 0, totalVertical = 0;
        int tempSmall;

        for (int i = 0; i < matrix.length; i++) {//minimalizacja wierszy i zapisanie
            tempSmall = getSmallestHorizontal(matrix, i);
            if (tempSmall > 0) {
                totalHorizontal += tempSmall;
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][j] != -1) {
                        matrix[i][j] -= tempSmall;
                    }
                }
            }
        }
        if(!run){
            return 0;
        }
        for (int i = 0; i < matrix.length; i++) {//minimalizacja kolumn i zapisanie
            tempSmall = getSmallestVertical(matrix, i);
            if (tempSmall > 0) {
                totalVertical += tempSmall;
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[j][i] != -1) {
                        matrix[j][i] -= tempSmall;
                    }
                }
            }
        }
        return totalHorizontal + totalVertical;
    }

    private void insertInfinity(int[][] matrix, int from, int to) { //funkcja wstawiająca do podanej macierzy nieskończoności (-1) na podstawie 2 ierzchołków - wejściowego i docelowego
        if(!run){
            return;
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[from][i] = -1;
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][to] = -1;
        }
        matrix[to][0] = -1; //wstaw nieskończoność na powrocie do węzła pierwotnego
    }

    private int getSmallestHorizontal(int[][] matrix, int line) { //z danego rzędu zwraca najmniejszą wartość rożną od -1
        int horizontal = MAX_VALUE;
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[line][j] < horizontal && matrix[line][j] != -1) {
                horizontal = matrix[line][j];
            }
        }
        if (horizontal == MAX_VALUE) {
            return -1;
        } else {
            return horizontal;
        }
    }

    private int getSmallestVertical(int[][] matrix, int column) { //
        int vertical = MAX_VALUE;
        for (int[] ints : matrix) {
            if (ints[column] < vertical && ints[column] != -1) {
                vertical = ints[column];
            }
        }
        if (vertical == MAX_VALUE) {
            return -1;
        } else {
            return vertical;
        }
    }

    private int getCost(int[][] matrix, int from, int to) { //zwraca koszt dojścia do wierzchołka na podstawie podanej macierzy
        return matrix[from][to];
    }

    private int[][] copy(int[][] src) {
        if (src == null) {
            return null;
        }

        int[][] copy = new int[src.length][];
        for (int i = 0; i < src.length; i++) {
            copy[i] = src[i].clone();
        }

        return copy;
    }

    private int findSmallestFromArray(ArrayList<ArrayList<Integer>> array) {
        int tempSmallest = MAX_VALUE;
        int indexOfSmallest = -1;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).get(0) < tempSmallest) {
                tempSmallest = array.get(i).get(0);
                indexOfSmallest = i;
            }
        }
        return indexOfSmallest;
    }


    @Override
    public String getName() {
        return "Branch and bound";
    }

    @Override
    public void stopExecution() {
        this.run = false;
    }
}



