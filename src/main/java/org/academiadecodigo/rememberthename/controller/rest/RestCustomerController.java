package org.academiadecodigo.rememberthename.controller.rest;


import org.academiadecodigo.rememberthename.command.CustomerDto;
import org.academiadecodigo.rememberthename.converters.CustomerDtoToCustomer;
import org.academiadecodigo.rememberthename.converters.CustomerToCustomerDto;
import org.academiadecodigo.rememberthename.persistence.model.Customer;
import org.academiadecodigo.rememberthename.service.CustomerService;
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

    private CustomerService customerService;
    private CustomerDtoToCustomer customerDtoToCustomer;
    private CustomerToCustomerDto customerToCustomerDto;


    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setCustomerDtoToCustomer(CustomerDtoToCustomer customerDtoToCustomer) {
        this.customerDtoToCustomer = customerDtoToCustomer;
    }

    @Autowired
    public void setCustomerToCustomerDto(CustomerToCustomerDto customerToCustomerDto) {
        this.customerToCustomerDto = customerToCustomerDto;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<CustomerDto>> listCustomers() {

        List<CustomerDto> customerDto = customerService.list().stream()
                .map(customer -> customerToCustomerDto.convert(customer))
                .collect(Collectors.toList());

        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<CustomerDto> showCustomer(@PathVariable Integer id) {

        Customer customer = customerService.get(id);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customerToCustomerDto.convert(customer), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || customerDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer savedCustomer = customerService.save(customerDtoToCustomer.convert(customerDto));

        // get help from the framework building the path for the newly created resource
        UriComponents uriComponents = uriComponentsBuilder.path("/api/customer/" + savedCustomer.getId()).build();

        // set headers with the created path
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<CustomerDto> editCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult, @PathVariable Integer id) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (customerDto.getId() != null && !customerDto.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (customerService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        customerDto.setId(id);

        customerService.save(customerDtoToCustomer.convert(customerDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable Integer id) {

        try {

            customerService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
