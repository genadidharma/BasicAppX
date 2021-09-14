package org.aplas.basicappx;

public class Temperature {
    private double celcius;

    public Temperature() {
        this.celcius = 0;
    }

    public void setCelcius(double val) {
        this.celcius = val;
    }

    public void setFahrenheit(double val) {
        this.celcius = (val - 32) * 5 / 9;
    }

    public void setKelvins(double val) {
        this.celcius = val - 273.15;
    }

    public double getCelcius() {
        return this.celcius;
    }

    public double getFahrenheit() {
        return (this.celcius * 9 / 5) + 32;
    }

    public double getKelvins() {
        return this.celcius + 273.15;
    }

    public double convert(String oriUnit, String convUnit, double value) {
        switch (oriUnit) {
            case "°C":
                setCelcius(value);
                break;
            case "°F":
                setFahrenheit(value);
                break;
            case "K":
                setKelvins(value);
                break;
        }

        double result = 0;
        switch (convUnit) {
            case "°C":
                result = getCelcius();
                break;
            case "°F":
                result = getFahrenheit();
                break;
            case "K":
                result = getKelvins();
                break;
        }

        return result;
    }
}
