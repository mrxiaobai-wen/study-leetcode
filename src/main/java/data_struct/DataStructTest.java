package data_struct;

import org.junit.Test;

import java.io.IOException;

/**
 * 数据结构测试
 */
public class DataStructTest {

    @Test
    public void BSTTest() {
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

    @Test
    public void AVL_RecursionTest() throws IOException {
        // 例1：构造正确
        /*AVL_Recursion tree1 = new AVL_Recursion();
        int[] arr1 = new int[]{10,8,20,4,9,16,24,3,21};
        for (int cur : arr1) {
            tree1.insert(cur);
        }*/

        // 例2：构造错误
        AVL_Recursion tree2 = new AVL_Recursion();
        int[] arr2 = new int[]{3,4,8,9,10,16,20,21,24};
        for (int cur : arr2) {
            tree2.insert(cur);
        }
        // tree2.remove(21);
        tree2.remove(20);
        System.out.println(tree2.search(4));
        System.out.println(tree2.search(20));
    }
}
