package it.device.device_management.repository;



import it.device.device_management.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DispositivoRepository extends JpaRepository<Dispositivo,Integer> {
}
