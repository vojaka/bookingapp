package com.example.bookingapp.repository;

import com.example.bookingapp.entity.WorkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkTypeRepository extends JpaRepository<WorkType,Long> {

}
