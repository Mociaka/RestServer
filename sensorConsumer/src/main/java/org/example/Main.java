package org.example;

import org.example.modul.Measurement;
import org.example.modul.Sensor;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class Main {
    private static String urlRegistration = "http://localhost:8081/sensors/registration";
    private static String urlAdd = "http://localhost:8081/measurements/add";

    private static String  send(String url, Measurement measurement){
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForObject(url, measurement, String.class);

    }

    public static void main(String[] args) {
        Random random = new Random();


        while (true){
            System.out.println(send(urlAdd, new Measurement(
                    random.nextFloat(-20,70),
                    random.nextBoolean(),
                    new Sensor("second")
            )));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}