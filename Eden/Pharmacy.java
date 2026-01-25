package com.medmap.medmap;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String area;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL)
    private List<Inventory> inventories;

    public Pharmacy() {}

    public Pharmacy(String name, String city, String area, String phone, String email) {
        this.name = name;
        this.city = city;
        this.area = area;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public String getArea() { return area; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
}
