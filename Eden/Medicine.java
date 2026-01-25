package com.medmap.medmap;

import jakarta.persistence.*;

@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Medicine() {}

    public Medicine(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}
