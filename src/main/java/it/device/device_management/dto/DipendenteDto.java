package it.device.device_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DipendenteDto {

    @NotBlank(message = "Lo username non può essere null, e non può essere vuoto o composto solo da spazi")
    private String username;
    @NotBlank(message = "Il nome non può essere null, e non può essere vuoto o composto solo da spazi")
    private String nome;
    @NotBlank(message = "Il cognome non può essere null, e non può essere vuoto o composto solo da spazi")
    private String cognome;
    @Email
    @NotBlank(message = "L'email non può essere null, e non può essere vuota o composta solo da spazi")
    private String email;
}
