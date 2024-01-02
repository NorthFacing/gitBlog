package y2020.m01.d23.listener.v1;

/**
 * @author adolphor
 */
public class ConcreteSubject extends Subject {
  private String state;

  public void changeState(String newState) {
    state = newState;
    notifyObservers(state);
  }
}
