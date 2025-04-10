package com.bookmyshow.movie_booking_system.service;


import com.bookmyshow.movie_booking_system.entity.Screen;
import com.bookmyshow.movie_booking_system.entity.Seat;
import com.bookmyshow.movie_booking_system.enums.SeatType;
import com.bookmyshow.movie_booking_system.repository.ScreenRepository;
import com.bookmyshow.movie_booking_system.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    ScreenRepository screenRepository;

    @Autowired
    SeatRepository seatRepository;

    public int insertSeatsForScreen(long screenId,int rows, int columns,int basePrice){
        Optional<Screen> optionalScreen = screenRepository.findById(screenId);
        if (optionalScreen.isEmpty()) {
            throw new RuntimeException("Screen not found with ID: " + screenId);
        }

        Screen screen = optionalScreen.get();

        List<Seat> seats = new ArrayList<>();
        for(int row=0;row<rows;row++){
            char rowChar = (char)('A'+row);
            for(int col = 1;col<=columns;col++){
                SeatType seatType;
                int price = basePrice;
                if(row<=2){
                    seatType = SeatType.VIP;
                    price += 150;
                }
                else if(row<=8){
                    seatType = SeatType.PREMIUM;
                    price += 75;
                }
                else{
                    seatType = SeatType.REGULAR;
                }
                Seat seat = new Seat();
                seat.setScreen(screen);
                seat.setPrice(price);
                seat.setSeatType(seatType);
                seat.setRowNum(String.valueOf(rowChar));
                seat.setColumnNum(col);;
                seats.add(seat);
            }
        }
        List<Seat> res  = seatRepository.saveAll(seats);
        System.out.println("Seats saved for screen " + screenId);
        if(res.size()==rows*columns){
            return 1;
        }
        return 0;
    }
}
