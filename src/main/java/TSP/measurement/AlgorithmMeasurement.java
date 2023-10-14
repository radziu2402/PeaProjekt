package TSP.measurement;

import TSP.algorithms.Algorithm;
import TSP.algorithms.AlgorithmResult;
import TSP.algorithms.IncorrectDataException;
import TSP.data.Matrix;

public class AlgorithmMeasurement {
    /**
     * Timer to be used to count time execution of an algorithm
     */
    private final AlgorithmTimer timer = new AlgorithmTimer();

    public MeasuredAlghorithmResult measureAlgorithm(Algorithm algorithm, Matrix data) throws IncorrectDataException, TimerException {
        timer.start();
        AlgorithmResult result = algorithm.runAlgorithm(data);
        timer.stop();

        return new MeasuredAlghorithmResult(result, timer.getElapsedTime());
    }
}
