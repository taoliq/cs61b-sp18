import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void randomTest() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        String history = "";

        for (int i = 0; i < 500; i++) {
           if (ads.isEmpty() || StdRandom.bernoulli()) {
                if (StdRandom.bernoulli()) {
                    sad.addFirst(i);
                    ads.addFirst(i);
                    history += "addFirst(" + i + ")\n";
                } else {
                    sad.addLast(i);
                    ads.addLast(i);
                    history += "addLast(" + i + ")\n";
                }
           } else {
               Integer excepted, actual;
               if (StdRandom.bernoulli()) {
                   excepted = ads.removeFirst();
                   actual = sad.removeFirst();
                   history += "removeFrist()\n";
               } else {
                   excepted = ads.removeLast();
                   actual = sad.removeLast();
                   history += "removeLast()\n";
               }
               assertEquals(history, excepted, actual);
           }
        }

    }
}
