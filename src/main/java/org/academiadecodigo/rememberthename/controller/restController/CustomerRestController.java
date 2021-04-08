
package org.academiadecodigo.rememberthename.controller.restController;

import org.academiadecodigo.rememberthename.command.CustomerDto;
import org.academiadecodigo.rememberthename.converters.CustomerToCustomerDto;
import org.academiadecodigo.rememberthename.converters.ReservationDtoToReservation;
import org.academiadecodigo.rememberthename.persistence.model.Customer;
import org.academiadecodigo.rememberthename.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class CustomerRestController {

    private CustomerService customerService;

    public CustomerService getCustomerService() {
        return customerService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List> readAllCustomers() {
        CustomerToCustomerDto ctcd = new CustomerToCustomerDto();
        List list = customerService.list().stream().map(c -> ctcd.convert(c)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomerDto> readCustomer(@PathVariable Integer id) {
        Customer requestedCustomer = customerService.get(id);
        CustomerToCustomerDto ctcd = new CustomerToCustomerDto();
        return new ResponseEntity<CustomerDto>(ctcd.convert(requestedCustomer), HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/customer/{id}/account",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List> readCustomerAccounts(@PathVariable Integer id) {
        ReservationDtoToReservation reservationDtoToReservation = new ReservationDtoToReservation();
        List customerReservations = customerService.get(id).getReservations();
        return new ResponseEntity<>(reservationDtoToReservation.convert(customerReservations), HttpStatus.OK);
    }
}