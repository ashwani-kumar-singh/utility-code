import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;
import java.util.UUID;

public class SuperSerialization {

  private static ObjectMapper mapper = new ObjectMapper();
  private static ObjectReader reader = mapper.readerFor(Child.class);

  @SneakyThrows
  public static void main(String[] args) {
    /*Child child = new Child();
    child.setId(UUID.randomUUID().toString());
    System.out.println(child);
    Child child1 = reader.readValue(mapper.writeValueAsString(child));
    System.out.println(child1);

    ZoneId zoneId = TimeZone.getTimeZone("Asia/Calcutta").toZoneId();
    System.out.println(zoneId);
    System.out.println(ZoneId.of("Asia/Calcutta"));

    ZonedDateTime now = ZonedDateTime.now();
    ZonedDateTime before1MinFromNow = now.minus(1, ChronoUnit.MINUTES);
    ZonedDateTime after1MinFromNow = now.plus(1, ChronoUnit.MINUTES);
    System.out.println(Duration.between(before1MinFromNow, now).toMinutes());
    System.out.println(Duration.between(before1MinFromNow, now).toMinutes());
    System.out.println(Duration.between(now, after1MinFromNow).toMinutes());
    System.out.println(Duration.between(after1MinFromNow, now).toMinutes());*/
  }

  @Setter
  @Getter
  @ToString
  @EqualsAndHashCode
  private static class Parent {

    private String id;

  }


  @Setter
  @Getter
  @ToString(callSuper = true)
  @EqualsAndHashCode(callSuper = true)
  private static class Child extends Parent {

  }
}