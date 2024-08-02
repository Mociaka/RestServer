package com.example.restserver.server;

import com.example.restserver.model.Sensor;
import com.example.restserver.repository.MeasurementRepository;
import com.example.restserver.repository.SensorRepository;
import com.example.restserver.util.SensorWithNoUniqueName;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SensorServer {
    private final SensorRepository sensorRepository;

    @Transactional
    public void registration(Sensor sensor) throws SensorWithNoUniqueName {
        if (sensorRepository.findByName(sensor.getName()).orElse(null)== null) {
            sensorRepository.save(sensor);
        }else {
            throw new SensorWithNoUniqueName();
        }

    }
}
