package net.zackzhang.code.codinginterviews.problems;

public class JumpFloor {

    public int jump(int target) {
        if (target == 0) {
            return 0;
        } else if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            return jump(target - 1) + jump(target - 2);
        }
    }

    public static void test() {
        System.out.println(new JumpFloor().jump(7));
    }
}
