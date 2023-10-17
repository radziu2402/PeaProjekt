package TSP.measurement;

public class AlgorithmTimer {
    /**
     * Elapsed time between start() and stop() of a timer
     */
    private long startTime = -1;

    /**
     * Start timer
     */
    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public long getElapsedTime() throws TimerException {
        if(startTime == -1) {
            throw new TimerException("Zegar nie został wystartowany!");
        }

        return System.currentTimeMillis() - this.startTime;
    }
}
