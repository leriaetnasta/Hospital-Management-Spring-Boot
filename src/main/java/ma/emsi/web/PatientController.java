package ma.emsi.web;

import ma.emsi.entities.Patient;
import ma.emsi.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class PatientController {
    private
    PatientRepository patientRepository;
    public PatientController(PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }
    @GetMapping(path= "/index")
    public String patients(Model model,
                           @RequestParam(name= "page", defaultValue = "0") int page, // parametre d'url : request.getparametre(page), si on specifie pas le parametre il va prendre la valeur 0 par defaut
                           @RequestParam(name= "size", defaultValue = "5") int size){
        Page<Patient> pagePatients= patientRepository.findAll(PageRequest.of(page, size)); //  je veux les patients de la page 0 et size 5
        model.addAttribute("listpatients", pagePatients.getContent()); // getcontent donne la liste des patients de la page
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return "patients";
    }
}
