package com.bookmyshow.movie_booking_system.service;

import com.bookmyshow.movie_booking_system.dto.BookingDetailsDTO;
import com.bookmyshow.movie_booking_system.dto.MovieDTO;
import com.bookmyshow.movie_booking_system.dto.ShowDetailsDTO;
import com.bookmyshow.movie_booking_system.dto.request.BookingRequestDTO;
import com.bookmyshow.movie_booking_system.dto.response.BookingResponseDTO;
import com.bookmyshow.movie_booking_system.entity.mysql.*;
import com.bookmyshow.movie_booking_system.enums.SeatStatus;
import com.bookmyshow.movie_booking_system.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
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

    public BookingResponseDTO addBooking(BookingRequestDTO bookingDTO, String email) {
        Booking booking = new Booking();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            log.error("User not found");
            throw new RuntimeException("User not found");
        }
        User user = optionalUser.get();
        user.addBooking(booking);

        Optional<ShowTime> showTimeOptional = showTimeRepository.findById(bookingDTO.getShowTimeId());
        if (showTimeOptional.isEmpty()) {
            log.error("Show Time not found");
            throw new RuntimeException("Show Time not found");
        }
        ShowTime showTime = showTimeOptional.get();
        showTime.addBooking(booking);

        log.info("booking in progress for user:{}", email);
        int availableSeats = showTime.getAvailableSeats() - bookingDTO.getSeatIds().size();
        showTime.setAvailableSeats(availableSeats);

        String paymentStatus = "Paid";
        double totalPrice = bookingDTO.getTotalPrice();
        Date bookingDate = new Date();

        booking.setPaymentStatus(paymentStatus);
        booking.setTotalPrice(totalPrice);
        booking.setBookingDate(bookingDate);

        for (String seatId : bookingDTO.getSeatIds()) {
            ShowSeat showSeat = showSeatRepository.findBySeatAndShowTime(seatId, showTime.getId());
            showSeat.setStatus(SeatStatus.BOOKED);
            booking.addSeat(showSeat);
        }

        Booking savedBooking = bookingRepository.save(booking);
        return new BookingResponseDTO(savedBooking.getId(), user.getId(), showTime.getId(), bookingDTO.getSeatIds().size(), totalPrice, bookingDate);
    }

    public BookingDetailsDTO getBooking(long bookingId) {
        Booking booking = bookingRepository.fetchBookingWithDetails(bookingId);
        if (booking == null) {
            throw new RuntimeException("booking not found");
        }
        ShowTime showTime = booking.getShowTime();
        Screen screen = showTime.getScreen();
        Cinema cinema = screen.getCinema();
        List<ShowSeat> seatsBooked = booking.getSeatsBooked();
        Movie movie = showTime.getMovie();
        MovieDTO movieDTO = new MovieDTO(movie.getTitle(), movie.getDuration(), movie.getGenre(), movie.getPosterUrl());
        ShowDetailsDTO showDetailsDTO = new ShowDetailsDTO(showTime.getStartTime().toString(), screen.getScreenName(), cinema.getName(), cinema.getLocation());
        List<String> seatNumbers = new ArrayList<>();
        for (ShowSeat showSeat : seatsBooked) {
            Seat seat = showSeat.getSeat();
            seatNumbers.add(seat.getSeatNumber());
        }
        return new BookingDetailsDTO(bookingId, showTime.getLanguage().getLanguageName(), movieDTO, showDetailsDTO, seatNumbers, booking.getTotalPrice());
    }
}
