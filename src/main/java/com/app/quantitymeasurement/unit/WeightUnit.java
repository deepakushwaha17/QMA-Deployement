package com.app.quantitymeasurement.unit;

/**
 * WeightUnit.java
 * 
 * The WeightUnit enumeration implements IMeasurable interface and provides 
 * methods for unit conversion. It defines various units of weight measurement 
 * along with their conversion factors relative to a base unit (grams). This 
 * enumeration is used in the QuantityMeasurement application to facilitate 
 * conversions and comparisons between different weight units.
 * 
 * <p>The base unit for conversion is grams. Each unit stores a conversion factor 
 * relative to grams (the base unit). This design simplifies unit conversions by 
 * always converting through a common base unit. </p>
 * 
 * <p>Example: 1 KILOGRAM 1000.0 grams, 1 POUND 453.592 grams,
 * 1 OUNCE 28.3495 grams</p>
*/

public enum WeightUnit implements IMeasurable {
	// Conversion factor to the base unit (grams)
	MILLIGRAM(0.001),
	GRAM(1.0),
	KILOGRAM (1000.0),
	POUND (453.592),
	TONNE  (1000000.0);
	
	// Conversion factor to the base unit (grams)
	private final double conversionFactor;
	
	// Constructor to initialize the conversion factor
	WeightUnit(double conversionFactor){
		this.conversionFactor = conversionFactor;
	}
	
	/* Get the conversion factor to the base unit (grams). 
	 * @return the conversion factor to grams 
	*/
	public double getConversionFactor() {
		return conversionFactor;
	}
	
	@Override
	public String getUnitName() {
		return this.toString();
	}
	
	/**
	* Convert value from this unit to base unit (grams). New responsibility added.
	*
	* <p> This method is used internally for all conversions. It ensures consistent
	* rounding to two decimal places across all operations.
	*
	* @param value the value in this unit
	* @return the value converted to base unit (grams) and then rounded to two decimal places
	*/
	
	public double convertToBaseUnit (double value) {
		double base = value * this.getConversionFactor();
		return Math.round(base * 100.0) / 100.0; 
	}
	
	/**
	* Convert value from base unit (grams) to this unit. New responsibility added.
	*
	* <p> This method is used internally for all conversions. It ensures consistent
	* rounding to two decimal places across all operations.
	*
	* @param baseValue the value in base unit (grams)
	* @return the value converted to this unit and then rounded to two decimal places
	*/
	public double convertFromBaseUnit(double baseValue) {
		
		// Convert from base unit (grams) and round-off handling to two decimal places
		return Math.round((baseValue / this.conversionFactor) * 100.0) / 100.0;
	}
	
	@Override
    public String getMeasurementType() {
    	return this.getClass().getSimpleName();
    }
    
    @Override
    public IMeasurable getUnitInstance(String unitName) {
    	for(WeightUnit unit : WeightUnit.values()) {
    		if(unit.getUnitName().equalsIgnoreCase(unitName)) {
    			return unit;
    		}
    	}
    	throw new IllegalArgumentException("Invalid WeightUnit " + unitName);
    }

	public static void main(String[] args) {
		double kilograms = 10.0;
		double grams = WeightUnit.KILOGRAM.convertToBaseUnit(kilograms);
		System.out.println(kilograms + " kilograms is " + grams + " grams.");
		
		double milligrams = WeightUnit.MILLIGRAM.convertFromBaseUnit(grams);
		System.out.println(grams + " grams is " + milligrams + " milligrams.");
	}
}
