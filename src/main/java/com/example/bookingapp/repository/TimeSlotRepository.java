package com.example.bookingapp.repository;

import com.example.bookingapp.entity.Location;
import com.example.bookingapp.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot,Long> {
    List<TimeSlot> findByOrderByIdAsc();
}
