package data_struct;

import common.TreeNode;

/**
 * 二叉排序树
 */
public class BST {

    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(10);
        bst.insert(6);
        bst.insert(14);
        bst.insert(4);
        bst.insert(8);
        bst.insert(12);
        bst.insert(16);
        bst.insert(17);
        System.out.println(bst.search(14));
        System.out.println(bst.remove(14));
        System.out.println(bst.search(14));
        System.out.println(bst.search(12));
        System.out.println(bst.search(16));
    }

    private TreeNode root;

    public boolean search(int data) {
        if (root == null) {
            return false;
        }
        TreeNode search = root;
        while (search != null) {
            if (search.val == data) {
                return true;
            } else if (search.val > data) {
                search = search.left;
            } else {
                search = search.right;
            }
        }
        return false;
    }

    public boolean insert(int data) {
        TreeNode cur = new TreeNode();
        cur.val = data;
        if (root == null) {
            root = cur;
            return true;
        }
        TreeNode search = root;
        while (search != null) {
            if (search.val == data) {
                return false;
            } else if (search.val > data) {
                if (search.left == null) {
                    search.left = cur;
                    return true;
                }
                search = search.left;
            } else {
                if (search.right == null) {
                    search.right = cur;
                    return true;
                }
                search = search.right;
            }
        }
        return true;
    }

    public boolean remove(int data) {
        if (root == null) {
            return false;
        }
        TreeNode removeParent = null;
        TreeNode removeNode = root;
        while (removeNode != null) {
            if (removeNode.val == data) {
                break;
            }
            removeParent = removeNode;
            if (removeNode.val > data) {
                removeNode = removeNode.left;
            } else {
                removeNode = removeNode.right;
            }
        }
        if (removeNode == null) {
            return false;
        }
        if (removeNode.left == null && removeNode.right == null) {
            // 1、没有左右孩子节点，直接删除
            if (removeParent == null) {
                root = null;
            } else if (removeParent.left == removeNode) {
                removeParent.left = null;
            } else {
                removeParent.right = null;
            }
        } else if (removeNode.right == null) {
            // 2、只有左孩子节点
            if (removeParent == null) {
                root = removeNode.left;
            } else if (removeParent.left == removeNode) {
                removeParent.left = removeNode.left;
            } else {
                removeParent.right = removeNode.left;
            }
        } else if (removeNode.left == null) {
            // 3、只有右孩子节点
            if (removeParent == null) {
                root = removeNode.right;
            } else if (removeParent.left == removeNode) {
                removeParent.left = removeNode.right;
            } else {
                removeParent.right = removeNode.right;
            }
        } else {
            // 4、左右孩子节点都存在
            TreeNode realRP = removeNode;
            TreeNode realRN = removeNode.right;
            while (realRN != null && realRN.left != null) {
                realRP = realRN;
                realRN = realRN.left;
            }
            removeNode.val = realRN.val;
            if (realRP == removeNode) {
                removeNode.right = realRN.right;
            } else {
                realRP.left = realRN.right;
            }
        }
        return true;
    }
}
