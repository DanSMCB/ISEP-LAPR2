package app.algorithm;

/**
 * Adapter class that works with MaximumSum class.
 * @author RodrigoPeireso <1211345@isep.ipp.pt>
 */
public class MaximumSumAdapter implements MaxSumSublist{
    /**
     * It returns a sublist of the given list, which is the maximum sum sublist of the given list.
     *
     * @param array an array of integers.
     * @return The maximum sum sublist.
     */
    @Override
    public int[] max(int[] array) {
        return MaximumSum.getMaxSumSublist(array);
    }
}
