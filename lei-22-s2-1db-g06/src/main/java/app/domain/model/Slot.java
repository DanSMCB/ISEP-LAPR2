package app.domain.model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;

import java.util.concurrent.TimeUnit;

public class Slot implements Serializable {
    /**
     * The starting time of a given slot
     */
    private LocalTime startTime;
    /**
     * The ending time of a given slot
     */
    private LocalTime endTime;
    /**
     * Slot duration of the mass vaccination center
     */
    private int slotDuration;
    /**
     * Number of vaccines per slot of the mass vaccination center
     */
    private int numberOfVaccinesSlot;

    /**
     * Creates an instance of Slot, receiving startTime,slotDuration and numberOfVaccinesSlot;
     * @param startTime
     * @param slotDuration
     * @param numberOfVaccinesSlot
     */
    public Slot(LocalTime startTime, int slotDuration, int numberOfVaccinesSlot) {
        this.startTime=LocalTime.of(startTime.getHour(), startTime.getMinute(), startTime.getSecond());
        this.slotDuration = slotDuration;
        this.numberOfVaccinesSlot = numberOfVaccinesSlot;
        this.endTime = startTime.plusMinutes(slotDuration);
    }
    /**
     * This function returns the start time of the event
     *
     * @return startTime
     */
    public LocalTime getStartTime() {
        return startTime;
    }
    /**
     * This function returns the end time of the event
     *
     * @return endTime
     */
    public LocalTime getEndTime(){
        return endTime;
    }
    /**
     * This function is used to add a scheduled vaccine by decreasing the number of available slots
     */
    public void addScheduledVaccine(){
        this.numberOfVaccinesSlot=numberOfVaccinesSlot-1;
    }
    /**
     * This function returns true if the number of vaccines in the slot is greater than or equal to 1
     *
     * @return boolean value of the comparison
     */
    public boolean validateSlot(){
        return this.numberOfVaccinesSlot >= 1;
    }
    /**
     * Check if the given time is between the start and end times.
     *
     * @param time The time to check
     * @return A boolean value of the comparison.
     */
    public boolean checkSlot(LocalTime time){
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }
}
