package y2020.m01.d23.publish.subscribe.v5;

/**
 * @author adolphor
 */
public class ConcreteSubject extends Subject {
  private String state;

  public void changeState(String newState) {
    state = newState;
    notifyObservers();
  }
}
