package TSP.implementations.automated;

import TSP.data.DataProvider;
import TSP.data.Matrix;
import TSP.data.RandomDataGenerator;

public class RandomDataProvider implements DataProvider {
    private final int[] numberOfVertices;
    int actualIndex = 0;

    public RandomDataProvider(int[] numbersOfVertices) {
        this.numberOfVertices = numbersOfVertices;
    }

    @Override
    public Matrix getData() {
        if(actualIndex >= numberOfVertices.length-1) return null;

        return (new RandomDataGenerator()).generateData(numberOfVertices[++actualIndex]);
    }
}
