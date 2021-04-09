package org.academiadecodigo.rememberthename.converters;

import org.academiadecodigo.rememberthename.command.ReservationDto;
import org.academiadecodigo.rememberthename.persistence.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationToReservationDto extends AbstractConverter<Reservation, ReservationDto> {


    @Override
    public ReservationDto convert(Reservation reservation) {

        ReservationDto reservationDto = new ReservationDto();

        reservationDto.setId(reservation.getId());
        reservationDto.setName(reservation.getName());
        reservationDto.setNumberOfClients(reservation.getNumberOfClients());

        return reservationDto;

    }
}
