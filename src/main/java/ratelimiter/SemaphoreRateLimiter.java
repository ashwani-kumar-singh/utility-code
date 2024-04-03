package ratelimiter;

import java.util.concurrent.Semaphore;

public class SemaphoreRateLimiter {

    private final Semaphore semaphore;

    public SemaphoreRateLimiter(int permits) {
        this.semaphore = new Semaphore(permits);
    }

    public boolean throttle() {
        if (semaphore.tryAcquire()) {
            // Perform the throttled operation
            System.out.println("Throttled operation executed. Permits left: " + semaphore.availablePermits());
            return true;
        } else {
            System.out.println("Request throttled. Try again later.");
            return false;
        }
    }

    public static void main(String[] args) {
        SemaphoreRateLimiter rateLimiter = new SemaphoreRateLimiter(5); // Allow 5 operations concurrently

        for (int i = 0; i < 10; i++) {
            rateLimiter.throttle();
        }
    }
}
