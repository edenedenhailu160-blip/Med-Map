package com.medmap.medmap.repository;

import com.medmap.medmap.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByPharmacyIdAndMedicineId(Long pharmacyId,Long medicineId);

    List<Inventory> findByPharmacyCityAndPharmacyAreaAndMedicineName(
            String city,
            String area,
            String name
    );
}
