package com.medmap.medmap.repository;

import com.medmap.medmap.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

    List<Pharmacy> findByCityAndArea(String city, String area);

}
    
   
   
