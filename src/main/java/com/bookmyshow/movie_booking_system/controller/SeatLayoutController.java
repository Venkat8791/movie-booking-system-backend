package com.bookmyshow.movie_booking_system.controller;

import com.bookmyshow.movie_booking_system.entity.mongodb.LayoutDocument;
import com.bookmyshow.movie_booking_system.service.mongo.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
