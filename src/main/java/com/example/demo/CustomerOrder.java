package com.example.demo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class CustomerOrder {

    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String book;
    @Setter
    @Getter
    private String date;
    @Setter
    @Getter
    private String address;

}
