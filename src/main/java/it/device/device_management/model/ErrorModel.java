package it.device.device_management.model;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorModel {
    private String messaggio;
    private LocalDateTime dataErrore;
}