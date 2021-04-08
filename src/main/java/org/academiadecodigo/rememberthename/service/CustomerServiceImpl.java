package org.academiadecodigo.rememberthename.service;

import org.academiadecodigo.rememberthename.persistence.model.AbstractModel;
import org.academiadecodigo.rememberthename.persistence.model.Customer;
import org.academiadecodigo.rememberthename.persistence.model.Reservation;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerServiceImpl extends AbstractService<Customer> implements CustomerService{


    @Override
    public Customer get(Integer id) {
        return modelMap.get(id);
    }

    @Override
    public Customer save(Customer customer) {

        if (customer.getId() == null) {
            customer.setId(getNextId());
        }

        modelMap.put(customer.getId(), customer);
        return customer;
    }

    public void delete(Integer id) {
        Customer customer = get(id);

        if (!customer.getReservations().isEmpty()) {

        }
        modelMap.remove(id);
    }

    @Override
    public List<Customer> list() {
        return new ArrayList<>(modelMap.values());
    }


    @Override
    public Set<Integer> listCustomerReservationIds (Integer id) {

        List<Reservation> reservations = modelMap.get(id).getReservations();

        return reservations.stream()
                .map(AbstractModel::getId)
                .collect(Collectors.toSet());
    }

    @Override
    public Reservation addReservation(Integer cid, Reservation convert) {
        return null;
    }

    @Override
    public void closeReservation(Integer cid, Integer aid) {

    }
}
