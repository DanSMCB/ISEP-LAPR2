package app.algorithm;

/**
 * Class implemented to obtain the contiguous subsequence with maximum sum.
 *
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public class MaximumSum{

    /**
     * It returns a sublist of the given list, which is the maximum sum sublist of the given list.
     *
     * @param arrivalNumberList an array of integers.
     * @return The maximum sum sublist.
     */
    public static int[] getMaxSumSublist(int[] arrivalNumberList) {
        int[] indexes = findMaxSumSublistIndexes(arrivalNumberList);
        int start = indexes[0], end = indexes[1];

        int n = end - start + 1;
        int[] maxSumSublist = new int[n];
        for (int i = 0; i < n; i++) {
            maxSumSublist[i] = arrivalNumberList[start+i];
        }
        return maxSumSublist;
    }

    /**
     * It returns the start and end indexes of the maximum sum sublist.
     *
     * @param arrivalNumberList The list of arrival numbers.
     * @return The start and end indexes of the maximum sum sublist.
     */
    public static int[] findMaxSumSublistIndexes(int[] arrivalNumberList) {
        int size = arrivalNumberList.length;
        int maximumSubArraySum = arrivalNumberList[0];
        int start = 0, end = 0;

        for (int left = 0; left < size; left++) {

            int currentSum = 0;

            for (int right = left; right < size; right++) {
                currentSum += arrivalNumberList[right];

                if (currentSum > maximumSubArraySum) {
                    maximumSubArraySum = currentSum;
                    start = left;
                    end = right;
                }
            }
        }
        return new int[] {start, end};
    }

    /**
     * It takes an array of integers and returns the sum of those integers.
     *
     * @param list an array of integers.
     * @return The sum of the elements in the array.
     */
    public static int sum(int[] list) {
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        return sum;
    }

}
