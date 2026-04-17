package com.app.quantitymeasurement.unit;

//Functional interface
@FunctionalInterface
interface SupportsArithmetic {
	boolean isSupported();
}

public interface IMeasurable {

	// Default variable to indicate all measurable units support arithmetic
	// operations by default
	SupportsArithmetic supportsArithmetic = () -> true;

	/**
	 * Get the conversion factor to the base unit (grams).
	 * 
	 * @return the conversion factor to grams.
	 **/
	public double getConversionFactor();

	/**
	 * Convert value from this unit to base unit (inches). New responsibility added.
	 * <p>
	 * This method is used internally for all conversions. It ensures consistent
	 * rounding to two decimal places across all operations.
	 * 
	 * @param value the value in this unit
	 * @return the value converted to base unit (inch) and then rounded to two
	 *         decimal places
	 **/
	public double convertToBaseUnit(double value);

	/**
	 * Convert value from base unit (inches) to this unit. New responsibility added.
	 * <p>
	 * This method is used internally for all conversions. It ensures consistent
	 * rounding to two decimal places across all operations.
	 * 
	 * @param baseValue the value in base unit (inches)
	 * @return the value converted to this unit and then rounded to two decimal
	 *         places
	 */
	public double convertFromBaseUnit(double baseValue);

	public String getUnitName();

	// Optional capability check
	default boolean supportsArithmetic() {
		return supportsArithmetic.isSupported();
	}

	// Validate operation support at runtime
	default void validateOperationSupport(String operation) {
		// Subclasses can override to validate specific operations
	}

	public String getMeasurementType();

	public IMeasurable getUnitInstance(String unitName);

	// main method to test IMeasurable interface
	public static void main(String[] args) {
		System.out.println("IMeasurable Interface");
	}
}