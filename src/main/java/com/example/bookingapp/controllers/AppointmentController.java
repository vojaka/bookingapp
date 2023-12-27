package com.example.bookingapp.controllers;

import com.example.bookingapp.entity.Appointment;
import com.example.bookingapp.entity.Person;
import com.example.bookingapp.entity.TimeSlot;
import com.example.bookingapp.entity.AppointmentStatus;
import com.example.bookingapp.repository.AppointmentRepository;
import com.example.bookingapp.repository.LocationRepository;
import com.example.bookingapp.repository.PersonRepository;
import com.example.bookingapp.repository.TimeSlotRepository;
import com.example.bookingapp.service.BookingAppService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@Log4j2
public class AppointmentController {

    @Autowired
    TimeSlotRepository timeSlotRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    BookingAppService bookingAppService;

    @Autowired
    PersonRepository personRepository;

//    @GetMapping("appointments")
//    public List<Appointment> getAppointments(){
//        return appointmentRepository.findAll();
//    }

//    @GetMapping("timeslot/{id}")
//    public TimeSlot viewTimeSlot(@PathVariable Long id){
//        TimeSlot timeSlot = timeSlotRepository.findById(id).get();
//        return timeSlot;
//    }

    @PostMapping("timeslot/{id}/appointment/{PersonId}")
    public String addAppointment(@PathVariable Long id, @PathVariable String PersonId) {
        TimeSlot timeSlot = timeSlotRepository.findById(id).get();
        Person person = personRepository.findById(PersonId).get();
        Appointment appointment = new Appointment();
        appointment.setTimeSlot(timeSlot);
        appointment.setAppointmentStatus(AppointmentStatus.ACTIVE);
        appointment.setPerson(person);
        appointment.setStatusUpdateTime(LocalDateTime.now());
        appointmentRepository.save(appointment);
        timeSlot.setAppointment(appointment);
        timeSlotRepository.save(timeSlot);
        return "New added " + appointment.getId();
    }


//    @DeleteMapping("timeslot/{id}")
//    public List<TimeSlot> deleteTimeSlot(@PathVariable Long id){
//        timeSlotRepository.deleteById(id);
//        return timeSlotRepository.findAll();
//    }
//
//    @PutMapping("timeslot/{id}")
//    public String editLocation(@PathVariable Long id, @RequestBody TimeSlot timeSlot){
//        timeSlotRepository.save(timeSlot);
//        return "Successfully edited:" + id;
//    }

}
