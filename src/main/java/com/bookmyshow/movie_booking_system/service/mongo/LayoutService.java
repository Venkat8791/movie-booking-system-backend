package com.bookmyshow.movie_booking_system.service.mongo;

import com.bookmyshow.movie_booking_system.dto.request.LayoutRequestDTO;
import com.bookmyshow.movie_booking_system.dto.SeatRowDTO;
import com.bookmyshow.movie_booking_system.dto.SeatSectionDTO;
import com.bookmyshow.movie_booking_system.entity.mongodb.LayoutDocument;
import com.bookmyshow.movie_booking_system.entity.mongodb.SeatD;
import com.bookmyshow.movie_booking_system.entity.mongodb.SeatRow;
import com.bookmyshow.movie_booking_system.entity.mongodb.SeatSection;
import com.bookmyshow.movie_booking_system.exception.dto.LayoutDocumentNotFoundException;
import com.bookmyshow.movie_booking_system.repository.mongo.LayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LayoutService {

    @Autowired
    LayoutRepository layoutRepository;

    public LayoutDocument fetchLayoutByScreenId(long screenId){
        Optional<LayoutDocument> layoutDocumentOptional =  layoutRepository.findByScreenId(screenId);
        return layoutDocumentOptional.orElseThrow(()->  new LayoutDocumentNotFoundException("Failed to fetch layout document for the screen: " + screenId));
    }

    public LayoutDocument insertSeatLayoutToScreenId(LayoutRequestDTO layoutRequestDTO){
        LayoutDocument layoutDocument = new LayoutDocument();
        long screenId = layoutRequestDTO.getScreenId();
        layoutDocument.setScreenId(screenId);
        layoutDocument.setCinemaId(layoutRequestDTO.getCinemaId());
        List<SeatSection> sections = new ArrayList<>();
        for(SeatSectionDTO seatSectionDTO: layoutRequestDTO.getSections()){
            SeatSection seatSection = new SeatSection();
            seatSection.setSectionName(seatSectionDTO.getSectionName());
            seatSection.setPrice(seatSectionDTO.getPrice());
            List<SeatRow> seatRows = new ArrayList<>();
            for(SeatRowDTO seatRowDTO: seatSectionDTO.getSeatRows()){
                SeatRow seatRow = new SeatRow();
                List<SeatD> seats = new ArrayList<>();
                String rowLabel = seatRowDTO.getLabel();
                seatRow.setLabel(rowLabel);
                int gapNumber = 1;
                for(String s: seatRowDTO.getSeats()){
                    SeatD seatD = new SeatD();
                    String seatId;
                    if(s.isEmpty()){
                        seatId = "GAP-" + rowLabel + gapNumber;
                        gapNumber+=1;
                    }
                    else{
                        seatId = "S" + screenId + "-" + s;
                    }
                    seatD.setSeatId(seatId);
                    seatD.setLabel(s.isEmpty()? "GAP":s);
                    seatD.setGap(s.isEmpty());
                    seats.add(seatD);
                }
                seatRow.setSeats(seats);
                seatRows.add(seatRow);
            }
            seatSection.setRows(seatRows);
            sections.add(seatSection);
        }
        layoutDocument.setSections(sections);
        return layoutRepository.save(layoutDocument);
    }
}
