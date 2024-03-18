package abstracttest;

import java.util.Objects;

public class ImplementationClass2 extends AbstractClass {

  static AbstractClass getInstance() {
    if (Objects.isNull(SingletonHelper.INSTANCE)) {
      synchronized (ImplementationClass2.class){
        if(Objects.isNull(SingletonHelper.INSTANCE))
          SingletonHelper.INSTANCE = new ImplementationClass2();
      }
    }
    return SingletonHelper.INSTANCE;
  }

  @Override
  public String get() {
    return "ImplementationClass2";
  }


  private static class SingletonHelper {
    private static AbstractClass INSTANCE;
  }
}
