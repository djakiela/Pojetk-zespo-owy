package pl.wsb.spa.exceptions;

import java.time.LocalDate;

public class TreatmentReservedException extends RuntimeException {
    public TreatmentReservedException(String treatmentId, LocalDate reservationDate) {
        super(String.format("On %s, the treatment %s is booked.", reservationDate, treatmentId));
    }
}
