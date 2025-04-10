package com.bookmyshow.movie_booking_system.service;

import com.bookmyshow.movie_booking_system.entity.Seat;
import com.bookmyshow.movie_booking_system.entity.ShowSeat;
import com.bookmyshow.movie_booking_system.entity.ShowTime;
import com.bookmyshow.movie_booking_system.enums.SeatStatus;
import com.bookmyshow.movie_booking_system.repository.SeatRepository;
import com.bookmyshow.movie_booking_system.repository.ShowSeatRepository;
import com.bookmyshow.movie_booking_system.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ShowSeatDataService {

    @Autowired
    private ShowTimeRepository showTimeRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    public int createShowSeatsForShowTime(long showTimeId){
        ShowTime showTime = showTimeRepository.findById(showTimeId).orElseThrow(()-> new RuntimeException("ShowTime not found"));
        List<Seat> seats = seatRepository.findByScreen(showTime.getScreen().getId());

        List<ShowSeat> showSeats = new ArrayList<>();
        for(Seat seat: seats){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setShowTime(showTime);
            showSeat.setSeat(seat);
            showSeat.setStatus(SeatStatus.AVAILABLE);
            showSeats.add(showSeat);
        }

        List<ShowSeat> res = showSeatRepository.saveAll(showSeats);
        if(res.size()==seats.size()){
            return 1;
        }
        return 0;
    }
}
