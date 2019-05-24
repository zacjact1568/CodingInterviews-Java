package net.zackzhang.code.codinginterviews.problems;

import java.util.Stack;

public class StackWithMinFunction {

    private Stack<Integer> mStack = new Stack<>();
    // 用来装递减元素
    private Stack<Integer> mMinStack = new Stack<>();

    private void push(int node) {
        mStack.push(node);
        if (mMinStack.isEmpty() || node < mMinStack.peek()) {
            // 当 mMinStack 为空或新入栈的元素小于 mMinStack 中栈顶元素（最小元素）才入栈
            mMinStack.push(node);
        } else {
            // 否则将当前 mMinStack 的栈顶元素重复入栈
            // i.e. 在 mMinStack 中，用最小元素代替当前入栈的元素
            // 这样可以保证 mMinStack 栈顶始终是最小元素
            mMinStack.push(mMinStack.peek());
        }
    }

    private void pop() {
        mStack.pop();
        mMinStack.pop();
    }

    public int top() {
        return mStack.peek();
    }

    private int min() {
        return mMinStack.peek();
    }

    public static void test() {
        StackWithMinFunction swmf = new StackWithMinFunction();
        swmf.push(5);
        swmf.push(1);
        swmf.pop();
        swmf.push(3);
        swmf.push(2);
        swmf.pop();
        System.out.println(swmf.min());
    }
}
