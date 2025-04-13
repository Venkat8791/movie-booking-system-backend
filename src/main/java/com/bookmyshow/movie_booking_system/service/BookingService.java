package com.bookmyshow.movie_booking_system.service;

import com.bookmyshow.movie_booking_system.dto.*;
import com.bookmyshow.movie_booking_system.entity.*;
import com.bookmyshow.movie_booking_system.enums.SeatStatus;
import com.bookmyshow.movie_booking_system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowTimeRepository showTimeRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;

    @Autowired
    MovieRepository movieRepository;

    public PostBookingResponseDTO addBooking(BookingDTO bookingDTO){
        System.out.println(bookingDTO);
        for(long seatId: bookingDTO.getSeatIds()){
            System.out.println(seatId);
        }
        Booking booking = new Booking();
        Optional<User> optionalUser = userRepository.findById(bookingDTO.getUserId());
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not Found");
        }
        User user = optionalUser.get();
        user.addBooking(booking);

        Optional<ShowTime> showTimeOptional = showTimeRepository.findById(bookingDTO.getShowTimeId());
        if(showTimeOptional.isEmpty()){
            throw new RuntimeException("Show Time not found");
        }
        ShowTime showTime = showTimeOptional.get();
        showTime.addBooking(booking);

        int availableSeats = showTime.getAvailableSeats() - bookingDTO.getSeatIds().size();
        showTime.setAvailableSeats(availableSeats);

        String paymentStatus = "Paid";
        double totalPrice = bookingDTO.getTotalPrice();
        Date bookingDate = new Date();

        booking.setPaymentStatus(paymentStatus);
        booking.setTotalPrice(totalPrice);
        booking.setBookingDate(bookingDate);

        for(long seatId: bookingDTO.getSeatIds()){
            ShowSeat showSeat = showSeatRepository.findBySeatAndShowTime(seatId,showTime.getId());
            showSeat.setStatus(SeatStatus.BOOKED);
            booking.addSeat(showSeat);
        }

        Booking savedBooking = bookingRepository.save(booking);
        return new PostBookingResponseDTO(savedBooking.getId(),user.getId(),showTime.getId(),bookingDTO.getSeatIds().size(),totalPrice,bookingDate);
    }

    public BookingDetailsDTO getBooking(long bookingId){
        Booking booking = bookingRepository.fetchBookingWithDetails(bookingId);
        if(booking == null){
            throw new RuntimeException("booking not found");
        }
        ShowTime showTime = booking.getShowTime();
        Screen screen = showTime.getScreen();
        Cinema cinema = screen.getCinema();
        List<ShowSeat> seatsBooked = booking.getSeatsBooked();
        Movie movie = showTime.getMovie();
        MovieDTO movieDTO = new MovieDTO(movie.getTitle(),movie.getDuration(),movie.getGenre(),movie.getPosterUrl(),movie.getLanguage());
        ShowDetailsDTO showDetailsDTO = new ShowDetailsDTO(showTime.getStartTime().toString(),screen.getScreenName(),cinema.getName(),cinema.getLocation());
        List<String> seatNumbers = new ArrayList<>();
        for(ShowSeat showSeat : seatsBooked){
            Seat seat = showSeat.getSeat();
            seatNumbers.add(seat.getSeatNumber());
        }
        return new BookingDetailsDTO(bookingId,movieDTO,showDetailsDTO,seatNumbers,booking.getTotalPrice());






    }
}
