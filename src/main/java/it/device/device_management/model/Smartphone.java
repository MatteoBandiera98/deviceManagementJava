package it.device.device_management.model;




import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;




@Data
@Table(name = "smartphones")
@Entity
public class Smartphone extends Dispositivo {
    private int memoria;
    private boolean dualSim;
}