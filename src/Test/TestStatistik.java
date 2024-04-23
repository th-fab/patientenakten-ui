import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestStatistik {

    @Test
    public void testMaximum() {
        double[] array = {1.0, 2.5, 3.8, 2.1};
        double max = Statistik.maximum(array);
        Assert.assertEquals(3.8, max, 0.001);
    }

    @Test
    public void testMaximumWithEmptyArray() {
        double[] array = {};
        double max = Statistik.maximum(array);
        Assert.assertTrue(Double.isNaN(max));
    }

    @Test
    public void testMaxId() {
        double[] array = {1.0, 2.5, 3.8, 2.1};
        double[] maxId = Statistik.maxId(array);
        double expectedMax = 3.8;
        double expectedId = 2;
        Assert.assertArrayEquals(new double[]{expectedMax, expectedId}, maxId, 0.001);
    }

    @Test
    public void testMaxIdWithEmptyArray() {
        double[] array = {};
        double[] maxId = Statistik.maxId(array);
        Assert.assertNull(maxId);
    }

    @Test
    public void testMinimum() {
        double[] array = {1.0, 2.5, 3.8, 2.1};
        double min = Statistik.minimum(array);
        Assert.assertEquals(1.0, min, 0.001);
    }

    @Test
    public void testMinimumWithEmptyArray() {
        double[] array = {};
        double min = Statistik.minimum(array);
        Assert.assertTrue(Double.isNaN(min));
    }

    @Test
    public void testMinId() {
        double[] array = {1.0, 2.5, 3.8, 2.1};
        double[] minId = Statistik.minId(array);
        double expectedMin = 1.0;
        double expectedId = 0;
        Assert.assertArrayEquals(new double[]{expectedMin, expectedId}, minId, 0.001);
    }

    @Test
    public void testMinIdWithEmptyArray() {
        double[] array = {};
        double[] minId = Statistik.minId(array);
        Assert.assertNull(minId);
    }

    @Test
    public void testAverage() {
        double[] array = {1.0, 2.0, 3.0, 4.0, 5.0};
        double avg = Statistik.average(array);
        Assert.assertEquals(3.0, avg, 0.001);
    }

    @Test
    public void testAverageWithEmptyArray() {
        double[] array = {};
        double avg = Statistik.average(array);
        Assert.assertTrue(Double.isNaN(avg));
    }

    @Test
    public void testStandardDeviation() {
        double[] array = {1.0, 2.0, 3.0, 4.0, 5.0};
        double sd = Statistik.standardDeviation(array);
        Assert.assertEquals(1.5811, sd, 0.001);
    }

    @Test
    public void testStandardDeviationWithEmptyArray() {
        double[] array = {};
        double sd = Statistik.standardDeviation(array);
        Assert.assertTrue(Double.isNaN(sd));
    }

    @Test
    public void testRegressionsgerade() {
        double[] x = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] y = {2.0, 3.0, 4.0, 5.0, 6.0};
        double[] regression = Statistik.regressionsgerade(x, y);
        double expectedSlope = 1.0;
        double expectedIntercept = 1.0;
        Assert.assertArrayEquals(new double[]{expectedSlope, expectedIntercept}, regression, 0.001);
    }

    @Test
    public void testRegressionsgeradeWithInvalidInput() {
        double[] x = {1.0, 2.0, 3.0};
        double[] y = {2.0, 3.0, 4.0, 5.0};
        double[] regression = Statistik.regressionsgerade(x, y);
        Assert.assertNull(regression);
    }

    @Test
    public void testHistc() {
        double[] data = {1.2, 2.4, 3.6, 4.8, 6.0};
        double[] interval = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
        double[][] histogram = Statistik.histc(data, interval);
        double[][] expectedHistogram = {{1.5, 2.5, 3.5, 4.5, 5.5}, {1, 1, 1, 1, 1}};
        Assert.assertArrayEquals(expectedHistogram, histogram);
    }

    @Test
    public void testHistcWithEmptyInterval() {
        double[] data = {1.2, 2.4, 3.6, 4.8, 6.0};
        double[] interval = {};
        double[][] histogram = Statistik.histc(data, interval);
        Assert.assertNull(histogram);
    }

    @Test
    public void testMakeArrayFromList() {
        ArrayList<Double> arrayList = new ArrayList<>();
        arrayList.add(1.0);
        arrayList.add(2.5);
        arrayList.add(3.8);
        double[] array = Statistik.makeArrayFromList(arrayList);
        double[] expectedArray = {1.0, 2.5, 3.8};
        Assert.assertArrayEquals(expectedArray, array, 0.001);
    }
}
