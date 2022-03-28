package com.example.bookingapp.repository;

import com.example.bookingapp.entity.Appointment;
import com.example.bookingapp.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

}
