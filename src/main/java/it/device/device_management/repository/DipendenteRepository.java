package it.device.device_management.repository;


import it.device.device_management.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DipendenteRepository extends JpaRepository<Dipendente,Integer> {
}