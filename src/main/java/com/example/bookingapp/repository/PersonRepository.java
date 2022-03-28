package com.example.bookingapp.repository;

import com.example.bookingapp.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,String> {
    Person getPersonByEmail(String email);
}
