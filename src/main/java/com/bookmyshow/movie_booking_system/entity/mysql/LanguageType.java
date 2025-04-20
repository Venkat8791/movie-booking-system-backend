package com.bookmyshow.movie_booking_system.entity.mysql;

public enum LanguageType {
    ENGLISH("English"),
    HINDI("Hindi"),
    TELUGU("Telugu"),
    TAMIL("Tamil"),
    MALAYALAM("Malayalam");

    private String languageName;

    LanguageType(String name) {
        this.languageName = name;
    }
    public String getLanguageName(){
        return languageName;
    }

}
