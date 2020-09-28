package daily_question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 *
 * 给定一个二叉树
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 进阶：
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 */
public class ConnectRightNode_117 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        // 基础版，非常量级额外空间
        //return fun1(root);

        // 进阶，使用常量级额外空间
        return fun2(root);
    }

    Node last = null,nextStart = null;
    private Node fun2(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root;
        while(start != null) {
            last = null;
            nextStart = null;
            for (Node p = start;p != null;p = p.next) {
                if (p.left != null) {
                    handleNode(p.left);
                }
                if (p.right != null) {
                    handleNode(p.right);
                }
            }
            start = nextStart;
        }
        return root;
    }

    private void handleNode(Node root) {
        if (last != null) {
            last.next = root;
        }
        last = root;
        if (nextStart == null) {
            nextStart = root;
        }
    }

    private Node fun1(Node root) {
        if (root == null) {
            return root;
        }
        List<Node> parentList = new ArrayList<>();
        parentList.add(root);
        while(parentList.size() > 0) {
            List<Node> childrenList = new ArrayList<>();
            Node pre = null;
            while(parentList.size() > 0) {
                Node cur = parentList.remove(0);
                if (pre != null) {
                    pre.next = cur;
                }
                pre = cur;
                if (cur.left != null) {
                    childrenList.add(cur.left);
                }
                if (cur.right != null) {
                    childrenList.add(cur.right);
                }
            }
            parentList = childrenList;
        }
        return root;
    }
}
