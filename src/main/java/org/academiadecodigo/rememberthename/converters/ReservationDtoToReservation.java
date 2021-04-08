package org.academiadecodigo.rememberthename.converters;

import org.academiadecodigo.rememberthename.command.ReservationDto;
import org.academiadecodigo.rememberthename.persistence.model.Reservation;
import org.academiadecodigo.rememberthename.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;

public class ReservationDtoToReservation extends AbstractConverter<Reservation, ReservationDto>{

    private ReservationService reservationService;

    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public Reservation convert(ReservationDto reservationDto) {


    }

}
