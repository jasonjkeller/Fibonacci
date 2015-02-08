/**
 * Created by jasonjkeller on 2/7/15.
 * 
 * Fibonacci.java 
 *  
 * A simple program which prompts the user for a Fibonacci number
 * and calculates the result using four different approaches.
 * 
 * Approach 1: Standard recursive solution
 * Approach 2: Recursive solution utilizing caching
 * Approach 3: Dynamic programming solution
 * Approach 4: Space Efficient dynamic programming solution
 */


import java.util.Scanner;

public class Fibonacci {
    static long cache[];
    final static int NO_VALUE = -1;

    public static void main(String[] args) {
        int FIB_MAX; // Fibonacci number to calculate
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter a Fibonacci number to calculate: ");
        FIB_MAX = in.nextInt();
        System.out.println("===========================================");

        // cache to store computed results
        cache = new long[FIB_MAX + 1];
        // initialize cache array for fibRecCached
        for (int i = 0; i < cache.length; i++) {
            cache[i] = NO_VALUE;
        }
        
        if (FIB_MAX > 40)
            System.out.println("Fibonacci number is prohibitively large for non-cached Recursive method");
        else
            System.out.println(fibRec(FIB_MAX) + ", Recursive");

        System.out.println(fibRecCached(FIB_MAX) + ", Recursive - Cached");
        System.out.println(fibDynProg(FIB_MAX) + ", Dynamic Programming");
        System.out.println(fibDynProgEfficient(FIB_MAX) + ", Space Efficient Dynamic Programming");
    }
    
    // Approach 1: Standard recursive solution
    public static long fibRec(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else return (fibRec(n - 1) + fibRec(n - 2));
    }

    // Approach 2: Recursive solution utilizing caching
    public static long fibRecCached(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else if (cache[n] == NO_VALUE) {
            cache[n] = (fibRecCached(n - 1) + fibRecCached(n - 2));
        }
        return cache[n];
    }

    // Approach 3: Dynamic programming solution
    public static long fibDynProg(int n) {
        long cache[] = new long[n + 1];
        cache[0] = 0;
        cache[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }
        return cache[n];
    }
    
    // Approach 4: Space Efficient dynamic programming solution, stores only last two results
    public static long fibDynProgEfficient(int n) {
        long back2 = 0;
        long back1 = 1;
        long next;
    
        if (n == 0) return 0;

        for (int i = 2; i < n; i++) {
            next = back1 + back2;
            back2 = back1;
            back1 = next;
        }
        return (back1 + back2);
    }
}
