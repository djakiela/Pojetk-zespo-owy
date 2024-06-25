package pl.wsb.spa.interfaces;

import pl.wsb.spa.exceptions.ClientNotFoundException;
import pl.wsb.spa.exceptions.ReservationNotFoundException;
import pl.wsb.spa.exceptions.TreatmentNotFoundException;
import pl.wsb.spa.exceptions.TreatmentReservedException;

import java.time.LocalDate;
import java.util.Collection;

public interface SpaCapability {

    // clients //
    String addClient(String firstName, String lastName, LocalDate birthDate);
    String getClientFullName(String clientId);
    int getNumberOfUnderageClients();

    // treatments //
    String addTreatment(String description, double duration, LocalDate date, double price);
    double getTreatmentDuration(String treatmentId);
    int getNumberOfTreatmentsOnDate(LocalDate date);

    // reservations //
    String addNewReservation(String clientId, String treatmentId, LocalDate date)
            throws ClientNotFoundException, TreatmentNotFoundException, TreatmentReservedException;

    String confirmReservation(String reservationId)
            throws ReservationNotFoundException;

    boolean isTreatmentReserved(String treatmentId, LocalDate date)
            throws TreatmentNotFoundException;

    int getNumberOfUnconfirmedReservations(LocalDate date);

    Collection<String> getTreatmentIdsReservedByClient(String clientId)
            throws ClientNotFoundException;
}
