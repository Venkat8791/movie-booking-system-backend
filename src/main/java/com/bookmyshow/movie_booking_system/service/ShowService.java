package com.bookmyshow.movie_booking_system.service;

import com.bookmyshow.movie_booking_system.dto.GetShowDTO;
import com.bookmyshow.movie_booking_system.dto.GetShowTimeSeatLayoutDTO;
import com.bookmyshow.movie_booking_system.dto.GetShowTimesDTO;
import com.bookmyshow.movie_booking_system.dto.SeatDTO;
import com.bookmyshow.movie_booking_system.entity.ShowSeat;
import com.bookmyshow.movie_booking_system.entity.ShowTime;
import com.bookmyshow.movie_booking_system.enums.SeatStatus;
import com.bookmyshow.movie_booking_system.enums.SeatType;
import com.bookmyshow.movie_booking_system.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        String showDate = showTime.getShowDate().toString();
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
        return new GetShowTimeSeatLayoutDTO(showTimeId,cinemaName,screenName,showTimeName,movieName,showDate,seatsForTheShow);



    }

    public GetShowTimesDTO getShowTimesForDay(long cinemaId,long movieId, String showDate) {
        LocalDate date = LocalDate.parse(showDate);
        List<ShowTime> showTimes = showTimeRepository.findShowsByMovieIdAndShowDate(cinemaId,movieId,date);
        List<GetShowDTO> getShowDTOS = new ArrayList<>();
        for(ShowTime showTime: showTimes){
            Long showTimeId = showTime.getId();
            Long screenId = showTime.getScreen().getId();
            String screenName = showTime.getScreen().getScreenName();
            String showTimeName = showTime.getStartTime().toString();
            int noOfSeats = showTime.getScreen().getTotalSeats();
            int availableSeats = showTime.getAvailableSeats();
            String language = showTime.getLanguage()!=null ? showTime.getLanguage().getLanguageName() : "";
            GetShowDTO getShowDTO = new GetShowDTO(showTimeId,screenId,screenName,showTimeName,language,noOfSeats,availableSeats);
            getShowDTOS.add(getShowDTO);
        }
        return new GetShowTimesDTO(cinemaId,movieId,showDate,getShowDTOS);
    }
}
