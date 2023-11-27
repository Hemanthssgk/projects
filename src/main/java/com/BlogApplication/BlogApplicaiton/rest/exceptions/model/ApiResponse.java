package com.BlogApplication.BlogApplicaiton.rest.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse {
    String message;
    Date timeStamp;
    boolean status;
}
