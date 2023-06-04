package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;

public class Statistics {
    private ArrayList<Announcement> deals;

    public Statistics(ArrayList<Announcement> deals) {
        this.deals = deals;
    }

    public double calcSimpleRegressionArea(double area){
        SimpleRegression regression = new SimpleRegression();
        for (Announcement deal : deals) {
            if (!(deal.getProperty() instanceof Land)){
                regression.addData(deal.getProperty().getArea(), deal.getProperty().getPrice());
            }
        }
        return regression.predict(area);
    }

    public double calcSimpleRegressionDistance(double distanceFromCenter){
        SimpleRegression regression = new SimpleRegression();
        for (Announcement deal : deals) {
            if (!(deal.getProperty() instanceof Land)){
                regression.addData(deal.getProperty().getDistanceFromCenter(), deal.getProperty().getPrice());
            }
        }
        return regression.predict(distanceFromCenter);
    }

    public double calcSimpleRegressionBedrooms(int numberOfBedrooms){
        SimpleRegression regression = new SimpleRegression();
        for (Announcement deal : deals) {
            if (deal.getProperty() instanceof House){
                regression.addData(((House) deal.getProperty()).getNumberOfBedrooms(), deal.getProperty().getPrice());
            }
            else if (deal.getProperty() instanceof Apartment){
                regression.addData(((Apartment) deal.getProperty()).getNumberOfBedrooms(), deal.getProperty().getPrice());
            }

        }
        return regression.predict(numberOfBedrooms);
    }

    public double calcSimpleRegressionBathrooms(int numberOfBathrooms){
        SimpleRegression regression = new SimpleRegression();
        for (Announcement deal : deals) {
            if (deal.getProperty() instanceof House){
                regression.addData(((House) deal.getProperty()).getNumberOfBathrooms(), deal.getProperty().getPrice());
            }
            else if (deal.getProperty() instanceof Apartment){
                regression.addData(((Apartment) deal.getProperty()).getNumberOfBathrooms(), deal.getProperty().getPrice());
            }

        }
        return regression.predict(numberOfBathrooms);
    }

    public double calcSimpleRegressionParkingSpaces(int numberOfParkingSpaces){
        SimpleRegression regression = new SimpleRegression();
        for (Announcement deal : deals) {
            if (deal.getProperty() instanceof House){
                regression.addData(((House) deal.getProperty()).getNumberOfParkingSpaces(), deal.getProperty().getPrice());
            }
            else if (deal.getProperty() instanceof Apartment){
                regression.addData(((Apartment) deal.getProperty()).getNumberOfParkingSpaces(), deal.getProperty().getPrice());
            }

        }
        return regression.predict(numberOfParkingSpaces);
    }

    public double calcMultipleRegression(double area, double distanceFromCenter, int numberOfBedrooms, int numberOfBathrooms, int numberOfParkingSpaces){
        return -1;
    }


}
