package com.example.bookingapp.entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.joda.time.LocalTime;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String longAddress;
    @NotNull
    private LocalTime locationInitialTime;
    @NotNull
    private LocalTime locationEndTime;

    @OneToMany
    @JoinColumn(name = "id")
    private List<TimeSlot> timeSlots;



}
