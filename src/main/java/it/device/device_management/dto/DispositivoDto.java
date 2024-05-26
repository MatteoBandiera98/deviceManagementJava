package it.device.device_management.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.device.device_management.enums.Stato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import it.device.device_management.model.Dispositivo;
import lombok.Getter;

@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ComputerDto.class, name = "computer"),
        @JsonSubTypes.Type(value = SmartphoneDto.class, name = "smartphone")
})
@Data
public abstract class DispositivoDto {
    @NotBlank(message = "Il nome non può essere null")
    private String nome;

    @NotBlank(message = "La marca non può essere null")
    private String marca;

    @NotNull(message = "Lo stato non può essere nullo")
    private Stato stato;

    public abstract Dispositivo toModel();

    public DispositivoDto() {}


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setStao(Stato stato) {
        this.stato = stato;
    }
}
