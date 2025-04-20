package com.bookmyshow.movie_booking_system.service.mongo;

import com.bookmyshow.movie_booking_system.entity.mongodb.LayoutDocument;
import com.bookmyshow.movie_booking_system.repository.mongo.LayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LayoutService {

    @Autowired
    LayoutRepository layoutRepository;

    public LayoutDocument fetchLayoutByScreenId(long screenId){
        Optional<LayoutDocument> layoutDocumentOptional =  layoutRepository.findByScreenId(screenId);
        return layoutDocumentOptional.orElseGet(LayoutDocument::new);
    }
}
