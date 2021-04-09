package org.academiadecodigo.rememberthename.controller.web;

import org.academiadecodigo.rememberthename.command.ReservationDto;
import org.academiadecodigo.rememberthename.converters.CustomerToCustomerDto;
import org.academiadecodigo.rememberthename.converters.ReservationDtoToReservation;
import org.academiadecodigo.rememberthename.persistence.model.Reservation;
import org.academiadecodigo.rememberthename.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class ReservationController {

    private CustomerService customerService;

    private ReservationDtoToReservation reservationDtoToReservation;
    private CustomerToCustomerDto customerToCustomerDto;

    @Autowired
    public void setReservationDtoToReservation(ReservationDtoToReservation reservationDtoToReservation) {
        this.reservationDtoToReservation = reservationDtoToReservation;
    }

    @Autowired
    public void setCustomerToCustomerDto(CustomerToCustomerDto customerToCustomerDto) {
        this.customerToCustomerDto = customerToCustomerDto;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/{cid}/reservation"})
    public String addAccount(@PathVariable Integer cid, @Valid @ModelAttribute("reservation") ReservationDto reservationDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/customer/" + cid;
        }

        Reservation reservation = reservationDtoToReservation.convert(reservationDto);
        customerService.addReservation(cid, reservation);
        return "redirect:/customer/" + cid;

    }

    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/reservation/{rid}/delete")
    public String closeReservation(@PathVariable Integer cid, @PathVariable Integer rid) {
        customerService.deleteReservation(cid, rid);

        return "1234";
    }
}

