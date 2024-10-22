package app.controller;

import app.algorithm.MaxSumSublist;
import app.algorithm.MaximumSum;
import app.algorithm.MaximumSumAdapter;
import app.algorithm.SumAdapter;
import app.domain.model.CenterCoordinator;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.domain.store.EmployeeStore;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

public class AnalyseCenterPerformanceController {
    private Company company;

    private VaccinationCenter vaccinationCenter;

    private int[] arrivalNumberList;

    private int[] maxSumSublist;

    private int timeInterval;

    private List<MaxSumSublist> algorithms;


    public AnalyseCenterPerformanceController() throws Exception {
        company = App.getInstance().getCompany();
        getCCVaccinationCenter();
        algorithms = List.of(new MaximumSumAdapter(), new SumAdapter());
    }

    /**
     * This function gets the vaccination center of the current center coordinator
     */
    public void getCCVaccinationCenter() throws Exception {
        Email email = App.getInstance().getCurrentUserSession().getUserId();
        EmployeeStore employeeStore = company.getEmployeeStore();
        CenterCoordinator cc = (CenterCoordinator) employeeStore.findEmployeeByEmail(email.getEmail());
        vaccinationCenter = cc.getVaccinationCenter();
    }

    /**
     * This function sets and validates the time interval to create the arrivalNumberList.
     *
     * @param timeInterval the interval in minutes that you want to check the number of people in the center
     * @return A boolean value.
     */
    public boolean setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
        return vaccinationCenter.getAppointmentStore().validateTimeInterval(timeInterval);
    }

    /**
     * This function returns an array of integers, which represents the number of patients in the waiting room at a certain
     * time interval
     *
     * @param dayToCheck the day you want to check
     * @return The method returns an array of integers.
     */
    public int[] getArrivalNumberList(LocalDate dayToCheck) {
        arrivalNumberList = vaccinationCenter.getAppointmentStore().getArrivalNumberList(dayToCheck, timeInterval);
        return arrivalNumberList;
    }

    /**
     * The method returns the maximum sum sublist of the Arrival Number List
     *
     * @return The maximum sum sublist is being returned.
     */
    public int[] getMaxSumSublist() throws ClassNotFoundException {
        MaxSumSublist alg = findAlgorithm();
        maxSumSublist= alg.max(arrivalNumberList);
        return maxSumSublist;
    }

    /**
     * It returns the start and end indexes of the sublist that has the maximum sum.
     *
     * @return The indexes of the sublist with the maximum sum.
     */
    public int[] findMaxSumSublistIndexes() {
        return MaximumSum.findMaxSumSublistIndexes(arrivalNumberList);
    }

    /**
     * It returns the sum of the elements in the sublist that has the maximum sum.
     *
     * @return The sum of the maximum sum sublist.
     */
    public int sumSublist() {
        return MaximumSum.sum(maxSumSublist);
    }

    private MaxSumSublist findAlgorithm() throws ClassNotFoundException {
        Properties props = App.getInstance().getProperties();
        String key = (String) props.get("MaxSublist.Algorithm");
        for (MaxSumSublist alg:algorithms) {
            if (("app.algorithm."+key+"Adapter").equals(alg.getClass().getName()))
                return alg;
        }
        throw new ClassNotFoundException("Max Sublist algorithm not found");
    }
}
