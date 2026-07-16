package ca.tetervak.petdata.controller;

import ca.tetervak.petdata.repository.PetOwnerRepository;
import ca.tetervak.petdata.repository.PetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Controller
public class PetController {

    private final PetRepository petRepository;
    private final PetOwnerRepository ownerRepository;

    public PetController(PetRepository petRepository, PetOwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    @GetMapping({"/", "/index"})
    public String showIndex() {
        return "index";
    }

    @GetMapping("/pets")
    public String listPets(Model model) {
        model.addAttribute("pets", petRepository.findAll());
        return "pet-list";
    }

    @GetMapping("/pets/{id}")
    public String showPetDetails(@PathVariable Integer id, Model model) {
        model.addAttribute("pet", petRepository.findById(id).orElseThrow());
        return "pet-details";
    }

    @GetMapping("/owners")
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerRepository.findAll());
        return "owner-list";
    }

    @GetMapping("/owners/{id}")
    public String showOwnerDetails(@PathVariable Integer id, Model model) {
        model.addAttribute("owner", ownerRepository.findById(id).orElseThrow());
        return "owner-details";
    }

    @ModelAttribute("localDate")
    LocalDate getlLocalDate(){
        return LocalDate.now();
    }

    @ExceptionHandler(NoSuchElementException.class)
    ModelAndView dataNotFound(NoSuchElementException e){
        ModelAndView mv = new ModelAndView("data-not-found", "message", e.getMessage());
        mv.addObject("localDate", getlLocalDate());
        return mv;
    }
}
