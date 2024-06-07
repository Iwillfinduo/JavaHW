package edu.phystech.hw4.stepper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kzlv4natoly
 */

public class Stepper {

    public enum Side {
        LEFT, RIGHT
    }

    private final List<Side> history = new ArrayList<>();
    private final Object lock = new Object();
    private boolean isLeftTurn = true;

    public void leftStep() {
        synchronized (lock) {
            while (!isLeftTurn) {
                try {
                    lock.wait();
                }
                catch (Exception exception) {
                    Thread.currentThread().interrupt();
                }
            }
            isLeftTurn = false;
            this.history.add(Side.LEFT);

            lock.notifyAll(); // used notifyAll() because last test didn't pass with notify
        }
    }

    public void rightStep()  {
        synchronized (lock) {
            while (isLeftTurn) {
                try {
                    lock.wait();
                }
                catch (Exception exception) {
                    Thread.currentThread().interrupt();
                }
            }
            isLeftTurn = true;
            this.history.add(Side.RIGHT);

            lock.notifyAll();
        }
    }

    public List<Side> getHistory() {
        return history;
    }
}
