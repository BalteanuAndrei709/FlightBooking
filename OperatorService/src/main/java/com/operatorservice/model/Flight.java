package com.operatorservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "flight")
public class Flight {

    @Id
    private Integer id;
    private String operator;
    private String from;
    private String to;
    private Date departureTime;
    private Date arrivalTime;
    private Date departureDate;
}


