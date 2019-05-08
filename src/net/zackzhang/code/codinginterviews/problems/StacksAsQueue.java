package net.zackzhang.code.codinginterviews.problems;

import java.util.Stack;

public class StacksAsQueue {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        /* 这一段多余，写复杂了
        // 当 stack2 不空，把数据从 stack2 拉到 stack1 来（倒序）
        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }
        // 再执行压入
        */
        stack1.push(node);
    }

    public int pop() {
        // 当 stack2 为空，从 stack1 拉数据（倒序）
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        // 再执行弹出
        return stack2.pop();
    }

    public static void test() {
        StacksAsQueue saq = new StacksAsQueue();
        saq.push(1);
        saq.push(2);
        saq.push(3);
        System.out.println(saq.pop());
        saq.push(4);
        System.out.println(saq.pop());
        System.out.println(saq.pop());
        saq.push(5);
        System.out.println(saq.pop());
        saq.push(6);
        saq.push(7);
        saq.push(8);
        System.out.println(saq.pop());
    }
}
