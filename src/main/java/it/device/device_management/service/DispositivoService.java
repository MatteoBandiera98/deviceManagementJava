package it.device.device_management.service;

import it.device.device_management.dto.ComputerDto;
import it.device.device_management.dto.DispositivoDto;
import it.device.device_management.dto.SmartphoneDto;
import it.device.device_management.enums.Stato;
import it.device.device_management.exception.DipendenteNonTrovatoException;
import it.device.device_management.exception.DispositivoNonDisponibileException;
import it.device.device_management.exception.DispositivoNonTrovatoException;
import it.device.device_management.model.Computer;
import it.device.device_management.model.Dipendente;
import it.device.device_management.model.Dispositivo;
import it.device.device_management.model.Smartphone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.device.device_management.repository.DipendenteRepository;
import it.device.device_management.repository.DispositivoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public String saveDispositivo(DispositivoDto dispositivoDto) {
        if (dispositivoDto instanceof ComputerDto) {
            ComputerDto computerDto = (ComputerDto) dispositivoDto;
            Computer computer = new Computer();
            computer.setNome(dispositivoDto.getNome());
            computer.setMarca(dispositivoDto.getMarca());
            computer.setStato(dispositivoDto.getStato());
            computer.setMonitor(computerDto.getMonitor());
            computer.setRam(computerDto.getRam());
            dispositivoRepository.save(computer);
            return "Il dispositivo " + computerDto.getNome() + " è stato salvato con successo";
        } else if (dispositivoDto instanceof SmartphoneDto) {
            SmartphoneDto smartphoneDto = (SmartphoneDto) dispositivoDto;
            Smartphone smartphone = new Smartphone();
            smartphone.setNome(dispositivoDto.getNome());
            smartphone.setMarca(dispositivoDto.getMarca());
            smartphone.setStato(dispositivoDto.getStato());
            smartphone.setMemoria(smartphoneDto.getMemoria());
            smartphone.setDualSim(smartphoneDto.isDualSim());
            dispositivoRepository.save(smartphone);
            return "Il dispositivo " + smartphoneDto.getNome() + " è stato salvato con successo";
        } else {
            return "Tipo di dispositivo non supportato";
        }
    }

    public List<Dispositivo> getAllDispositivi() {
        return dispositivoRepository.findAll();
    }

    public Optional<Dispositivo> getDispositivoById(int id) {
        return dispositivoRepository.findById(id);
    }

    public Dispositivo updateDispositivo(int id, DispositivoDto dispositivoDto) {
        Optional<Dispositivo> dispositivoOptional = getDispositivoById(id);
        if (dispositivoOptional.isPresent()) {
            Dispositivo dispositivo = dispositivoOptional.get();
            if (dispositivoDto instanceof ComputerDto) {
                ComputerDto computerDto = (ComputerDto) dispositivoDto;
                Computer computer = (Computer) dispositivo;
                computer.setRam(computerDto.getRam());
                computer.setMonitor(computerDto.getMonitor());
                dispositivoRepository.save(computer);
                return computer;
            } else if (dispositivoDto instanceof SmartphoneDto) {
                SmartphoneDto smartphoneDto = (SmartphoneDto) dispositivoDto;
                Smartphone smartphone = (Smartphone) dispositivo;
                smartphone.setMemoria(smartphoneDto.getMemoria());
                smartphone.setDualSim(smartphoneDto.isDualSim());
                dispositivoRepository.save(smartphone);
                return smartphone;
            }
        }
        throw new DispositivoNonTrovatoException("Il dispositivo non è stato trovato.");
    }

    public String deleteDispositivo(int id) {
        Optional<Dispositivo> dispositivoOptional = getDispositivoById(id);
        if (dispositivoOptional.isPresent()) {
            dispositivoRepository.delete(dispositivoOptional.get());
            return "Dispositivo con id " + id + " è stato eliminato con successo.";
        }
        throw new DispositivoNonTrovatoException("Il dispositivo da eliminare non è stato trovato.");
    }

    public String patchDispositivoDipendente(int idDispositivo, int idDipendente) {
        Optional<Dispositivo> dispositivoOptional = getDispositivoById(idDispositivo);
        if (dispositivoOptional.isPresent()) {
            Dispositivo dispositivo = dispositivoOptional.get();
            if (dispositivo.getStato() == Stato.DISPONIBILE) {
                Optional<Dipendente> dipendenteOptional = dipendenteRepository.findById(idDipendente);
                if (dipendenteOptional.isPresent()) {
                    Dipendente dipendente = dipendenteOptional.get();
                    dispositivo.setDipendente(dipendente);
                    dispositivo.setStato(Stato.ASSEGNATO);
                    dispositivoRepository.save(dispositivo);
                    return "Dispositivo con id " + idDispositivo + "associato con successo al dipendente con id " + idDipendente;
                }
                throw new DipendenteNonTrovatoException("Dipendente non trovato");
            }
            throw new DispositivoNonDisponibileException("Il dispositivo richiesto non è al momento disponibile, ma risulta: " + dispositivo.getStato());
        }
        throw new DispositivoNonTrovatoException("Dispositivo non trovato");
    }
}
