package pl.wsb.spa.services;

import pl.wsb.spa.exceptions.ClientNotFoundException;
import pl.wsb.spa.exceptions.ReservationNotFoundException;
import pl.wsb.spa.exceptions.TreatmentNotFoundException;
import pl.wsb.spa.exceptions.TreatmentReservedException;
import pl.wsb.spa.exceptions.InvalidTreatmentDataException;
import pl.wsb.spa.interfaces.SpaCapability;
import pl.wsb.spa.models.Spa;
import pl.wsb.spa.models.Client;
import pl.wsb.spa.models.Treatment;
import pl.wsb.spa.models.TreatmentReservation;
import pl.wsb.spa.models.Accommodation;

import java.time.LocalDate;
import java.util.UUID;
import java.util.*;
import java.util.stream.Collectors;

public class SpaService implements SpaCapability {
    final Spa spa;

    public SpaService(Spa spa) {
        this.spa = spa;
    }

    // overriden methods from interface
    // clients //
    @Override
    public String addClient(String firstName, String lastName, LocalDate birthDate){
        String clientId = UUID.randomUUID().toString();
        Client newClient = new Client(clientId, birthDate, firstName, lastName, null, null, null);
        return addClient(newClient);
    }

    @Override
    public String getClientFullName(String clientId) {
        for (Client client : this.spa.getClients()) {
            if (client.getId().equals(clientId)) {
                return client.getFullName();
            }
        }
        throw new ClientNotFoundException(clientId);
    }

    @Override
    public int getNumberOfUnderageClients() {
        int count = 0;
        for (Client client : this.spa.getClients()) {
            if (client.getAge() < 18) {
                count++;
            }
        }
        return count;
    }

    // treatments //
    @Override
    public String addTreatment(String description, double duration, LocalDate date, double price)  {
        if (duration <= 0 || description == null || description.isEmpty() || date == null || price <= 0) {
            throw new InvalidTreatmentDataException("Invalid treatment data provided.");
        }
        String treatmentId = UUID.randomUUID().toString();
        Treatment newTreatment = new Treatment(treatmentId, description, duration, date, price);
        this.spa.getTreatments().put(treatmentId, newTreatment);
        return treatmentId;
    }

    @Override
    public double getTreatmentDuration(String treatmentId) {
        Treatment treatment = this.spa.getTreatments().get(treatmentId);
        if (treatment != null) {
            return treatment.getDuration();
        }
        throw new TreatmentNotFoundException("treatment not found");
    }

    @Override
    public int getNumberOfTreatmentsOnDate(LocalDate date) {
        return (int) this.spa.getTreatments().values().stream()
                .filter(treatment -> treatment.getDate().equals(date))
                .count();
    }

    // reservations //
    @Override
    public String addNewReservation(String clientId, String treatmentId, LocalDate date) throws ClientNotFoundException, TreatmentNotFoundException, TreatmentReservedException {
        return addNewReservation(clientId, treatmentId, date, null);
    }

    public String addNewReservation(String clientId, String treatmentId, LocalDate date, Accommodation accommodation) throws ClientNotFoundException, TreatmentNotFoundException, TreatmentReservedException {
        Client client = this.spa.getClients().stream()
                .filter(c -> c.getId().equals(clientId))
                .findFirst()
                .orElse(null);
        if (client == null) {
            throw new ClientNotFoundException("client id not found: " + clientId);
        }

        Treatment treatment = this.spa.getTreatments().get(treatmentId);
        if (treatment == null) {
            throw new TreatmentNotFoundException("treatment id not found: " + treatmentId);
        }
        for (TreatmentReservation reservation : this.spa.getReservations().values()) {
            if (reservation.getTreatment().getId().equals(treatmentId) && reservation.getDate().equals(date)) {
                throw new TreatmentReservedException(treatmentId, date);
            }
        }
        String reservationId = UUID.randomUUID().toString();
        TreatmentReservation newReservation = new TreatmentReservation(date, client, treatment, accommodation);
        this.spa.getReservations().put(reservationId, newReservation);
        return reservationId;
    }

    @Override
    public String confirmReservation(String reservationId) throws ReservationNotFoundException {
        TreatmentReservation reservation = this.spa.getReservations().get(reservationId);
        if (reservation == null) {
            throw new ReservationNotFoundException("reservation not found " + reservationId);
        }
        reservation.confirmReservation();
        return reservationId;
    }

    @Override
    public boolean isTreatmentReserved(String treatmentId, LocalDate date) throws TreatmentNotFoundException {
        Treatment treatment = this.spa.getTreatments().get(treatmentId);
        if (treatment == null) {
            throw new TreatmentNotFoundException("treatment not found with id: " + treatmentId);
        }
        return this.spa.getReservations().values().stream()
                .anyMatch(reservation -> reservation.getTreatment().getId().equals(treatmentId) && reservation.getDate().equals(date));
    }

    @Override
    public int getNumberOfUnconfirmedReservations(LocalDate date) {
        return (int) this.spa.getReservations().values().stream()
                .filter(reservation -> !reservation.isConfirmed() && reservation.getDate().equals(date))
                .count();
    }

    @Override
    public Collection<String> getTreatmentIdsReservedByClient(String clientId) throws ClientNotFoundException {
        if (this.spa.getClients().stream().noneMatch(client -> client.getId().equals(clientId))) {
            throw new ClientNotFoundException("client not found " + clientId);
        }
        return this.spa.getReservations().values().stream()
                .filter(reservation -> reservation.getClient().getId().equals(clientId))
                .map(reservation -> reservation.getTreatment().getId())
                .collect(Collectors.toSet());
    }

    // Additional methods
    public String addClient(Client client) {
        this.spa.getClients().add(client);
        return client.getId();
    }

    public Client getClientById(String clientId) {
        for (Client client : this.spa.getClients()) {
            if (client.getId().equals(clientId)) {
                return client;
            }
        }
        return null;
    }

    public void addReservation(String reservationId, TreatmentReservation reservation) {
        this.spa.getReservations().put(reservationId, reservation);
    }

    public String addReservation(TreatmentReservation reservation) {
        String reservationId = reservation.getDate().toString() + "-" + reservation.getClient().getFullName();
        this.spa.getReservations().put(reservationId, reservation);
        return reservationId;
    }

    public TreatmentReservation getReservationById(String reservationId) {
        return this.spa.getReservations().get(reservationId);
    }

    public SpecialService getSpecialServiceByName(String serviceName) {
        for (SpecialService service : this.spa.getSpecialServices()) {
            if (service.getName().equals(serviceName)) {
                return service;
            }
        }
        return null;
    }

    public void addSpecialService(SpecialService service) {
        this.spa.getSpecialServices().add(service);
    }

    public String addAccommodation(String type, double pricePerNight, int numberOfNights) {
        String accommodationId = UUID.randomUUID().toString();
        Accommodation newAccommodation = new Accommodation(accommodationId, type, pricePerNight, numberOfNights);
        this.spa.getReservations().values().forEach(reservation -> {
            if (reservation.getAccommodation() == null) {
                reservation.setAccommodation(newAccommodation);
            }
        });
        return accommodationId;
    }
}
