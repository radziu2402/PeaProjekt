package TSP.measurement;

public class AlgorithmTimer {
    /**
     * Elapsed time between start() and stop() of a timer
     */
    private long elapsedTime = -1;

    /**
     * Flag that tells if the timer is running, if so you cannot get elapsed time.
     */
    private boolean running = false;

    /**
     * Start timer
     */
    public void start() {
        this.elapsedTime = System.currentTimeMillis();
        this.running = true;
    }

    /**
     * Stop timer and calculate elapsed time
     */
    public void stop() {
        this.elapsedTime = System.currentTimeMillis() - this.elapsedTime;
        running = false;
    }

    public long getElapsedTime() throws TimerException {
        if(elapsedTime == -1) {
            throw new TimerException("Zegar nie został wystartowany!");
        }

        if(running) {
            throw new TimerException("Zegar jest nadal włączony!");
        }

        return this.elapsedTime;
    }
}
