package pl.wsb.spa.models;

import pl.wsb.spa.services.SpecialService;
import java.util.*;

public class Spa {
    private String name;
    private Set<SpecialService> specialServices = new HashSet<>();
    private Map<String, Treatment> treatments = new HashMap<>();
    private List<Client> clients = new ArrayList<>();
    private Map<String, TreatmentReservation> reservations = new HashMap<>();

    // Constructors, Getters, Setters

    public Spa(String name, Set<SpecialService> specialServices, Map<String, Treatment> treatments, List<Client> clients) {
        this.name = name;
        this.clients = clients;
        this.specialServices = specialServices;
        this.treatments = treatments;
    }

    public Spa(String name, Set<SpecialService> specialServices, Map<String, Treatment> treatments) {
        this.name = name;
        this.specialServices = specialServices;
        this.treatments = treatments;
    }

    public Spa(String name, Map<String, Treatment> treatments) {
        this.name = name;
        this.treatments = treatments;
    }

    public Spa(String name) {
        this.name = name;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SpecialService> getSpecialServices() {
        return specialServices;
    }

    public void setSpecialServices(Set<SpecialService> specialServices) {
        this.specialServices = specialServices;
    }

    public Map<String, Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(Map<String, Treatment> treatments) {
        this.treatments = treatments;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Map<String, TreatmentReservation> getReservations() {
        return reservations;
    }

    public void setReservations(Map<String, TreatmentReservation> reservations) {
        this.reservations = reservations;
    }
}
