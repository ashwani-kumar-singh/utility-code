import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

  public static String script =
      "local underscore = \"_\"\n" + "local botRef = ARGV[1]\n" + "local agentId = ARGV[2]\n"
          + "local incrementBy = ARGV[3]\n"
          + "local keys = redis.call(\"KEYS\", botRef .. \"_*\")\n"
          + "for i,key in ipairs(keys) do\n"
          + "\tlocal convCount = redis.call(\"HGET\", key, agentId)\n" + "\tif convCount then\n"
          + "\t\tredis.call(\"HINCRBY\", key, agentId, incrementBy)\n" + "\tend\n" + "end\n"
          + "return true";

  private static StatefulRedisConnection<String, String> redisConnection;
  private static String[] keys = new String[] {"botRef", "agentId", "incrementBy"};
  private static RedisAsyncCommands<String, String> redisAsyncCommands;
  private static RedisCommands<String, String> redisCommands;

  public static void main(String[] args) throws InterruptedException {
    redisConnection = getRedisConnection();
    redisAsyncCommands = redisConnection.async();
    redisCommands = redisConnection.sync();
    String digest = redisCommands.scriptLoad(script);
    Random random = new Random();
    AtomicInteger finalCount = new AtomicInteger(0);
    for (int i = 0; i < 10; i++) {
      CompletableFuture.supplyAsync(() -> {
        boolean isIncrement = random.nextBoolean();
        finalCount.updateAndGet(n -> n + (isIncrement ? 1 : -1));
        System.out.println(isIncrement ? "Incr" : "Desr");
        return redisAsyncCommands.evalsha(digest, ScriptOutputType.INTEGER, keys, "9098", "1121",
            isIncrement ? "1" : "-1");
      }).thenAccept(e -> {
        System.out.println(String.format("Updated : %s", e));
      });
    }
    Thread.sleep(3000);
    System.out.println(finalCount.get());
  }

  private static StatefulRedisConnection<String, String> getRedisConnection() {
    RedisURI uri = RedisURI.builder().withHost("localhost").withPort(6379).withDatabase(3).build();
    return RedisClient.create(uri).connect();
  }

  private String compileScript(String script) {
    String digest = null;
    try {
      digest = redisAsyncCommands.scriptLoad(script).get();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return digest;
  }
}
