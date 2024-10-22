package app.algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    private List<String> record1= List.of("161593120","Joana","COVID-19","Spikevax","Primeira","21C16-05","5/30/2022 8:00","5/30/2022 8:24","5/30/2022 9:11","5/30/2022 9:43");
    private List<String> record2= List.of("161593125","João","COVID-19","Spikevax","Primeira","21C16-05","5/30/2022 8:00","5/30/2022 8:19","5/30/2022 9:00","5/30/2022 9:34");
    private List<String> record3= List.of("161593127","Madalena","COVID-19","Spikevax","Primeira","21C16-05","5/30/2022 8:00","5/30/2022 8:01","5/30/2022 8:18","5/30/2022 8:57");
    private List<String> record4= List.of("161593137","Alberto","COVID-19","Spikevax","Primeira","21C16-05","5/30/2022 8:00","5/30/2022 8:00","5/30/2022 8:16","5/30/2022 8:51");

    private List<List<String>> UnorderedList = new ArrayList<>();
    private final List<List<String>> OrderedByLeaving = new ArrayList<>();
    private final List<List<String>> OrderedByArrival = new ArrayList<>();

    @Test
    void quickSortTest() {
        OrderedByArrival.add(record4);
        OrderedByArrival.add(record3);
        OrderedByArrival.add(record2);
        OrderedByArrival.add(record1);

        OrderedByLeaving.add(record4);
        OrderedByLeaving.add(record3);
        OrderedByLeaving.add(record2);
        OrderedByLeaving.add(record1);

        UnorderedList.add(record3);
        UnorderedList.add(record2);
        UnorderedList.add(record1);
        UnorderedList.add(record4);

        assertEquals(OrderedByLeaving, QuickSort.quickSort(UnorderedList,0,UnorderedList.size()-1,9));
        assertEquals(OrderedByArrival, QuickSort.quickSort(UnorderedList,0,UnorderedList.size()-1,7));
    }
}