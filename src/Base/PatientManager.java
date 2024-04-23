package Base;

import java.io.File;
import java.util.ArrayList;
/**
 * @author Linus Bohl
 */

public class PatientManager {

    private static PatientManager instance = null;
    private ArrayList<Patient> patients;
    private File patientListFile;

    private PatientManager() {
        patients = new ArrayList<>();
        patientListFile = FileSystem.getListFile("patientlist");
        refreshList();
        instance = this;

    }

    /**
     * Gets the Instance of the PatientManager Class. Creates a new Instance if there isn't one already
     * @return the instance of the PatientManager Class
     */
    public static PatientManager getInstance() {
        if(instance == null)
            new PatientManager();


        return instance;
    }

    /**
     * Reloads all the Patients from File
     */
    public void refreshList()
    {
        if(patientListFile.length() != 0)
            loadPatientsFromFile();
    }

    /**
     * Loads all Patients from the list file and fills the <b>patients</b> ArrayList with them
     */
    private void loadPatientsFromFile()
    {
        ArrayList<Patient> pats = new ArrayList<>();

        String content = FileSystem.readContentsFromFile(patientListFile);

        String[] lines = content.split("\n");

        for(String s : lines)
        {
            String[] fields = s.split(";");

            for(String field : fields)
                field.replace(";", "");

            String patientNumber = fields[0];
            String firstName = fields[1];
            String lastName = fields[2];

            pats.add(new Patient(patientNumber, firstName, lastName));
        }

        patients.clear();
        patients = pats;

    }

    /**
     * Saves all patients from the <b>patients</b> ArrayList to the related list file
     */
    public void savePatientsToFile()
    {
        StringBuilder sb = new StringBuilder();

        for(Patient p : patients)
        {
            sb.append(p.toCSVString());
            sb.append("\n");
        }

        String content = sb.toString();

        FileSystem.writeContentsToFile(patientListFile, content, false);
    }

    /**
     * Adds a Patient
     * @param p the Patient to add
     */
    public void addPatient(Patient p)
    {
        if(findPatient(p.getPatientNumber()) != null)
            return;
        patients.add(p);
        savePatientsToFile();
        refreshList();
    }

    /**
     * Deletes a Patient from the <b>patients</b> ArrayList
     * @param patientNumber the unique ID of the patient to delete
     */
    public void deletePatient(String patientNumber)
    {
        Patient p = findPatient(patientNumber);
        if(p != null) {
            patients.remove(p);
            savePatientsToFile();
            refreshList();
            FileSystem.deletePatientFile(patientNumber);
        }
    }

    /**
     * Finds a Patient from the <b>patients</b> ArrayList with a given patient number
     * @param patientNumber the unique ID of the patient
     * @return the Patient with the given patient number
     */
    public Patient findPatient(String patientNumber)
    {
        Patient patient = null;
        for(Patient p : patients)
        {
            if(p.getPatientNumber().equals(patientNumber))
                patient = p;
        }

        return patient;
    }

    /**
     * Gets the amount of the currently enlistet patients
     * @return The size of the <b>patients</b> ArrayList
     */
    public int getAmountOfPatient() {
        refreshList();
        return patients.size();
    }

    /**
     * Gets the amount of all Ratings saved
     * @return the amount of saved ratings (int)
     */
    public int getTotalAmountRatings() {
        refreshList();
        int amount = 0;
        if(patients.size() > 0) {
            for (Patient p : patients) {
                amount += p.getNumberOfRatings();
            }
        }
        return amount;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }
}
