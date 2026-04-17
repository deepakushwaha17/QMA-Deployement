package com.app.quantitymeasurement.unit;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS(false),
    FAHRENHEIT(true);

    private final Function<Double, Double> toBaseConversion;

    TemperatureUnit(boolean isFahrenheit) {
        if (isFahrenheit) {
            this.toBaseConversion = f -> (f - 32) * 5 / 9.0;
        } else {
            this.toBaseConversion = c -> c; 
        }
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toBaseConversion.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        if (this == FAHRENHEIT) {
            return (baseValue * 9 / 5.0) + 32; 
        }
        return baseValue;
    }

    @Override
    public boolean supportsArithmetic() {
        return false;
    }

    @Override
    public String getUnitName() {
        return name();
    }
    
    @Override
    public String getMeasurementType() {
    	return this.getClass().getSimpleName();
    }
    
    @Override
    public IMeasurable getUnitInstance(String unitName) {
    	for(TemperatureUnit unit : TemperatureUnit.values()) {
    		if(unit.getUnitName().equalsIgnoreCase(unitName)) {
    			return unit;
    		}
    	}
    	throw new IllegalArgumentException("Invalid TemperatureUnit " + unitName);
    }

    @Override
    public void validateOperationSupport(String operation) {
        if (!supportsArithmetic()) {
            throw new UnsupportedOperationException(
                this.name() + " does not support " + operation + " operations."
            );
        }
    }

    /**
     * Converts this value to another temperature unit
     */
    public double convertTo(double value, TemperatureUnit targetUnit) {
        double base = convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(base);
    }

    @Override
    public String toString() {
        return "TemperatureUnit{" +
                "unit=" + name() +
                ", baseValue=" + getConversionFactor() +
                '}';
    }
    
    

    // Main method to test TemperatureUnit
    public static void main(String[] args) {
        double tempC = 100.0;
        double tempF = TemperatureUnit.CELSIUS.convertTo(tempC, FAHRENHEIT);

        System.out.println(tempC + "°C = " + tempF + "°F");

        double tempF2 = 212.0;
        double tempC2 = TemperatureUnit.FAHRENHEIT.convertTo(tempF2, CELSIUS);

        System.out.println(tempF2 + "°F = " + tempC2 + "°C");

        // Test arithmetic exception
        try {
            CELSIUS.validateOperationSupport("addition");
        } catch (UnsupportedOperationException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}