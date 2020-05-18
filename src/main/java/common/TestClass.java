package common;

import dynamic_programming.medium.NumMatrix;
import org.junit.Test;

public class TestClass {

    @Test
    public void testNumMatrix() {
        int[][] matrix = new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        NumMatrix test = new NumMatrix(matrix);

        System.out.println(test.sumRegion(2, 1, 4, 3));
        System.out.println(test.sumRegion(1, 1, 2, 2));
        System.out.println(test.sumRegion(1, 2, 2, 4));
    }
}
