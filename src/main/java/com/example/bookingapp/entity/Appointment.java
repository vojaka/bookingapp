package com.example.bookingapp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Time;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String personCode;
    private Time statusUpdateTime;
    private AppointmentStatus appointmentStatus;

    @OneToOne
    @JoinColumn(name = "timeslot_id")
    private TimeSlot timeSlot;

    @OneToOne
    @JoinColumn(name="work_id")
    private Work work;

    @OneToOne
    private Person person;
}