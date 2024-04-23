package Base;

import java.io.File;
import java.util.ArrayList;
/**
 * @author Daniel Koch
 */

public class Patient {

    private String firstName, lastName;
    private String patientNumber;

    private File patientFile;

    private ArrayList<Rating> ratings;

    /**
     * Constructor for the Patient Class
     * @param patientNumber the uniuqe id number of the Patient
     * @param firstName the first name of the patient
     * @param lastName the last name of the patient
     */
    public Patient(String patientNumber, String firstName, String lastName)
    {
        this.patientNumber = patientNumber;
        this.firstName = firstName;
        this.lastName = lastName;

        ratings = new ArrayList<>();

        loadRatingFile();
        if(patientFile.length() != 0)
            getRatingsFromFile();
    }

    /**
     * Saves the ratings ArrayList to the Patients file
     */
    public void saveRatingsToFile()
    {
        StringBuilder sb = new StringBuilder();

        for(Rating r : ratings)
        {
            sb.append(r.toCSVString());
            sb.append("\n");
        }

        String contentsToWrite = sb.toString();

        FileSystem.writeContentsToFile(patientFile, contentsToWrite, false);

        loadRatingFile();
    }

    /**
     * Fills the ratings ArrayList with the Data from the patients file
     */
    public void getRatingsFromFile()
    {
        ArrayList<Rating> ratingsFromFile = new ArrayList<>();
        //Getting Filecontent as whole String
        String fileContent = FileSystem.readContentsFromFile(patientFile);

        //Split the String by lines
        String[] lines = fileContent.split("\n");

        //going through every line exctracting the information and saving it to a new Base.Rating
        for(String s : lines) {
            if (!s.isBlank()) {
                Rating r = new Rating();

                String[] fields = s.split(";");
                for (String s1 : fields) {
                    s1.replace(";", "");
                }

                r.setDate(fields[0]);
                r.setCommon(Integer.parseInt(fields[1]));
                r.setWellbeing(Integer.parseInt(fields[2]));
                r.setTreatment(Integer.parseInt(fields[3]));
                r.setStaff(Integer.parseInt(fields[4]));
                if (fields[5].isEmpty())
                    r.setFeedbackMessage("");
                else
                    r.setFeedbackMessage(fields[5]);

                ratingsFromFile.add(r);
            }
        }

        this.ratings = ratingsFromFile;
    }

    /**
     * Finds a rating in the list from a given date
     * @param date the date of the rating
     * @return the rating at the given date, null if there wasn't a rating at the given date
     */
    public Rating findRating(String date)
    {
        Rating rating = null;
        for(Rating r : ratings)
        {
            if(r.getDate().equals(date))
                rating = r;
        }

        return rating;
    }

    /**
     * Adds a rating to the list of ratings stored for the instance of a patient object
     * @param rating the rating to add to the list
     */
    public void addRating(Rating rating)
    {
        if(findRating(rating.getDate()) != null)
            return;

        ratings.add(rating);
        saveRatingsToFile();
    }

    /**
     * Converts the patients data to a CSV String, separated by semicolons
     * @return the patients data as a CSV String
     */
    public String toCSVString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(patientNumber);
        sb.append(";");
        sb.append(firstName);
        sb.append(";");
        sb.append(lastName);

        return sb.toString();
    }

    /**
     * Gets the total count of saved ratings for the patient object
     * @return The amount of items stored in the <b>ratings</b> ArrayList
     */
    public int getNumberOfRatings() {
        if(ratings.size() > 0)
            return ratings.size();
        else
            return 0;
    }
    /**
     * Finds the most recent Rating in the ratings list and returns it
     * @return A String containing the date of the last added Rating
     */
    public String getDateStringOfLastRating() {
        if(ratings.size() > 0) {
            Rating r = ratings.get(ratings.size() - 1);
            return r.getDate();
        } else {
            return " ";
        }
    }

    /**
     * loads the rating file assosciated with the patient number
     */
    private void loadRatingFile()
    {
        patientFile = FileSystem.getRatingFile(patientNumber);
    }

    /**
     * Getter for the ratings ArrayList
     * @return the ratings ArrayList
     */
    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    /**
     * Getter for the patientNumber String
     * @return the patient number(String)
     */
    public String getPatientNumber() {
        return patientNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public File getPatientFile() {
        return patientFile;
    }


}


