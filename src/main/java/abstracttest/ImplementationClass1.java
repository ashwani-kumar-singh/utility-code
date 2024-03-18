package abstracttest;

import java.util.Objects;

public class ImplementationClass1 extends AbstractClass {

  static AbstractClass getInstance() {
    if (Objects.isNull(SingletonHelper.INSTANCE)) {
      synchronized (ImplementationClass1.class){
        if(Objects.isNull(SingletonHelper.INSTANCE))
          SingletonHelper.INSTANCE = new ImplementationClass1();
      }
    }
    return SingletonHelper.INSTANCE;
  }

  @Override
  public String get() {
    return "ImplementationClass1";
  }


  private static class SingletonHelper {
    private static AbstractClass INSTANCE;
  }
}
