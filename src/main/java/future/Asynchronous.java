package future;

public class Asynchronous {
  public static void main(String[] args) {

  }

  void createThreadLambda(){
    Thread newThread = new Thread(()-> System.out.print("new thread created"));
    newThread.start();
  }
}
