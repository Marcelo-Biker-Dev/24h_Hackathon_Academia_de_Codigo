package org.academiadecodigo.rememberthename.converters;

import org.academiadecodigo.rememberthename.command.CustomerDto;
import org.academiadecodigo.rememberthename.persistence.model.Customer;

public class CustomerToCustomerDto extends AbstractConverter<Customer, CustomerDto> {


    @Override
    public CustomerDto convert(Customer customer) {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhone(customer.getPhone());

        return customerDto;
    }
}
