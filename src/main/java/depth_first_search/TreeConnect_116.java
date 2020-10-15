package depth_first_search;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 *
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
 * <p>
 * 输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}
 * <p>
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TreeConnect_116 {
    /**
     * 思路一：使用层序遍历，
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        return fun1(root);
    }

    /**
     * 使用层序遍历。因为是完美二叉树，所以可以使用2的次方来界定每一层的结束点。
     * 广度优先！
     */
    private Node fun1(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> tempQueue = new LinkedBlockingQueue<>();
        int level = 0;
        tempQueue.add(root);
        int index = 1;
        int curNum = 1;
        while (!tempQueue.isEmpty()) {
            Node currentNode = tempQueue.poll();
            if (currentNode.left != null && currentNode.right != null) {
                tempQueue.add(currentNode.left);
                tempQueue.add(currentNode.right);
            }
            if (index < curNum) {
                Node nextNode = tempQueue.peek();
                currentNode.next = nextNode;
                index++;
            } else {
                level++;
                index = 1;
                curNum = (int) Math.pow(2, level);
            }
        }

        return root;
    }

    private Node fun2(Node root) {
        // 参考代码
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 1; i <= size; i++) {
                Node currentNode = queue.poll();
                if (currentNode.left != null && currentNode.right != null) {
                    queue.add(currentNode.left);
                    queue.add(currentNode.right);
                }
                if (i < size) {
                    Node nextNode = queue.peek();
                    currentNode.next = nextNode;
                }
            }
        }

        return root;
    }

    /**
     * 参考简洁代码
     * </p>
     * 进阶：常量级额外空间
     */
    private Node fun3(Node root) {
        /**
         * void connect(TreeLinkNode *root) {
         *     if (root == NULL) return;
         *     TreeLinkNode *pre = root;
         *     TreeLinkNode *cur = NULL;
         *     while(pre->left) {
         *         cur = pre;
         *         while(cur) {
         *             cur->left->next = cur->right;
         *             if(cur->next) cur->right->next = cur->next->left;
         *             cur = cur->next;
         *         }
         *         pre = pre->left;
         *     }
         * }
         */

        if (root == null) {
            return null;
        }
        Node pre = root;
        while (pre.left != null) {
            Node cur = pre;
            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            pre = pre.left;
        }

        return root;
    }


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

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

    ;
}
