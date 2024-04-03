package ratelimiter;

public class SimpleRateLimiter {

    private  long lastExecutionTime = 0;
    private long intervalInMillis;

    public SimpleRateLimiter(long requestPerSeconds) {
        this.intervalInMillis = 1000/requestPerSeconds;
    }

    public void throttle() throws InterruptedException {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastExecutionTime;

        if(elapsedTime < intervalInMillis) {
            Thread.sleep(intervalInMillis - elapsedTime);
        }
        lastExecutionTime = System.currentTimeMillis();
        // Perform the throttled operation
        System.out.println("Throttled operation executed at: " + lastExecutionTime);
    }
}
