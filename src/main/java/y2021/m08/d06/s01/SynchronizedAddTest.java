package y2021.m08.d06.s01;

/**
 * @Author: Bob.Zhu
 * @Date: 2021/8/22 17:12
 */
public class SynchronizedAddTest {
  public volatile int i;
  public synchronized void add() {
    i++;
  }
}
