package pl.wsb.spa.services;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeService extends SpecialService {
    public TimeService(String name) {
        super(name);
    }

    public void orderService() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("Aktualny czas: " + currentTime.format(format) + " dla klienta: " + getName());
    }

    public int calculateCost(int quantity, double unitPrice) {
        return quantity * (int) unitPrice;
    }

    public boolean highDemand() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.getHour() >= 7 && currentTime.getHour() <= 10;
    }

    public boolean highDemand(LocalTime time) {
        return time.getHour() >= 7 && time.getHour() <= 10;
    }
}
