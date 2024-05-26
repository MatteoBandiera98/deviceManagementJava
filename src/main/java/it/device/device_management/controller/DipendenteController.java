package it.device.device_management.controller;



import it.device.device_management.dto.DipendenteDto;
import it.device.device_management.exception.DipendenteNonTrovatoException;
import it.device.device_management.exception.MyBadRequestException;
import it.device.device_management.model.Dipendente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import it.device.device_management.service.DipendenteService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;



    @PostMapping("/dipendenti")
    public String saveDipendente(@RequestBody @Validated DipendenteDto dipendenteDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MyBadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("",(s1, s2)->s1+s2));
        }
        return dipendenteService.saveDipendente(dipendenteDto);
    }

    @GetMapping("/dipendenti")
    public List<Dipendente> getAllDipendenti(){
        return dipendenteService.getAllDipendenti();
    }

    @GetMapping("/dipendenti/{id}")
    public Dipendente getDipendenteById( @PathVariable int id){
        Optional<Dipendente> dipendenteOptional = dipendenteService.getDipendenteById(id);
        if(dipendenteOptional.isPresent()){
            Dipendente dipendente = dipendenteOptional.get();
            return dipendente;

        } else {
            throw new DipendenteNonTrovatoException("Il dipendente con id " + id + " non è stato trovato.");
        }
    }

    @PutMapping("/dipendenti/{id}")
    public Dipendente updateDipendente( @PathVariable int id, @RequestBody @Validated DipendenteDto dipendenteDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new MyBadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("",(s1,s2)->s1+s2));
        }

        return dipendenteService.updateDipendente(id,dipendenteDto);
    }

    @DeleteMapping("/dipendenti/{id}")
    public String deleteDipendente(@PathVariable int id){
        return dipendenteService.deleteDipendente(id);
    }

    @PatchMapping("/dipendenti/{id}")
    public String patchFotoDipendente( @PathVariable int id, @RequestBody MultipartFile foto) throws IOException {
        return dipendenteService.patchFotoDipendente(id,foto);
    }
}
