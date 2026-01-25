package com.medmap.medmap;

import jakarta.persistence.*;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pharmacy pharmacy;

    @ManyToOne
    private Medicine medicine;

    private int quantity;
    private double price;

    public Inventory() {}

    public Inventory(Pharmacy pharmacy, Medicine medicine, int quantity, double price) {
        this.pharmacy = pharmacy;
        this.medicine = medicine;
        this.quantity = quantity;
        this.price = price;
    }

    public Pharmacy getPharmacy() { return pharmacy; }
    public Medicine getMedicine() { return medicine; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    public void setPrice(double price){
        this.price=price;
    
    }
}
