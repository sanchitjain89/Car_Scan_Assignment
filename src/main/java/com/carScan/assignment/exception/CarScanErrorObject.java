package com.carScan.assignment.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarScanErrorObject extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
    private String message;
    private String timestamp;

    public CarScanErrorObject(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
