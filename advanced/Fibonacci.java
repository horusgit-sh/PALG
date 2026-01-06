package advanced;

public class Fibonacci
{
    static int index = 45;
    static long n_calls = 0;

    static long fibo_iter(int n)
    {
        long start = System.nanoTime();

        long[] F = new long[n + 2];
        F[0] = 0;
        F[1] = 1;
        for (int i = 2; i <= n; i++)
            F[i] = F[i - 1] + F[i - 2];

        long result = F[n];
        long elapsed = System.nanoTime() - start;
        System.out.println("fibo_iter time: " + (elapsed / 1_000_000.0) + " ms (" + elapsed + " ns)");
        return result;
    }

    static long fibo_rec(int n)
    {
        n_calls = 0;
        long start = System.nanoTime();
        long result = fibo_rec_internal(n);
        long elapsed = System.nanoTime() - start;
        System.out.println("fibo_rec time: " + (elapsed / 1_000_000.0) + " ms (" + elapsed + " ns)");
        return result;
    }

    private static long fibo_rec_internal(int n)
    {
        n_calls++;
        if (n < 2)
            return n;
        else
            return fibo_rec_internal(n - 1) + fibo_rec_internal(n - 2);
    }

    public static void main(String[] args)
    {
        System.out.println("F(" + index + ")= " + fibo_iter(index));
        System.out.println("F(" + index + ")= " + fibo_rec(index));
        System.out.println("Number of recursive calls: " + n_calls);
    }
}
