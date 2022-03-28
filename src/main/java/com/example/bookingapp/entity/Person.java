package com.example.bookingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
     @Id
     private String personCode;
     private String firstName; // NotNull ja NotBlank
     private String lastName; // NotNull ja NotBlank
     @Column(unique = true)
     private String email;
     private String phone;
     private String password;
}
