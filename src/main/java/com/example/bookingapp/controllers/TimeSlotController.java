package com.example.bookingapp.controllers;

import com.example.bookingapp.entity.Location;
import com.example.bookingapp.entity.TimeSlot;
import com.example.bookingapp.repository.LocationRepository;
import com.example.bookingapp.repository.TimeSlotRepository;
import com.example.bookingapp.service.BookingAppService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Log4j2
public class TimeSlotController {

    @Autowired
    TimeSlotRepository timeSlotRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    BookingAppService bookingAppService;

    @GetMapping("timeslot")
    public List<TimeSlot> getTimeslot(){
        return timeSlotRepository.findByOrderByIdAsc();
    }

    @GetMapping("timeslot/{id}")
    public TimeSlot viewTimeSlot(@PathVariable Long id){
        TimeSlot timeSlot = timeSlotRepository.findById(id).get();
        return timeSlot;
    }

    @PostMapping("location/{id}/timeslot")
    public String addTimeSlot(@PathVariable Long id, @RequestBody TimeSlot timeSlot){
            Location location = locationRepository.findById(id).get();
            if (bookingAppService.checkTimeSlot(id, timeSlot, location) == true)
            {
                timeSlot.setLocation(location);
                timeSlot.setIsTaken(true);
                timeSlotRepository.save(timeSlot);
                return "New added " + timeSlot.getId();
            } else {
                return "Not added ";
            }
    }

    @DeleteMapping("timeslot/{id}")
    public List<TimeSlot> deleteTimeSlot(@PathVariable Long id){
        timeSlotRepository.deleteById(id);
        return timeSlotRepository.findAll();
    }

    @PutMapping("timeslot/{id}")
    public String editLocation(@PathVariable Long id, @RequestBody TimeSlot timeSlot){
        timeSlotRepository.save(timeSlot);
        return "Successfully edited:" + id;
    }

}
