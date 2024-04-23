package Base;

import java.util.ArrayList;

/**
 * @author Ataa Al sharoua
 */
public class Statistics {

	/**
	 * Gets the biggest entry from a given double Array
	 *
	 * @param array An Array of doubles
	 * @return <b>type: double</b> biggest Number of the Array, NaN if the Array was empty or didnt exist
	 */
	public static double maximum(double[] array) {
		if (array != null && array.length > 0) {
			double max = array[0];

			for (int i = 0; i < array.length; i++) {
				if (array[i] > max)
					max = array[i];
			}

			return max;
		} else {
			return Double.NaN;
		}
	}

	/**
	 * Gets the Value and Index of the biggest Element in a given Array
	 *
	 * @param array An Array of doubles
	 * @return A double Array with index 0 = the value of the biggest element and index = 1 the ID of the biggest elment
	 */
	public static double[] maxId(double[] array) {
		if (array != null && array.length > 0) {
			double max = array[0];
			int id = 0;

			for (int i = 1; i < array.length; i++) {
				if (array[i] > max) {
					max = array[i];
					id = i;
				}
			}

			return new double[]{max, id};
		} else {
			return null;
		}
	}


	/**
	 * Gets the smallest Number in a given Array
	 *
	 * @param array An Array of doubles
	 * @return the smallest number in the Array (type = double). NaN if Array was empty or didnt exist
	 */
	public static double minimum(double[] array) {
		if (array != null && array.length > 0) {
			double min = array[0];

			for (int i = 1; i < array.length; i++) {
				if (array[i] < min) {
					min = array[i];
				}
			}
			return min;
		} else {
			return Double.NaN;
		}
	}

	/**
	 * Gets the smallest element and its index from a double array
	 *
	 * @param array An Array of doubles
	 * @return A double Array with index 0 = the value of the smallest element and index = 1 the ID of the smallest elment
	 */
	public static double[] minId(double[] array) {
		if (array != null && array.length > 0) {
			double min = array[0];
			int id = 0;

			for (int i = 1; i < array.length; i++) {
				if (array[i] < min) {
					min = array[i];
					id = i;
				}
			}
			return new double[]{min, id};
		} else {
			return null;
		}
	}

	/**
	 * Calculates the average value of a given double array
	 *
	 * @param array An Array of doubles
	 * @return the average (type = double)
	 */
	public static double average(double array[]) {
		if (array == null || array.length == 0) {
			return Double.NaN;
		}
		int count = array.length;
		double sum = 0;

		for (int i = 0; i < count; i++) {
			sum += array[i];
		}

		double avg = sum / count;
		return avg;

	}

	/**
	 * Calculates the standard deviation of an Array of doubles
	 *
	 * @param array An Array of doubles
	 * @return the standard deviation (type = double)
	 */
	public static double standardDeviation(double array[]) {
		if (array == null || array.length == 0)
			return Double.NaN;

		int count = array.length;
		double avg = average(array);
		double result = 0;

		for (int i = 0; i < count; i++) {
			result = result + Math.pow(array[i] - avg, 2);
		}

		return Math.sqrt(result / (count - 1));
	}


	/**
	 * Calculates the slope and intersect of the regression line of a given Dataset of x and y Points
	 * @param x Array of x coordinates
	 * @param y Array of y coordinates
	 * @return an Array of size 2. Index 0 = Slope; Index 1 = Intersect
	 */
	public static double[] regressionLine(double x[], double y[]) {
		double[] regLine = null;

		if (x != null && y != null && x.length == y.length) {

			double mwx = average(x);
			double mwy = average(y);


			double z1 = 0;

			for (int i = 0; i < x.length; i++) {
				z1 = z1 + x[i] * y[i];
			}
			double zaehler = z1 - (x.length * mwx * mwy);


			double z2 = 0;

			for (int j = 0; j < x.length; j++) {
				z2 = z2 + Math.pow(x[j], 2);
			}

			double nenner = z2 - (x.length * (Math.pow(mwx, 2)));

			if (nenner != 0) {
				double a = zaehler / nenner;
				double b = mwy - (a * mwx);
				regLine = new double[]{a, b};
			}
		}
		return regLine;
	}


	/**
	 * Creates a histogram from given Data an Interval
	 * @param data The Data
	 * @param interval The Interval
	 * @return The Histogram
	 */
	public static double[][] histc(double data[], double interval[]) {
		if (interval == null) {
			return null;
		}

		int n = interval.length - 1;

		if (n > 0) {
			double hist[][] = new double[2][n];

			for (int k = 0; k < n; k++) // alle Intervalle
			{
				hist[0][k] = (interval[k + 1] + interval[k]) / 2;
			}

			if (data != null) {
				for (int i = 0; i < data.length; i++) {
					for (int k = 0; k < n; k++) {
						if ((data[i] > interval[k]) && (data[i] <= interval[k + 1])) {
							hist[1][k] = hist[1][k] + 1;
							break;
						}
					}
				}
			}

			return hist;
		} else {
			return null;
		}
	}

	/**
	 * Helper method to convert an ArrayList to a regular Array
	 *
	 * @param arrayList The ArrayList to convert to an Array
	 * @return The Array
	 */
	public static double[] makeArrayFromList(ArrayList<Double> arrayList) {
		double[] array = new double[arrayList.size()];

		for (int i = 0; i < arrayList.size(); i++) {
			array[i] = arrayList.get(i);
		}

		return array;
	}
}
