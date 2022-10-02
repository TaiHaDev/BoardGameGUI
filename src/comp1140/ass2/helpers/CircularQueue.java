package comp1140.ass2.helpers;

import java.util.LinkedList;
import java.util.Queue;

public class CircularQueue<T> extends LinkedList<T> implements Queue<T> {

    /**
     * Implements the circular queue data structure
     * through overriding the pop method and re-queueing
     * the head.
     *
     * @return T
     */
    @Override
    public T pop() {
        this.addLast(this.getFirst());
        this.removeFirst();
        return this.getFirst();
    }

}