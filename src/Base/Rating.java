package Base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author Maximilian Harich
 */

public class Rating {

    private int common;
    private int wellbeing;
    private int treatment;
    private int staff;
    private String feedbackMessage;

    private String date;

    public Rating()
    {

    }


    /**
     * Builds a CSV String from the Data stored in the Rating separated by semicolons.
     * @return a CSV String containign all the Rating Data separated by semicolons.
     */
    public String toCSVString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(date);
        sb.append(";");
        sb.append(common);
        sb.append(";");
        sb.append(wellbeing);
        sb.append(";");
        sb.append(treatment);
        sb.append(";");
        sb.append(staff);
        sb.append(";");
        sb.append(feedbackMessage);

        return sb.toString();
    }

    /**
     * Gets the Current date as a formatted String
     * @return the current date as formatted String
     */
    public static String getCurrentDateTime()
    {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(new Date());
    }

    /**
     * Getter
     * @return the value of allgemein
     */
    public int getCommon() {
        return common;
    }

    /**
     * Sets the value of allgemein
     * @param common the value to be set to allgemein
     */
    public void setCommon(int common) {
        this.common = common;
    }

    /**
     * Getter
     * @return the value of wellbeing
     */
    public int getWellbeing() {
        return wellbeing;
    }

    /**
     * Sets the value of wellbeing
     * @param wellbeing the value to be set to wellbeing
     */
    public void setWellbeing(int wellbeing) {
        this.wellbeing = wellbeing;
    }

    /**
     * Getter
     * @return the value f behandlung
     */
    public int getTreatment() {
        return treatment;
    }

    /**
     * Sets the value of behandlung
     * @param treatment the value to be set to behandlung
     */
    public void setTreatment(int treatment) {
        this.treatment = treatment;
    }

    /**
     * Getter
     * @return the value of staff
     */
    public int getStaff() {
        return staff;
    }

    /**
     * Sets the value of staff
     * @param staff the value to be set to staff
     */
    public void setStaff(int staff) {
        this.staff = staff;
    }

    /**
     * Getter
     * @return the value of date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of date
     * @param datetime the value to be set to date
     */
    public void setDate(String datetime) {
        this.date = datetime;
    }

    /**
     * Getter
     * @return the value of feedbackMessage
     */
    public String getFeedbackMessage() {
        return feedbackMessage;
    }

    /**
     * Sets the Value of feedbackMessage
     * @param feedbackMessage the value to be set to feedbackMessage
     */
    public void setFeedbackMessage(String feedbackMessage) {
        if(feedbackMessage.isEmpty() || feedbackMessage.length() == 0)
            this.feedbackMessage = "empty";
        else
            this.feedbackMessage = feedbackMessage;
    }
}
