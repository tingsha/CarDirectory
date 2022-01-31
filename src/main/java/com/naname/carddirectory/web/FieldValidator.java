package com.naname.carddirectory.web;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FieldValidator {
    public boolean checkCarNumber(String number){
        return number.matches("([АВЕКМНОРСТУХавекмнорстухABEKMHOPCTYXabekmhopctyx]\\s*\\d{3}\\s*[АВЕКМНОРСТУХавекмнорстухABEKMHOPCTYXabekmhopctyx]{2}\\s*\\d{2,3})|([АВЕКМНОРСТУХавекмнорстухABEKMHOPCTYXabekmhopctyx]{2}\\s*\\d{3}\\s*\\d{2,3})");
    }

    public boolean checkYear(String year){
        try{
            int y = Integer.parseInt(year);
            return y >= 1950 && y <= LocalDate.now().getYear();
        } catch (NumberFormatException e){
            return false;
        }
    }
}