import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPatientManager {

    private PatientManager patientManager;

    @Before
    public void setUp() {
        patientManager = PatientManager.getInstance();
        patientManager.refreshList();
    }

    @Test
    public void testAddPatient() {
        // Test new patient
        Patient patient = new Patient("123456", "Max", "Harich");
        patientManager.addPatient(patient);

        // Check if patient was added
        Patient addedPatient = patientManager.findPatient("123456");
        Assert.assertNotNull(addedPatient);
        Assert.assertEquals("Max", addedPatient.getFirstName());
        Assert.assertEquals("Harich", addedPatient.getLastName());

        // Test adding duplicate patient
        Patient duplicatePatient = new Patient("123456", "Linus", "Bohl");
        patientManager.addPatient(duplicatePatient);

        // Check that duplicate patient was not added
        Patient foundDuplicatePatient = patientManager.findPatient("123456");
        Assert.assertNotNull(foundDuplicatePatient);
        Assert.assertEquals("Max", foundDuplicatePatient.getFirstName());
        Assert.assertEquals("Harich", foundDuplicatePatient.getLastName());
    }

    @Test
    public void testDeletePatient() {
        // Add a patient
        Patient patient = new Patient("123456", "Max", "Harich");
        patientManager.addPatient(patient);

        // Delete the patient
        patientManager.deletePatient("123456");

        // Check if patient was deleted
        Patient deletedPatient = patientManager.findPatient("123456");
        Assert.assertNull(deletedPatient);
    }

    @Test
    public void testFindPatient() {
        // Add a patient
        Patient patient = new Patient("123456", "Max", "Harich");
        patientManager.addPatient(patient);

        // Find the patient
        Patient foundPatient = patientManager.findPatient("123456");

        // Check if patient was found
        Assert.assertNotNull(foundPatient);
        Assert.assertEquals("Max", foundPatient.getFirstName());
        Assert.assertEquals("Harich", foundPatient.getLastName());
    }

    @Test
    public void testGetAmountOfPatient() {
        // Add multiple patients
        Patient patient1 = new Patient("123456", "Max", "Harich");
        patientManager.addPatient(patient1);

        Patient patient2 = new Patient("654321", "Linus", "Bohl");
        patientManager.addPatient(patient2);

        // Check the amount of patients
        int amount = patientManager.getAmountOfPatient();
        Assert.assertEquals(2, amount);
    }

    @Test
    public void testGetTotalAmountRatings() {
        // Add a patient with ratings
        Patient patient = new Patient("123456", "Max", "Harich");
        patient.addRating(new Rating("2023-06-01", 4, 3, 5, 4, "Good job!"));
        patient.addRating(new Rating("2023-06-05", 3, 4, 4, 5, "Keep up the good work!"));
        patientManager.addPatient(patient);

        // Check the total amount of ratings
        int totalAmount = patientManager.getTotalAmountRatings();
        Assert.assertEquals(2, totalAmount);
    }
}
