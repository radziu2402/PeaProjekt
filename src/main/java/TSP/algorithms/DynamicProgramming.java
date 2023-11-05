package TSP.algorithms;

import TSP.data.Matrix;

public class DynamicProgramming implements Algorithm {
    private volatile boolean run = true;

    @Override
    public AlgorithmResult runAlgorithm(Matrix matrix) throws IncorrectDataException {
        run = true;
        if (matrix == null) {
            throw new IncorrectDataException("Najpierw wczytaj lub wygeneruj dane.");
        } else {
            int n = matrix.size;
            int[][] distanceMatrix = matrix.getDistanceMatrix();

            // Inicjalizacja tablicy dynamicznej
            int[][] dp = new int[1 << n][n];
            for (int i = 0; i < (1 << n); i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = -1;
                }
            }
            dp[1][0] = 0;

            // Obliczanie najkrótszych tras
            for (int mask = 1; mask < (1 << n); mask++) {
                if (!run) {
                    break;
                }
                for (int u = 0; u < n; u++) {
                    if ((mask & (1 << u)) != 0) {
                        for (int v = 0; v < n; v++) {
                            if ((mask & (1 << v)) != 0 && u != v && dp[mask ^ (1 << u)][v] != -1) {
                                if (dp[mask][u] == -1) {
                                    dp[mask][u] = dp[mask ^ (1 << u)][v] + distanceMatrix[v][u];
                                } else {
                                    dp[mask][u] = Math.min(dp[mask][u], dp[mask ^ (1 << u)][v] + distanceMatrix[v][u]);
                                }
                            }
                        }
                    }
                }
            }

            int finalMask = (1 << n) - 1;
            int minCost = Integer.MAX_VALUE;
            int lastVertex = -1;
            for (int v = 1; v < n; v++) {
                if (dp[finalMask][v] != -1 && dp[finalMask][v] + distanceMatrix[v][0] < minCost) {
                    minCost = dp[finalMask][v] + distanceMatrix[v][0];
                    lastVertex = v;
                }
            }

            // Odtworzenie najlepszej trasy
            int[] bestTour = new int[n];
            int mask = finalMask;
            int vertex = lastVertex;
            for (int i = n - 1; i >= 0; i--) {
                bestTour[i] = vertex;
                int prevMask = mask ^ (1 << vertex);
                for (int u = 0; u < n; u++) {
                    if ((prevMask & (1 << u)) != 0 && vertex != u && dp[mask][vertex] == dp[prevMask][u] + distanceMatrix[u][vertex]) {
                        mask = prevMask;
                        vertex = u;
                        break;
                    }
                }
            }

            return new AlgorithmResult(n, bestTour, minCost, getName());
        }
    }

    @Override
    public String getName() {
        return "Dynamic programming";
    }

    @Override
    public void stopExecution() {
        this.run = false;
    }
}
