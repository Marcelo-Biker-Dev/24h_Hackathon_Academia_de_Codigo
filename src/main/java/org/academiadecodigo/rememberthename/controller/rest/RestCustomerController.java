package org.academiadecodigo.rememberthename.controller.rest;


import org.academiadecodigo.rememberthename.command.CustomerDto;
import org.academiadecodigo.rememberthename.converters.CustomerDtoToCustomer;
import org.academiadecodigo.rememberthename.converters.CustomerToCustomerDto;
import org.academiadecodigo.rememberthename.persistence.model.Customer;
import org.academiadecodigo.rememberthename.service.CustomerService;
import org.academiadecodigo.rememberthename.service.mock.CustomerServiceMockImpl;
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
 * REST controller responsible for {@link Customer} related CRUD operations
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class RestCustomerController {

    private CustomerServiceMockImpl customerService;


    @Autowired
    public void setCustomerService(CustomerServiceMockImpl customerService) {
        this.customerService = customerService;
    }


    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<Customer>> listCustomers() {

        List<Customer> customer = customerService.list();

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Customer> showCustomer(@PathVariable Integer id) {

        Customer customer = customerService.get(id);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || customer.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer savedCustomer = customerService.save(customer);

        // get help from the framework building the path for the newly created resource
        UriComponents uriComponents = uriComponentsBuilder.path("/api/customer/" + savedCustomer.getId()).build();

        // set headers with the created path
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/{id}/edit.html")
    public ResponseEntity<Customer> editCustomer(@Valid @RequestBody Customer customer, BindingResult bindingResult, @PathVariable Integer id) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (customer.getId() != null && !customer.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (customerService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        customer.setId(id);

        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer id) {

        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
