package app.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaximumSumTest {

    @Test
    void testGetMaxSumSublist() {
        int[] list1 = {15, 23, -3, 5, 9, -10}, list2 = {-1, 2, 3, -2, 1,-1};
        int[] expected1 = {15, 23, -3, 5, 9}, expected2 = {2, 3};
        Assertions.assertArrayEquals(expected1, MaximumSum.getMaxSumSublist(list1));
        Assertions.assertArrayEquals(expected2, MaximumSum.getMaxSumSublist(list2));
    }

    @Test
    void testFindMaxSumSublistIndexes() {
        int[] list1 = {15, 23, -3, 5, 9, -10}, list2 = {-1, 2, 3, -2, 1,-1};
        int[] expected1 = {0,4}, expected2 = {1, 2};
        Assertions.assertArrayEquals(expected1, MaximumSum.findMaxSumSublistIndexes(list1));
        Assertions.assertArrayEquals(expected2, MaximumSum.findMaxSumSublistIndexes(list2));
    }
}