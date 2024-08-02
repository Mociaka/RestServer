package com.example.restserver.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Measurement")
@Setter
@Getter
@NoArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value may be between -100 and 100")
    @Max(value = 100, message = "Value may be between -100 and 100")
    @Column(name = "value")
    private Float value;

    @NotNull(message = "Raining should not be empty")
    @Column(name = "raining")
    private Boolean raining;

    @NotNull(message = "Sensor should not be empty")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id", nullable = false)
    private Sensor sensor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                ", createdAt=" + createdAt +
                '}';
    }
}
