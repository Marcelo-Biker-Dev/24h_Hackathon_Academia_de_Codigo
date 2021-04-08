package org.academiadecodigo.rememberthename.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends AbstractModel{

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private List<Reservation> reservations = new ArrayList<>();

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservations);
    }


}
