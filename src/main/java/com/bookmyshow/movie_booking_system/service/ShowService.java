package com.bookmyshow.movie_booking_system.service;

import com.bookmyshow.movie_booking_system.dto.GetShowTimeSeatLayoutDTO;
import com.bookmyshow.movie_booking_system.dto.SeatDTO;
import com.bookmyshow.movie_booking_system.entity.ShowSeat;
import com.bookmyshow.movie_booking_system.entity.ShowTime;
import com.bookmyshow.movie_booking_system.enums.SeatStatus;
import com.bookmyshow.movie_booking_system.enums.SeatType;
import com.bookmyshow.movie_booking_system.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    ShowTimeRepository showTimeRepository;

    public GetShowTimeSeatLayoutDTO getSeatsForShowTime(long id){
        Optional<ShowTime> showTimeOptional = showTimeRepository.findById(id);
        if(showTimeOptional.isEmpty()){
            return null;
        }
        List<ShowSeat> seats = showTimeRepository.findSeatsByShowTime(id);
        ShowTime showTime = showTimeOptional.get();
        long showTimeId = showTime.getId();
        String screenName = showTime.getScreen().getScreenName();
        String cinemaName = showTime.getScreen().getCinema().getName();
        String movieName = showTime.getMovie().getTitle();
        String showTimeName = showTime.getStartTime().toString();
        List<SeatDTO> seatsForTheShow = new ArrayList<>();
        for(ShowSeat showSeat: seats){
            long seatId = showSeat.getSeat().getId();
            String rowNumber = showSeat.getSeat().getRowNum();
            int columnNumber = showSeat.getSeat().getColumnNum();
            int price = showSeat.getSeat().getPrice();
            SeatType seatType = showSeat.getSeat().getSeatType();
            SeatStatus seatStatus = showSeat.getStatus();
            SeatDTO seatDTO = new SeatDTO(seatId,rowNumber,columnNumber,seatType,price,seatStatus);
            seatsForTheShow.add(seatDTO);
        }
        return new GetShowTimeSeatLayoutDTO(showTimeId,cinemaName,screenName,showTimeName,movieName,seatsForTheShow);



    }
}
