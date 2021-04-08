package org.academiadecodigo.rememberthename.service;

import org.academiadecodigo.rememberthename.persistence.model.Reservation;

public class ReservationServiceImpl extends AbstractService<Reservation> implements ReservationService{


    @Override
    public Reservation get(Integer id) {
        return modelMap.get(id);
    }

    @Override
    public Integer add(Reservation reservation) {

        if (reservation.getId() == null) {
            reservation.setId(getNextId());
        }

        modelMap.put(reservation.getId(), reservation);

        return reservation.getId();
    }
}
