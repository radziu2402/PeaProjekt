package TSP.algorithms;

public class AlgorithmResult {
    /**
     * Array that contains the best possible hamiltonian cycle in graph
     */
    private final int[] bestTour;

    /**
     * Cost associated with best hamiltonian cycle
     */
    private final int minCost;

    /**
     * Number of vertices that the algorithm ran on.
     */
    private final int numberOfVertices;

    AlgorithmResult(int numberOfVertices, int[] bestTour, int minCost) {
        this.numberOfVertices = numberOfVertices;
        this.bestTour = bestTour;
        this.minCost = minCost;
    }

    public int getMinCost() {
        return this.minCost;
    }

    public int[] getBestTour() {
        return bestTour;
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }
}
