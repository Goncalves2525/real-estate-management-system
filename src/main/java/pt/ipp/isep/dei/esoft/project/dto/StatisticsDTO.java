package pt.ipp.isep.dei.esoft.project.dto;

public class StatisticsDTO {
    private int n;
    private double confidenceLevel = 0.95;
    private double alfa;
    private double intercept;
    private double slope;

    public StatisticsDTO(int n, double confidenceLevel, double alfa, double intercept, double slope) {
        this.n = n;
        this.confidenceLevel = confidenceLevel;
        this.alfa = alfa;
        this.intercept = intercept;
        this.slope = slope;
    }

    public int getN() {
        return n;
    }

    public double getConfidenceLevel() {
        return confidenceLevel;
    }

    public double getAlfa() {
        return alfa;
    }

    public double getIntercept() {
        return intercept;
    }

    public double getSlope() {
        return slope;
    }
}
