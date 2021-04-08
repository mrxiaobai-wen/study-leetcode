package data_struct;

/**
 * AVL递归实现
 */
public class AVL_Recursion {

    private AVLTreeNode root;

    public AVL_Recursion() {}

    public AVLTreeNode search(int key) {
        return search(root,key);
    }

    public void insert(int key) {
        root = insert(root,key);
    }

    public boolean remove(int key) {
        AVLTreeNode removeNode = null;
        if ((removeNode = search(key)) != null) {
            root = remove(root,removeNode);
            return true;
        }
        return false;
    }

    private AVLTreeNode search(AVLTreeNode cur,int key) {
        while (cur != null) {
            if (cur.key == key) {
                return cur;
            } else if (cur.key < key) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return null;
    }

    private AVLTreeNode insert(AVLTreeNode cur,int key) {
        if (cur == null) {
            return new AVLTreeNode(key);
        }
        if (cur.key == key) {
            return cur;
        } else if (cur.key > key) {
            cur.left = insert(cur.left,key);
            if (height(cur.left) - height(cur.right) == 2) {
                // 平衡遭到破坏
                if (key < cur.left.key) {
                    // 左左情况
                    cur = LLAdjust(cur);
                } else {
                    // 左右情况
                    cur = LRAdjust(cur);
                }
            }
        } else {
            cur.right = insert(cur.right,key);
            if (height(cur.right) - height(cur.left) == 2) {
                if (key > cur.right.key) {
                    // 右右情况
                    cur = RRAdjust(cur);
                } else {
                    // 右左情况
                    cur = RLAdjust(cur);
                }
            }
        }
        cur.height = Math.max(height(cur.left),height(cur.right)) + 1;
        return cur;
    }

    private AVLTreeNode remove(AVLTreeNode cur,AVLTreeNode needRemoveNode) {
        if (cur == null || needRemoveNode == null) {
            return null;
        }
        if (needRemoveNode.key < cur.key) {
            // 在左子树上删除
            cur.left = remove(cur.left,needRemoveNode);
            if (height(cur.right) - height(cur.left) == 2) {
                // 失去平衡
                AVLTreeNode right = cur.right;
                if (height(right.left) > height(right.right)) {
                    cur = RLAdjust(cur);
                } else {
                    cur = RRAdjust(cur);
                }
            }
        } else if (needRemoveNode.key > cur.key) {
            // 在右子树上删除
            cur.right = remove(cur.right,needRemoveNode);
            if (height(cur.left) - height(cur.right) == 2) {
                AVLTreeNode left = cur.left;
                if (height(left.right) > height(left.left)) {
                    cur = LRAdjust(cur);
                } else {
                    cur = LLAdjust(cur);
                }
            }
        } else {
            // 当前为待删除节点
            if (cur.left != null && cur.right != null) {
                // 待删除节点左右子节点都存在
                if (height(cur.left) > height(cur.right)) {
                    // 如果左子树比右子树高，那么找出左子树中的最大节点，替换当前节点，然后删除左子树最大节点
                    AVLTreeNode maxNode = max(cur.left);
                    cur.key = maxNode.key;
                    cur.left = remove(cur.left,maxNode);
                } else {
                    // 否则找出右子树中的最小节点，替换当前节点，然后删除右子树最小节点
                    AVLTreeNode minNode = min(cur.right);
                    cur.key = minNode.key;
                    cur.right = remove(cur.right,minNode);
                }
            } else {
                cur = cur.left != null ? cur.left : cur.right;
            }
        }
        return cur;
    }

    /**
     * 左旋
     */
    private AVLTreeNode leftRotation(AVLTreeNode cur) {
        AVLTreeNode newNode = cur.right;
        cur.right = newNode.left;
        newNode.left = cur;
        cur.height = Math.max(height(cur.left),height(cur.right)) + 1;
        newNode.height = Math.max(height(newNode.left),height(newNode.right)) + 1;
        return newNode;
    }

    /**
     * 右旋
     */
    private AVLTreeNode rightRotation(AVLTreeNode cur) {
        AVLTreeNode newNode = cur.left;
        cur.left = newNode.right;
        newNode.right = cur;
        cur.height = Math.max(height(cur.left),height(cur.right)) + 1;
        newNode.height = Math.max(height(newNode.left),height(newNode.right)) + 1;
        return newNode;
    }

    /**
     * 左左对应情况-右旋
     */
    private AVLTreeNode LLAdjust(AVLTreeNode cur) {
        return rightRotation(cur);
    }

    /**
     * 右右对应情况-左旋
     */
    private AVLTreeNode RRAdjust(AVLTreeNode cur) {
        return leftRotation(cur);
    }

    /**
     * 左右对应情况-先左旋左子树，后右旋当前节点
     */
    private AVLTreeNode LRAdjust(AVLTreeNode cur) {
        leftRotation(cur.left);
        return rightRotation(cur);
    }

    /**
     * 右左对应情况-先右旋右子树，后左旋当前节点
     */
    private AVLTreeNode RLAdjust(AVLTreeNode cur) {
        rightRotation(cur.right);
        return leftRotation(cur);
    }

    private int height(AVLTreeNode node) {
        if (node != null) {
            return node.height;
        }
        return 0;
    }

    private AVLTreeNode min(AVLTreeNode cur) {
        if (cur == null) {
            return null;
        }
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    private AVLTreeNode max(AVLTreeNode cur) {
        if (cur == null) {
            return null;
        }
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur;
    }

    private class AVLTreeNode {
        public int key;
        public int height = 1;
        public AVLTreeNode left;
        public AVLTreeNode right;

        public AVLTreeNode() {}

        public AVLTreeNode(int key) {
            this.key = key;
        }
    }
}
