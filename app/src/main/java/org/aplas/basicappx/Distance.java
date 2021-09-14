package org.aplas.basicappx;

public class Distance {
    private double meter;

    public Distance() {
        this.meter = 0;
    }

    public void setMeter(double val) {
        this.meter = val;
    }

    public void setInch(double val) {
        this.meter = val / 39.3701;
    }

    public void setMile(double val) {
        this.meter = val / 0.000621371;
    }

    public void setFoot(double val) {
        this.meter = val / 3.28084;
    }

    public double getMeter() {
        return this.meter;
    }

    public double getInch() {
        return this.meter * 39.3701;
    }

    public double getMile() {
        return this.meter * 0.000621371;
    }

    public double getFoot() {
        return this.meter * 3.28084;
    }

    public double convert(String oriUnit, String convUnit, double value) {
        switch (oriUnit) {
            case "Mtr":
                setMeter(value);
                break;
            case "Inc":
                setInch(value);
                break;
            case "Mil":
                setMile(value);
                break;
            case "Ft":
                setFoot(value);
                break;
        }

        double result = 0;
        switch (convUnit) {
            case "Mtr":
                result = getMeter();
                break;
            case "Inc":
                result = getInch();
                break;
            case "Mil":
                result = getMile();
                break;
            case "Ft":
                result = getFoot();
                break;
        }

        return result;
    }
}
