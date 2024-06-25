package pl.wsb.spa;

import pl.wsb.spa.exceptions.ClientNotFoundException;
import pl.wsb.spa.exceptions.TreatmentNotFoundException;
import pl.wsb.spa.exceptions.TreatmentReservedException;
import pl.wsb.spa.exceptions.ReservationNotFoundException;
import pl.wsb.spa.models.*;
import pl.wsb.spa.services.SpaService;
import java.time.LocalDate;
import java.util.Collection;

import pl.wsb.spa.services.TimeService;
import pl.wsb.spa.services.LuggageService;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {

        Client client = new Client("1", LocalDate.of(2002, 5, 23), "Karol", "Bator", "bator@gmail.com", "123456789", "Rzeszów");
        Treatment treatment = new Treatment("101", "Relaxing Massage", 1.5, LocalDate.of(2024, 6, 25), 200);

        String client_id = client.getId();
        String client_name = client.getFullName();
        int client_age = client.getAge();
        System.out.println("Klient o ID " + client_id + " ma " + client_age + " lat, a nazywa się " + client_name);
        System.out.println();

        Accommodation accommodation = new Accommodation("201", "Double Room", 150, 2);
        TreatmentReservation reservation = new TreatmentReservation(LocalDate.now(), client, treatment, accommodation);

        System.out.println("Rezerwacja potwierdzona: " + reservation.isConfirmed());
        reservation.confirmReservation();
        System.out.println("Rezerwacja potwierdzona: " + reservation.isConfirmed());
        System.out.println();

        System.out.println("Cena zabiegu: " + reservation.getTreatment().getPrice());
        treatment.setPrice(250);
        System.out.println("Nowa cena zabiegu: " + reservation.getTreatment().getPrice());
        System.out.println("Cena całkowita (z noclegiem): " + reservation.getTotalPrice());
        System.out.println();


        TimeService timeService = new TimeService(client_name);
        LuggageService luggageService = new LuggageService(client_name);

        timeService.orderService();
        luggageService.orderService();
        System.out.println();

        Spa spa = new Spa("Spa Testowe");
        SpaService spaService = new SpaService(spa);

        String id_klienta = spaService.addClient("TestImie", "TestNazwisko", LocalDate.of(2006, 7, 10));
        System.out.println("id klienta: " + id_klienta);
        try {
            System.out.println("Pełne imię klienta: " + spaService.getClientFullName(id_klienta));
        } catch (ClientNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println();

        System.out.println("Liczba niepełnoletnich klientów: " + spaService.getNumberOfUnderageClients());
        System.out.println();

        String id_zabiegu = spaService.addTreatment("Masaż całego ciała", 2.0, LocalDate.of(2024, 6, 26), 300);
        System.out.println("id zabiegu: " + id_zabiegu);
        System.out.println();

        try {
            System.out.println("Czas trwania zabiegu: " + spaService.getTreatmentDuration(id_zabiegu) + " godzin");
        } catch (TreatmentNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println();

        System.out.println("Liczba zabiegów na dzień 2024-06-26: " + spaService.getNumberOfTreatmentsOnDate(LocalDate.of(2024, 6, 26)));
        System.out.println();

        String reservationId = null;
        try {
            reservationId = spaService.addNewReservation(id_klienta, id_zabiegu, LocalDate.of(2024, 6, 26), accommodation);
            System.out.println("Rezerwacja o id: " + reservationId + " dodana");
        } catch (ClientNotFoundException | TreatmentNotFoundException | TreatmentReservedException e) {
            System.err.println("Rezerwacja nie dodana " + e.getMessage());
        }
        System.out.println();

        try {
            String confirmedReservationId = spaService.confirmReservation(reservationId);
            System.out.println("Rezerwacja potwierdzona: " + confirmedReservationId);
        } catch (ReservationNotFoundException e) {
            System.err.println("Rezerwacja nieudana " + e.getMessage());
        }
        System.out.println();

        try {
            boolean isReserved = spaService.isTreatmentReserved(id_zabiegu, LocalDate.of(2024, 6, 26));
            System.out.println("Zabieg " + id_zabiegu + " zarezerwowany na dzień 2024-06-26: " + isReserved);
        } catch (TreatmentNotFoundException e) {
            System.err.println("Błąd " + e.getMessage());
        }
        System.out.println();

        int unconfirmedReservations = spaService.getNumberOfUnconfirmedReservations(LocalDate.of(2024, 6, 26));
        System.out.println("Niepotwierdzone rezerwacje na dzień 2024-06-26: " + unconfirmedReservations);
        System.out.println();

        try {
            Collection<String> reservedTreatments = spaService.getTreatmentIdsReservedByClient(id_klienta);
            System.out.println("Klient " + id_klienta + " zarezerwował takie zabiegi: " + reservedTreatments);
        } catch (ClientNotFoundException e) {
            System.err.println("Błąd: " + e.getMessage());
        }
        System.out.println();
    }
}
