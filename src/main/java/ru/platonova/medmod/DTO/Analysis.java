package ru.platonova.medmod.DTO;

import java.util.Date;

public class Analysis {
    private String name;
    private float value;
    private float minValue;
    private float maxValue;
    private float minValueFemale;
    private float maxValueFemale;
    private Date date;

    public Analysis(String name, float value, float minValue, float maxValue, float minValueFemale, float maxValueFemale, Date date) {
        this.name = name;
        this.value = value;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.maxValueFemale = maxValueFemale;
        this.minValueFemale = minValueFemale;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public float getMaxValueFemale() {
        return maxValueFemale;
    }

    public void setMaxValueFemale(float maxValueFemale) {
        this.maxValueFemale = maxValueFemale;
    }

    public float getMinValueFemale() {
        return minValueFemale;
    }

    public void setMinValueFemale(float minValueFemale) {
        this.minValueFemale = minValueFemale;
    }
}
