package pl.wsb.spa.models;

public class Accommodation {
    private String id;
    private String type; // e.g., "Single Room", "Double Room"
    private double pricePerNight;
    private int numberOfNights;

    public Accommodation(String id, String type, double pricePerNight, int numberOfNights) {
        this.id = id;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.numberOfNights = numberOfNights;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public double getTotalPrice() {
        return pricePerNight * numberOfNights;
    }
}
