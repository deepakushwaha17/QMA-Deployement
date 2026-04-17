package com.app.quantitymeasurement.unit;

/**
 * VolumeUnit.java
 * 
 * The VolumeUnit enumeration implements IMeasurable interface and provides
 * methods for unit conversion. It defines various units of volume measurement
 * along with their conversion factors relative to a base unit (litre). This
 * enumeration is used in the QuantityMeasurement application to facilitate
 * conversions and comparisons between different volume units.
 * 
 * <p>
 * The base unit for conversion is litre. Each unit stores a conversion factor
 * relative to litre (the base unit). This design simplifies unit conversions by
 * always converting through a common base unit.
 * </p>
 * 
 * <p>
 * Example: 1 MILLILITRE 0.001 Litre, 1 GALOON 3.78541 Litre
 * </p>
 */

public enum VolumeUnit implements IMeasurable {
	// Conversion factor to the base unit (litre)
	MILLILITRE(0.001), LITRE(1.0), GALLON(3.78541);

	// Conversion factor to the base unit (litre)
	private final double conversionFactor;

	// Constructor to initialize the conversion factor
	VolumeUnit(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	/*
	 * Get the conversion factor to the base unit (litre).
	 * 
	 * @return the conversion factor to litre
	 */
	public double getConversionFactor() {
		return conversionFactor;
	}

	@Override
	public String getUnitName() {
		return this.toString();
	}

	@Override
	public String getMeasurementType() {
		return this.getClass().getSimpleName();
	}

	@Override
	public IMeasurable getUnitInstance(String unitName) {
		for (VolumeUnit unit : VolumeUnit.values()) {
			if (unit.getUnitName().equalsIgnoreCase(unitName)) {
				return unit;
			}
		}
		throw new IllegalArgumentException("Invalid VolumeUnit " + unitName);
	}

	/**
	 * Convert value from this unit to base unit (litre). New responsibility added.
	 *
	 * <p>
	 * This method is used internally for all conversions. It ensures consistent
	 * rounding to two decimal places across all operations.
	 *
	 * @param value the value in this unit
	 * @return the value converted to base unit (litre) and then rounded to two
	 *         decimal places
	 */

	public double convertToBaseUnit(double value) {
		double base = value * this.getConversionFactor();
		return Math.round(base * 100.0) / 100.0;
	}

	/**
	 * Convert value from base unit (litre) to this unit. New responsibility added.
	 *
	 * <p>
	 * This method is used internally for all conversions. It ensures consistent
	 * rounding to two decimal places across all operations.
	 *
	 * @param baseValue the value in base unit (litre)
	 * @return the value converted to this unit and then rounded to two decimal
	 *         places
	 */
	public double convertFromBaseUnit(double baseValue) {

		// Convert from base unit (litre) and round-off handling to two decimal places
		return Math.round((baseValue / this.conversionFactor) * 100.0) / 100.0;
	}

	public static void main(String[] args) {
		double millilitre = 1000.0;
		double litre = VolumeUnit.MILLILITRE.convertToBaseUnit(millilitre);
		System.out.println(millilitre + " millilitre is " + litre + " litre.");

		double litres = 3.78541;
		double gallon = VolumeUnit.GALLON.convertFromBaseUnit(litres);
		System.out.println(litres + " litre is " + gallon + " gallon.");
	}
}
