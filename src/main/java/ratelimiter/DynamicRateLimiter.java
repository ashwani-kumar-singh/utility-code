package ratelimiter;

public class DynamicRateLimiter {

    private final Object lock = new Object();
    private long lastExecutionTime = 0;
    private long coolDownMillis = 5000;

    public void throttle() throws InterruptedException {
        long currentExecTime = System.currentTimeMillis();
        synchronized (lock) {
            long elapsedTime = currentExecTime - lastExecutionTime;
            if (elapsedTime < coolDownMillis){
                lock.wait(coolDownMillis - elapsedTime);
            }
            lastExecutionTime = System.currentTimeMillis();
            System.out.println("Throttled operation executed at: " + lastExecutionTime);
        }
    }

    public void setCoolDownMillis(long coolDownMillis) {
        synchronized (lock) {
            this.coolDownMillis = coolDownMillis;
            lock.notify();
        }
    }

    public static void main(String[] args) {
        DynamicRateLimiter throttling = new DynamicRateLimiter();

        for (int i = 0; i < 5; i++) {
            try {
                throttling.throttle();
                // Adjust cooldown dynamically
                throttling.setCoolDownMillis((i + 1) * 1000); // Cooldown increases each iteration
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
