package net.zackzhang.code.codinginterviews.problems;

import java.util.Stack;

/** 栈的压入、弹出序列 */
public class IsPopOrder {

    private boolean isPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int num : pushA) {
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == popA[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }

    public static void test() {
        System.out.println(new IsPopOrder().isPopOrder(
                new int[]{1, 2, 3, 4, 5},
                new int[]{4, 5, 3, 2, 1}
        ));
    }
}
