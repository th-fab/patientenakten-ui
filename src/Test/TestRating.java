import org.junit.Assert;
import org.junit.Test;

public class TestRating {

    @Test
    public void testToCSVString() {
        Rating rating = new Rating("2023-06-01", 4, 3, 5, 4, "Good job!");
        String csvString = rating.toCSVString();
        String expectedCSVString = "2023-06-01;4;3;5;4;Good job!";
        Assert.assertEquals(expectedCSVString, csvString);
    }

    @Test
    public void testGetCurrentDateTime() {
        String currentDateTime = Rating.getCurrentDateTime();
        Assert.assertNotNull(currentDateTime);
    }

    @Test
    public void testSetFeedbackMessage() {
        Rating rating = new Rating();
        rating.setFeedbackMessage("");
        Assert.assertEquals("empty", rating.getFeedbackMessage());

        rating.setFeedbackMessage("Great job!");
        Assert.assertEquals("Great job!", rating.getFeedbackMessage());
    }

}
