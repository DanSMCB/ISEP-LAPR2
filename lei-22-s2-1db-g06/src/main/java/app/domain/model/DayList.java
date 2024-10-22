package app.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;

public class DayList implements Serializable {
    /**
     * Hour that a certain vaccination center opens
     */
    private LocalTime openingHour;
    /**
     *  Hour that a certain vaccination center closes
     */
    private LocalTime closingHour;
    /**
     * Slot duration of the mass vaccination center
     */
    private int slotDuration;
    /**
     * Number of vaccines per slot of the mass vaccination center
     */
    private int numberOfVaccinesSlot;
    /**
     * List responsible for storing days
     */
    private List<DaySchedule>dayList;

    /**
     * This function adds a day to the dayList
     *
     * @param day day to add to the list
     */
    public void addDay(DaySchedule day){
        dayList.add(day);
    }
    /**
     * It takes a date as a parameter and returns the day schedule that corresponds to that date
     *
     * @param date the date of the day you want to find
     * @return The date that matches the search
     */
    public DaySchedule findDay(LocalDate date){
        if (dayList.isEmpty()) {
            DaySchedule day = new DaySchedule(openingHour, closingHour, slotDuration, numberOfVaccinesSlot, date);
            addDay(day);
            return day;
        }else{
            for (DaySchedule day:dayList) {
                if(day.checkDay(date))
                    return day;
            }
            DaySchedule day = new DaySchedule(openingHour, closingHour, slotDuration, numberOfVaccinesSlot, date);
            addDay(day);
            return day;
        }
    }
    public DayList(LocalTime openingHour, LocalTime closingHour, int slotDuration, int numberOfVaccinesSlot){
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.slotDuration = slotDuration;
        this.numberOfVaccinesSlot = numberOfVaccinesSlot;
        this.dayList= new ArrayList<>();
    }
}
