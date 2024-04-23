import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.ArrayList;

public class TestPatient {
    private Patient patient;

    @Before
    public void setUp() {
        patient = new Patient("123456", "Max", "Harich");
    }

    @Test
    public void testAddRating() {
        Rating rating = new Rating();
        rating.setDate("2023-06-16");
        rating.setAllgemein(4);
        rating.setWohl(5);
        rating.setBehandlung(3);
        rating.setPersonal(4);
        rating.setFeedbackMessage("Good experience");

        patient.addRating(rating);

        ArrayList<Rating> ratings = patient.getRatings();
        Assert.assertEquals(1, ratings.size());

        Rating addedRating = ratings.get(0);
        Assert.assertEquals("2023-06-16", addedRating.getDate());
        Assert.assertEquals(4, addedRating.getAllgemein());
        Assert.assertEquals(5, addedRating.getWohl());
        Assert.assertEquals(3, addedRating.getBehandlung());
        Assert.assertEquals(4, addedRating.getPersonal());
        Assert.assertEquals("Good experience", addedRating.getFeedbackMessage());
    }

    @Test
    public void testFindRating() {
        Rating rating1 = new Rating();
        rating1.setDate("2023-06-15");
        Rating rating2 = new Rating();
        rating2.setDate("2023-06-16");
        Rating rating3 = new Rating();
        rating3.setDate("2023-06-17");

        patient.addRating(rating1);
        patient.addRating(rating2);
        patient.addRating(rating3);

        Rating foundRating = patient.findRating("2023-06-16");
        Assert.assertEquals(rating2, foundRating);

        Rating nonExistentRating = patient.findRating("2023-06-18");
        Assert.assertNull(nonExistentRating);
    }

    @Test
    public void testToCSVString() {
        String csvString = patient.toCSVString();
        Assert.assertEquals("123456;Max;Harich", csvString);
    }

    @Test
    public void testGetNumberOfRatings() {
        Assert.assertEquals(0, patient.getNumberOfRatings());

        Rating rating1 = new Rating();
        patient.addRating(rating1);
        Assert.assertEquals(1, patient.getNumberOfRatings());

        Rating rating2 = new Rating();
        patient.addRating(rating2);
        Assert.assertEquals(2, patient.getNumberOfRatings());
    }

    @Test
    public void testGetDateStringOfLastRating() {
        Assert.assertNull(patient.getDateStringOfLastRating());

        Rating rating1 = new Rating();
        rating1.setDate("2023-06-15");
        Rating rating2 = new Rating();
        rating2.setDate("2023-06-16");
        Rating rating3 = new Rating();
        rating3.setDate("2023-06-17");

        patient.addRating(rating1);
        Assert.assertEquals("2023-06-15", patient.getDateStringOfLastRating());

        patient.addRating(rating2);
        Assert.assertEquals("2023-06-16", patient.getDateStringOfLastRating());

        patient.addRating(rating3);
        Assert.assertEquals("2023-06-17", patient.getDateStringOfLastRating());
    }

    @Test
    public void testGetters() {
        Assert.assertEquals("123456", patient.getPatientNumber());
        Assert.assertEquals("Max", patient.getFirstName());
        Assert.assertEquals("Harich", patient.getLastName());

        File patientFile = patient.getPatientFile();
        Assert.assertNotNull(patientFile);
        Assert.assertTrue(patientFile.exists());
    }
}
       
