package org.academiadecodigo.rememberthename.service;

import org.academiadecodigo.rememberthename.model.Reservation;

public interface ReservationService {

    Reservation get(Integer id);

    Integer add(Reservation reservation);
}
