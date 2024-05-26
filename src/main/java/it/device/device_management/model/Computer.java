package it.device.device_management.model;

import jakarta.persistence.Entity;
import lombok.Data;



@Data
@Entity

public class Computer extends Dispositivo {
    public int ram;
    public int monitor;
}
