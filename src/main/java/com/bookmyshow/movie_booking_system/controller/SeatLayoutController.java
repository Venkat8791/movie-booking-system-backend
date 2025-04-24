package com.bookmyshow.movie_booking_system.controller;

import com.bookmyshow.movie_booking_system.dto.request.LayoutRequestDTO;
import com.bookmyshow.movie_booking_system.entity.mongodb.LayoutDocument;
import com.bookmyshow.movie_booking_system.service.mongo.LayoutService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mxmovies/v1")
public class SeatLayoutController {

    @Autowired
    LayoutService layoutService;

    @GetMapping("/layouts/{screenId}")
    public ResponseEntity<LayoutDocument> getLayoutByScreenId(@PathVariable long screenId){
        LayoutDocument layoutDocument = layoutService.fetchLayoutByScreenId(screenId);
        return ResponseEntity.status(200).body(layoutDocument);
    }

    @PostMapping("/layouts/{screenId}")
    public ResponseEntity<LayoutDocument> insertSeatLayoutIntoScreenId(@RequestBody @Valid LayoutRequestDTO layoutRequestDTO){
        LayoutDocument layoutDocument = layoutService.insertSeatLayoutToScreenId(layoutRequestDTO);
        return ResponseEntity.status(200).body(layoutDocument);
    }
}
