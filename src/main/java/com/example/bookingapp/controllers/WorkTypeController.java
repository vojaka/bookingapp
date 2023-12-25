package com.example.bookingapp.controllers;

import com.example.bookingapp.entity.Person;
import com.example.bookingapp.repository.PersonRepository;
import com.example.bookingapp.repository.WorkTypeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class WorkTypeController {

    @Autowired
    WorkTypeRepository workTypeRepository;

//    @PostMapping("worktype")
//    public String signup(@RequestBody Person person){
//        personRepository.save(person);
//        return "New person added: " + person.getFirstName() + " " + person.getLastName();
//    }
}
