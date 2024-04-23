package UI.Helpers;

import Base.Patient;
import Base.PatientManager;
import Base.Rating;
import javafx.scene.chart.XYChart;
import java.util.ArrayList;

public class StatsCompiler {

    public static ArrayList<XYChart.Series> compileSeries(ArrayList<Rating> ratings) {

        ArrayList<XYChart.Series> series = new ArrayList<>();

        XYChart.Series seriesCommon = new XYChart.Series();
        XYChart.Series seriesWellbeing = new XYChart.Series();
        XYChart.Series seriesTreatment = new XYChart.Series();
        XYChart.Series seriesStaff = new XYChart.Series();

        if(ratings == null || ratings.size() == 0) {
            series.add(seriesCommon);
            series.add(seriesWellbeing);
            series.add(seriesTreatment);
            series.add(seriesStaff);
            return series;
        }

        int[] common = new int[] {0, 0, 0, 0, 0};
        int[] wellbeing = new int[] {0, 0, 0, 0, 0};
        int[] treatment = new int[] {0, 0, 0 ,0, 0};
        int[] staff = new int[] {0, 0, 0, 0, 0};


        for(Rating r : ratings) {
            common[r.getCommon()]++;
            wellbeing[r.getWellbeing()]++;
            treatment[r.getTreatment()]++;
            staff[r.getStaff()]++;
        }

        addDataPoints(seriesCommon, common);
        addDataPoints(seriesWellbeing, wellbeing);
        addDataPoints(seriesTreatment, treatment);
        addDataPoints(seriesStaff, staff);


        series.add(seriesCommon);
        series.add(seriesWellbeing);
        series.add(seriesTreatment);
        series.add(seriesStaff);

        return series;
    }

    public static void addDataPoints(XYChart.Series series, int[] points) {
        series.getData().add(new XYChart.Data("Sehr gut", points[0]));
        series.getData().add(new XYChart.Data("Gut", points[1]));
        series.getData().add(new XYChart.Data("Mittel", points[2]));
        series.getData().add(new XYChart.Data("Schlecht", points[3]));
        series.getData().add(new XYChart.Data("Sehr schlecht", points[4]));
    }

    public static ArrayList<Rating> getAllRatings() {
        ArrayList<Base.Rating> ratings = new ArrayList<>();
        PatientManager pm = PatientManager.getInstance();

        for(Patient p : pm.getPatients()) {
            for(Rating r : p.getRatings()) {
                ratings.add(r);
            }
        }
        return ratings;
    }
}
