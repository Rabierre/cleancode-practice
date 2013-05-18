package me.rabierre.prime;

/**
 * Created with IntelliJ IDEA.
 * User: seojihye
 * Date: 13. 5. 14.
 * Time: 오전 2:01
 * To change this template use File | Settings | File Templates.
 */
public class RefactoredPrintPrimes {
    private static final int MAX_NUMBER_RANGE = 101;
    public static final int FIRST_PRIME_NUMBER = 2;

    private static int primes[] = new int[MAX_NUMBER_RANGE];

    private static final int ORDMAX = 30;

    private static int multi[] = new int[ORDMAX + 1];


    public static void main(String[] args) {
        findPrimes();
        printPrimePages(primes);
    }

    private static void findPrimes() {
        primes[1] = 2;

        int J = 1;
        int ORD = 2;
        int SQUARE = 9;

        for (int i = FIRST_PRIME_NUMBER; i < MAX_NUMBER_RANGE; ++i) {
            do {
                J = J + 2;  // 홀수는 2씩 증가
                if (J == SQUARE) {
                    ORD = ORD + 1;
                    SQUARE = primes[ORD] * primes[ORD];
                    multi[ORD - 1] = J;
                }

            } while (!isPrime(J, ORD));
            primes[i] = J;
        }
    }

    private static boolean isPrime(int j, int ORD) {
        for (int number = 2; number < ORD; ++number) {
            while (multi[number] < j) {
                multi[number] = multi[number] + primes[number] * 2;   // fixme ?
            }
            if (multi[number] == j) {
                return false;
            }
        }
        return true;
    }

    // todo extract to class
    private static void printPrimePages(int[] primes) {
        final int RR = 50;
        final int CC = 4;
        int pageNumber = 1;
        int pageOffSet = 1;
        int rowOffSet;

        while (pageOffSet < MAX_NUMBER_RANGE) {
            System.out.println("The first " + MAX_NUMBER_RANGE + "Prime Numbers --- Page " + pageNumber + "\n");
            for (rowOffSet = pageOffSet; rowOffSet < pageOffSet + RR; rowOffSet++) {
                for (int C = 0; C < CC; C++) {
                    if (rowOffSet + C * RR < MAX_NUMBER_RANGE) {
                        System.out.println(primes[rowOffSet + C * RR] + "\n");
                    }
                }
            }
            System.out.println("\f");
            pageNumber = pageNumber + 1;
            pageOffSet = pageOffSet + RR * CC;
        }
    }
}
