package com.example.bookingapp.service;

import com.example.bookingapp.entity.Location;
import com.example.bookingapp.entity.TimeSlot;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class BookingAppService {
    public boolean checkTimeSlot(Long id, TimeSlot timeSlot, Location location) {


////        SimpleDateFormat localDateFormat = new SimpleDateFormat("hh:mm:ss");
////        String timeSlotEndTime = localDateFormat.format(timeSlot.getEndDate());
//        LocalTime timeSlotEndTime = LocalTime.parse(timeSlot.getEndDate());
//        log.info(timeSlotEndTime);
        log.info("checking timeslot");
//            log.info(timeSlotEndTime + ", " + String.valueOf(location.getLocationEndTime()));
        return true;
//        if (
//                //timeSlot.getEndDate().compareTo(timeSlot.getInitialDate())>0
////                       || timeSlotEndTime.compareTo(location.getLocationEndTime())>0
//        )
//        {
//
//        }
//        return false;
    }
}
