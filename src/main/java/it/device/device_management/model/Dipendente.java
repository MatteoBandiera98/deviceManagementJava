package it.device.device_management.model;






import jakarta.persistence.*;
import lombok.Data;




import java.util.List;

@Data
@Entity
@Table(name = "dipendenti")
public class Dipendente {

    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String nome;
    private String cognome;
    private String email;

    @OneToMany(mappedBy = "dipendente")

    private List<Dispositivo> dispositivi;

    private String foto;

}
