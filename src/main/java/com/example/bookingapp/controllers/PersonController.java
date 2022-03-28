package com.example.bookingapp.controllers;



import com.example.bookingapp.entity.Person;
import com.example.bookingapp.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("person")
    public List<Person> getCategory(){
        return personRepository.findAll();
    }

    @GetMapping("person/{personCode}")
    public Person viewPerson(@PathVariable String personCode){
        Person person = personRepository.findById(personCode).get();
        return person;
    }

    @DeleteMapping("person/{personCode}")
    public List<Person> deletePerson(@PathVariable String personCode){
        personRepository.deleteById(personCode);
        return personRepository.findAll();
    }

    @DeleteMapping("person")
    public String deleteAllPersons(){
        personRepository.flush();
        return "All products deleted";
    }

    @PutMapping("person/{id}")
    public String editPerson(@PathVariable Long id, @RequestBody Person person){
        personRepository.save(person);
        return "Person successfully edited:" + id;
    }

    @PostMapping("person")
    public String signup(@RequestBody Person person){
        personRepository.save(person);
        return "New person added: " + person.getFirstName() + " " + person.getLastName();
    }

}
