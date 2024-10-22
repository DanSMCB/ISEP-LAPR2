package app.ui.console;

import app.controller.VaccinatedDailyRecordController;
import app.domain.shared.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Generates a CSV file with the daily data about the vaccinated users in each vaccination center
 * @author Daniel Braga <1200801@isep.ipp.pt>
 */

public final class VaccinatedDailyRecordUI extends TimerTask {

    private VaccinatedDailyRecordController vaccinatedDailyRecordController;
    public VaccinatedDailyRecordUI(){
        vaccinatedDailyRecordController = new VaccinatedDailyRecordController();
    }

    /**
     * Time it takes between tasks (1 day) in milliseconds
     */
    private final static long TIME_BETWEEN_TASKS = 1000*60*60*24;
    /**
     * Time of the day to execute the task
     */
    private static final int TIME_TASK = getTimeTask();
    /**
     * Minutes of the created calendar
     */
    private final static int ZERO_MINUTES = 0;
    /**
     * Amount added to the date in the calendar
     */
    private final static int AMOUNT = 0;

    /** Construct and use a TimerTask and Timer. */
    public static void main (String... arguments ) {
        TimerTask timerTask = new VaccinatedDailyRecordUI();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, getDateOfExecution(), TIME_BETWEEN_TASKS);
    }

    /**
     * Implements TimerTask's abstract run method.
     */
    @Override public void run(){
        LocalDateTime now = LocalDateTime.now();
        String filePath="DailyRecord_"+now.getDayOfMonth()+"_"+now.getMonth().getValue()+"_"+now.getYear()+".csv";
        File file = new File(filePath);
        try {
            FileWriter csvWriter = new FileWriter(file);
            csvWriter.append("Date");
            csvWriter.append(";");
            csvWriter.append("Name of the vaccination center");
            csvWriter.append(";");
            csvWriter.append("Vaccinated Users");
            csvWriter.append("\n");

            for(int i=0;i<vaccinatedDailyRecordController.getMVCenterStore().getSize();i++){
                csvWriter.append(String.valueOf(now.getDayOfMonth())).append("/").append(String.valueOf(now.getMonth().getValue())).append("/").append(String.valueOf(now.getYear()));
                csvWriter.append(";");
                csvWriter.append(vaccinatedDailyRecordController.getMVCenterStore().getMVCenter(i).getName());
                csvWriter.append(";");
                csvWriter.append(String.valueOf(vaccinatedDailyRecordController.getNumberOfVaccinated(vaccinatedDailyRecordController.getMVCenterStore().getMVCenter(i))));
                csvWriter.append("\n");
            }

            for(int i=0;i<vaccinatedDailyRecordController.getHCCenterStore().getSize();i++){
                csvWriter.append(String.valueOf(now.getDayOfMonth())).append("/").append(String.valueOf(now.getMonth().getValue())).append("/").append(String.valueOf(now.getYear()));
                csvWriter.append(";");
                csvWriter.append(vaccinatedDailyRecordController.getHCCenterStore().getHCCenter(i).getName());
                csvWriter.append(";");
                csvWriter.append(String.valueOf(vaccinatedDailyRecordController.getNumberOfVaccinated(vaccinatedDailyRecordController.getHCCenterStore().getHCCenter(i))));
                csvWriter.append("\n");
            }

            csvWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the date of the first execution
     * @return date of the execution
     */
    public static Date getDateOfExecution(){
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DATE,AMOUNT);
        Calendar result = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), TIME_TASK, ZERO_MINUTES);
        return result.getTime();
    }

    /**
     * Get the specific hours to execute the task from the configuration file
     * @return hours for the execution
     */
    public static int getTimeTask(){
        Properties props = new Properties();
        String fileName = "config.properties";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            props.load(fis);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Integer.parseInt(props.getProperty(Constants.PARAMS_DAILY_RECORD_TASK_TIME));
    }

}
