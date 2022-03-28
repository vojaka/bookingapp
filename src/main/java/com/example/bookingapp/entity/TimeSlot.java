package com.example.bookingapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @JsonDeserialize(using= LocalDateDeserializer.class)
    private LocalDate initialDate;

    @NotNull
    @JsonDeserialize(using= LocalDateDeserializer.class)
    private LocalDate endDate;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime initialTime;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    private Boolean isTaken;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}
