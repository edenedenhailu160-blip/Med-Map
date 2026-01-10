package medmap;

public class Medicine {
    private String name;
    private String pharmacy;
    private int quantity;

    public Medicine(String name, String pharmacy, int quantity) {
        this.name = name;
        this.pharmacy = pharmacy;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getPharmacy() {
        return pharmacy;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
