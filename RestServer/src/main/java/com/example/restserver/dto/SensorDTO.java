package com.example.restserver.dto;

import com.example.restserver.model.Sensor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SensorDTO {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30, message = "Size may be between 3 and 30 characters")
    private String name;

    public static Sensor concertToSensor(SensorDTO sensorDTO){
        Sensor sensor = new Sensor();

        sensor.setName(sensorDTO.getName());

        return sensor;
    }

    public static SensorDTO concertToSensorDTO(Sensor sensor){
        SensorDTO sensorDTO = new SensorDTO();

        sensorDTO.setName(sensor.getName());

        return sensorDTO;
    }


}
