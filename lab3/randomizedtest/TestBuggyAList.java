package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correctList = new AListNoResizing<>();
        BuggyAList<Integer> bugList = new BuggyAList<>();

        for (int i = 0; i < 3; i++) {
            correctList.addLast(i);
            bugList.addLast(i);
        }

        for (int i = 0; i < 3; i++) {
            assertEquals(correctList.removeLast(), bugList.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                assertEquals(L.getLast(), B.getLast());
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                assertEquals(size, B.size());
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                // removeLast
                assertEquals(L.size(), B.size());
                if (L.size() == 0) {
                    continue;
                }
                assertEquals(L.removeLast(), B.removeLast());
            }
        }
    }
}
