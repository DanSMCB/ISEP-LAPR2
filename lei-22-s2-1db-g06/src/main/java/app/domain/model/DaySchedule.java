package app.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DaySchedule implements Serializable {
    /**
     * Hour that a certain vaccination center opens
     */
    private LocalTime openingHour;
    /**
     *  Hour that a certain vaccination center closes
     */
    private LocalTime closingHour;
    /**
     * Date of the vaccination appointment
     */
    private LocalDate date;
    /**
     * Slot duration of the mass vaccination center
     */
    private int slotDuration;
    /**
     * Number of vaccines per slot of the mass vaccination center
     */
    private int numberOfVaccinesSlot;
    /**
     * List responsible for storing Slots
     */
    private List<Slot> slotList;

    /**
     * Creates an instance of DaySchedule, receiving the openingHour, closingHour, slotDuration; numberOfVaccinesSlot and date
      * @param openingHour Hour that a certain vaccination center opens
     * @param closingHour Hour that a certain vaccination center closes
     * @param slotDuration Slot duration of the mass vaccination center
     * @param numberOfVaccinesSlot Number of vaccines per slot of the mass vaccination center
     * @param date Date of the vaccination appointment
     */
    public DaySchedule(LocalTime openingHour, LocalTime closingHour, int slotDuration, int numberOfVaccinesSlot, LocalDate date){
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.date = date;
        this.slotList = new ArrayList<>();
        this.slotDuration = slotDuration;
        this.numberOfVaccinesSlot=numberOfVaccinesSlot;
        LocalTime newStartTime = LocalTime.of(openingHour.getHour(), openingHour.getMinute(), openingHour.getSecond());
        while (newStartTime.isBefore(closingHour)){
            Slot slot = new Slot(newStartTime, slotDuration, numberOfVaccinesSlot);
            newStartTime = newStartTime.plusMinutes(slotDuration);
            this.slotList.add(slot);
        }
    }
    /**
     * Find a slot in the list of slots that is valid for the given time.
     *
     * @param time The time you want to check if the slot is valid.
     * @return Slot
     */
    public Slot findSlot(LocalTime time) throws Exception {
        for (Slot sl:slotList) {
            if (sl.checkSlot(time))
                return sl;
        }
        throw new Exception("The vaccination center is not open at this schedule time");
    }
    /**
     * This function checks if the date is the same as the date passed in.
     *
     * @param date The date to check
     * @return A boolean value of the comparison
     */
    public boolean checkDay(LocalDate date){
        return this.date.isEqual(date);
    }
}
