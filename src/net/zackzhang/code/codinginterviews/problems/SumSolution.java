package net.zackzhang.code.codinginterviews.problems;

public class SumSolution {

    public int sum(int n) {
        int result = n;
        int temp1 = 0;
        boolean temp2 = (n > 0) && temp1 == (result += sum(n - 1));
        return result;
    }

    public static void test() {
        System.out.println(new SumSolution().sum(23));
    }
}
