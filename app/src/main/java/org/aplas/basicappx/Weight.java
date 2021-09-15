package org.aplas.basicappx;

public class Weight {
    private double gram;

    public Weight() {
        this.gram = 0;
    }

    public void setGram(double val) {
        this.gram = val;
    }

    public void setOunce(double val) {
        this.gram = val * 28.349523;
    }

    public void setPound(double val) {
        this.gram = val * 453.59237;
    }

    public double getGram() {
        return this.gram;
    }

    public double getOunce() {
        return this.gram / 28.349523;
    }

    public double getPound() {
        return this.gram / 453.59237;
    }

    public double convert(String oriUnit, String convUnit, double value) {
        switch (oriUnit) {
            case "Grm":
                setGram(value);
                break;
            case "Onc":
                setOunce(value);
                break;
            case "Pnd":
                setPound(value);
                break;
        }

        double result = 0;
        switch (convUnit) {
            case "Grm":
                result = getGram();
                break;
            case "Onc":
                result = getOunce();
                break;
            case "Pnd":
                result = getPound();
                break;
        }

        return result;
    }
}
