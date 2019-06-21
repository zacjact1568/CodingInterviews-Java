package net.zackzhang.code.codinginterviews.problems;

/** 反转链表 */
public class ReverseList {

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode now = head;
        ListNode next;
        while (now != null) {
            next = now.next;
            now.next = prev;
            prev = now;
            now = next;
        }
        return prev;
    }

    private ListNode reverse2(ListNode head) {
        // 若 head 为最末节点，返回
        if (head == null || head.next == null) return head;
        // last 总是最末节点
        ListNode last = reverse2(head.next);
        // 每一层递归的 head 依次表示链表上的所有节点
        // 在每一层递归中将 head 与 head.next 的关系反转即可
        head.next.next = head;
        head.next = null;
        return last;
    }

    public static void test() {
        // 构造链表
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(5);
        ListNode d = new ListNode(8);
        ListNode e = new ListNode(3);
        ListNode f = new ListNode(6);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        System.out.println(new ReverseList().reverse(a).val);
    }
}
