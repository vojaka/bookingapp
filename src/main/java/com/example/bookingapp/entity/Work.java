package com.example.bookingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String workName;
    private Integer workDurationMinutes;

    @ManyToOne
    @JoinColumn(name = "WorkType_WorkTypeId")
    private WorkType workType;
}
