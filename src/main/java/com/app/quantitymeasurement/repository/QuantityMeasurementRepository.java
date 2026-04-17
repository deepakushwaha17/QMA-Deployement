package com.app.quantitymeasurement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.quantitymeasurement.model.QuantityMeasurementEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity , Long>{

	List<QuantityMeasurementEntity> findByOperation(String operation); 
	
	List<QuantityMeasurementEntity> findByThisMeasurementType(String measurementType);
	
	List<QuantityMeasurementEntity> findByCreatedAtAfter(LocalDateTime date);
	
	@Query("SELECT e FROM QuantityMeasurementEntity e where e.operation = : operation " + 
			"AND e.isError = false")
	List<QuantityMeasurementEntity> findSuccessfulOperations(@Param("operation") String operation); 
	
	long countByOperationAndIsErrorFalse(String operation);
	
	List<QuantityMeasurementEntity> findByIsErrorTrue();
}
