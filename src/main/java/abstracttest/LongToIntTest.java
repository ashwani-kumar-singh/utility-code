package abstracttest;

import org.springframework.util.ObjectUtils;

public class LongToIntTest {

  public static void main(String[] args) {
    Integer integer = (int) Long.MAX_VALUE;
    System.out.println(Math.toIntExact(Long.MAX_VALUE));
  }
}
