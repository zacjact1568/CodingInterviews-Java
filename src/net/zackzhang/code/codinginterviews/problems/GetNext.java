package net.zackzhang.code.codinginterviews.problems;

public class GetNext {

    private static class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode parent = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    private TreeLinkNode get(TreeLinkNode pNode) {
        if (pNode == null) return null;
        TreeLinkNode node;
        if (pNode.right != null) {
            // 有右子树
            node = pNode.right;
            // 下一节点是右子树最左边的
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        // 无右子树
        node = pNode;
        while (node.parent != null) {
            if (node.parent.left == node) {
                // 若 node 是其父节点的左子树，父节点就是下一节点
                return node.parent;
            }
            // 若不是，再往上找
            node = node.parent;
        }
        // 若找到根节点都没有符合条件的（说明这是最后一个节点），返回 null
        return null;
    }

    public static void test() {
        TreeLinkNode a = new TreeLinkNode(1);
        TreeLinkNode b = new TreeLinkNode(2);
        TreeLinkNode c = new TreeLinkNode(3);
        TreeLinkNode d = new TreeLinkNode(4);
        TreeLinkNode e = new TreeLinkNode(5);
        TreeLinkNode f = new TreeLinkNode(6);
        TreeLinkNode g = new TreeLinkNode(7);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        b.parent = a;
        c.left = f;
        c.right = g;
        c.parent = a;
        d.parent = b;
        e.parent = b;
        f.parent = c;
        g.parent = c;
        System.out.println(new GetNext().get(e));
    }
}
