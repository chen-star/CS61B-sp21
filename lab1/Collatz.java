/**
 * Class that prints the Collatz sequence starting from a given number.
 *
 * @author Alex Chen
 */
public class Collatz {

    /**
     * Returns the next number of Collatz sequence.
     * @param n: current number
     * @return next sequence number of n
     */
    public static int nextNumber(int n) {
        if (n == 1) {
            return 1;
        }
        return n % 2 == 0 ? n / 2 : n * 3 + 1;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");
        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
    }
}

