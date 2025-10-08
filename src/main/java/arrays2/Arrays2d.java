package main.java.arrays2;

import java.util.Random;

public class Arrays2d {

    private static void prohodRadky(int[][] a, int i, int k) {
        int M = a.length;
        int N = a[0].length;

        if (i < 0 || i >= M || k < 0 || k >= M) {
            throw new IllegalArgumentException("Row index out of bounds");
        }
        else
        {
            for (int j = 0; j < N; j++) {
                int temp = a[i][j];
                a[i][j] = a[k][j];
                a[k][j] = temp;
            }
        }

    }

    private static void prohodSloupce(int[][] a, int j, int k) {
        int M = a.length;
        int N = a[0].length;

        if (j < 0 || j >= N || k < 0 || k >= N) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        else
        {
            for (int i = 0; i < M; i++) {
                int temp = a[i][j];
                a[i][j] = a[i][k];
                a[i][k] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int M = 1 << 13;
        int N = M;

        int [][] A = new int [M][N];
        //fillRandom(A, 0, 9);
        /*
        transformMatrix(A);
        printMatrix(A);
        */
        measureExecutionTime(() -> {
            transformMatrix(A);
        });
    }
    private static void printMatrix(int[][] A) {
        int M = A.length;
        int N = A[0].length;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void fillRandom(int[][] A, int min, int max) {
        Random rand = new Random();
        int M = A.length;
        int N = A[0].length;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = rand.nextInt(max - min + 1) + min;
            }
        }
    }

    private static void flipMatrix(int[][] A) {
        int M = A.length;
        for (int i = 0; i < M / 2; i++) {
            prohodr3(A, i, M - 1 - i);
        }
    }

    private static void prohodr2(int[][] a, int i, int k) {
        int M = a.length;
        int N = a[0].length;

        if (i < 0 || i >= M || k < 0 || k >= M) {
            throw new IllegalArgumentException("Row index out of bounds");
        } else {
            int[] pom = new int[N];
            System.arraycopy(a[i], 0, pom, 0, N);
            System.arraycopy(a[k], 0, a[i], 0, N);
            System.arraycopy(pom, 0, a[k], 0, N);
        }
    }


    private static void prohodr3(int[][] a, int i, int k) {
        int M = a.length;

        if (i < 0 || i >= M || k < 0 || k >= M) {
            throw new IllegalArgumentException("Row index out of bounds");
        } else {
            int[] pom = a[i];
            a[i] = a[k];
            a[k] = pom;
        }
    }

    private static void mirror2(int[][] a) {
        int M = a.length;
        int N = a[0].length;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N/2; j++) {
                int pom = a[i][j];
                a[i][j] = a[i][N-1-j];
                a[i][N - 1 - j] = pom;
            }
        }
    }

    private static void transformMatrix(int[][] a) {
        int M = a.length;
        int N = a[0].length;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j || i + j == M - 1) {
                    a[i][j] = 0;
                } else if (i < j && i + j < M - 1) {
                    a[i][j] = 1;
                } else if (i < j && i + j > M - 1) {
                    a[i][j] = 2;
                } else if (i > j && i + j > M - 1) {
                    a[i][j] = 3;
                } else {
                    a[i][j] = 4;
                }
            }
        }
    }

    private static void measureExecutionTime(Runnable task) {
        long start = System.nanoTime();
        task.run();
        long end = System.nanoTime();
        float durationMs = (float)(end - start) / 1_000_000;
        System.out.println("Execution time: " + durationMs + " ms");
    }

}
