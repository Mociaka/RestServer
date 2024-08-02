package com.example.restserver.server;

import com.example.restserver.model.Measurement;
import com.example.restserver.model.Sensor;
import com.example.restserver.repository.MeasurementRepository;
import com.example.restserver.repository.SensorRepository;
import com.example.restserver.util.SensorNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeasurementsServer {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    public List<Measurement> findAll(String name) {
        return (sensorRepository.findByName(name).orElseThrow(SensorNotFoundException::new).getMeasurements());
    }

    @Transactional
    public boolean addMeasurement(Measurement measurement) {
        measurement.setCreatedAt(new Date());
        String sensorName = measurement.getSensor().getName();
        Optional<Sensor> sensor = sensorRepository.findByName(sensorName);

        sensor.orElseThrow(SensorNotFoundException::new);

        if (sensorName != null && !sensorName.isBlank()) {

            try {

                measurement.setSensor(sensor.get());
                measurementRepository.save(measurement);

            } catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int countOfRainingDays(String name) {
        Optional<Sensor> sensor = sensorRepository.findByName(name);
        sensor.orElseThrow(SensorNotFoundException::new);

        long rainyDaysCount = 0;

        List<Measurement> allBySensor = measurementRepository.findAllBySensor(sensor.get());

        rainyDaysCount = allBySensor.stream().filter(Measurement::getRaining).count();

        return (int) rainyDaysCount;
    }
}
