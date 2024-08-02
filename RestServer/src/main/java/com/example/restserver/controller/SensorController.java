package com.example.restserver.controller;

import com.example.restserver.dto.SensorDTO;
import com.example.restserver.model.Sensor;
import com.example.restserver.server.MeasurementsServer;
import com.example.restserver.server.SensorServer;
import com.example.restserver.util.SensorErrorResponse;
import com.example.restserver.util.SensorWithNoUniqueName;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final SensorServer sensorServer;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            sensorServer.registration(SensorDTO.concertToSensor(sensorDTO));
        } catch (SensorWithNoUniqueName e) {

            return new ResponseEntity<>("The sensor with this name already existed!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }


}
