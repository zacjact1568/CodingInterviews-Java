package net.zackzhang.code.codinginterviews.problems;

public class RectCover {

    public int cover(int target) {
        if (target == 0) {
            return 0;
        } else if (target == 1) {
            // f(1) = 1
            return 1;
        } else if (target == 2) {
            // f(2) = 2
            return 2;
        } else {
            // f(n) = f(n-1) + f(n-2)
            return cover(target - 1) + cover(target - 2);
        }
    }

    public static void test() {
        System.out.println(new RectCover().cover(4));
    }
}
