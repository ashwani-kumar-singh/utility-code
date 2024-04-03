package ratelimiter;

public class RateLimiter {

    public static void main(String[] args) {
        SimpleRateLimiter simpleRateLimiter = new SimpleRateLimiter(2);
        try {
            simpleRateLimiter.throttle();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
