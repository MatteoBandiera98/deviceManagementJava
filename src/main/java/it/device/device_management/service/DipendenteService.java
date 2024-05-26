package it.device.device_management.service;

import com.cloudinary.Cloudinary;
import it.device.device_management.dto.DipendenteDto;
import it.device.device_management.enums.Stato;
import it.device.device_management.exception.DipendenteNonTrovatoException;
import it.device.device_management.model.Dipendente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import it.device.device_management.repository.DipendenteRepository;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired


    public String saveDipendente(DipendenteDto dipendenteDto) {
        Dipendente dipendente = new Dipendente();
        dipendente.setNome(dipendenteDto.getNome());
        dipendente.setCognome(dipendenteDto.getCognome());
        dipendente.setEmail(dipendenteDto.getEmail());
        dipendente.setUsername(dipendenteDto.getEmail());
        dipendenteRepository.save(dipendente);

        return "Dipendente con username " + dipendenteDto.getUsername() + " è stato salvato con successo";
    }

    public List<Dipendente> getAllDipendenti() {
        return dipendenteRepository.findAll();
    }

    public Optional<Dipendente> getDipendenteById(int id) {
        return dipendenteRepository.findById(id);
    }

    public Dipendente updateDipendente(int id, DipendenteDto dipendenteDto) {
        Optional<Dipendente> dipendenteOptional = getDipendenteById(id);
        if (dipendenteOptional.isPresent()) {
            Dipendente dipendente = dipendenteOptional.get();
            dipendente.setNome(dipendenteDto.getNome());
            dipendente.setCognome(dipendenteDto.getCognome());
            dipendente.setUsername(dipendenteDto.getUsername());
            dipendente.setEmail(dipendenteDto.getEmail());
            dipendenteRepository.save(dipendente);
            return dipendente;
        } else {
            throw new DipendenteNonTrovatoException("Il dipendente con id " + id + " non è stato trovato");
        }
    }

    public String deleteDipendente(int id) {
        Optional<Dipendente> dipendenteOptional = getDipendenteById(id);
        if (dipendenteOptional.isPresent()) {
            Dipendente dipendente = dipendenteOptional.get();

            if (!dipendente.getDispositivi().isEmpty()) {
                dipendente.getDispositivi().stream().forEach(dispositivo -> dispositivo.setStato(Stato.DISPONIBILE));
                dipendente.getDispositivi().stream().forEach(dispositivo -> dispositivo.setDipendente(null));
            }
            dipendenteRepository.delete(dipendenteOptional.get());
            return "Il dipendente con id " + id + " è stato eliminato con successo.";
        } else {
            throw new DipendenteNonTrovatoException("Il dipendente con id " + id + " non è stato trovato");
        }


    }

    public String patchFotoDipendente(int id, MultipartFile foto) throws IOException {
        Optional<Dipendente> dipendenteOptional = getDipendenteById(id);
        if (dipendenteOptional.isPresent()) {
            String url = (String) cloudinary.uploader().upload(foto.getBytes(), Collections.emptyMap()).get("url");
            Dipendente dipendente = dipendenteOptional.get();
            dipendente.setFoto(url);
            dipendenteRepository.save(dipendente);
            return "Foto con url " + url + " salvata e associata correttamente al dipendente con id " + id;
        } else {
            throw new DipendenteNonTrovatoException("Il dipendente con id " + id + " non è stato trovato");
        }
    }




}
