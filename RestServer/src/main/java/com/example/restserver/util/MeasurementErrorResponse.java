package com.example.restserver.util;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MeasurementErrorResponse {
    private String message;

    private long timestamp;
}
