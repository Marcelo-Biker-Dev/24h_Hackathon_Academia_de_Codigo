package org.academiadecodigo.rememberthename.service;

import org.academiadecodigo.rememberthename.model.Customer;

public interface AuthService {

    boolean authenticate(Integer id);

    Customer getAccessingCustomer();

}
