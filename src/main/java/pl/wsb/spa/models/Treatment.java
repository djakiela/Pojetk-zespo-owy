package pl.wsb.spa.models;

import java.time.LocalDate;

public class Treatment {
    private String id;
    private String description;
    private double duration; // in hours
    private LocalDate date;
    private double price;

    public Treatment(String id, String description, double duration, LocalDate date, double price) {
        this.id = id;
        this.description = description;
        this.duration = duration;
        this.date = date;
        this.price = price;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
