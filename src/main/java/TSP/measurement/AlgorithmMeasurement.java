package TSP.measurement;

import TSP.algorithms.Algorithm;
import TSP.algorithms.AlgorithmResult;
import TSP.algorithms.IncorrectDataException;
import TSP.data.Matrix;

public class AlgorithmMeasurement {
    private final long upperTimeLimit;

    public AlgorithmMeasurement(long upperTimeLimit) {
        this.upperTimeLimit = upperTimeLimit;
    }

    /**
     * Timer to be used to count time execution of an algorithm
     */
    private final AlgorithmTimer timer = new AlgorithmTimer();

    public MeasuredAlghorithmResult measureAlgorithm(Algorithm algorithm, Matrix data) throws IncorrectDataException, TimerException {
        timer.start();
        AlgorithmResult result = algorithm.runAlgorithm(data);

        return new MeasuredAlghorithmResult(
                result,
                timer.getElapsedTime(),
                timer.getElapsedTime() < upperTimeLimit
        );
    }
}
