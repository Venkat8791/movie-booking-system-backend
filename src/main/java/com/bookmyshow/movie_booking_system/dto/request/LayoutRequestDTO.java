package com.bookmyshow.movie_booking_system.dto.request;

import com.bookmyshow.movie_booking_system.dto.SeatSectionDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class LayoutRequestDTO {
    @NotNull
    private long screenId;
    @NotNull
    private long cinemaId;

    @NotNull
    private List<SeatSectionDTO> sections;
}
