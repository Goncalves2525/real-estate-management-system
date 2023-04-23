package pt.ipp.isep.dei.esoft.project.domain;

public class Commission {
    private float value;
    private int percentage;

    private final float DEFAULT_VALUE = 0;
    private final int DEFAULT_PERCENTAGE = 0;

    public Commission(float value, int percentage){
        this.value = value;
        this.percentage = percentage;
    }

    public Commission(float value){
        this.value = value;
    }

    public Commission(int percentage){
        this.percentage = percentage;
    }

    public Commission(){
        this.value = DEFAULT_VALUE;
        this.percentage = DEFAULT_PERCENTAGE;
    }
    public double getValue(){
        return this.value;
    }

    public float getPercentage(){
        return this.percentage;
    }

    public void setValue(float value){
        this.value = value;
    }

    public void setPercentage(int percentage){
        this.percentage = percentage;
    }
}
