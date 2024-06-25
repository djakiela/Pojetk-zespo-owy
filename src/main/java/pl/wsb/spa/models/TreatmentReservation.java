package pl.wsb.spa.models;

import java.time.LocalDate;

public class TreatmentReservation {
    private LocalDate date;
    private boolean isConfirmed;
    private Client client;
    private Treatment treatment;
    private Accommodation accommodation;

    public TreatmentReservation(LocalDate date, Client client, Treatment treatment, Accommodation accommodation) {
        this.date = date;
        this.client = client;
        this.treatment = treatment;
        this.accommodation = accommodation;
        this.isConfirmed = false;
        System.out.println(client.getFullName() + " zarezerwował zabieg nr " + treatment.getId() + ".");
    }

    public void confirmReservation() {
        this.isConfirmed = true;
        System.out.println("Klient " + client.getFullName() + " właśnie potwierdził rezerwację na dzień " + this.date.toString());
    }

    // Getters and Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public double getTotalPrice() {
        double totalPrice = treatment.getPrice();
        if (accommodation != null) {
            totalPrice += accommodation.getTotalPrice();
        }
        return totalPrice;
    }
}
