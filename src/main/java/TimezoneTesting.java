import java.util.TimeZone;

public class TimezoneTesting {

  public static void main(String[] args) {
    String timezone = "UTC±00:00";
    TimeZone id = TimeZone.getTimeZone(timezone);
    String[] timeZoneId = TimeZone.getAvailableIDs(id.getRawOffset());
    if (timeZoneId.length > 0) {
      timezone = timeZoneId[0];
    }
    System.out.println(timezone);
  }
}
