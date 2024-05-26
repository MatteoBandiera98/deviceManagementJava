package it.device.device_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import it.device.device_management.model.Smartphone;
import it.device.device_management.model.Dispositivo;

@Data
public class SmartphoneDto extends DispositivoDto {
    @NotNull(message = "La memoria non può essere null")
    @Min(value = 1, message = "La memoria deve essere maggiore di zero")
    private Integer memoria;

    @NotNull(message = "Il campo dualSim non può essere nullo")
    private Boolean dualSim;

    @Override
    public Dispositivo toModel() {
        Smartphone smartphone = new Smartphone();
        smartphone.setNome(getNome());
        smartphone.setMarca(getMarca());
        smartphone.setStato(getStato());
        smartphone.setMemoria(getMemoria());
        smartphone.setDualSim(getDualSim());
        return smartphone;
    }
    public boolean isDualSim() {
        return dualSim;
    }
}



