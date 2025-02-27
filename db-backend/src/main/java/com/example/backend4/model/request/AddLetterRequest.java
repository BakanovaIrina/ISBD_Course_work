package com.example.backend4.model.request;

import java.io.Serializable;

public class AddLetterRequest implements Serializable {
    public String childName;
    public String childSurname;
    public String country;
    public String region;
    public String city;
    public String street;
    public String house;
    public Integer room;
    public String giftName;
    public String actions;
    public String descriptions;
    public Boolean truth;
    public Boolean approval;
    public Boolean positivities;
}
