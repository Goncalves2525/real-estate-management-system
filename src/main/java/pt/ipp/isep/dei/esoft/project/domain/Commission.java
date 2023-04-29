package pt.ipp.isep.dei.esoft.project.domain;

public class Commission {
    private float value;
    private double percentage;
    private final float DEFAULT_VALUE = 0;
    private final int DEFAULT_PERCENTAGE = 0;

    public Commission(float value, int percentage){
        this.value = value;
        this.percentage = percentage;
    }

    public Commission(float value){
        this.value = value;
    }

    public Commission(double percentage){
        this.percentage = percentage;
    }

    public Commission(){
        this.value = DEFAULT_VALUE;
        this.percentage = DEFAULT_PERCENTAGE;
    }
    public double getValue(){
        return this.value;
    }

    public double getPercentage(){
        return this.percentage;
    }

    public void setValue(float value){
        this.value = value;
    }

    public void setPercentage(double percentage){
        this.percentage = percentage;
    }

    public String toString(){
        return String.format("Value: %.2f Percentage: %.2f", value, percentage);
    }
}
