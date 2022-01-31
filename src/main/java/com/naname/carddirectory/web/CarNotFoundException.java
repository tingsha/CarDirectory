package com.naname.carddirectory.web;

public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(String number){
        super("Could not find car with number: " + number);
    }
}
