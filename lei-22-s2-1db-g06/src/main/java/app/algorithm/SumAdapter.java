package app.algorithm;

import com.isep.mdis.Sum;
/**
 * Adapter class that works with Sum class.
 * @author RodrigoPeireso <1211345@isep.ipp.pt>
 */
public class SumAdapter implements MaxSumSublist{
    /**
     * It returns a sublist of the given list, which is the maximum sum sublist of the given list.
     *
     * @param array an array of integers.
     * @return The maximum sum sublist.
     */
    @Override
    public int[] max(int[] array) {
        return Sum.Max(array);
    }
}
