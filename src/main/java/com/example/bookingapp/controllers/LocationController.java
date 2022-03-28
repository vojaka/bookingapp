package com.example.bookingapp.controllers;
import com.example.bookingapp.entity.Location;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.bookingapp.repository.LocationRepository;
import java.util.List;


@RestController
@Log4j2
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @GetMapping("location/timeslots")
    public List<Location> getLocationTimeSlots() {
        List<Location> locations = locationRepository.findAll();
        return locations;
    }

    @GetMapping("location")
    public List<Location> getLocation() {
        List<Location> locations = locationRepository.findAll();
        return locations;
    }

//    @GetMapping("locations")
//    public List<Location> getLocations() {
//        List<Location> locations = locationRepository.getLocationsByTimeSlots(Boolean.TRUE);
//        return locations;
//    }

    @GetMapping("location/{id}")
    public Location viewLocation(@PathVariable Long id){
        Location location = locationRepository.findById(id).get();
        return location;
    }

    @PostMapping("location")
    public String addLocation(@RequestBody Location location){
        locationRepository.save(location);
        return "New product location: " + location.getName();
    }

    @DeleteMapping("location/{id}")
    public List<Location> deleteLocation(@PathVariable Long id){
        locationRepository.deleteById(id);
        return locationRepository.findAll();
    }

    @PutMapping("location/{id}")
    public String editLocation(@PathVariable Long id, @RequestBody Location location){
        locationRepository.save(location);
        return "Product successfully edited:" + id;
    }

}
