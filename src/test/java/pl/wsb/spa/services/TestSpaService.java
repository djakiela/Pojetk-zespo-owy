package pl.wsb.spa.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wsb.spa.exceptions.TreatmentNotFoundException;
import pl.wsb.spa.models.Spa;
import pl.wsb.spa.models.Treatment;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestSpaService {
    private SpaService service;
    private String validTreatmentId;
    private String validTreatmentDescription;
    private double validTreatmentDuration;
    private LocalDate validTreatmentDate;
    private double validTreatmentPrice;

    @BeforeEach
    void setUp() {
        service = new SpaService(new Spa("TestSpa"));
        validTreatmentDescription = "TestTreatment";
        validTreatmentDuration = 1.5;
        validTreatmentDate = LocalDate.of(2024, 6, 25);
        validTreatmentPrice = 200.0;
        validTreatmentId = service.addTreatment(validTreatmentDescription, validTreatmentDuration, validTreatmentDate, validTreatmentPrice);
    }

    @Test
    void shouldAddTreatmentSuccessfully() {
        // given in @BeforeEach

        // when
        Treatment addedTreatment = service.getSpa().getTreatments().get(validTreatmentId);

        // then
        assertNotNull(addedTreatment, "Treatment should be added");
        assertEquals(validTreatmentDescription, addedTreatment.getDescription(), "Description should match");
        assertEquals(validTreatmentDuration, addedTreatment.getDuration(), "Duration should match");
        assertEquals(validTreatmentDate, addedTreatment.getDate(), "Date should match");
        assertEquals(validTreatmentPrice, addedTreatment.getPrice(), "Price should match");
    }

    @Test
    void shouldThrowTreatmentNotFoundExceptionWhenTreatmentDoesNotExist() {
        // when & then
        assertThrows(TreatmentNotFoundException.class, () -> {
            service.getTreatmentDuration("NonExistentId");
        }, "Should throw TreatmentNotFoundException for a non-existent treatment ID");
    }
}
