package org.academiadecodigo.rememberthename.service;

import org.academiadecodigo.rememberthename.model.Reservation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ReservationServiceImpl {

    private Map<Integer, Reservation> reservationMap = new HashMap<>();


    private Integer getNextId() {
        return reservationMap.isEmpty() ? 1 : Collections.max(reservationMap.keySet()) + 1;
    }

    public void add(Reservation reservation) {

        if (reservation.getId() == null) {
            reservation.setId(getNextId());
        }

        reservationMap.put(reservation.getId(), reservation);
    }
}
