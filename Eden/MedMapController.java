package com.medmap.medmap;

import com.medmap.medmap.repository.InventoryRepository;
import com.medmap.medmap.repository.MedicineRepository;
import com.medmap.medmap.repository.PharmacyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MedMapController {

    private final PharmacyRepository pharmacyRepo;
    private final MedicineRepository medicineRepo;
    private final InventoryRepository inventoryRepo;

    public MedMapController(
            PharmacyRepository pharmacyRepo,
            MedicineRepository medicineRepo,
            InventoryRepository inventoryRepo
    ) {
        this.pharmacyRepo = pharmacyRepo;
        this.medicineRepo = medicineRepo;
        this.inventoryRepo = inventoryRepo;
    }

    // ================= USER PAGE =================

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("cities",
                pharmacyRepo.findAll()
                        .stream()
                        .map(Pharmacy::getCity)
                        .distinct()
                        .toList());

        model.addAttribute("areas",
                pharmacyRepo.findAll()
                        .stream()
                        .map(Pharmacy::getArea)
                        .distinct()
                        .toList());

        model.addAttribute("medicines", medicineRepo.findAll());

        return "index";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam String city,
            @RequestParam String area,
            @RequestParam String medicine,
            Model model
    ) {
        var results =
                inventoryRepo.findByPharmacyCityAndPharmacyAreaAndMedicineName(
                        city, area, medicine);

        model.addAttribute("results", results);
        return "results";
    }

    // ================= ADMIN PAGE =================

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("pharmacies", pharmacyRepo.findAll());
        model.addAttribute("medicines", medicineRepo.findAll());
        return "admin";
    }

    @PostMapping("/add-pharmacy")
    public String addPharmacy(
            @RequestParam String name,
            @RequestParam String city,
            @RequestParam String area,
            @RequestParam String phone,
            @RequestParam String email
    ) {
        pharmacyRepo.save(
                new Pharmacy(name, city, area, phone, email)
        );
        return "redirect:/admin";
    }

    @PostMapping("/add-medicine")
    public String addMedicine(@RequestParam String name) {
        if (medicineRepo.findByName(name) == null) {
            medicineRepo.save(new Medicine(name));
        }
        return "redirect:/admin";
    }

    
@PostMapping("/update-inventory")
public String updateInventory(
        @RequestParam Long pharmacyId,
        @RequestParam Long medicineId,
        @RequestParam int quantity,
        @RequestParam double price,
        Model model
) {
    try {
        var pharmacy = pharmacyRepo.findById(pharmacyId).orElse(null);
        var medicine = medicineRepo.findById(medicineId).orElse(null);

        if (pharmacy == null || medicine == null) {
            model.addAttribute("error", "Invalid pharmacy or medicine selected");
            return "admin";
        }

        Inventory existing = inventoryRepo.findByPharmacyIdAndMedicineId(pharmacyId, medicineId);

        if (existing != null) {
            existing.setQuantity(quantity);
            existing.setPrice(price);
            inventoryRepo.save(existing);
        } else {
            inventoryRepo.save(new Inventory(pharmacy, medicine, quantity, price));
        }

        model.addAttribute("success", "Inventory updated successfully");
    } catch (Exception e) {
        e.printStackTrace(); // optional for debugging
        model.addAttribute("error", "Something went wrong: " + e.getMessage());
    }

    return "admin";
}
}
