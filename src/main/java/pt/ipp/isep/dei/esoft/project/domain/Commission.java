package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

/**
 * This class represents a Commission.
 * A commission is a value and a percentage.
 */
public class Commission implements Serializable {
    private float value;
    private double percentage;
    private final float DEFAULT_VALUE = 0;
    private final int DEFAULT_PERCENTAGE = 0;

    /**
     * Instantiates a new Commission.
     *
     * @param value      the value
     * @param percentage the percentage
     */
    public Commission(float value, int percentage){
        this.value = value;
        this.percentage = percentage;
    }
    /**
     * Instantiates a new Commission.
     *
     * @param value      the value
     */
    public Commission(float value){
        this.value = value;
    }
    /**
     * Instantiates a new Commission.
     *
     * @param percentage the percentage
     */
    public Commission(double percentage){
        this.percentage = percentage;
    }
    /**
     * Instantiates a new Commission.
     * Default constructor.
     */
    public Commission(){
        this.value = DEFAULT_VALUE;
        this.percentage = DEFAULT_PERCENTAGE;
    }

    /**
     * Gets value.
     * @return
     */
    public float getValue(){
        return this.value;
    }
    /**
     * Gets percentage.
     * @return
     */
    public double getPercentage(){
        return this.percentage;
    }
    /**
     * Sets value.
     * @param value
     */
    public void setValue(float value){
        this.value = value;
    }
    /**
     * Sets percentage.
     * @param percentage
     */
    public void setPercentage(double percentage){
        this.percentage = percentage;
    }
    /**
     * To string Commission.
     * @return
     */
    public String toString(){
        return String.format("Value: %.2f Percentage: %.2f", value, percentage);
    }
}
