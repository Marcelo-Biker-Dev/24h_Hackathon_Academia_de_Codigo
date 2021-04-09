package org.academiadecodigo.rememberthename.controller.rest;
import org.academiadecodigo.rememberthename.command.ReservationDto;
import org.academiadecodigo.rememberthename.converters.ReservationDtoToReservation;
import org.academiadecodigo.rememberthename.converters.ReservationToReservationDto;
import org.academiadecodigo.rememberthename.persistence.model.Customer;
import org.academiadecodigo.rememberthename.persistence.model.Reservation;
import org.academiadecodigo.rememberthename.service.CustomerService;
import org.academiadecodigo.rememberthename.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller responsible for {@link Reservation} related CRUD operations
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class RestReservationController {

    private CustomerService customerService;
    private ReservationService reservationService;
    private ReservationDtoToReservation reservationDtoToReservation;
    private ReservationToReservationDto reservationToReservationDto;


    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setAccountService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Autowired
    public void setAccountToAccountDto(ReservationToReservationDto reservationToReservationDto) {
        this.reservationToReservationDto = reservationToReservationDto;
    }

    @Autowired
    public void setAccountDtoToAccount(ReservationDtoToReservation reservationDtoToReservation) {
        this.reservationDtoToReservation = reservationDtoToReservation;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/reservations")
    public ResponseEntity<List<ReservationDto>> listCustomerReservations(@PathVariable Integer cid) {

        Customer customer = customerService.get(cid);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ReservationDto> reservationDtos = customer.getReservations().stream().map(reservation -> reservationToReservationDto.convert(reservation)).collect(Collectors.toList());

        return new ResponseEntity<>(reservationDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/reservations/{aid}")
    public ResponseEntity<ReservationDto> showCustomerAccount(@PathVariable Integer cid, @PathVariable Integer aid) {

        Reservation reservation = reservationService.get(aid);

        if (reservation == null || reservation.getCustomer() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!reservation.getCustomer().getId().equals(cid)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(reservationToReservationDto.convert(reservation), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/{cid}/account")
    public ResponseEntity<?> addAccount(@PathVariable Integer cid, @Valid @RequestBody ReservationDto reservationDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || reservationDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {

            Reservation reservation = customerService.addReservation(cid, reservationDtoToReservation.convert(reservationDto));

            UriComponents uriComponents = uriComponentsBuilder.path("/api/customer/" + cid + "/account/" + reservation.getId()).build();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponents.toUri());

            return new ResponseEntity<>(headers, HttpStatus.CREATED);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/account/{aid}/close")
    public ResponseEntity<?> closeAccount(@PathVariable Integer cid, @PathVariable Integer rid) {

        try {

            customerService.deleteReservation(cid, rid);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


