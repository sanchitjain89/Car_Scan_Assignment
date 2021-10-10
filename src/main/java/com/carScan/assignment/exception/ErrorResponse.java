package com.carScan.assignment.exception;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement(name = "error")
@Data
public class ErrorResponse {

    private String message;
    private List<String> details;
    private String timestamp;

    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
        timestamp = new Date().toInstant().toString();
    }
    
    
}
