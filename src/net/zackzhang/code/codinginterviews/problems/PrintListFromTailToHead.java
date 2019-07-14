package net.zackzhang.code.codinginterviews.problems;

import java.util.ArrayList;
import java.util.Stack;

/** 从尾到头打印链表 */
public class PrintListFromTailToHead {

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 使用栈
    private ArrayList<Integer> print(ListNode listNode) {
        Stack<Integer> s = new Stack<>();
        while (listNode != null) {
            s.push(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> res = new ArrayList<>();
        while (!s.isEmpty()) {
            res.add(s.pop());
        }
        return res;
    }

    // 使用递归
    private ArrayList<Integer> print2(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        recursive(listNode, res);
        return res;
    }

    private void recursive(ListNode head, ArrayList<Integer> res) {
        if (head == null) return;
        recursive(head.next, res);
        res.add(head.val);
    }

    public static void test() {
        // 构造链表
        ListNode a = new ListNode(4);
        ListNode b = new ListNode(6);
        ListNode c = new ListNode(2);
        ListNode d = new ListNode(1);
        ListNode e = new ListNode(7);
        ListNode f = new ListNode(3);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        System.out.println(new PrintListFromTailToHead().print2(a));
    }
}
