package com.example.restserver.dto;

import com.example.restserver.model.Measurement;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Setter
@Getter
@NoArgsConstructor
public class MeasurementDTO {

    private int id;

    @NotNull
    @Range(min = -100, max = 100, message = "Value may be between -100 and 100")
    private Float value;

    private Boolean raining;

    @JsonProperty("sensor")
    private SensorDTO sensorDTO;

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "id=" + id +
                ", value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensorDTO +
                '}';
    }

    public static MeasurementDTO converToMeasurementDTO(Measurement measurement) {
        MeasurementDTO measurementDTO = new MeasurementDTO();

        measurementDTO.setId(measurement.getId());
        measurementDTO.setValue(measurement.getValue());
        measurementDTO.setRaining(measurement.getRaining());
        measurementDTO.setSensorDTO(SensorDTO.concertToSensorDTO(measurement.getSensor()));

        return measurementDTO;
    }

    public static Measurement converToMeasurement(MeasurementDTO measurementDTO) {
        Measurement measurement = new Measurement();

        measurement.setId(measurementDTO.getId());
        measurement.setValue(measurementDTO.getValue());
        measurement.setRaining(measurementDTO.getRaining());
        measurement.setSensor(SensorDTO.concertToSensor(measurementDTO.getSensorDTO()));

        return measurement;
    }
}
