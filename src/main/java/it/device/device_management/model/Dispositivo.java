package it.device.device_management.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import it.device.device_management.enums.Stato;


import jakarta.persistence.*;
import lombok.Data;




@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Dispositivo {

    @Id
    @GeneratedValue
    private int id;

    private String nome;
    private String marca;

    @Enumerated(EnumType.STRING)
    private Stato stato;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    @JsonIgnore(value = true)
    private Dipendente dipendente;
}