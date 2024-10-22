package app.algorithm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class QuickSort{

    /**
     * It takes a list of lists, and swaps the ith and jth lists
     *
     * @param importedList the list of lists that you want to sort
     * @param i the index of the first item to be swapped
     * @param j the index of the first row to be swapped
     */
    public static void swap(List<List<String>> importedList, int i, int j) {
        List<String> temp = importedList.get(i);
        importedList.set(i, importedList.get(j));
        importedList.set(j, temp);
    }

    /**
     * It takes a list of lists, a low index, a high index, and an index of the list to sort by. It then returns the index
     * of the pivot
     *
     * @param importedList the list of lists that we want to sort
     * @param low the lowest index of the array to be sorted
     * @param high the last index of the list
     * @param index the index of the column to be sorted
     * @return The index of the pivot
     */
    public static int partition(List<List<String>> importedList, int low, int high, int index) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy H:mm");
        // pivot
        List<String> pivot = importedList.get(high);

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for(int j = low; j <= high - 1; j++) {

            // If current element is smaller
            // than the pivot
            if (LocalDateTime.parse(importedList.get(j).get(index),formatter).isBefore(LocalDateTime.parse(pivot.get(index),formatter))) {
                // Increment index of
                // smaller element
                i++;
                swap(importedList, i, j);
            }
        }
        swap(importedList, i + 1, high);
        return (i + 1);
    }


    /**
     * The function takes in a list of lists, a low index, a high index, and an index to sort by. It then partitions the
     * list of lists by the index, and then recursively calls itself on the left and right partitions
     *
     * @param importedList the list of lists that is being sorted
     * @param low the lowest index of the list
     * @param high the last index of the list
     * @param index the index of the column that you want to sort by
     * @return The list of lists is being returned.
     */
    public static List<List<String>> quickSort(List<List<String>> importedList, int low, int high, int index) {
        if (low < high) {
            // pi is partitioning index, dateList[p]
            // is now at right place
            int pi = partition(importedList, low, high,index);

            // Separately sort elements before
            // partition and after partition
            quickSort(importedList, low, pi - 1, index);
            quickSort(importedList, pi + 1, high, index);
        }
        return importedList;
    }
}