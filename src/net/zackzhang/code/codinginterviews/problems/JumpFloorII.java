package net.zackzhang.code.codinginterviews.problems;

public class JumpFloorII {

    public int jump(int target) {
        // 2^(n-1)
        int result = 1;
        for (int i = 0; i < target - 1; i++) {
            result *= 2;
        }
        return result;
    }

    public int jump2(int target) {
        return 1 << (target - 1);
    }

    public static void test() {
        System.out.println(new JumpFloorII().jump2(7));
    }
}
