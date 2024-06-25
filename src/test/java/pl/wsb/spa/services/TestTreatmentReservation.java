package pl.wsb.spa.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wsb.spa.models.Client;
import pl.wsb.spa.models.Spa;
import pl.wsb.spa.models.Treatment;
import pl.wsb.spa.models.TreatmentReservation;
import pl.wsb.spa.models.Accommodation;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestTreatmentReservation {
    private SpaService service;
    private String clientId;
    private String treatmentId;
    private TreatmentReservation reservation;

    @BeforeEach
    void setUp() {
        service = new SpaService(new Spa("TestSpa"));
        Client client = new Client("1", LocalDate.of(2002, 5, 23), "Test", "Client", "test@example.com", "123456789", "Test Address");
        clientId = service.addClient(client);
        treatmentId = service.addTreatment("Relaxing Massage", 1.5, LocalDate.of(2024, 6, 25), 200);
        Accommodation accommodation = new Accommodation("201", "Double Room", 150, 2);
        reservation = new TreatmentReservation(LocalDate.now(), client, new Treatment(treatmentId, "Relaxing Massage", 1.5, LocalDate.of(2024, 6, 25), 200), accommodation);
    }

    @Test
    void shouldCreateReservationSuccessfully() {
        // given in @BeforeEach

        // when
        TreatmentReservation newReservation = new TreatmentReservation(reservation.getDate(), reservation.getClient(), reservation.getTreatment(), reservation.getAccommodation());

        // then
        assertNotNull(newReservation, "Reservation should be created");
        assertEquals(reservation.getClient(), newReservation.getClient(), "Client should match");
        assertEquals(reservation.getTreatment(), newReservation.getTreatment(), "Treatment should match");
        assertEquals(reservation.getAccommodation(), newReservation.getAccommodation(), "Accommodation should match");
    }
}
