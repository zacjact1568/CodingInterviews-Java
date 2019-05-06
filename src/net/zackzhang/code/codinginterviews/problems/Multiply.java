package net.zackzhang.code.codinginterviews.problems;

import java.util.Arrays;

public class Multiply {

    public int[] multiply(int[] A) {
        int[] B = new int[A.length];
        int result;
        for (int i = 0; i < B.length; i++) {
            result = 1;
            for (int j = 0; j < A.length; j++) {
                if (i == j) continue;
                result *= A[j];
            }
            B[i] = result;
        }
        return B;
    }

    public int[] multiply2(int[] A) {
        int n = A.length;
        int[] B = new int[n];
        B[0] = 1;
        for (int i = 1; i < n; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }
        int temp = 1;
        for (int i = n - 1; i >= 0; i--) {
            B[i] *= temp;
            temp *= A[i];
        }
        return B;
    }

    public static void test() {
        System.out.println(Arrays.toString(new Multiply().multiply2(new int[]{1, 2, 4, 8})));
    }
}
