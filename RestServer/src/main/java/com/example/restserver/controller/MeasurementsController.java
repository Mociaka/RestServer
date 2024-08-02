package com.example.restserver.controller;

import com.example.restserver.dto.MeasurementDTO;
import com.example.restserver.model.Measurement;
import com.example.restserver.model.Sensor;
import com.example.restserver.server.MeasurementsServer;
import com.example.restserver.server.SensorServer;
import com.example.restserver.util.SensorErrorResponse;
import com.example.restserver.util.SensorNotFoundException;
import com.example.restserver.util.SensorWithNoUniqueName;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementsController {
    private final MeasurementsServer measurementsServer;


    @PostMapping("/add")
    public HttpStatus add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (!measurementsServer.addMeasurement(MeasurementDTO.converToMeasurement(measurementDTO))) {
                return HttpStatus.BAD_REQUEST;
            }
        }

        return HttpStatus.OK;
    }

    @GetMapping
    public List<MeasurementDTO> findAll(@RequestParam(value = "name") String name) {
        return measurementsServer.findAll(name).stream()
                .map(MeasurementDTO::converToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public int rainyDaysCount(@RequestParam(value = "name") String name) {
        return measurementsServer.countOfRainingDays(name);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handlerException(SensorNotFoundException e) {
        SensorErrorResponse errorResponse = new SensorErrorResponse("The sensor with this name not found! Please register it", System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

}
