  package com.medmap.jar;

public class MedmapController {
    package com.medmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MedmapController {

    @Autowired
    private MedicineRepository medicineRepository;

    // This shows the home page
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // This handles the search for "A.A" or other cities
    @PostMapping("/search")
    public String search(@RequestParam String city, Model model) {
        List<Medicine> results = medicineRepository.findByCity(city);
        model.addAttribute("results", results);
        model.addAttribute("userCity", city);
        return "results";
    }

    // This shows the upload page for pharmacies
    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    // This saves the pharmacy's medicine data
    @PostMapping("/save")
    public String saveMedicine(@ModelAttribute Medicine medicine) {
        medicine.setCategory("Blood Pressure"); // Keeps the scope small
        medicineRepository.save(medicine);
        return "redirect:/upload?success";
    }
}
   
   
