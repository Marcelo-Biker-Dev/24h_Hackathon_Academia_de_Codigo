package org.academiadecodigo.rememberthename.service;

import org.academiadecodigo.rememberthename.model.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    Customer get(Integer id);

    List<Customer> list();

    Set<Integer> listCustomerAccountIds(Integer id);

    void add(Customer customer);
}
