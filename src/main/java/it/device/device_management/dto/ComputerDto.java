package it.device.device_management.dto;




import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import it.device.device_management.model.Computer;
import it.device.device_management.model.Dispositivo;


@Data
public class ComputerDto extends DispositivoDto {
    @NotNull
    @Positive(message = "la ram non può essere null, e deve avere valore maggiore di zero")
    public int ram;

    @NotNull
    @Positive(message = "il monitor non può essere null, e deve avere valore maggiore di zero")
    public int monitor;

    @Override
    public Dispositivo toModel() {
        Computer computer = new Computer();
        computer.setNome(getNome());
        computer.setMarca(getMarca());
        computer.setStato(getStato());
        computer.setRam(getRam());
        computer.setMonitor(getMonitor());
        return computer;
    }
}