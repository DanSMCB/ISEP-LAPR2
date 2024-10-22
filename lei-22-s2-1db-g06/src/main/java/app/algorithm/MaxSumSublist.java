package app.algorithm;

/**
 * Interface to the algorithms that calculate the max sum sublist
 * @author Rodrigo Peireso <1211345@isep.ipp.pt>
 */
public interface MaxSumSublist {
    /**
     * It returns a sublist of the given list, which is the maximum sum sublist of the given list.
     *
     * @param array an array of integers.
     * @return The maximum sum sublist.
     */
    int[] max(int[] array);

}
