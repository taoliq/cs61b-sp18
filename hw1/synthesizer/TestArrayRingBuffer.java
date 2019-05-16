package synthesizer;
import org.junit.Test;

import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
//        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(10);
//        Random random = new Random();
//        while (!arb.isFull()) {
//            arb.enqueue(random.nextInt());
//        }
//        Iterator<Integer> iterator = arb.iterator();
        int[] arb = new int[]{1, 2, 3, 4};
        for (int x : arb) {
            for (int y : arb) {
                System.out.println(x + " + " + y);
            }
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
