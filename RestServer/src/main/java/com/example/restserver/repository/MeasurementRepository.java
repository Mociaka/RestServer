package com.example.restserver.repository;

import com.example.restserver.model.Measurement;
import com.example.restserver.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findAllBySensor(Sensor sensor);
}
