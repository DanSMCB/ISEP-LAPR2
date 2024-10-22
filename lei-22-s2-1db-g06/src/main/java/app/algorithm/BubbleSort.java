package app.algorithm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BubbleSort {

    /**
     * It takes a list of lists, and an index of the list to sort by, and returns a sorted list of lists
     *
     * @param importedList The list of lists that you want to sort.
     * @param timeToSortIndex The index of the column that contains the date/time to sort by.
     * @return A list of lists of strings.
     */
    public static List<List<String>> bubbleSort(List<List<String>> importedList, int timeToSortIndex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy H:mm");
        int n = importedList.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++) {
                if (LocalDateTime.parse(importedList.get(j).get(timeToSortIndex),formatter).isAfter(LocalDateTime.parse(importedList.get(j + 1).get(timeToSortIndex),formatter))) {
                    // swap dateList[j+1] and dateList[j]
                    List<String> temp = importedList.get(j);
                    importedList.set(j, importedList.get(j + 1));
                    importedList.set(j + 1, temp);
                }
            }
        return importedList;
    }
}