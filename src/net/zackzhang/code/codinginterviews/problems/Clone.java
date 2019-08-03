package net.zackzhang.code.codinginterviews.problems;

import java.util.HashMap;

/** 复杂链表的复制 */
public class Clone {

    private static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    private RandomListNode clone(RandomListNode pHead) {
        // 新旧链表对应节点键值对
        HashMap<RandomListNode, RandomListNode> hm = new HashMap<>();
        RandomListNode bHead = null, aCurNode = pHead, bLastNode = null;
        // 遍历链表
        while (aCurNode != null) {
            // 新当前节点
            RandomListNode bCurNode;
            // 检查新当前节点是否已创建
            if (hm.containsKey(aCurNode)) {
                // 已创建，直接取
                bCurNode = hm.get(aCurNode);
            } else {
                // 未创建，创建并加入 hm
                bCurNode = new RandomListNode(aCurNode.label);
                hm.put(aCurNode, bCurNode);
            }
            // 处理 next
            if (bLastNode == null) {
                // 新头节点
                bHead = bCurNode;
            } else {
                // 将让新上一节点指向新当前节点
                bLastNode.next = bCurNode;
            }
            // 更新新链表的上一节点
            bLastNode = bCurNode;
            // 处理 random
            RandomListNode aRandNode = aCurNode.random, bRandNode;
            if (aRandNode != null) {
                // 由于 hm 中有且仅有已创建的节点
                // 因此在 hm 中查找旧当前节点的 random
                if (hm.containsKey(aRandNode)) {
                    // 找到
                    bRandNode = hm.get(aRandNode);
                } else {
                    // 未找到，新建并加入 hm
                    bRandNode = new RandomListNode(aRandNode.label);
                    hm.put(aRandNode, bRandNode);
                }
                // 将新当前节点的 random 指向它
                bCurNode.random = bRandNode;
            }
            // 更新旧链表的当前节点
            aCurNode = aCurNode.next;
        }
        return bHead;
    }

    private RandomListNode clone2(RandomListNode pHead) {
        if (pHead == null) return null;
        RandomListNode curNode = pHead;
        // 新建节点，将其插入旧节点后面
        while (curNode != null) {
            RandomListNode newNode = new RandomListNode(curNode.label);
            RandomListNode nextNode = curNode.next;
            curNode.next = newNode;
            newNode.next = nextNode;
            curNode = nextNode;
        }
        curNode = pHead;
        // 复制旧结点的 random 给新结点
        while (curNode != null) {
            curNode.next.random = curNode.random == null ? null : curNode.random.next;
            curNode = curNode.next.next;
        }
        curNode = pHead;
        // 按新旧节点拆分链表
        RandomListNode newHead = pHead.next;
        while(curNode != null) {
            RandomListNode newNode = curNode.next;
            curNode.next = newNode.next;
            newNode.next = newNode.next == null ? null : newNode.next.next;
            curNode = curNode.next;
        }
        return newHead;
    }

    public static void test() {
        // 构造复杂链表
        RandomListNode a = new RandomListNode(1);
        RandomListNode b = new RandomListNode(2);
        RandomListNode c = new RandomListNode(3);
        RandomListNode d = new RandomListNode(4);
        RandomListNode e = new RandomListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        a.random = c;
        b.random = e;
        d.random = b;
        System.out.println(new Clone().clone(a));
    }
}
